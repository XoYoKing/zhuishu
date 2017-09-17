package cn.longzzai.controller;

import cn.longzzai.VO.MajorCateListVO;
import cn.longzzai.exception.BookException;
import cn.longzzai.myenum.ResultEnum;
import cn.longzzai.pojo.MajorCate;
import cn.longzzai.pojo.ZzaiResult;
import cn.longzzai.service.MajorCateService;
import cn.longzzai.utils.UpdateMajorFromOnline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 主分类管理
 *
 * @author longcho
 * 2017-09-16-20:46
 */
@Controller
@RequestMapping("/majorcate")
@Slf4j
public class MajorCateController {
    @Autowired
    private MajorCateService majorCateService;

    //主分类列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ZzaiResult list() {
        return majorCateService.findAll();
    }

    //主分类单个详情
    @RequestMapping("/{majorCateId}")
    @ResponseBody
    public ZzaiResult one(@PathVariable int majorCateId) {
        return majorCateService.findById(majorCateId);
    }

    //添加主分类
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ZzaiResult add(@RequestParam("majorCateName") String majorCateName,
                          @RequestParam(value = "sex", defaultValue = "0") int sex) {
        return majorCateService.save(majorCateName, sex);
    }

    //修改主分类
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ZzaiResult update(MajorCate majorCate) {
        return majorCateService.update(majorCate);
    }


    //批量从网上更新主分类
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public ZzaiResult update() {
        try {
            MajorCateListVO majorListVOByUrl = UpdateMajorFromOnline.getMajorListVOByUrl();
            List<MajorCate> majorCateList = UpdateMajorFromOnline.majorCateListVOToMajorCate(majorListVOByUrl);
            return majorCateService.updateByList(majorCateList);
        } catch (Exception e) {
            log.error("【书籍主分类】，批量从网上更新主分类失败，e={}" ,e);
            throw new BookException(ResultEnum.BOOK_UPDATE_FAILD);
        }
    }


    //删除主分类
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ZzaiResult delete(@RequestParam("majorCateId") int majorCateId) {
        return majorCateService.delete(majorCateId);
    }
}
