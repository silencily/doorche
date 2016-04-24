/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.admin.sm.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.silencer.doorche.admin.sm.service.PermissionService;
import org.silencer.doorche.entity.TsmPermission;
import org.silencer.doorche.support.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gejb
 * @since 2016-04-12
 */
@Transactional
@Service
public class PermissionServiceImpl extends AbstractService implements PermissionService {
    @Override
    public List<TsmPermission> list() {
        DetachedCriteria dc = DetachedCriteria.forClass(TsmPermission.class);
        dc.addOrder(Order.asc("sort"));
        return list(dc);
    }
}
