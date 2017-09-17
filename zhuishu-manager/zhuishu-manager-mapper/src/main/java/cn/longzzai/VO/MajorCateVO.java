package cn.longzzai.VO;

import lombok.Data;

/**
 * 更新书籍分类需要的中间件
 * url:http://api.zhuishushenqi.com/cats
 *
 * @author longcho
 * 2017-09-17-15:23
 */
@Data
public class MajorCateVO {
    private String name;
    private int bookCount;
}
