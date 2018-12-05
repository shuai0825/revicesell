package com.pmcc.revicesell.service;

import com.pmcc.revicesell.dataobject.ProductCategory;
import com.pmcc.revicesell.repository.ProductCategoryRepository;
import com.pmcc.revicesell.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sky
 * @create 2018-06-15 10:44
 * @desc
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ProductCategoryRepository categoryRepository;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }
}
