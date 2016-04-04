/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.security;

import org.springframework.security.core.userdetails.User;

import java.util.*;

/**
 * @author gejb
 * @since 2015-09-04
 */
public class DoorcheUserDetails extends User {
    private Integer userId;//用户id
    private String no;//用户编号
    private String chName;//用户名称
    private String email;//邮箱
    private String mobile;//手机

    private Map<String, DoorchePermission> operationPermissions;//操作权限
    private List<DoorchePermission> menuPermissions;//菜单权限


    public DoorcheUserDetails(Integer userId, String no, String chName, String email, String mobile, String username,
                              String password, boolean enabled, Set<DoorcheGrantedAuthority> authorities) {
        super(username, password, enabled, true, true, true, authorities);
        this.userId = userId;
        this.no = no;
        this.chName = chName;
        this.email = email;
        this.mobile = mobile;
        buildMenuAndOperationPermissions(authorities);
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNo() {
        return no;
    }

    public String getChName() {
        return chName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Map<String, DoorchePermission> getOperationPermissions() {
        return operationPermissions;
    }

    public List<DoorchePermission> getMenuPermissions() {
        return menuPermissions;
    }

    /**
     * 构建菜单权限和操作权限
     *
     * @param authorities 权限集合
     */
    private void buildMenuAndOperationPermissions(Set<DoorcheGrantedAuthority> authorities) {
        this.operationPermissions = new HashMap<String, DoorchePermission>();
        Map<Integer, DoorchePermission> menuPermissionMap = new HashMap<Integer, DoorchePermission>();
        for (DoorcheGrantedAuthority authority : authorities) {
            Map<String, DoorchePermission> operationPermission = authority.getOperationPermissions();
            operationPermissions.putAll(operationPermission);
            Map<Integer, DoorchePermission> menuPermission = authority.getMenuPermissions();
            for (Map.Entry<Integer, DoorchePermission> entry : menuPermission.entrySet()) {
                Integer key = entry.getKey();
                DoorchePermission doorchePermission = entry.getValue();
                if (menuPermissionMap.containsKey(key)) {
                    org.silencer.doorche.security.DoorchePermission existDoorchePermission = menuPermissionMap.get(key);
                    for (org.silencer.doorche.security.DoorchePermission child : doorchePermission.getChildren()) {
                        existDoorchePermission.addChild(child);
                    }
                } else {
                    menuPermissionMap.put(key, doorchePermission);
                }
            }
        }
        for (org.silencer.doorche.security.DoorchePermission DoorchePermission : menuPermissionMap.values()) {
            Collections.sort(DoorchePermission.getChildren());//排序菜单节点
        }
        menuPermissions = new ArrayList<DoorchePermission>(menuPermissionMap.values());//排序目录
        Collections.sort(menuPermissions);
    }

}
