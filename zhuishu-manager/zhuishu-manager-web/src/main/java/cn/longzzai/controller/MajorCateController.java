package cn.longzzai.controller;

import cn.longzzai.pojo.ZzaiResult;
import cn.longzzai.service.MajorCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主分类管理
 *
 * @author longcho
 * 2017-09-16-20:46
 */
@Controller
@RequestMapping("/majorcate")
public class MajorCateController {
    @Autowired
    private MajorCateService majorCateService;

    @RequestMapping("/list")
    @ResponseBody
    public ZzaiResult list(){
        return majorCateService.findAll();
    }
}
