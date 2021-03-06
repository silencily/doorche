package org.silencer.doorche.admin.sm.controller;

import org.silencer.doorche.admin.support.web.AbstractAdminController;
import org.silencer.doorche.security.DoorchePermission;
import org.silencer.doorche.security.DoorcheUserDetails;
import org.silencer.doorche.support.AbstractControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 系统入口控制器
 *
 * @author gejb
 * @since 2016/2/18
 */
@Controller
public class EntryController extends AbstractAdminController {
    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }
}
