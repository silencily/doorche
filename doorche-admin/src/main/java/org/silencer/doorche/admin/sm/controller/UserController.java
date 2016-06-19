package org.silencer.doorche.admin.sm.controller;

import org.silencer.doorche.admin.sm.service.RoleService;
import org.silencer.doorche.admin.sm.service.UserService;
import org.silencer.doorche.admin.support.web.AbstractAdminController;
import org.silencer.doorche.entity.TsmRole;
import org.silencer.doorche.entity.TsmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author gejb
 * @since 2016/2/19
 */
@Controller
@RequestMapping("/sm/user")
public class UserController extends AbstractAdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping
    public String list(Model model) {
        List<TsmUser> list = userService.list(TsmUser.class);
        model.addAttribute("list", list);
        return "sm/user/list";
    }

    @RequestMapping("/new")
    public String newing(Model model) {
        TsmUser user = new TsmUser();
        model.addAttribute("user", user);
        return "sm/user/info";
    }

    @RequestMapping("/edit")
    public String edit(Model model, Integer id) {
        TsmUser tsmUser = userService.load(TsmUser.class, id);
        model.addAttribute("user", tsmUser);
        return "sm/user/info";
    }

    @RequestMapping("/save")
    public String save(Model model, TsmUser user) {
        if (user.getId() == null) {
            user.setPassword(userService.getDefaultPassword());
        }
        userService.saveOrUpdate(user);
        model.addAttribute("user", user);
        this.addMessage(model, getMessage("COMMON_SAVE_SUCCESS"));
        return "/sm/user/info";
    }

    @RequestMapping("/selectRoles")
    public String selectRoles(Model model) {
        List<TsmRole> list = roleService.list(TsmRole.class);
        model.addAttribute("list", list);
        return "/sm/user/selectRoles";

    }

}
