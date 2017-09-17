package cn.longzzai.service;

import cn.longzzai.exception.BookException;
import cn.longzzai.mapper.MinorCateMapper;
import cn.longzzai.myenum.ResultEnum;
import cn.longzzai.pojo.MinorCate;
import cn.longzzai.pojo.MinorCateExample;
import cn.longzzai.pojo.ZzaiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 书籍副分类
 *
 * @author longcho
 * 2017-09-16-10:51
 */
@Service
@Slf4j
public class MinorCateServiceImpl implements MinorCateService {
    @Autowired
    private MinorCateMapper minorCateMapper;
    @Autowired
    private MajorCateService majorCateService;

    @Override
    public ZzaiResult findById(int minorCateId) {
        MinorCate minorCate = minorCateMapper.selectByPrimaryKey(minorCateId);
        if (minorCate == null) {
            throw new BookException(ResultEnum.BOOK_CATE_NOT_FIND);
        }
        return ZzaiResult.ok(minorCate);
    }

    /**
     * 查询主分类下所有副分类
     * @param minorCateName
     * @param majorCateName 可为null
     * @return
     */
    @Override
    public ZzaiResult findByName(String minorCateName ,String majorCateName) {
        MinorCateExample example = new MinorCateExample();
        example.createCriteria().andMinorCateNameEqualTo(minorCateName);
        List<MinorCate> minorCateList = minorCateMapper.selectByExample(example);
        if (minorCateList != null && minorCateList.size() > 0 ) {
            if (majorCateName == null){
                return ZzaiResult.ok(minorCateList.get(0));
            }
            for (MinorCate minorCate : minorCateList) {
                if (minorCate.getMajorCateName().equals(majorCateName)){
                    return ZzaiResult.ok(minorCate);
                }
            }
        }
        throw new BookException(ResultEnum.BOOK_CATE_NOT_FIND);
    }

    @Override
    public ZzaiResult updateByList(List<MinorCate> minorCateList) {
        if (minorCateList.size() > 0) {
            for (MinorCate minorCate : minorCateList) {
                try {
                    save(minorCate.getMinorCateName(), minorCate.getMajorCateName());
                } catch (Exception e) {
                    log.error("【书籍副分类】，批量更新出问题，e={} minorName={} , majorName", e , minorCate.getMinorCateName() ,minorCate.getMajorCateName());
                }
            }
        }
        return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult save(String minorCateName, String majorCateName) {
        MinorCate minorCate = null;
        try {
            minorCate = (MinorCate) findByName(minorCateName , majorCateName).getData();
        } catch (Exception e) {
            log.info("【添加副分类】 ，1 e={}", e.getMessage());
            if (minorCate == null){
                minorCate = new MinorCate();
                minorCate.setMinorCateName(minorCateName);
                if (majorCateName != null) {
                    minorCate.setMajorCateName(majorCateName);
                }
                minorCateMapper.insert(minorCate);
                return ZzaiResult.ok();
            }

        }
        throw new BookException(ResultEnum.BOOK_CATE_EXIST);

    }

    @Override
    public ZzaiResult update(MinorCate minorCate) {
        try {
            MinorCate minorCate1 = (MinorCate) findById(minorCate.getMinorCateId()).getData();
            minorCateMapper.updateByPrimaryKey(minorCate);
        } catch (Exception e) {
            log.error("【书籍副分类】 更新副分类失败 ，e={}", e.getMessage());
            throw new BookException(ResultEnum.BOOK_UPDATE_FAILD);
        }
        return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult delete(int minorCateId) {
        int i = minorCateMapper.deleteByPrimaryKey(minorCateId);
        if (i != 1) {
            throw new BookException(ResultEnum.BOOK_CATE_DELETE_FAILD);
        }
        return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult delete(String minorCateName) {
        MinorCateExample example = new MinorCateExample();
        example.createCriteria().andMinorCateNameEqualTo(minorCateName);
        int i = minorCateMapper.deleteByExample(example);
        if (i == 0) {
            throw new BookException(ResultEnum.BOOK_CATE_DELETE_FAILD);
        }
        return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult delete(List<MinorCate> minorCateList) throws BookException {
        try {
            for (MinorCate minorCate : minorCateList) {
                if (minorCate.getMinorCateId() != null) {
                    delete(minorCate.getMinorCateId());
                } else {
                    delete(minorCate.getMajorCateName());
                }
            }
        } catch (Exception e) {
            log.error("【书籍副分类】 批量删除副分类失败 ，e={}", e.getMessage());
            throw new BookException(ResultEnum.BOOK_CATE_DELETE_FAILD);
        }
        return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult findAll() {
        MinorCateExample example = new MinorCateExample();
        example.createCriteria().andMinorCateIdIsNotNull();
        List<MinorCate> minorCateList = minorCateMapper.selectByExample(example);
        return ZzaiResult.ok(minorCateList);
    }

    @Override
    public ZzaiResult findByMajorName(String majorCateName) {
        MinorCateExample example = new MinorCateExample();
        example.createCriteria().andMajorCateNameEqualTo(majorCateName);
        List<MinorCate> minorCateList = minorCateMapper.selectByExample(example);
        return ZzaiResult.ok(minorCateList);
    }
}
