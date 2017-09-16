package cn.longzzai.service;

import cn.longzzai.exception.BookException;
import cn.longzzai.mapper.MajorCateMapper;
import cn.longzzai.myenum.ResultEnum;
import cn.longzzai.pojo.MajorCate;
import cn.longzzai.pojo.MajorCateExample;
import cn.longzzai.pojo.ZzaiResult;
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
public class MajorCateServiceImpl implements MajorCateService {
    @Autowired
    private MajorCateMapper majorCateMapper;

    @Override
    public ZzaiResult findById(int majorCateId) {
        MajorCate majorCate = majorCateMapper.selectByPrimaryKey(majorCateId);
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
        return null;
    }

    @Override
    public ZzaiResult save(String majorCateName) {
        MajorCate majorCate = (MajorCate) findByName(majorCateName).getData();
        if (majorCate != null) {
            throw new BookException(ResultEnum.BOOK_CATE_EXIST);
        }
        majorCateMapper.insert(majorCate);
        return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult update(MajorCate majorCate) {
        MajorCate majorCate1 = (MajorCate) findById(majorCate.getMajorCateId()).getData();
        if (majorCate == null) {
            throw new BookException(ResultEnum.BOOK_CATE_NOT_EXIST);
        }
        majorCateMapper.updateByPrimaryKey(majorCate);
        return ZzaiResult.ok();
    }

    @Override
    public ZzaiResult delete(int majorCateId) {
        majorCateMapper.deleteByPrimaryKey(majorCateId);
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
        majorCateMapper.deleteByExample(example);
        //TODO 删除所有副分类
        return ZzaiResult.ok();
    }
}
