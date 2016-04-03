/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.silencer.doorche.entity.TsmDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 系统字典缓存器
 *
 * @author gejb
 * @since 2016-04-03
 */
@Component
public class SystemDictionaryCache {
    private static final Log logger = LogFactory.getLog(SystemDictionaryCache.class);

    public static final String SYSTEM_DICTIONARY_CACHE_NAME = "systemDictionaryCache";

    private final ReadWriteLock lock = new ReentrantReadWriteLock();//读写锁，“读/写互斥”，“写/写互斥”，“读/读不互斥”
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    public void loadCache() {
        logger.info("开始加载系统字典缓存器...");
        lock.writeLock().lock();
        Session session = sessionFactory.openSession();//此处系统启动时不能使用getCurrentSession()，自己开启Hibernate Session
        try {
            Cache cache = cacheManager.getCache(SYSTEM_DICTIONARY_CACHE_NAME);
            cache.clear();
            Criteria criteria = session.createCriteria(TsmDict.class);
            criteria.add(Restrictions.eq("isDeleted", TsmDict.IS_FLAG_NO));
            criteria.add(Restrictions.isNull("code"));//查询一级编码
            List<TsmDict> tsmDicts = (List<TsmDict>) criteria.list();
            for (int i = 0; i < tsmDicts.size(); i++) {
                TsmDict tsmDict = tsmDicts.get(i);
                Criteria criteria2 = sessionFactory.getCurrentSession().createCriteria(TsmDict.class);
                criteria2.add(Restrictions.eq("parent", tsmDict));
                criteria2.add(Restrictions.eq("isDeleted", TsmDict.IS_FLAG_NO));
                criteria2.addOrder(Order.asc("sort"));
                List<TsmDict> scdTsmDicts = (List<TsmDict>) criteria2.list();//二级编码
                Map<String, String> dictsMap = new LinkedHashMap<String, String>();
                for (TsmDict scdTsmDict : scdTsmDicts) {
                    dictsMap.put(scdTsmDict.getCode(), scdTsmDict.getName());
                }
                String typeCode = tsmDict.getTypeCode();
                cache.put(typeCode, dictsMap);
            }
            logger.info("完成加载系统字典缓存器，共加载数量：" + tsmDicts.size() + "个.");
        } finally {
            session.close();
            lock.writeLock().unlock();
        }
    }

    /**
     * 获取系统字典
     *
     * @param typeCode 类型编码
     * @return
     */
    public Map<String, String> getDict(String typeCode) {
        lock.readLock().lock();
        Cache.ValueWrapper valueWrapper = null;
        try {
            Cache cache = cacheManager.getCache(SYSTEM_DICTIONARY_CACHE_NAME);
            valueWrapper = cache.get(typeCode);
            if (valueWrapper == null) {
                logger.debug("获取系统参数缓存未命中[typeCode:" + typeCode + "]");
                lock.readLock().unlock();
                lock.writeLock().lock();//获取写锁，需要更新缓存
                try {
                    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TsmDict.class);
                    criteria.add(Restrictions.eq("isDeleted", TsmDict.IS_FLAG_NO));
                    criteria.add(Restrictions.eq("typeCode", typeCode));
                    criteria.add(Restrictions.isNotNull("code"));//二级编码
                    criteria.addOrder(Order.asc("sort"));
                    List<TsmDict> tsmDicts = (List<TsmDict>) criteria.list();
                    if (tsmDicts.size() > 0) {
                        Map<String, String> dictsMap = new LinkedHashMap<String, String>();
                        for (TsmDict tsmDict : tsmDicts) {
                            dictsMap.put(tsmDict.getCode(), tsmDict.getName());
                        }
                        cache.put(typeCode, dictsMap);
                        valueWrapper = cache.get(typeCode);
                    } else {
                        logger.warn("获取系统字典缓存失败，请核实是否设置该系统字典[typeCode:" + typeCode + "]");
                    }
                } finally {
                    lock.readLock().lock();//读锁重入，降级为读锁
                    lock.writeLock().unlock();
                }
            }

        } finally {
            lock.readLock().unlock();
        }
        return valueWrapper == null ? null : (Map<String, String>) valueWrapper;
    }

    /**
     * 获取字典值
     *
     * @param typeCode 类型编码
     * @param code     编码
     * @return
     */
    public String getValue(String typeCode, String code) {
        Map<String, String> dict = getDict(typeCode);
        return dict == null ? null : dict.get(code);
    }
}
