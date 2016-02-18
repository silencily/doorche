package org.silencer.doorche.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.silencer.doorche.security.DoorcheUserDetails;
import org.silencer.doorche.security.SecurityContextHelper;

/**
 * 控制器基类
 * @author gejb
 * @since 2016/2/18
 */
public abstract class AbstractControllerSupport {
    protected final Log logger = LogFactory.getLog(getClass());
    /**
     * 获取当前用户信息
     *
     * @return 当前登录用户信息
     */
    public DoorcheUserDetails obtainCurrentUser() {
        return (DoorcheUserDetails) SecurityContextHelper.obtainCurrentSecurityUser();

    }
}
