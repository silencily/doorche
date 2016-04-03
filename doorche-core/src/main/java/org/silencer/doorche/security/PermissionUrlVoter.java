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

/**
 * 访问权限url投票器，判断授权中是否包含被访问的url
 *
 * @author gejb
 * @since 2016-04-03
 */
public class PermissionUrlVoter implements AccessDecisionVoter<FilterInvocation> {
    public static final Log logger = LogFactory.getLog(PermissionUrlVoter.class);

    public static final String ROOT_URL = "/";

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        int result = ACCESS_ABSTAIN;
        String accessUrl = object.getRequestUrl();
        logger.debug("PermissionUrlVoter vote the request url :" + accessUrl);
        if(ROOT_URL.equals(accessUrl)){
            return ACCESS_GRANTED;//根路径url通过
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof DoorcheUserDetails) {
            DoorcheUserDetails userDetails = (DoorcheUserDetails) principal;
            List<DoorchePermission> permissions = userDetails.getMenuPermissions();
            boolean containUrl = false;
            //只处理两级菜单权限url
            for (int i = 0; i < permissions.size(); i++) {
                DoorchePermission permission = permissions.get(i);
                for(DoorchePermission urlPermission:permission.getChildren()){
                    if (accessUrl.equals(urlPermission.getHref())) {
                        containUrl = true;
                        break;
                    }
                }
                if(containUrl){
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
        return result;
    }
}
