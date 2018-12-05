package com.pmcc.revicesell.utils;/**
 * Created by 天地 on 2018/3/27.
 */

import java.util.Random;

/**
 * @author 天地
 * @create 2018-03-27 19:00
 * @desc 生成主键工具类
 **/
public class KeyUtil {
    /**
     * 生成主键工具类
     * 格式时间+6位随机数
     *加锁，避免多并发
     * @return
     */
    public static synchronized String genUniqueKey() {
        return System.currentTimeMillis() + String.valueOf(new Random().nextInt(900000) + 100000);
    }
}
