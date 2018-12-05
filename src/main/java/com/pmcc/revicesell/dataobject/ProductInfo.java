package com.pmcc.revicesell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pmcc.revicesell.enums.ProductStatusEnum;
import com.pmcc.revicesell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sky
 * @create 2018-06-15 11:35
 * @desc 商品详情
 **/
@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {
    @Id
    private String productId;
    /**名字*/
    private String productName;
    /**单价*/
    private BigDecimal productPrice;
    /**库存*/
    private Integer productStock;
    /**描述*/
    private String productDescription;
    /**小图*/
    private String productIcon;
    /**状态，0正常1下架*/
    private Integer productStatus=ProductStatusEnum.UP.getCode();
    /**类目编号*/
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;
    @JsonIgnore
   public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
