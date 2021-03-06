/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 访问权限url投票器，判断授权中是否包含被访问的url
 *
 * @author gejb
 * @since 2016-04-03
 */
public class PermissionUrlVoter implements AccessDecisionVoter<FilterInvocation> {
    public static final Log logger = LogFactory.getLog(PermissionUrlVoter.class);

    public static final String ROOT_URL = "/";

    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        int result = ACCESS_ABSTAIN;
        String accessUrl = object.getRequestUrl();
        logger.debug("PermissionUrlVoter vote the request url: " + accessUrl);
        //去掉?后面get请求参数进行对比
        if (accessUrl.contains("?")) {
            accessUrl = accessUrl.substring(0, accessUrl.indexOf("?"));
            logger.debug("removed the '?' in request url: " + accessUrl);
        }
        if (ROOT_URL.equals(accessUrl)) {
            return ACCESS_GRANTED;//根路径url通过
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof DoorcheUserDetails) {
            DoorcheUserDetails userDetails = (DoorcheUserDetails) principal;
            List<DoorchePermission> menuPermissions = userDetails.getMenuPermissions();
            boolean containUrl = false;
            //只处理两级菜单权限url
            for (int i = 0; i < menuPermissions.size(); i++) {
                DoorchePermission permission = menuPermissions.get(i);
                for (DoorchePermission urlPermission : permission.getChildren()) {
                    if (accessUrl.equals(urlPermission.getHref())) {
                        containUrl = true;
                        break;
                    }
                }
                if (containUrl) {
                    break;
                }
            }
            if (containUrl) {
                result = ACCESS_GRANTED;
            } else {
                //检查操作权限
                Map<String, DoorchePermission> operationPermissions = userDetails.getOperationPermissions();
                for (DoorchePermission permission : operationPermissions.values()) {
                    if (accessUrl.equals(permission.getHref())) {
                        containUrl = true;
                        break;
                    }
                }
                if (containUrl) {
                    result = ACCESS_GRANTED;
                } else {
                    logger.debug("url access denied: " + accessUrl);
                    result = ACCESS_DENIED;
                }
            }
        }
        return result;
    }
}
