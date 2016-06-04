/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.activiti.identity;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

/**
 * @author gejb
 * @since 2016-06-04
 */
public class CustomGroupEntityManagerFactory implements SessionFactory {
    private GroupIdentityManager groupIdentityManager;

    public void setGroupIdentityManager(GroupIdentityManager groupIdentityManager) {
        this.groupIdentityManager = groupIdentityManager;
    }

    @Override
    public Class<?> getSessionType() {
        return GroupIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return (Session) groupIdentityManager;
    }
}
