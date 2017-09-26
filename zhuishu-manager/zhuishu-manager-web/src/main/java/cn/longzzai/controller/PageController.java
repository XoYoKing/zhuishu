package cn.longzzai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面管理
 *
 * @author longcho
 * 2017-09-22-8:32
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String gotoIndex() {
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
