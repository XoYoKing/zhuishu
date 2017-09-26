package cn.longzzai.controller;

import cn.longzzai.VO.CateAllListVO;
import cn.longzzai.exception.BookException;
import cn.longzzai.myenum.ResultEnum;
import cn.longzzai.pojo.MinorCate;
import cn.longzzai.pojo.ZzaiResult;
import cn.longzzai.service.MinorCateService;
import cn.longzzai.utils.UpdateCateAllFromOnline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 副分类管理
 *
 * @author longcho
 * 2017-09-16-20:46
 */
@Controller
@RequestMapping("/minorcate")
@Slf4j
public class MinorCateController {
    @Autowired
    private MinorCateService minorCateService;

    //所有副分类列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ZzaiResult list() {
        log.info("查询所有副分类");
        return minorCateService.findAll();
    }

    //副分类列表
    @RequestMapping(value = "/listDetail", method = RequestMethod.GET )
    @ResponseBody
    public ZzaiResult list(@RequestParam("majorCateName") String majorCateName) {
        return minorCateService.findByMajorName(majorCateName);
    }

    //副分类单个详情
    @RequestMapping("/{minorCateId}")
    @ResponseBody
    public ZzaiResult one(@PathVariable int minorCateId) {
        return minorCateService.findById(minorCateId);
    }

    //添加副分类
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ZzaiResult add(@RequestParam("minorCateName") String minorCateName,
                          @RequestParam(value = "majorCateName",required = false) String majorCateName) {
        return minorCateService.save(minorCateName, majorCateName);
    }

    //修改副分类
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ZzaiResult update(MinorCate minorCate) {
        return minorCateService.update(minorCate);
    }


    //批量从网上更新副分类
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public ZzaiResult update() {
        try {
            CateAllListVO cateAllListVOByUrl = UpdateCateAllFromOnline.getCateAllListVOByUrl();
            List<MinorCate> minorCateList = UpdateCateAllFromOnline.CateAllListVOToMinorCate(cateAllListVOByUrl);
            return minorCateService.updateByList(minorCateList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【书籍副分类】，批量从网上更新副分类失败，e={}" ,e);
            throw new BookException(ResultEnum.BOOK_UPDATE_FAILD);
        }
    }


    //删除副分类
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ZzaiResult delete(@RequestParam("minorCateId") int minorCateId) {
        return minorCateService.delete(minorCateId);
    }
}
