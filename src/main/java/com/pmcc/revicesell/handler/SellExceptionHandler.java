package com.pmcc.revicesell.handler;




import com.pmcc.revicesell.config.ProjectUrlConfig;
import com.pmcc.revicesell.exception.ResponseBankException;
import com.pmcc.revicesell.exception.SellException;
import com.pmcc.revicesell.exception.SellerAuthorizeException;
import com.pmcc.revicesell.utils.ResultVoUtil;
import com.pmcc.revicesell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 廖师兄
 * 2017-07-30 17:44
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常，跳转到登录页
    //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
        .concat(projectUrlConfig.getWechatOpenAuthorize())
        .concat("/sell/wechat/authorize")
        .concat("?returnUrl=")
        .concat(projectUrlConfig.getSell())
        .concat("/sell/seller/login"));
    }
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVoUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)    //触发该异常，返回非403
    public ResultVO handlerResponseBankException(ResponseBankException e){
        return ResultVoUtil.error(10,e.getMessage());
    }
}
