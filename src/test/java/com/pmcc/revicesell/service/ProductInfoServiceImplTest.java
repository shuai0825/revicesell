package com.pmcc.revicesell.service;

import com.pmcc.revicesell.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("123456");
        log.info(productInfo.toString());
    }

    @Test
    public void findUpAll() {

        List<ProductInfo> upAll = productInfoService.findUpAll();
        log.info(upAll.toString());
    }

    @Test
    public void findAll() {
        PageRequest request=PageRequest.of(0,20);
        Page<ProductInfo> all = productInfoService.findAll(request);
        log.info(all.getContent().toString());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("土豆牛肉粥");
        productInfo.setProductPrice(BigDecimal.valueOf(12369.32));
        productInfo.setProductStock(23);
        productInfo.setProductDescription("好喝，不贵");
        productInfo.setProductIcon("1234");
        productInfo.setCategoryType(2);
        productInfoService.save(productInfo);
    }
}