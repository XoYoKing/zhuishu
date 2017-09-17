package cn.longzzai.VO;

import lombok.Data;

import java.util.List;

/**从url中映射的中间件
 * url:http://api.zhuishushenqi.com/cats/lv2
 * @author longcho
 * 2017-09-17-17:06
 */
@Data
public class CateAllListVO {
    private List<CateVO> male;
    private List<CateVO> female;
    private List<CateVO> picture;
    private List<CateVO> press;
    private boolean ok;
}
