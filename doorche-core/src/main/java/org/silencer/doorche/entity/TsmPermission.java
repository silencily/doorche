package org.silencer.doorche.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author gejb
 * @since 2015/12/7
 */
@Entity
@Table(name = "T_SM_PERMISSION")
public class TsmPermission extends AbstractEntity {
    public static final int CATALOG_TYPE = 0;
    public static final int MENU_TYPE = 1;
    public static final int OPERATE_TYPE = 2;
    private TsmPermission parent;
    private String type;
    private String name;
    private Integer sort;
    private String href;
    private String icon;
    private String permission;
    private String isShow;
    private Set<TsmRole> tsmRoles = new HashSet<TsmRole>();

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public TsmPermission getParent() {
        return parent;
    }

    public void setParent(TsmPermission parent) {
        this.parent = parent;
    }

    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "NAEM")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SORT")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "HREF")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Column(name = "ICON")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "PERMISSION")
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Column(name = "IS_SHOW")
    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    @ManyToMany(mappedBy = "tsmPermissions",fetch = FetchType.LAZY)
    public Set<TsmRole> getTsmRoles() {
        return tsmRoles;
    }

    public void setTsmRoles(Set<TsmRole> tsmRoles) {
        this.tsmRoles = tsmRoles;
    }
}
