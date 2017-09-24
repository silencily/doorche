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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            userService.saveOrUpdate(user);
        } else {
            TsmUser user1 = userService.load(TsmUser.class, user.getId());
            user1.setName(user.getLoginName());
            user1.setNo(user.getNo());
            user1.setLoginName(user.getLoginName());
            user1.setMobile(user.getMobile());
            user1.setEmail(user.getEmail());
            user1.setIsDisable(user.getIsDisable());
            userService.saveOrUpdate(user1);
            user = user1;
        }
        model.addAttribute("user", user);
        this.addSuccessMessage(model, getMessage("COMMON_SAVE_SUCCESS"));
        return "/sm/user/info";
    }

    @RequestMapping("/selectRoles")
    public String selectRoles(Model model, Integer id) {
        List<TsmRole> list = roleService.list(TsmRole.class);
        model.addAttribute("list", list);
        return "/sm/user/selectRoles";
    }

    @RequestMapping("/assignRoles")
    public String assignRoles(Integer id, @RequestParam(value = "roleIds") Integer[] roleIds, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", id);
        TsmUser tsmUser = userService.load(TsmUser.class, id);
        Set<TsmRole> tsmRoles = tsmUser.getTsmRoles();
        for (Integer roleId : roleIds) {
            TsmRole role = roleService.load(TsmRole.class, roleId);
            tsmRoles.add(role);
        }
        tsmUser.setTsmRoles(tsmRoles);
        userService.saveOrUpdate(tsmUser);
        this.addSuccessMessage(redirectAttributes, getMessage("sm.user.assignRoles.suc"));
        return "redirect:/sm/user/edit";
    }

    @RequestMapping("delete")
    public String delete(Integer id, RedirectAttributes redirectAttributes) {
        TsmUser tsmUser = userService.load(TsmUser.class, id);
        //角色置空
        tsmUser.setTsmRoles(null);
        userService.saveOrUpdate(tsmUser);
        userService.delete(TsmUser.class, id);
        this.addSuccessMessage(redirectAttributes, getMessage("COMMON_DELETE_SUCCESS"));
        return "redirect:/sm/user?recondition=true";
    }

    @RequestMapping("deleteRole")
    public String deleteRole(Integer id, Integer roleId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", id);
        TsmUser tsmUser = userService.load(TsmUser.class, id);
        Set<TsmRole> tsmRoles = tsmUser.getTsmRoles();
        for (TsmRole tsmRole : tsmRoles) {
            if (tsmRole.getId().equals(roleId)) {
                tsmRoles.remove(tsmRole);
                break;
            }
        }
        tsmUser.setTsmRoles(tsmRoles);
        userService.saveOrUpdate(tsmUser);
        this.addSuccessMessage(redirectAttributes, getMessage("sm.user.deleteRole.suc"));
        return "redirect:/sm/user/edit";
    }

}
