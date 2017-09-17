package cn.longzzai.service;

import cn.longzzai.exception.BookException;
import cn.longzzai.pojo.MajorCate;
import cn.longzzai.pojo.MinorCate;
import cn.longzzai.pojo.ZzaiResult;

import java.util.List;

/**
 * 书籍副分类
 *
 * @author longcho
 * 2017-09-16-10:40
 */
public interface MinorCateService {

    ZzaiResult findById(int minorCateId) throws BookException;

    ZzaiResult findAll();

    /**
     * 查询主分类下所有副分类
     * @param minorCateName
     * @param majorCateName 可以为null
     * @return
     */
    ZzaiResult findByName(String minorCateName ,String majorCateName);

    ZzaiResult save(String minorCateName ,String majorCateName);
    
    ZzaiResult update(MinorCate minorCate) throws BookException;

    ZzaiResult delete(int minorCateId) throws BookException;

    ZzaiResult delete(String minorCateName) throws BookException;

    ZzaiResult delete(List<MinorCate> minorCateList) throws BookException;

    ZzaiResult updateByList(List<MinorCate> minorCateList);

    ZzaiResult findByMajorName(String majorCateName);
}
