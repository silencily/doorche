/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.activiti.identity;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.silencer.doorche.entity.TsmRole;
import org.silencer.doorche.entity.TsmUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gejb
 * @since 2016-06-04
 */
public class CustomGroupEntityManager extends GroupEntityManager {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Group> findGroupsByUser(String userId) {
        Session session = sessionFactory.getCurrentSession();
        TsmUser tsmUser = (TsmUser) session.load(TsmUser.class, Integer.parseInt(userId));
        List<Group> groups = new ArrayList<Group>();
        for (TsmRole role : tsmUser.getTsmRoles()) {
            groups.add(new CustomGroupEntity(role));
        }
        return groups;
    }
}
