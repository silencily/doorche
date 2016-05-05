package org.silencer.doorche.admin.sm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.silencer.doorche.admin.sm.service.PermissionService;
import org.silencer.doorche.admin.support.web.AbstractAdminController;
import org.silencer.doorche.entity.TsmDict;
import org.silencer.doorche.entity.TsmPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Map<String, String>> tree(String excludeId) {
        logger.debug("excludeId:[" + excludeId + "]");
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        Map<String, String> node1 = new HashMap<String, String>();
        node1.put("text", "node1");
        data.add(node1);
        Map<String, String> node2 = new HashMap<String, String>();
        node2.put("text", "node2");
        data.add(node2);
        Map<String, String> node3 = new HashMap<String, String>();
        node3.put("text", "node2");
        node3.put("icon", "glyphicon glyphicon-stop");
        node3.put("selectedIcon", "glyphicon glyphicon-stop");
        data.add(node3);
        return data;
    }
}
