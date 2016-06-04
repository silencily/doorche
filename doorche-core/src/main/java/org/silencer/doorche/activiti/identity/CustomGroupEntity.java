/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.activiti.identity;

import org.activiti.engine.identity.Group;
import org.silencer.doorche.entity.TsmRole;

/**
 * @author gejb
 * @since 2016-06-04
 */
public class CustomGroupEntity implements Group {
    private TsmRole tsmRole;

    public CustomGroupEntity(TsmRole tsmRole) {
        this.tsmRole = tsmRole;
    }

    @Override
    public String getId() {
        return tsmRole.getId().toString();
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public String getName() {
        return tsmRole.getName();
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getType() {
        return tsmRole.getCode();
    }

    @Override
    public void setType(String string) {

    }
}
