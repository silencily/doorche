package org.silencer.doorche.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.silencer.doorche.context.ConditionContextManager;
import org.silencer.doorche.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Service基类
 *
 * @author gejb
 * @since 2016/3/4
 */
public abstract class AbstractService implements IService {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private ConditionContextManager conditionContextManager;

    /**
     * 获取hibernate数据处理对象
     *
     * @return hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Override
    public <T extends AbstractEntity> List<T> list(Class<T> clazz) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.eq("isDeleted", "0"));//默认过滤物理删除的记录
        return list(dc);
    }

    /**
     * 查询，默认待分页
     *
     * @param dc
     * @param <T>
     * @return
     */
    protected <T extends AbstractEntity> List<T> list(DetachedCriteria dc) {
        conditionContextManager.recoverQuery();//恢复系统自动处理查询条件
        return (List<T>) hibernateTemplate.findByCriteria(dc);
    }
}
