/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.admin.sm.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.silencer.doorche.admin.sm.service.PermissionService;
import org.silencer.doorche.entity.TsmPermission;
import org.silencer.doorche.support.AbstractService;
import org.silencer.doorche.support.TreeViewNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author gejb
 * @since 2016-04-12
 */
@Transactional
@Service
public class PermissionServiceImpl extends AbstractService implements PermissionService {
    public List<TsmPermission> list() {
        DetachedCriteria dc = DetachedCriteria.forClass(TsmPermission.class);
        dc.addOrder(Order.asc("sort"));
        return list(dc);
    }

    public List<TreeViewNode> listTreeViewNode(String excludeId) {
        List<TreeViewNode> treeViewNodes = new ArrayList<TreeViewNode>();
        DetachedCriteria dc = DetachedCriteria.forClass(TsmPermission.class);
        dc.add(Restrictions.ne("type", String.valueOf(TsmPermission.OPERATE_TYPE)));
        if (StringUtils.isNotBlank(excludeId)) {
            dc.add(Restrictions.ne("id", Integer.parseInt(excludeId)));
        }
        dc.addOrder(Order.asc("sort"));
        List<TsmPermission> permissions = list(dc);
        for (int i = 0; i < permissions.size(); i++) {
            TreeViewNode node = new TreeViewNode();
            TsmPermission permission = permissions.get(i);
            node.setId(permission.getId());
            node.setText(permission.getName());
            node.setParentId(permission.getParent() == null ? TreeViewNode.ROOT_ID : permission.getParent().getId());
            treeViewNodes.add(node);
        }
        return treeViewNodes;
    }

    public List<TsmPermission> listByIds(String[] permissionIdArray) {
        if (permissionIdArray == null || !(permissionIdArray.length > 0)) {
            return null;
        }
        Integer[] ids = new Integer[permissionIdArray.length];
        for (int i = 0; i < permissionIdArray.length; i++) {
            ids[i] = Integer.parseInt(permissionIdArray[i]);
        }
        DetachedCriteria dc = DetachedCriteria.forClass(TsmPermission.class);
        dc.add(Restrictions.in("id", ids));
        return list(dc);
    }
}
