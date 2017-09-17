package cn.longzzai.service;

import cn.longzzai.exception.BookException;
import cn.longzzai.mapper.MajorCateMapper;
import cn.longzzai.myenum.ResultEnum;
import cn.longzzai.pojo.MajorCate;
import cn.longzzai.pojo.MajorCateExample;
import cn.longzzai.pojo.ZzaiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 书籍主分类
 *
 * @author longcho
 * 2017-09-16-10:51
 */
@Service
@Slf4j
public class MajorCateServiceImpl implements MajorCateService {
    @Autowired
    private MajorCateMapper majorCateMapper;

    @Override
    public ZzaiResult findById(int majorCateId) {
            MajorCate majorCate = majorCateMapper.selectByPrimaryKey(majorCateId);
            if (majorCate == null){
                throw new BookException(ResultEnum.BOOK_CATE_NOT_FIND);
            }
            return ZzaiResult.ok(majorCate);
    }

    @Override
    public ZzaiResult findByName(String majorCateName) {
        MajorCateExample example = new MajorCateExample();
        example.createCriteria().andMajorCateNameEqualTo(majorCateName);
        List<MajorCate> majorCateList = majorCateMapper.selectByExample(example);
        if (majorCateList != null && majorCateList.size() > 0) {
            return ZzaiResult.ok(majorCateList.get(0));
        }
        throw new BookException(ResultEnum.BOOK_CATE_NOT_FIND);
    }

    @Override
    public ZzaiResult updateByList(List<MajorCate> majorCateList) {
        if (majorCateList.size() > 0){
            for (MajorCate majorCate : majorCateList) {
                try {
                    save(majorCate.getMajorCateName() ,majorCate.getMajorCateSex());
                } catch (Exception e) {
                   log.error("【书籍主分类】，批量更新出问题，e={}",e);
                }
            }
        }
        return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult save(String majorCateName , int sex) {
        MajorCate majorCate = null;
        try {
            majorCate = (MajorCate) findByName(majorCateName).getData();

        } catch (BookException e) {
            log.info("【添加主分类】 ，e={}" ,e.getMessage());
            majorCate = new MajorCate();
            majorCate.setMajorCateName(majorCateName);
            majorCate.setMajorCateSex((byte)sex);
            majorCateMapper.insert(majorCate);
            return ZzaiResult.ok();

        }
        throw new BookException(ResultEnum.BOOK_CATE_EXIST);
    }

    @Override
    public ZzaiResult update(MajorCate majorCate) {
        try {
            MajorCate majorCate1 = (MajorCate) findById(majorCate.getMajorCateId()).getData();
            majorCateMapper.updateByPrimaryKey(majorCate);
        } catch (Exception e) {
            log.error("【书籍主分类】 更新主分类失败 ，e={}" ,e);
            throw new BookException(ResultEnum.BOOK_UPDATE_FAILD);
        }
        return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult delete(int majorCateId) {
            int i = majorCateMapper.deleteByPrimaryKey(majorCateId);
            if (i != 1){
                throw new BookException(ResultEnum.BOOK_CATE_DELETE_FAILD);
            }
            //TODO 删除所有副分类
            return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult findAll() {
        MajorCateExample example =new MajorCateExample();

        example.createCriteria().andMajorCateIdIsNotNull();
        List<MajorCate> majorCateList = majorCateMapper.selectByExample(example);
        return ZzaiResult.ok(majorCateList);
    }

    @Override
    public ZzaiResult delete(String majorCateName) {
        MajorCateExample example =new MajorCateExample();
        example.createCriteria().andMajorCateNameEqualTo(majorCateName);
            int i = majorCateMapper.deleteByExample(example);
            if (i ==0){
                throw new BookException(ResultEnum.BOOK_CATE_DELETE_FAILD);
            }
            //TODO 删除所有副分类
            return ZzaiResult.ok();
    }
}
