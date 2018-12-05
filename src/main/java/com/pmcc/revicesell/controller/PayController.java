package com.pmcc.revicesell.controller;/**
 * Created by 天地 on 2018/4/11.
 */


import com.lly835.bestpay.model.PayResponse;
import com.pmcc.revicesell.dto.OrderDTO;
import com.pmcc.revicesell.enums.ResultEnum;
import com.pmcc.revicesell.exception.SellException;
import com.pmcc.revicesell.service.OrderServiceImpl;

import com.pmcc.revicesell.service.PayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author 天地
 * @create 2018-04-11 17:20
 * @desc
 **/
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private PayServiceImpl payService;
    //ModelAndView,模板引擎
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId, @RequestParam("returnUrl") String returnUrl, Map<String,Object> map) {
        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);

        //通知微信，支付成功
        return  new ModelAndView("pay/success");
    }
}
