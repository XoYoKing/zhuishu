package cn.longzzai.exception;

import cn.longzzai.myenum.ResultEnum;

/**
 * 书籍方面的异常
 *
 * @author longcho
 * 2017-09-16-15:47
 */
public class BookException extends RuntimeException{
    private int code;
    public BookException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
    public BookException(int code ,String msg) {
        super(msg);
        this.code = code;
    }
}
