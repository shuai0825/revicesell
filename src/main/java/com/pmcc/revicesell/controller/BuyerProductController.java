package com.pmcc.revicesell.controller;

import com.pmcc.revicesell.dataobject.ProductCategory;
import com.pmcc.revicesell.dataobject.ProductInfo;
import com.pmcc.revicesell.service.CategoryServiceImpl;
import com.pmcc.revicesell.service.ProductInfoServiceImpl;
import com.pmcc.revicesell.utils.ResultVoUtil;
import com.pmcc.revicesell.vo.ProductInfoVO;
import com.pmcc.revicesell.vo.ProductVO;
import com.pmcc.revicesell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sky
 * @create 2018-06-15 15:19
 * @desc
 **/
@RestController
@RequestMapping("/buyer/product")

public class BuyerProductController {
    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Autowired
    private CategoryServiceImpl categoryService;

    // @Cacheable(cacheNames = "product",key = "123")//缓存，保证对象可序列化，有时不走下面方法
    @Cacheable(cacheNames = "product",key = "#openid",condition = "#openid.length()>3",unless = "#result.getCode!=0")//利用redis缓存，设置动态的key,加判断条件,状态码为0缓存
    @GetMapping("/list")
    public ResultVO list() {
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2.查询类目（一次性查询）
        //ArrayList<Integer> categoryTypeList = new ArrayList<>();
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼接
        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return  ResultVoUtil.success(productVOList);
    }
}
