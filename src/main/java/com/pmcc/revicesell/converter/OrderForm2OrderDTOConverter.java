package com.pmcc.revicesell.converter;/**
 * Created by 天地 on 2018/3/29.
 */

import com.alibaba.fastjson.JSON;
import com.pmcc.revicesell.dataobject.OrderDetail;
import com.pmcc.revicesell.dto.OrderDTO;
import com.pmcc.revicesell.enums.ResultEnum;
import com.pmcc.revicesell.exception.SellException;
import com.pmcc.revicesell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 天地
 * @create 2018-03-29 15:47
 * @desc
 **/
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        try {
            orderDetailList=JSON.parseArray(orderForm.getItems(),OrderDetail.class);
        }catch (Exception e){
            log.error("[对象转化] 错误 string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
       orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
