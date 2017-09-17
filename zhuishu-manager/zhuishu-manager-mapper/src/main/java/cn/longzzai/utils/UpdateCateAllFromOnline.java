package cn.longzzai.utils;

import cn.longzzai.VO.CateAllListVO;
import cn.longzzai.VO.CateVO;
import cn.longzzai.VO.MajorCateListVO;
import cn.longzzai.VO.MajorCateVO;
import cn.longzzai.pojo.MajorCate;
import cn.longzzai.pojo.MinorCate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longcho
 * 2017-09-17-15:31
 */
public class UpdateCateAllFromOnline {

    //从网络中获取所有分类集合
    public static CateAllListVO getCateAllListVOByUrl(){
        String doGet = HttpClientUtil.doGet("http://api.zhuishushenqi.com/cats/lv2");
        if (doGet != null){
            CateAllListVO cateAllListVO = JsonUtils.jsonToPojo(doGet, CateAllListVO.class);
            return cateAllListVO;
        }
        return null;
    }

    //将MajorCateListVO 转为  MajorCateList
    public  static List<MinorCate> CateAllListVOToMinorCate(CateAllListVO cateAllListVO){
        List<MinorCate> minorCateList = new ArrayList<>();

        for (CateVO cateVO : cateAllListVO.getMale()) {
            for (String s : cateVO.getMins()) {
                MinorCate minorCate = new MinorCate();
                minorCate.setMinorCateName(s);
                minorCate.setMajorCateName(cateVO.getMajor());
                minorCateList.add(minorCate);
            }
        }
        for (CateVO cateVO : cateAllListVO.getFemale()) {
            for (String s : cateVO.getMins()) {
                MinorCate minorCate = new MinorCate();
                minorCate.setMinorCateName(s);
                minorCate.setMajorCateName(cateVO.getMajor());
                minorCateList.add(minorCate);
            }
        }

        return minorCateList;
    }
}
