package com.pmcc.revicesell.vo;/**
 * Created by 天地 on 2018/3/27.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 天地
 * @create 2018-03-27 15:30
 * @desc 商品（包含类目）
 * @JsonProperty序列化返回前端为name
 **/
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -191109372596247907L;
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
