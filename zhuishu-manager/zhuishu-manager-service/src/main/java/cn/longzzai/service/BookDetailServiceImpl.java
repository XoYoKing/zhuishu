package cn.longzzai.service;

import cn.longzzai.exception.BookException;
import cn.longzzai.mapper.BookDetailMapper;
import cn.longzzai.myenum.ResultEnum;
import cn.longzzai.pojo.BookDetail;
import cn.longzzai.pojo.BookDetailExample;
import cn.longzzai.pojo.PageListResult;
import cn.longzzai.pojo.ZzaiResult;
import cn.longzzai.utils.CopyObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 书籍详情管理
 *
 * @author longcho
 * 2017-09-18-8:04
 */
@Service
@Slf4j
public class BookDetailServiceImpl implements BookDetailService {
    @Autowired
    private BookDetailMapper bookDetailMapper;

    @Override
    public ZzaiResult findByBookDetailId(int bookDetailId) {
        BookDetail bookDetail = bookDetailMapper.selectByPrimaryKey((long) bookDetailId);
        if (bookDetail == null) {
            throw new BookException(ResultEnum.BOOK_NOT_FIND);
        }
        return ZzaiResult.ok(bookDetail);
    }

    @Override
    public ZzaiResult findByBookId(String bookId) {
        BookDetailExample example = new BookDetailExample();
        example.createCriteria().andBookIdEqualTo(bookId);
        List<BookDetail> bookDetailList = bookDetailMapper.selectByExample(example);
        if (bookDetailList != null) {
            return ZzaiResult.ok(bookDetailList.get(0));
        }
        throw new BookException(ResultEnum.BOOK_NOT_FIND);
    }


    /**
     * @param majorCateName 主分类
     * @param minorCateName 副分类
     * @param serial        连载状态
     * @param isSerial      是否进行连载状态筛选 默认 0 ：不筛选
     * @param author        作者
     * @param ratio         留存率
     * @param follower      追书人数     注：其中 留存率 和 追书人数只能有一个 为1 才能参加赛选
     * @return 书籍排行
     */
    @Override
    public ZzaiResult findOrderByAuto(String majorCateName, String minorCateName, boolean serial, String author, int ratio, int follower, int isSerial, int page, int size) {
        BookDetailExample example = new BookDetailExample();
        BookDetailExample.Criteria criteria = example.createCriteria();
        if (majorCateName != null && !StringUtils.isEmpty(majorCateName)) {
            criteria.andMajorCateNameEqualTo(majorCateName);

        }
        if (minorCateName != null && !StringUtils.isEmpty(minorCateName)) {
            criteria.andMinorCateNameEqualTo(minorCateName);

        }
        if (isSerial == 1) {
            criteria.andIsSerialEqualTo(serial);
        }
        if (author != null && !StringUtils.isEmpty(author)) {
            criteria.andBookAuthorEqualTo(author);
        }
        if (follower == 1) {
            example.setOrderByClause("lately_follower desc");
        } else {
            example.setOrderByClause("retention_ratio desc");
        }

        //分页设置
        page = page < 1 ? 1 : page;
        PageHelper.startPage(page, size);
        List<BookDetail> bookDetailList = bookDetailMapper.selectByExample(example);
        //取出分页结果
        PageInfo<BookDetail> pageInfo = new PageInfo<>(bookDetailList);
        PageListResult pageListResult = new PageListResult();
        pageListResult.setRows(pageInfo.getList());
        pageListResult.setTotal(pageInfo.getTotal());
        return ZzaiResult.ok(pageListResult);
    }

    @Override
    public ZzaiResult add(BookDetail bookDetail) {
        int insert = 1;
        try {
            insert = bookDetailMapper.insert(bookDetail);
            log.info("【书籍详情】 ，成功添加单个书籍 ，bookname ={}", bookDetail.getBookTitle());
        } catch (Exception e) {
            log.error("【书籍详情】 ，添加单个书籍出错 ，下一步更新书籍，bookname = {} bookid={}",bookDetail.getBookTitle() ,bookDetail.getBookId());
        }
        try {
            findByBookId(bookDetail.getBookId());
        } catch (BookException e) {
            log.error("【书籍详情】 ，查询单个书籍出错 ，e={}", e.getMessage());
            update(bookDetail);
            log.info("【书籍详情】 ，成功更新单个书籍 ，bookname ={}", bookDetail.getBookTitle());
        }

        return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult update(BookDetail source) {
        try {
            BookDetail target = (BookDetail) findByBookId(source.getBookId()).getData();
            //复制属性
            String[] propertiesAsNull = CopyObjectUtil.getPropertiesAsNull(source);
            //不复制bookDetailId
            String[] strings = CopyObjectUtil.addString(propertiesAsNull, "bookDetailId");
            CopyObjectUtil.copyPropertiesNotNull(source, target, strings);
        } catch (Exception e) {
            log.error("【书籍详情】，更新书籍发生错误 e= {}", e);
            throw new BookException(ResultEnum.BOOK_UPDATE_FAILD);
        }
        return ZzaiResult.ok();
    }
}
