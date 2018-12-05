package com.pmcc.revicesell.service;

import com.pmcc.revicesell.dataobject.ProductCategory;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest {
    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        try {
            ProductCategory productCategory = categoryService.findOne(2);
            log.info(productCategory.toString());
        }catch (Exception e){
            log.info("查询id不存在");
        }


    }

    @Test
    public void findAll() {
        List<ProductCategory> categoryServiceAll = categoryService.findAll();
        log.info(categoryServiceAll.toString());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> integers = Arrays.asList(3, 4);
        List<ProductCategory> categoryTypeIns = categoryService.findByCategoryTypeIn(integers);
        log.info(categoryTypeIns.toString());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(2);
        productCategory.setCategoryName("干果1");
        categoryService.save(productCategory);
    }
}