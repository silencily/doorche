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

    @RequestMapping("/save")
    public String save(Model model, TsmDict dict, Integer parentId) {
        if (parentId != null) {
            //保存code节点
            TsmDict parent = dictService.load(TsmDict.class, parentId);
            dict.setParent(parent);
            dict.setTypeName(parent.getTypeName());
            dict.setTypeCode(parent.getTypeCode());
            dictService.saveOrUpdate(dict);
            List<TsmDict> children = dictService.listByParentId(parentId);
            model.addAttribute("dict", parent);
            model.addAttribute("children", children);
        } else {
            dictService.saveOrUpdate(dict);
            List<TsmDict> children = dictService.listByParentId(dict.getId());
            model.addAttribute("dict", dict);
            model.addAttribute("children", children);
        }
        this.addMessage(model, getMessage("COMMON_SAVE_SUCCESS"));

        return "sm/dict/info";
    }

    @RequestMapping("/edit")
    public String edit(Model model, Integer id) {
        TsmDict tsmDict = dictService.load(TsmDict.class, id);
        List<TsmDict> children = dictService.listByParentId(id);
        model.addAttribute("dict", tsmDict);
        model.addAttribute("children", children);
        return "sm/dict/info";
    }
}
