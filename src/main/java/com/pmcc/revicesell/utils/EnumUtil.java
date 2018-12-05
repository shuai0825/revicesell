package com.pmcc.revicesell.utils;/**
 * Created by 天地 on 2018/4/12.
 */


import com.pmcc.revicesell.enums.CodeEnum;

/**
 * @author 天地
 * @create 2018-04-12 15:40
 * @desc
 **/
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
