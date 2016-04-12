/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.admin.sm.service;

import org.silencer.doorche.entity.TsmPermission;
import org.silencer.doorche.support.IService;

import java.util.List;

/**
 * @author gejb
 * @since 2016-04-12
 */
public interface PermissionService extends IService {
    /**
     * 查询权限列表，按照sort字段进行排序
     *
     * @return 权限列表
     */
    public List<TsmPermission> list();
}
