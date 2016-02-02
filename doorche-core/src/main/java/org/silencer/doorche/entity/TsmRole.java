package org.silencer.doorche.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author gejb
 * @since 2015/12/7
 */
@Entity
@Table(name = "T_SM_ROLE")
public class TsmRole extends AbstractEntity {
    private String name;
    private String code;
    private String dataScope;
    private String isDisable;

    private Set<TsmUser> tsmUsers = new HashSet<TsmUser>();

    private Set<TsmPermission> tsmPermissions = new HashSet<TsmPermission>();
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "DATA_SCOPE")
    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    @Column(name = "IS_DISABLE")
    public String getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
    }

    @ManyToMany(mappedBy = "tsmRoles",fetch = FetchType.LAZY)
    public Set<TsmUser> getTsmUsers() {
        return tsmUsers;
    }

    public void setTsmUsers(Set<TsmUser> tsmUsers) {
        this.tsmUsers = tsmUsers;
    }

    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinTable(name = "T_SM_ROLE_PERMISSION", inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID"),
            joinColumns = @JoinColumn(name = "ROLE_ID"))
    public Set<TsmPermission> getTsmPermissions() {
        return tsmPermissions;
    }

    public void setTsmPermissions(Set<TsmPermission> tsmPermissions) {
        this.tsmPermissions = tsmPermissions;
    }

}
