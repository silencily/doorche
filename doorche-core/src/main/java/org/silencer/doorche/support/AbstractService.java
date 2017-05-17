package org.silencer.doorche.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.silencer.doorche.context.ConditionContextManager;
import org.silencer.doorche.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service基类
 *
 * @author gejb
 * @since 2016/3/4
 */
@Transactional
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

    @Transactional(readOnly = true)
    public <T extends AbstractEntity> List<T> list(Class<T> clazz) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
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
        dc.add(Restrictions.eq("isDeleted", "0"));//默认过滤物理删除的记录
        return (List<T>) hibernateTemplate.findByCriteria(dc);
    }

    public void save(AbstractEntity entity) {
        hibernateTemplate.save(entity);
    }

    public void update(AbstractEntity entity) {
        hibernateTemplate.update(entity);
    }

    public void saveOrUpdate(AbstractEntity entity) {
        hibernateTemplate.saveOrUpdate(entity);
    }

    @Transactional(readOnly = true)
    public <T extends AbstractEntity> T load(Class<T> clazz, Integer id) {
        return hibernateTemplate.load(clazz, id);
    }

    @Transactional(readOnly = true)
    public <T extends AbstractEntity> T get(Class<T> clazz, Integer id) {
        return hibernateTemplate.get(clazz, id);
    }

    public void merge(AbstractEntity entity) {
        hibernateTemplate.merge(entity);
    }

    public <T extends AbstractEntity> void delete(Class<T> clazz, Integer id) {
        AbstractEntity entity = load(clazz, id);
        entity.setIsDeleted(AbstractEntity.IS_FLAG_YES);
        update(entity);
    }
}
