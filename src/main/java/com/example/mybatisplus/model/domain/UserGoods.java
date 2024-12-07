package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalTime;
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
@ApiModel(value="UserGoods对象", description="")
public class UserGoods extends Model<UserGoods> {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long gooId;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String otime;

    private Integer payway;

    private Boolean isover;

    private Boolean isdiscount;

    private Float shouldpay;

    private Float actualpay;

    @TableField(exist = false)
    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
