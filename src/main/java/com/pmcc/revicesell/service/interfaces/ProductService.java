package com.pmcc.revicesell.service.interfaces;/**
 * Created by 天地 on 2018/3/27.
 */

import com.pmcc.revicesell.dataobject.ProductInfo;
import com.pmcc.revicesell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 天地
 * @create 2018-03-27 14:37
 * @desc 商品
 **/
public interface ProductService {
    ProductInfo findOne(String productId);
    /**查询所有在架商品列表*/
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);
    //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);


    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
