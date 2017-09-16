package cn.longzzai.service;

import cn.longzzai.pojo.BookDetail;
import cn.longzzai.pojo.MajorCate;

/**
 * 书籍主分类
 *
 * @author longcho
 * 2017-09-16-10:40
 */
public interface MajorCateService {

    MajorCate findById(int majorCateId);

    MajorCate findByName(String majorCateName);

    boolean save(String majorCateName);

    boolean update(MajorCate majorCate);

    MajorCate delete(int majorCateId);

    MajorCate delete(String majorCateName);
}
