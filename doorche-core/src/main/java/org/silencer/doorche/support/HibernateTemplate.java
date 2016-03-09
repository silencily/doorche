package org.silencer.doorche.support;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
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

    /**
     * 根据查询条件进行查询结果集
     *
     * @param dc 离线查询条件对象
     * @return 查询结果集
     */
    public List findByCriteria(DetachedCriteria dc) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = dc.getExecutableCriteria(session);

        return criteria.list();

    }

    public <T> List<T> findByClass(Class<T> clazz){
        DetachedCriteria dc =DetachedCriteria.forClass(clazz);
        return (List<T>)findByCriteria(dc);
    }


}
