package com.pmcc.revicesell.dto;/**
 * Created by 天地 on 2018/3/27.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.pmcc.revicesell.dataobject.OrderDetail;
import com.pmcc.revicesell.enums.OrderStatusEnum;
import com.pmcc.revicesell.enums.PayStatusEnum;
import com.pmcc.revicesell.utils.EnumUtil;
import com.pmcc.revicesell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 天地
 * @create 2018-03-27 16:37
 * @desc 订单的主表
 * dto用于各层数据的转化
 **/

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//返回非空（可以全局配置）
public class OrderDTO {
    private String orderId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信openid
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态,默认0新下单
     */
    private Integer orderStatus;
    /**
     * 支付状态,默认0未支付
     */
    private Integer payStatus;

    //date转化为long
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @JsonIgnore//对象转化json，字段忽略
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
