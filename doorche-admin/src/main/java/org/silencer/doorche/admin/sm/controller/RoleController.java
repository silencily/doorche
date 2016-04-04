package org.silencer.doorche.admin.sm.controller;

import org.silencer.doorche.admin.sm.service.RoleService;
import org.silencer.doorche.admin.support.web.AbstractAdminController;
import org.silencer.doorche.entity.TsmRole;
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
@RequestMapping("sm/role")
public class RoleController extends AbstractAdminController {
    @Autowired
    private RoleService roleService;

    @RequestMapping
    public String list(Model model) {
        List<TsmRole> list = roleService.list(TsmRole.class);
        model.addAttribute("list", list);
        return "sm/role/list";
    }
}
