package com.pmcc.revicesell.controller;/**
 * Created by 天地 on 2018/4/12.
 */

import com.pmcc.revicesell.dto.OrderDTO;
import com.pmcc.revicesell.enums.ResultEnum;
import com.pmcc.revicesell.service.OrderServiceImpl;
import com.pmcc.revicesell.service.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author 天地
 * @create 2018-04-12 14:27
 * @desc买家订单
 **/
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private WebSocket webSocket;

    /**
     * 订单列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size, Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        //orderDTOPage.getTotalPages()
        return new ModelAndView("order/list", map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId, Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (Exception e) {
            log.error("[卖家段取消订单] 发生异常"+e);
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId, Map<String,Object> map){
        OrderDTO orderDTO=new OrderDTO();
        try {
            orderDTO= orderService.findOne(orderId);
        } catch (Exception e) {
            log.error("[卖家订单查看详情] 发生异常{}"+e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    /**
     * 结束订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId, Map<String,Object> map){
        OrderDTO orderDTO=new OrderDTO();
        try {
            orderDTO= orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (Exception e) {
            log.error("[卖家确认订单] 发生异常{}"+e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
