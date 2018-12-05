package com.pmcc.revicesell.vo;/**
 * Created by 天地 on 2018/3/27.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 天地
 * @create 2018-03-27 15:34
 * @desc 商品详情
 **/
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = -4944037459104184351L;
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;
}
