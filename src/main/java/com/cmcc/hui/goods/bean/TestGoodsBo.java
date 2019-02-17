package com.cmcc.hui.goods.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xuhaitao
 * @Date 2019/2/16 18:25
 **/
@Data
public class TestGoodsBo {

    private long id;
    private String name;
    private BigDecimal price;
    private long stock;

}
