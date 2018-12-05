package com.pmcc.revicesell.utils;

import com.pmcc.revicesell.vo.ResultVO;

/**
 * @author sky
 * @create 2018-06-17 22:49
 * @desc
 **/
public class ResultVoUtil {
    public static ResultVO success(Object object){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success(){
        return success(null);
    }
    public static ResultVO error(Integer code,String msg){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(msg);
        return resultVO;
    }
}
