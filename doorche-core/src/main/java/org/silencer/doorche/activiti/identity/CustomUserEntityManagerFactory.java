/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.activiti.identity;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;

/**
 * @author gejb
 * @since 2016-06-04
 */
public class CustomUserEntityManagerFactory implements SessionFactory {
    private UserIdentityManager userIdentityManager;

    public void setUserIdentityManager(UserIdentityManager userIdentityManager) {
        this.userIdentityManager = userIdentityManager;
    }

    @Override
    public Class<?> getSessionType() {
        return UserIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return (Session) userIdentityManager;
    }
}
