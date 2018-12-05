package com.pmcc.revicesell.controller;/**
 * Created by 天地 on 2018/4/14.
 */
;
import com.pmcc.revicesell.service.interfaces.SeckKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 天地
 * @create 2018-04-14 15:18
 * @desc 秒杀
 **/
@RestController
@RequestMapping("/skill")
@Slf4j
public class SeckKillController {
    @Autowired
    private SeckKillService seckKillService;

    /**
     * 查询特价商品
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception{
        return seckKillService.querySeckKillProducInfo(productId);
    }

    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) throws Exception{
        seckKillService.orderProductMockDiffUser(productId);
        return seckKillService.querySeckKillProducInfo(productId);
    }
}
