/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.admin.sm.controller;

import org.silencer.doorche.admin.sm.service.ParameterService;
import org.silencer.doorche.admin.support.web.AbstractAdminController;
import org.silencer.doorche.entity.TsmParameter;
import org.silencer.doorche.entity.TsmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author gejb
 * @since 2016-04-13
 */
@Controller
@RequestMapping("/sm/parameter")
public class ParameterController extends AbstractAdminController {

    @Autowired
    private ParameterService parameterService;

    @RequestMapping
    public String list(Model model) {
        List<TsmParameter> list = parameterService.list(TsmParameter.class);
        model.addAttribute("list", list);
        return "sm/parameter/list";
    }

    @RequestMapping("/new")
    public String newing(Model model) {
        TsmParameter parameter = new TsmParameter();
        model.addAttribute("parameter", parameter);
        return "sm/parameter/info";
    }

    @RequestMapping("/save")
    public String save(TsmParameter parameter, RedirectAttributes redirectAttributes) {
        if (parameter.getId() == null) {
            parameterService.save(parameter);
        } else {
            parameterService.update(parameter);
        }
        redirectAttributes.addFlashAttribute("id", parameter.getId());
        return "redirect:/sm/parameter/edit";
    }

    @RequestMapping("/edit")
    public String edit(Model model) {
        Integer id = (Integer)model.asMap().get("id");
        TsmParameter tsmParameter = parameterService.load(TsmParameter.class, id);
        model.addAttribute("parameter", tsmParameter);
        return "sm/parameter/info";
    }

}
