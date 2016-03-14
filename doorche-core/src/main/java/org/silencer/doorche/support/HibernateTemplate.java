package org.silencer.doorche.support;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.silencer.doorche.context.ConditionContextManager;
import org.silencer.doorche.context.Paginator;
import org.silencer.doorche.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * hibernate数据操作模板对象
 *
 * @author gejb
 * @since 2016/3/9
 */
@Component
public class HibernateTemplate {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ConditionContextManager conditionContextManager;

    /**
     * 根据查询条件进行查询结果集
     *
     * @param dc 离线查询条件对象
     * @return 查询结果集
     */
    public List findByCriteria(DetachedCriteria dc) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = dc.getExecutableCriteria(session);

        //处理分页
        Paginator paginator = null;



        return criteria.list();

    }

    public <T> List<T> findByClass(Class<T> clazz) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        return (List<T>) findByCriteria(dc);
    }


    /**
     * 更新保存
     *
     * @param entity
     */
    public void update(AbstractEntity entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    /**
     * 新增保存
     *
     * @param entity
     */
    public void save(AbstractEntity entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }


}
