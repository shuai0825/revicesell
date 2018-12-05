package com.pmcc.revicesell.service.interfaces;/**
 * Created by 天地 on 2018/4/11.
 */


import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.pmcc.revicesell.dto.OrderDTO;

/**
 * @author 天地
 * @create 2018-04-11 17:26
 * @desc
 **/
public interface PayService {
    PayResponse create(OrderDTO orderDTO);
    PayResponse notify(String notifyData);
    RefundResponse refund(OrderDTO orderDTO);
}
