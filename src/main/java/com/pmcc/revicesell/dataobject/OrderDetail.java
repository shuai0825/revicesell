package com.pmcc.revicesell.dataobject;/**
 * Created by 天地 on 2018/3/27.
 */

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author 天地
 * @create 2018-03-27 16:54
 * @desc 订单详情
 **/
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;
    /***/
    private String orderId;
    /***/
    private String productId;
    /**商品名称*/
    private String productName;
    /**商品价格*/
    private BigDecimal productPrice;
    /**商品数量*/
    private Integer productQuantity;
    /**商品小图*/
    private String productIcon;

}
