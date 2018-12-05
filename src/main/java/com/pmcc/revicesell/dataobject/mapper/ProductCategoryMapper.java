package com.pmcc.revicesell.dataobject.mapper;/**
 * Created by 天地 on 2018/4/14.
 */


import com.pmcc.revicesell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 天地
 * @create 2018-04-14 10:40
 * @desc
 **/
@Component
public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER}) ")
    int insertByMap(Map<String, Object> map);//通过map

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER}) ")
    int insertByObject(ProductCategory productCategory);//通过对象

    //返回字段，没设置为null
    @Select("select * from product_category where category_type=#{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    //返回字段，没设置为null
    @Select("select * from product_category where category_name=#{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

   //修改,Param官方推荐
    @Update("update product_category set category_name= #{categoryName} where category_type=#{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName, @Param("categoryType") Integer categoryType);


    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);

    //通过配置文件的方式（不推荐）
    ProductCategory selectByCategoryType(Integer categoryType);

}
