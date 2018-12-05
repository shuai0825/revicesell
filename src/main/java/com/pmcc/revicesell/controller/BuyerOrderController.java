package com.pmcc.revicesell.controller;/**
 * Created by 天地 on 2018/3/29.
 */

import com.pmcc.revicesell.converter.OrderForm2OrderDTOConverter;
import com.pmcc.revicesell.dto.OrderDTO;
import com.pmcc.revicesell.enums.ResultEnum;
import com.pmcc.revicesell.exception.SellException;
import com.pmcc.revicesell.form.OrderForm;
import com.pmcc.revicesell.service.BuyerServiceImpl;
import com.pmcc.revicesell.service.OrderServiceImpl;
import com.pmcc.revicesell.utils.ResultVoUtil;
import com.pmcc.revicesell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 天地
 * @create 2018-03-29 15:25
 * @desc 订单
 **/
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderServiceImpl orderService;
   @Autowired
   private BuyerServiceImpl buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单] 参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVoUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("[查询订单列表] 参数不正确，openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest =  PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
        return ResultVoUtil.success(orderDTOPage.getContent());
    }

    //订单详情

    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderOne = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(orderOne);

    }

    //取消订单
    @GetMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid,orderId);
        return ResultVoUtil.success();
    }
}
