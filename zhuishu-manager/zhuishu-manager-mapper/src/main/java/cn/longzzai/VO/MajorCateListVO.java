package cn.longzzai.VO;

import lombok.Data;

import java.util.List;

/**
 * 更新书籍分类需要的中间件
 *
 * @author longcho
 * 2017-09-17-15:27
 */
@Data
public class MajorCateListVO {
    private List<MajorCateVO> female;
    private List<MajorCateVO> male;
    private boolean ok;
}
