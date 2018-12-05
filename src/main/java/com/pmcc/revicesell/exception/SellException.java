package com.pmcc.revicesell.exception;

import com.pmcc.revicesell.enums.ResultEnum;
import lombok.Getter;

/**
 * @author sky
 * @create 2018-06-19 9:26
 * @desc
 **/
@Getter
public class SellException  extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public SellException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
