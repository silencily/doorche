/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author gejb
 * @since 14-8-28
 */
public class SecurityContextHelper {
    private static final Log logger = LogFactory.getLog(SecurityContextHelper.class);

    /**
     * 获取当前安全用户信息
     *
     * @return 当前登录安全用户
     */
    public static UserDetails obtainCurrentSecurityUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("security context does not contain authentication.");
            }
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null || !(principal instanceof UserDetails)) {
            if (logger.isWarnEnabled()) {
                logger.warn("Principal [" + principal + "] is invalided.Please be sure you set SecurityUser type to principal.");
            }
            return null;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Current Auditor:" + principal);
        }
        return (UserDetails) principal;
    }

    /**
     * 获取当前登录安全用户id
     *
     * @return 用户id
     */
    public static int obtainCurrentSecurityUserId() {
        UserDetails userDetails = obtainCurrentSecurityUser();
        if (userDetails instanceof DoorcheUserDetails) {
            return ((DoorcheUserDetails) userDetails).getUserId();
        } else {
            logger.warn("当前未设置[DoorcheUserDetails]类型.");
            return 0;
        }
    }


}
