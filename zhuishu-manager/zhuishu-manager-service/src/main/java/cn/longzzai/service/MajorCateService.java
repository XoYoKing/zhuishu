package cn.longzzai.service;

import cn.longzzai.exception.BookException;
import cn.longzzai.pojo.MajorCate;
import cn.longzzai.pojo.ZzaiResult;

import java.util.List;

/**
 * 书籍主分类
 *
 * @author longcho
 * 2017-09-16-10:40
 */
public interface MajorCateService {

    ZzaiResult findById(int majorCateId) throws BookException;

    ZzaiResult findAll();

    ZzaiResult findByName(String majorCateName) throws BookException;

    //默认0 male   1 female
    ZzaiResult save(String majorCateName ,int sex) throws BookException;

    ZzaiResult update(MajorCate majorCate) throws BookException;

    ZzaiResult delete(int majorCateId) throws BookException;

    ZzaiResult delete(String majorCateName) throws BookException;

    ZzaiResult updateByList(List<MajorCate> majorCateList);
}
