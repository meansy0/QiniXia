package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

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
@ApiModel(value="Shop对象", description="")
public class Shop extends Model<Shop> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long mgrId;

    private String shoptype;

    private String shopadress;

    private String shopintro;

    public Boolean getIscheck() {
        return ischeck;
    }

    public void setIscheck(Boolean ischeck) {
        this.ischeck = ischeck;
    }

    private Boolean ischeck;

    private String shoppic;

    private String shopname;

    public String getCheckadvice() {
        return checkadvice;
    }

    public void setCheckadvice(String checkadvice) {
        this.checkadvice = checkadvice;
    }

    private String checkadvice;


    @TableField(exist = false)
    private Score score;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
