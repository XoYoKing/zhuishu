package cn.longzzai.service;

import cn.longzzai.pojo.BookDetail;
import cn.longzzai.pojo.ZzaiResult;

import java.util.List;

/**
 * 书籍详情管理
 *
 * @author longcho
 * 2017-09-17-21:40
 */
public interface BookDetailService {

    ZzaiResult findByBookDetailId(int bookDetailId);

    ZzaiResult findByBookId(String bookId);

    //ZzaiResult findByBookAuthor(String author);

    //ZzaiResult findByMajorCateName(String majorCateName ,boolean serial);

   // ZzaiResult findByMinorCateName(String minorCateName ,boolean serial);

    /**
     *
     * @param majorCateName 主分类
     * @param minorCateName 副分类
     * @param serial 连载状态
     * @param isSerial  是否进行连载状态筛选 默认 0 ：不筛选
     * @param author 作者
     * @param ratio 留存率
     * @param follower 追书人数     注：其中 留存率 和 追书人数只能有一个
     *
     * @return 书籍排行
     */
    ZzaiResult findOrderByAuto(String majorCateName ,String minorCateName , boolean serial ,String author ,int ratio , int follower ,int isSerial ,int page ,int size);

   // ZzaiResult findByClickNumber(boolean serial);

   // ZzaiResult findByAddRack(boolean serial);

    ZzaiResult add(BookDetail bookDetail);

    //ZzaiResult add(List<BookDetail> bookDetailList);

    ZzaiResult update(BookDetail bookDetail);



}
