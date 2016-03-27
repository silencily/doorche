package org.silencer.doorche.admin.sm.controller;

import org.silencer.doorche.admin.sm.service.UserService;
import org.silencer.doorche.admin.support.web.AbstractAdminController;
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

    @RequestMapping
    public String list(Model model) {
        List<TsmUser> list = userService.list(TsmUser.class);
        model.addAttribute("list", list);
        return "sm/user/list";
    }

}
