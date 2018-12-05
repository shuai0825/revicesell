package com.pmcc.revicesell.controller;/**
 * Created by 天地 on 2018/4/11.
 */

import com.pmcc.revicesell.config.ProjectUrlConfig;
import com.pmcc.revicesell.config.WechatMpConfig;
import com.pmcc.revicesell.enums.ResultEnum;
import com.pmcc.revicesell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * @author 天地
 * @create 2018-04-11 15:08
 * @desc
 **/
//RestController,返回json的
//Controller，跳转
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WechatMpConfig wechetMpConfig;

    @Autowired
    private WxMpService wxOpenService;

      @Autowired
     private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //1.配置
        //2.调用方法
        String url = projectUrlConfig.getWechatMpAuthorize()+"/sell/wechat/userInfo";
        //    String url = "http://127.0.0.1:8080/sell/wechat/userInfo";
        String redirectUrl = wechetMpConfig.wxMpService().oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, returnUrl);
        //SNSAPI_USERINFO,需要授权页面，获取个人信息
        //SNSAPI_BASE，不需要授权页面
        //参数3state，重定向后会带上state参数
        log.info("微信授权获取code" + redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        WxMpUser wxMpUser=null;
        returnUrl=projectUrlConfig.getWechatMpAuthorize()+"/sell/seller/login";
        try {
            wxMpOAuth2AccessToken = wechetMpConfig.wxMpService().oauth2getAccessToken(code);
             wxMpUser = wechetMpConfig.wxMpService().oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        } catch (WxErrorException e) {
            log.info("微信授权{}" + e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
         // String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";
        String url = projectUrlConfig.getWechatOpenAuthorize()+"/sell/wechat/qrUserInfo";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        log.info("wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
        String openId = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
