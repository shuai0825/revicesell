package com.pmcc.revicesell.repository;/**
 * Created by 天地 on 2018/3/27.
 */


import com.pmcc.revicesell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 天地
 * @create 2018-03-27 17:27
 * @desc 订单详情表
 **/
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
