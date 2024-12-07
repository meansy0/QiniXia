package com.example.mybatisplus.model.domain;

import java.time.LocalTime;

/**
 * @author gxy
 * @date 2022/3/8$
 */

public class Order {
    public Long goo_id;
    public Long id;
    public LocalTime otime;
    public boolean isover;
    public Integer payway;
    public boolean isdiscount;
    private Float shouldpay;
    private Float actualpay;
    private String goodsname;
    private String goodspic;
}
