package com.pmcc.revicesell.repository;/**
 * Created by 天地 on 2018/3/27.
 */


import com.pmcc.revicesell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 天地
 * @create 2018-03-27 17:21
 * @desc 订单主表
 **/
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
