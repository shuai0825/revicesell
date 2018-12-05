package com.pmcc.revicesell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sky
 * @create 2018-06-15 15:14
 * @desc http请求放回的最外层对象，该文件夹下用于放回前端的对象
 **/
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -5672823067725601547L;
    private Integer code;
    private String msg;
    private T data;
}
