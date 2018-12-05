package com.pmcc.revicesell.utils.serializer;/**
 * Created by 天地 on 2018/3/29.
 */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @author 天地
 * @create 2018-03-29 17:39
 * @desc 日期转化为long
 **/
public class Date2LongSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.getTime() / 1000);
    }
}
