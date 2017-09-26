package cn.longzzai.pojo;

import lombok.Data;

import java.util.List;

/**
 * 分页的返回结果
 *
 * @author longcho
 * 2017-09-18-10:30
 */
@Data
public class PageListResult {
    private long total;
    private List<?> rows;

}
