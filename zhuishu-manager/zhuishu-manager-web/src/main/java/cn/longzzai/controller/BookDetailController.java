package cn.longzzai.controller;

import cn.longzzai.VO.BookDetailAtFindListVO;
import cn.longzzai.pojo.BookDetail;
import cn.longzzai.pojo.ZzaiResult;
import cn.longzzai.service.BookDetailService;
import cn.longzzai.utils.BookDetailUpdateOnlineUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 书籍详情管理
 *
 * @author longcho
 * 2017-09-18-9:52
 */
@Controller
@Slf4j
@RequestMapping("/bookdetail")
public class BookDetailController {
    @Autowired
    private BookDetailService bookDetailService;

    @RequestMapping("/list")
    @ResponseBody
    public ZzaiResult list(@RequestParam(value = "majorCateName", defaultValue = "") String majorCateName,
                           @RequestParam(value = "minorCateName", defaultValue = "") String minorCateName,
                           @RequestParam(value = "serial", defaultValue = "true") Boolean serial,
                           @RequestParam(value = "author", defaultValue = "") String author,
                           @RequestParam(value = "ratio",defaultValue = "0") int ratio,
                           @RequestParam(value = "follower", defaultValue = "0") int follower,
                           @RequestParam(value = "isSerial", defaultValue = "0") int isSerial,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "20") int size) {
        if (size > 50) {
            size = 50;
        }
        try {
            return bookDetailService.findOrderByAuto(majorCateName, minorCateName, serial, author, ratio, follower, isSerial, page, size);
        } catch (Exception e) {
            e.printStackTrace();
            return ZzaiResult.build(500, "查询失败");
        }
    }

    @RequestMapping("/{bookId}")
    @ResponseBody
    public ZzaiResult one(@PathVariable String bookId) {
        return bookDetailService.findByBookId(bookId);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ZzaiResult add(BookDetail bookDetail) {
        return bookDetailService.add(bookDetail);
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    @ResponseBody
    public ZzaiResult add(List<BookDetail> bookDetailList) {
        if (bookDetailList != null && bookDetailList.size() > 0) {
            for (BookDetail bookDetail : bookDetailList) {
                try {
                    bookDetailService.add(bookDetail);
                } catch (Exception e) {
                    log.error("【书籍详情】 ，添加单个书籍出错 ，e={}", e);
                }
            }
        }
        return ZzaiResult.ok();
    }

    @RequestMapping("/updateAllOnline")
    @ResponseBody
    public ZzaiResult updateAllOnline(@RequestParam("majorCateName") String majorCateName) {
        String[] types = {"new" ,"hot" ,"reputation" ,"over"};
        for (String type : types) {
            List<BookDetailAtFindListVO> bookDetailAllListVOByUrl = BookDetailUpdateOnlineUtil.getBookDetailAllListVOByUrl(majorCateName ,type);
            List<BookDetail> bookDetailList = BookDetailUpdateOnlineUtil.CateAllListVOToBookDetail(bookDetailAllListVOByUrl);
            for (BookDetail bookDetail : bookDetailList) {
                try {
                    bookDetailService.add(bookDetail);
                } catch (Exception e) {
                    log.error("【书籍详情】，更新书籍controller ，e={}" ,e.getMessage());
                }
            }
        }
        return ZzaiResult.ok();
    }
}
