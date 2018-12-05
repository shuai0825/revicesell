package com.pmcc.revicesell.repository;

import com.pmcc.revicesell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sky
 * @create 2018-06-15 11:37
 * @desc
 **/
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
