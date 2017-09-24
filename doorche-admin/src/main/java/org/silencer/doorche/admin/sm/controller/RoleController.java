package org.silencer.doorche.admin.sm.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.silencer.doorche.admin.sm.service.PermissionService;
import org.silencer.doorche.admin.sm.service.RoleService;
import org.silencer.doorche.admin.support.web.AbstractAdminController;
import org.silencer.doorche.entity.TsmPermission;
import org.silencer.doorche.entity.TsmRole;
import org.silencer.doorche.entity.TsmUser;
import org.silencer.doorche.support.TreeTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gejb
 * @since 2016/2/29
 */
@Controller
@RequestMapping("sm/role")
public class RoleController extends AbstractAdminController {
    private static Log logger = LogFactory.getLog(RoleController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping
    public String list(Model model) {
        List<TsmRole> list = roleService.list(TsmRole.class);
        model.addAttribute("list", list);
        return "sm/role/list";
    }

    @RequestMapping("/new")
    public String newing(Model model) {
        try {
            TsmRole role = new TsmRole();
            List<TsmPermission> permissionList = permissionService.list();
            TreeTableModel<TsmPermission> treeTable = new TreeTableModel<TsmPermission>(permissionList);
            model.addAttribute("role", role);
            model.addAttribute("permissionList", treeTable.getNodes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sm/role/info";
    }

    @RequestMapping("/edit")
    public String edit(Model model, Integer id) {
        TsmRole role = roleService.load(TsmRole.class, id);
        Set<TsmPermission> checkedPermissionsSet = role.getTsmPermissions();
        model.addAttribute("role", role);
        List<TsmPermission> permissionList = permissionService.list();
        List<TsmPermission> checkedPermissions = new ArrayList<TsmPermission>(checkedPermissionsSet);
        try {
            TreeTableModel<TsmPermission> treeTable = new TreeTableModel<TsmPermission>(permissionList, checkedPermissions, "getId");
            model.addAttribute("permissionList", treeTable.getNodes());
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return "sm/role/info";
    }

    @RequestMapping("/save")
    public String save(Model model, TsmRole role, String permissionIds) {
        Set<TsmPermission> checkedPermissionsSet = null;
        List<TsmPermission> checkedPermissions = null;
        if (StringUtils.isNotBlank(permissionIds)) {
            String[] permissionIdArray = permissionIds.split(",");
            checkedPermissions = permissionService.listByIds(permissionIdArray);
            checkedPermissionsSet = new HashSet<TsmPermission>(checkedPermissions);
            role.setTsmPermissions(checkedPermissionsSet);
        }
        roleService.saveOrUpdate(role);
        List<TsmPermission> permissionList = permissionService.list();
        try {
            TreeTableModel<TsmPermission> treeTable = new TreeTableModel<TsmPermission>(permissionList, checkedPermissions, "getId");
            model.addAttribute("permissionList", treeTable.getNodes());
        } catch (Exception e) {
            this.addErrorMessage(model, getMessage("COMMON_SAVE_FAILED"));
            logger.error(e);
        }
        model.addAttribute("role", role);
        this.addSuccessMessage(model, getMessage("COMMON_SAVE_SUCCESS"));
        return "sm/role/info";
    }

    @RequestMapping("delete")
    public String delete(Integer id, RedirectAttributes redirectAttributes) {
        TsmRole tsmRole = roleService.load(TsmRole.class, id);
        Set<TsmUser> tsmUsers = tsmRole.getTsmUsers();
        if (tsmUsers != null && tsmUsers.size() > 0) {
            this.addErrorMessage(redirectAttributes, getMessage("sm.role.delete.inuse"));
        } else {
            //权限关系置空
            tsmRole.setTsmPermissions(null);
            roleService.saveOrUpdate(tsmRole);
            roleService.delete(TsmRole.class, id);
            this.addSuccessMessage(redirectAttributes, getMessage("COMMON_DELETE_SUCCESS"));
        }
        return "redirect:/sm/role?recondition=true";
    }
}
