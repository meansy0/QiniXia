package com.example.mybatisplus.model.domain;

public class Indent {


    //user_id  ;
    private Long userId;

//    goo_id ;
private Long gooId;

    private Long id;

    private String otime;
    private Integer payway;
    private Boolean isover;
    private Boolean isdiscount;

    private Float shouldpay;
    private Float actualpay;
    private String goodsname;
    private String goodspic;

    private String score;

    public Long getUserId() {
        return userId;
    }

    public Long getGooId() {
        return gooId;
    }

    public Long getId() {
        return id;
    }

    public String getOtime() {
        return otime;
    }

    public Integer getPayway() {
        return payway;
    }

    public Boolean getIsover() {
        return isover;
    }

    public Boolean getIsdiscount() {
        return isdiscount;
    }

    public Float getShouldpay() {
        return shouldpay;
    }

    public Float getActualpay() {
        return actualpay;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public String getGoodspic() {
        return goodspic;
    }

    public String getScore() {
        return score;
    }
}
