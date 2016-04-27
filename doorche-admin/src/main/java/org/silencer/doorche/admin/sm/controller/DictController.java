/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.admin.sm.controller;

import org.silencer.doorche.admin.sm.service.DictService;
import org.silencer.doorche.admin.support.web.AbstractAdminController;
import org.silencer.doorche.entity.TsmDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author gejb
 * @since 2016-04-27
 */
@Controller
@RequestMapping("/sm/dict")
public class DictController extends AbstractAdminController {

    @Autowired
    private DictService dictService;

    @RequestMapping
    public String list(Model model) {
        List<TsmDict> list = dictService.list();
        model.addAttribute("list", list);
        return "sm/dict/list";
    }
    @RequestMapping("/new")
    public String newing(Model model) {
        TsmDict dict = new TsmDict();
        model.addAttribute("dict", dict);
        return "sm/dict/info";
    }
}
