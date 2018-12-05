package com.pmcc.revicesell.service;/**
 * Created by 天地 on 2018/3/29.
 */

import com.pmcc.revicesell.dto.OrderDTO;
import com.pmcc.revicesell.enums.ResultEnum;
import com.pmcc.revicesell.exception.SellException;
import com.pmcc.revicesell.service.interfaces.BuyerService;
import com.pmcc.revicesell.service.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 天地
 * @create 2018-03-29 18:46
 * @desc
 **/
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwenr(openid, orderId);
    }



    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwenr(openid, orderId);
        if (orderDTO==null) {
            log.error("[取消订单] 查不到该订单，orderoid={}",orderId);
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwenr(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null){
            return null;
        }
        //判断该用户下是否存在该订单
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("[查询订单列表] 订单的openid不一致");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

}
