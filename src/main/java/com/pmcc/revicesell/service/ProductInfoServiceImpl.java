package com.pmcc.revicesell.service;

import com.pmcc.revicesell.dataobject.ProductInfo;
import com.pmcc.revicesell.enums.ProductStatusEnum;
import com.pmcc.revicesell.repository.ProductInfoRepository;
import com.pmcc.revicesell.service.interfaces.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sky
 * @create 2018-06-15 11:47
 * @desc
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository infoRepository;

    /**
     * 查询单个注意捕获异常
     * @param productId
     * @return
     */
    @Override
    public ProductInfo findOne(String productId) {
        return infoRepository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return infoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return infoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return infoRepository.save(productInfo);
    }
}
