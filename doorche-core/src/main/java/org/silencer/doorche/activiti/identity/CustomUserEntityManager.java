/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.activiti.identity;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.silencer.doorche.entity.TsmUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gejb
 * @since 2016-06-04
 */
public class CustomUserEntityManager extends UserEntityManager {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(String userId) {
        Session session = sessionFactory.getCurrentSession();
        TsmUser user = (TsmUser) session.load(TsmUser.class, Integer.parseInt(userId));
        return new CustomUserEntity(user);
    }

}
