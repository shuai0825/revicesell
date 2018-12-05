package com.pmcc.revicesell.service.interfaces;/**
 * Created by 天地 on 2018/4/14.
 */


import com.pmcc.revicesell.exception.SellException;
import com.pmcc.revicesell.utils.KeyUtil;
import com.pmcc.revicesell.utils.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 天地
 * @create 2018-04-14 15:22
 * @desc
 **/
@Service
public class SeckKillService {
    @Autowired
    private RedisLock redisLock;

    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("12", 10000);
        stock.put("12", 10000);
    }

    public String querySeckKillProducInfo(String productId) {
        return "国庆活动，限售数量" + products.get(productId) + "还剩余：" + stock.get(productId) + "份" +
                ",该商品购买人数：" + orders.size() + "人";
    }

    //synchronized同步锁，多个线程开启时，锁定相当于单线程
    //public synchronized void orderProductMockDiffUser(String productId) {
    public  void orderProductMockDiffUser(String productId) {
        long time = System.currentTimeMillis() + 10 * 000;
        if (!redisLock.lock(productId, String.valueOf(time))) {
            throw new SellException(101, "当前用户太多");
        }
        //1.查询库存
        int stockNum = stock.get("12");
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {
            //下单
            orders.put(KeyUtil.genUniqueKey(), productId);
            //减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }
        redisLock.unlock(productId, String.valueOf(time));
    }
}
