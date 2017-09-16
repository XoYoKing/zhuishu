package cn.longzzai.myenum;

import lombok.Getter;

/**
 * 返回错误码枚举
 *
 * @author longcho
 * 2017-09-16-15:50
 */
@Getter
public enum ResultEnum implements CodeEnum{
    BOOK_CATE_EXIST( 1 , "该书籍分类已存在"),
    BOOK_CATE_NOT_EXIST( 1 , "书籍分类不存在") ,
    ;
    private int code;
    private String msg;
    @Override
    public int getCode() {
        return code;
    }

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
