/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.activiti.identity;

import org.activiti.engine.identity.User;
import org.silencer.doorche.entity.TsmUser;

/**
 * @author gejb
 * @since 2016-06-04
 */
public class CustomUserEntity implements User {
    private TsmUser tsmUser;

    public CustomUserEntity(TsmUser tsmUser) {
        this.tsmUser = tsmUser;
    }

    @Override
    public String getId() {
        return tsmUser.getId().toString();
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public String getFirstName() {
        return tsmUser.getName();
    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public String getLastName() {
        return tsmUser.getLoginName();
    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public String getEmail() {
        return tsmUser.getEmail();
    }

    @Override
    public String getPassword() {
        return tsmUser.getPassword();
    }

    @Override
    public void setPassword(String string) {

    }

    @Override
    public boolean isPictureSet() {
        return false;
    }
}
