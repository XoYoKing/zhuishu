package cn.longzzai.service;

import cn.longzzai.pojo.BookDetail;
import cn.longzzai.pojo.MajorCate;
import cn.longzzai.pojo.ZzaiResult;

/**
 * 书籍主分类
 *
 * @author longcho
 * 2017-09-16-10:40
 */
public interface MajorCateService {

    ZzaiResult findById(int majorCateId);

    ZzaiResult findAll();

    ZzaiResult findByName(String majorCateName);

    ZzaiResult save(String majorCateName);

    ZzaiResult update(MajorCate majorCate);

    ZzaiResult delete(int majorCateId);

    ZzaiResult delete(String majorCateName);
}
