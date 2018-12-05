package com.pmcc.revicesell.service;

import com.pmcc.revicesell.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {
    @Autowired
    private PushMessageServiceImpl pushMessageService;
    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("123");
        pushMessageService.orderStatus(orderDTO);
    }
}