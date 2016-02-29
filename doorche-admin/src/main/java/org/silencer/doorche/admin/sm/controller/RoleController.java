package org.silencer.doorche.admin.sm.controller;

import org.silencer.doorche.admin.support.web.AbstractAdminController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gejb
 * @since 2016/2/29
 */
@Controller
@RequestMapping("sm/role")
public class RoleController extends AbstractAdminController {
    @RequestMapping
    public String list(){
        return "sm/role/list";
    }
}
