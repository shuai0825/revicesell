package com.pmcc.revicesell.dto;/**
 * Created by 天地 on 2018/3/29.
 */

import lombok.Data;

/**
 * @author 天地
 * @create 2018-03-29 10:42
 * @desc 购物车
 **/
@Data
public class CartDTO {
    /**商品id*/
    private String productId;
    /**数量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productQuantity = productQuantity;
        this.productId = productId;
    }
}
