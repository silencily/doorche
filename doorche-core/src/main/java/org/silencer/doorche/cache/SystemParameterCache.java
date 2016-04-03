/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.silencer.doorche.entity.TsmParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 管理系统参数缓存器
 *
 * @author gejb
 * @since 2016-04-03
 */
@Component
public class SystemParameterCache {
    private static final Log logger = LogFactory.getLog(SystemParameterCache.class);

    public static final String SYSTEM_PARAMETER_CACHE_NAME = "systemParameterCache";

    private final ReadWriteLock lock = new ReentrantReadWriteLock();//读写锁，“读/写互斥”，“写/写互斥”，“读/读不互斥”

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    public void loadCache() {
        logger.info("开始加载系统参数常量缓存器...");
        lock.writeLock().lock();
        Session session = sessionFactory.openSession();
        try {
            Cache cache = cacheManager.getCache(SYSTEM_PARAMETER_CACHE_NAME);
            cache.clear();//先清空缓存
            Criteria criteria = session.createCriteria(TsmParameter.class);
            criteria.add(Restrictions.eq("isDeleted", TsmParameter.IS_FLAG_NO));
            List<TsmParameter> parameters = (List<TsmParameter>) criteria.list();
            for (int i = 0; i < parameters.size(); i++) {
                TsmParameter parameter = parameters.get(i);
                String paramKey = parameter.getParamKey();
                String paramValue = parameter.getParamValue();
                cache.put(paramKey, paramValue);
            }
            logger.info("完成加载系统参数常量缓存器，加载数量为:" + parameters.size() + ".");
        } finally {
            session.close();
            lock.writeLock().unlock();
        }
    }

    /**
     * 获取系统参数缓存值
     *
     * @param key 参数key
     * @return
     */
    public String getValue(String key) {
        lock.readLock().lock();
        Cache.ValueWrapper valueWrapper = null;
        try {
            Cache cache = cacheManager.getCache(SYSTEM_PARAMETER_CACHE_NAME);
            valueWrapper = cache.get(key);
            if (valueWrapper == null) {
                logger.debug("获取系统参数缓存未命中[key:" + key + "]");
                lock.readLock().unlock();
                lock.writeLock().lock();//获取写锁，需要更新缓存
                try {
                    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TsmParameter.class);
                    criteria.add(Restrictions.eq("isDeleted", TsmParameter.IS_FLAG_NO));
                    criteria.add(Restrictions.eq("paramKey", key));
                    List<TsmParameter> parameters = (List<TsmParameter>) criteria.list();
                    if (parameters.size() > 0) {
                        TsmParameter parameter = parameters.get(0);
                        cache.put(parameter.getParamKey(), parameter.getParamValue());
                        valueWrapper = cache.get(key);
                    } else {
                        logger.warn("获取系统参数缓存失败，请核实是否设置该系统参数[key:" + key + "]");
                    }
                } finally {
                    lock.readLock().lock();//读锁重入，降级为读锁
                    lock.writeLock().unlock();
                }
            }

        } finally {
            lock.readLock().unlock();
        }
        return valueWrapper == null ? null : valueWrapper.get().toString();
    }
}
