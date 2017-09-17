package cn.longzzai.utils;

import cn.longzzai.VO.MajorCateListVO;
import cn.longzzai.VO.MajorCateVO;
import cn.longzzai.pojo.MajorCate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longcho
 * 2017-09-17-15:31
 */
public class UpdateMajorFromOnline {

    //从网络中获取主分类集合
    public static MajorCateListVO getMajorListVOByUrl(){
        String doGet = HttpClientUtil.doGet("http://api.zhuishushenqi.com/cats");
        if (doGet != null){
            MajorCateListVO majorCateListVO = JsonUtils.jsonToPojo(doGet, MajorCateListVO.class);
            return majorCateListVO;
        }
        return null;
    }

    //将MajorCateListVO 转为  MajorCateList
    public  static List<MajorCate> majorCateListVOToMajorCate(MajorCateListVO majorCateListVO){
        List<MajorCate> majorCateList = new ArrayList<>();
        for (MajorCateVO majorCateVO : majorCateListVO.getMale()) {
            MajorCate majorCate = new MajorCate();
            majorCate.setMajorCateName(majorCateVO.getName());
            //同一设置性别
            majorCate.setMajorCateSex((byte)0);
            majorCateList.add(majorCate);
        }
        for (MajorCateVO majorCateVO : majorCateListVO.getFemale()) {
            MajorCate majorCate = new MajorCate();
            majorCate.setMajorCateName(majorCateVO.getName());
            //同一设置性别
            majorCate.setMajorCateSex((byte)1);
            majorCateList.add(majorCate);
        }
        return majorCateList;
    }
}
