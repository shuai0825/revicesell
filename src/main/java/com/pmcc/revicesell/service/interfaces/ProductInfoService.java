package com.pmcc.revicesell.service.interfaces;

import com.pmcc.revicesell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author sky
 * @create 2018-06-15 11:42
 * @desc
 **/
public interface ProductInfoService {
    ProductInfo findOne(String productId);
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);
    //加库存

    //减库存

}
