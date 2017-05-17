/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gejb
 * @since 2015-08-29
 */
public class DoorcheGrantedAuthority implements GrantedAuthority {
    private String role;
    private Map<Integer, DoorchePermission> menuPermissions = new HashMap<Integer, DoorchePermission>();
    private Map<String, DoorchePermission> operationPermissions = new HashMap<String, DoorchePermission>();

    public DoorcheGrantedAuthority(String role) {
        this.role = role;
    }

    public String getAuthority() {
        return role;
    }

    public Map<Integer, DoorchePermission> getMenuPermissions() {
        return menuPermissions;
    }

    public void setMenuPermissions(Map<Integer, DoorchePermission> menuPermissions) {
        this.menuPermissions = menuPermissions;
    }

    public Map<String, DoorchePermission> getOperationPermissions() {
        return operationPermissions;
    }

    public void setOperationPermissions(Map<String, DoorchePermission> operationPermissions) {
        this.operationPermissions = operationPermissions;
    }
}
