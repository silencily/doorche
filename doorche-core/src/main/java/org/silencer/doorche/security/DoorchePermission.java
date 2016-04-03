/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gejb
 * @since 2015-08-30
 */
public class DoorchePermission implements Comparable<DoorchePermission> {
    private Integer id;
    private DoorchePermission parent;
    private String name;
    private Integer sort;
    private String href;
    private String icon;
    private String permission;
    private boolean show;
    private List<DoorchePermission> children = new ArrayList<DoorchePermission>();

    private Set<Integer> childrenFlags = new HashSet<Integer>();//用于判断孩子节点是否已包含

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DoorchePermission getParent() {
        return parent;
    }

    public void setParent(DoorchePermission parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<DoorchePermission> getChildren() {
        return children;
    }

    public void addChild(DoorchePermission child) {
        if (!childrenFlags.contains(child.getId())) {
            childrenFlags.add(child.getId());
            this.children.add(child);
        }
    }

    @Override
    public int compareTo(DoorchePermission o) {
        return this.getSort() - o.getSort();
    }
}
