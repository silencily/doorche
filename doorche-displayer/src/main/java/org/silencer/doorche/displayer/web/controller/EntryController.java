/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.displayer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gejb
 * @since 2015-06-06
 */
@Controller
public class EntryController {
    @RequestMapping(value = "/")
    public String entry() {
        return "index";
    }
}
