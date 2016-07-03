package org.silencer.doorche.admin.sm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.silencer.doorche.admin.sm.service.PermissionService;
import org.silencer.doorche.admin.support.web.AbstractAdminController;
import org.silencer.doorche.entity.TsmPermission;
import org.silencer.doorche.support.TreeViewNode;
import org.silencer.doorche.support.TreeViewNodePopulater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author gejb
 * @since 2016/2/29
 */
@Controller
@RequestMapping("/sm/permission")
public class PermissionController extends AbstractAdminController {
    private static final Log logger = LogFactory.getLog(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    @RequestMapping
    public String list(Model model) {
        concealPaginate();//树形列表，不需要进行分页
        List<TsmPermission> list = permissionService.list();
        model.addAttribute("list", list);
        return "sm/permission/list";
    }

    @RequestMapping("/new")
    public String newing(Model model) {
        TsmPermission permission = new TsmPermission();
        model.addAttribute("permission", permission);
        return "sm/permission/info";
    }

    @ResponseBody
    @RequestMapping("/tree")
    public List<TreeViewNode> tree(String excludeId) {
        logger.debug("excludeId:[" + excludeId + "]");
        List<TreeViewNode> dataSrcNodes = permissionService.listTreeViewNode(excludeId);
        List<TreeViewNode> treeViewNodes = TreeViewNodePopulater.populate(dataSrcNodes);
        return treeViewNodes;
    }

    @RequestMapping("/save")
    public String save(TsmPermission permission, Integer parentId, Model model) {

        if (parentId == null) {
            permission.setParent(null);
        } else {
            TsmPermission parent = permissionService.load(TsmPermission.class, parentId);
            permission.setParent(parent);
        }
        permissionService.saveOrUpdate(permission);
        model.addAttribute("permission", permission);
        this.addMessage(model, getMessage("COMMON_SAVE_SUCCESS"));
        return "sm/permission/info";
    }

    @RequestMapping("delete")
    public String delete(Integer id,RedirectAttributes redirectAttributes) {
        TsmPermission permission = permissionService.load(TsmPermission.class, id);
        if (permission.getTsmRoles() != null && permission.getTsmRoles().size() > 0) {
            this.addMessage(redirectAttributes, getMessage("sm.permission.delete.inuse"));
        } else {
            permissionService.delete(TsmPermission.class, id);
            this.addMessage(redirectAttributes, getMessage("COMMON_DELETE_SUCCESS"));
        }
        return "redirect:/sm/permission?recondition=true";
    }
}
