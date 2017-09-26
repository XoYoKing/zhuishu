package cn.longzzai.VO;

import lombok.Data;

/**
 * 在通过url查询分类下的bookdetail中间件
 *utl:例：http://novel.juhe.im/category-info?type=over&major=%E7%8E%84%E5%B9%BB&minor=&start=1&limit=20
 * @author longcho
 * 2017-09-18-14:12
 */
@Data
public class BookListAtFindListVO {
    private String code;
    private String message;
    private BookResultVO data;
}
