package com.pmcc.revicesell.repository;

import com.pmcc.revicesell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void contextLoads() {
       // ProductCategory productCategory = repository.findById(1).get();
        List<Integer> integers = Arrays.asList(1, 5);
        List<ProductCategory> repository = this.repository.findByCategoryTypeIn(integers);
        System.out.print(repository.size());
    }
    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(3);
        productCategory.setCategoryName("水果");
        productCategory.setCategoryType(1);
       repository.save(productCategory);

    }
}