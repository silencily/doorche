/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.admin.sm.service;

import org.silencer.doorche.support.IService;

/**
 * @author gejb
 * @since 2016-03-27
 */
public interface UserService extends IService {

    /**
     * 获取默认密码
     *
     * @return 默认密码
     */
    public String getDefaultPassword();
}
