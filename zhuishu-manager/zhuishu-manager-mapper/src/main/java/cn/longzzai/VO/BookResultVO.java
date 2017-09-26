package cn.longzzai.VO;

import lombok.Data;

import java.util.List;

/**
 * @author longcho
 * 2017-09-18-14:14
 */
@Data
public class BookResultVO {
    private int total;
    private List<BookDetailAtFindListVO> books;
    private String ok;
}
