package cn.longzzai.service;

import cn.longzzai.mapper.MajorCateMapper;
import cn.longzzai.pojo.BookDetail;
import cn.longzzai.pojo.MajorCate;
import cn.longzzai.pojo.MajorCateExample;
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
    public MajorCate findById(int majorCateId) {
        return majorCateMapper.selectByPrimaryKey(majorCateId);
    }

    @Override
    public MajorCate findByName(String majorCateName) {
        MajorCateExample example = new MajorCateExample();
        example.createCriteria().andMajorCateNameEqualTo(majorCateName);
        List<MajorCate> majorCateList = majorCateMapper.selectByExample(example);
        if (majorCateList !=null && majorCateList.size() > 0)
        {
            return majorCateList.get(0);
        }
        return null;
    }

    @Override
    public boolean save(String majorCateName) {
        MajorCate majorCate = findByName(majorCateName);
        if (majorCate != null){
            return false;
        }
        majorCateMapper.insert(majorCate);
        return true;
    }

    @Override
    public boolean update(MajorCate majorCate) {
        MajorCate majorCate1 = findById(majorCate.getMajorCateId());
        if (majorCate == null){
            return false;
        }
        majorCateMapper.updateByPrimaryKey(majorCate);
        return true;
    }

    @Override
    public void delete(int majorCateId) {
        majorCateMapper.deleteByPrimaryKey(majorCateId);

    }

    @Override
    public MajorCate delete(String majorCateName) {
        return null;
    }
}
