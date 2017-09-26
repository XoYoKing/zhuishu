package cn.longzzai.utils;

import cn.longzzai.VO.*;
import cn.longzzai.pojo.BookDetail;
import cn.longzzai.pojo.MinorCate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;

/**
 * 网上更新书籍。慢
 *
 * @author longcho
 * 2017-09-18-14:21
 */
@Slf4j
public class BookDetailUpdateOnlineUtil {

    //static final String[] type = {"new"};

    //从网络中获取所有分类集合
    public static List<BookDetailAtFindListVO> getBookDetailAllListVOByUrl(String majorCateName ,String type) {
        List<BookDetailAtFindListVO> result = new ArrayList<>();
            int i = 0;
            int page = 0;

            do {
                String doGet = HttpClientUtil.doGet("http://api.zhuishushenqi.com/book/by-categories?gender=male&type=" + type + "&major=" + majorCateName + "&minor=&start=" + i + "&limit=30");
                if (doGet != null) {
                    BookResultVO bookResultVO = JsonUtils.jsonToPojo(doGet, BookResultVO.class);
                    if (i == 0) {
                        //第一次设置总页数
                        page = (int) (bookResultVO.getTotal() / 30.0 + 0.5);

                    }
                    List<BookDetailAtFindListVO> books = bookResultVO.getBooks();
                    if (books == null || books.size() <= 0) {
                        return result;
                    }
                    for (BookDetailAtFindListVO book : books) {
                        result.add(book);
                    }
                }
                page--;
                i = i + 20;
            } while ( page > 0);

        return result;
    }

    //将MajorCateListVO 转为  MajorCateList
    public static List<BookDetail> CateAllListVOToBookDetail(List<BookDetailAtFindListVO> bookDetailAtFindListVOList) {
        List<BookDetail> bookDetailList = new ArrayList<>();

        for (BookDetailAtFindListVO bookDetailAtFindListVO : bookDetailAtFindListVOList) {
            BookDetail bookDetail = new BookDetail();
            try {
                BeanUtils.copyProperties(bookDetailAtFindListVO, bookDetail);
            } catch (BeansException e) {
                log.error("【从网络中更新书籍】, e={}", e);
            }
            bookDetailList.add(bookDetail);
        }


        return bookDetailList;
    }
}
