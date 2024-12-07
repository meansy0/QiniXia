package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gxy
 * @since 2022-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Goods对象", description="")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long shopId;

    private Float price;

    private String goodsintro;

    private Integer leftamout;

    private Integer salesamout;

    private Integer totalamout;

    private String goodsname;

    private String goodspic;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getGoodsintro() {
        return goodsintro;
    }

    public void setGoodsintro(String goodsintro) {
        this.goodsintro = goodsintro;
    }

    public Integer getLeftamout() {
        return leftamout;
    }

    public void setLeftamout(Integer leftamout) {
        this.leftamout = leftamout;
    }

    public Integer getSalesamout() {
        return salesamout;
    }

    public void setSalesamout(Integer salesamout) {
        this.salesamout = salesamout;
    }

    public Integer getTotalamout() {
        return totalamout;
    }

    public void setTotalamout(Integer totalamout) {
        this.totalamout = totalamout;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodspic() {
        return goodspic;
    }

    public void setGoodspic(String goodspic) {
        this.goodspic = goodspic;
    }
}
