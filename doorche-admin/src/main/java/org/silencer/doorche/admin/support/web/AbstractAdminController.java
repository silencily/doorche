package org.silencer.doorche.admin.support.web;

import org.silencer.doorche.security.DoorchePermission;
import org.silencer.doorche.security.DoorcheUserDetails;
import org.silencer.doorche.security.SecurityContextHelper;
import org.silencer.doorche.support.AbstractControllerSupport;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * @author gejb
 * @since 2016/2/19
 */
public abstract class AbstractAdminController extends AbstractControllerSupport {

    private final static String MODEL_ATTRIBUTE_NAME_CURRENTUSER = "currentUser";

    private final static String MODEL_ATTRIBUTE_NAME_CURRENTMENUS = "menus";

    /**
     * 获取当前用户信息
     *
     * @return 当前登录用户信息
     */
    @ModelAttribute(MODEL_ATTRIBUTE_NAME_CURRENTUSER)
    public DoorcheUserDetails obtainCurrentUser() {
        return (DoorcheUserDetails) SecurityContextHelper.obtainCurrentSecurityUser();

    }

    /**
     * 获取当前用户菜单权限
     *
     * @return 当前用户菜单权限
     */
    @ModelAttribute(MODEL_ATTRIBUTE_NAME_CURRENTMENUS)
    public List<DoorchePermission> obtainCurrentMenus() {

        return obtainCurrentUser() == null ? null : obtainCurrentUser().getMenuPermissions();
    }
}
