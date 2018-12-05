package com.pmcc.revicesell.service.interfaces;

import com.pmcc.revicesell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author sky
 * @create 2018-06-15 10:40
 * @desc 类目的服务层
 **/
public interface CategoryService {
    //查询指定类目
    ProductCategory findOne(Integer categoryId);
    //查询所有类目
    List<ProductCategory> findAll();
    //
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    //新增类目
    ProductCategory save(ProductCategory productCategory);
}
