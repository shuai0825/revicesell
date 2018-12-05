package com.pmcc.revicesell.service.interfaces;/**
 * Created by 天地 on 2018/3/29.
 */

import com.pmcc.revicesell.dto.OrderDTO;

/**
 * @author 天地
 * @create 2018-03-29 18:44
 * @desc
 **/
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
