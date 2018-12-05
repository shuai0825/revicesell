package com.pmcc.revicesell.service;/**
 * Created by 天地 on 2018/4/11.
 */

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.utils.JsonUtil;
import com.pmcc.revicesell.config.WechatPayConfig;
import com.pmcc.revicesell.dto.OrderDTO;
import com.pmcc.revicesell.enums.ResultEnum;
import com.pmcc.revicesell.exception.SellException;
import com.pmcc.revicesell.service.interfaces.PayService;


import com.pmcc.revicesell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 天地
 * @create 2018-04-11 17:27
 * @desc
 **/
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private WechatPayConfig wechatPayConfig;
    @Autowired
    private OrderServiceImpl orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName("微信点餐订单");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信支付] request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = wechatPayConfig.bestPayService().pay(payRequest);
        log.info("[微信支付] payResponse={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名,sdk
        //2.支付状态,sdk
        //3.支付金额
        //4.支付人（下单人==支付人）

        PayResponse payResponse = wechatPayConfig.bestPayService().asyncNotify(notifyData);
        log.info("[微信支付] 异步通知",JsonUtil.toJson(payResponse));
        //查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        //判断订单是否存在
        if(orderDTO==null){
            log.error("[微信支付]异步通知异常，订单不存在，orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //判断金额是否一致
        if(!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
            log.error("[微信支付]异步通知异常，订单金额不一致，orderId={}，微信通知金额={}，系统金额={}",payResponse.getOrderId()
            ,payResponse.getOrderAmount(),orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WECHAT_NOTIFY_MONEY_VERIFY);
        }
        //修改支付订单状态
        orderService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 退款
     * @param orderDTO
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信退款]，request={}",JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = wechatPayConfig.bestPayService().refund(refundRequest);
        log.info("[微信退款]，RefundResponse={}",JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
