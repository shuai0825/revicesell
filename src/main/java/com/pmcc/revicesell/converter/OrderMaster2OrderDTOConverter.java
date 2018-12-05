package com.pmcc.revicesell.converter;/**
 * Created by 天地 on 2018/3/29.
 */

import com.pmcc.revicesell.dataobject.OrderMaster;
import com.pmcc.revicesell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 天地
 * @create 2018-03-29 11:49
 * @desc
 **/
public class OrderMaster2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
