package org.silencer.doorche.admin.sm.controller;

import org.silencer.doorche.admin.sm.service.PermissionService;
import org.silencer.doorche.admin.support.web.AbstractAdminController;
import org.silencer.doorche.entity.TsmPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author gejb
 * @since 2016/2/29
 */
@Controller
@RequestMapping("/sm/permission")
public class PermissionController extends AbstractAdminController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping
    public String list(Model model) {
        List<TsmPermission> list = permissionService.list(TsmPermission.class);
        model.addAttribute("list", list);
        return "sm/permission/list";
    }
}
