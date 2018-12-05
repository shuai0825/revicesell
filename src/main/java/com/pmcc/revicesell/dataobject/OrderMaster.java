package com.pmcc.revicesell.dataobject;/**
 * Created by 天地 on 2018/3/27.
 */

import com.pmcc.revicesell.enums.OrderStatusEnum;
import com.pmcc.revicesell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 天地
 * @create 2018-03-27 16:37
 * @desc 订单的主表
 **/
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    private String orderId;
    /**买家名字*/
    private String buyerName;
    /**买家电话*/
    private String buyerPhone;
    /**买家地址*/
    private String buyerAddress;
    /**买家微信openid*/
    private String buyerOpenid;
    /**订单总金额*/
    private BigDecimal orderAmount;
    /**订单状态,默认0新下单*/
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
    /**支付状态,默认0未支付*/
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    private Date createTime;
    private Date updateTime;
}
