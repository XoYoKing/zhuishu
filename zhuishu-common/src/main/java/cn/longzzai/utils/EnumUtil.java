package cn.longzzai.utils;

import cn.longzzai.myenum.CodeEnum;

/**
 * enum管理
 *
 * @author longcho
 * 2017-09-16-15:55
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getEnumByCode(int code , Class<T> enumClass){
        for (T t : enumClass.getEnumConstants()) {
            if (t.getCode() == code){
                return t;
            }
        }
        return null;
    }

}
