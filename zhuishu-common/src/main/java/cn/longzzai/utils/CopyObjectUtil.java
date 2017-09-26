package cn.longzzai.utils;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * copy对象属性工具
 *
 * @author longcho
 * 2017-09-18-8:50
 */
public class CopyObjectUtil {

    /**
     * 复制属性到目标对象 属性名相同复制
     * @param source 源对象
     * @param target 目标对象
     * @param notCopy 不复制属性
     */
    public static  void copyPropertiesNotNull(Object source ,Object target ,String[] notCopy){
            BeanUtils.copyProperties(source ,target ,notCopy);

    }

    //获取为对象中属性为null的字符串集合
    public static String[] getPropertiesAsNull(Object source){
        BeanWrapper wrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(source.getClass());
        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor sourcePd : sourcePds) {
                if (wrapper.getPropertyValue(sourcePd.getName()) == null){
                    emptyNames.add(sourcePd.getName());
                }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static String[] addString(String[] source , String add){
       String[] result = new String[source.length + 1];
        for (int i = 0; i < source.length; i++) {
            result[i] = source[i];
        }
        result[source.length] = add ;
        return result;
    }
}
