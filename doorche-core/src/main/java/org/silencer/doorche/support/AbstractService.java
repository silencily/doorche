package org.silencer.doorche.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.silencer.doorche.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Service基类
 *
 * @author gejb
 * @since 2016/3/4
 */
public abstract class AbstractService {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SessionFactory sessionFactory;

    public <T extends AbstractEntity> List<T> listByCriteria(DetachedCriteria detachedCriteria) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = detachedCriteria.getExecutableCriteria(session);

        return list(criteria);
    }

    public <T extends AbstractEntity> List<T> list(Class<T> clazz) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(clazz);
        return list(criteria);
    }

    private <T extends AbstractEntity> List<T> list(Criteria criteria) {


        return (List<T>) criteria.list();
    }

}
