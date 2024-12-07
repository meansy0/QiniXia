package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
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
@ApiModel(value="Shopdiscount对象", description="")
public class Shopdiscount extends Model<Shopdiscount> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Float disvalue;

    private Integer nowamout;

    private Integer totalamout;

    private Float restriction;

    private LocalDate expirytime;

    private Float discharge;

    @TableField(exist = false)
    List<UerShopdiscount> uerShopdiscounts;

    public List<UerShopdiscount> getUerShopdiscounts() {
        return uerShopdiscounts;
    }

    public void setUerShopdiscounts(List<UerShopdiscount> uerShopdiscounts) {
        this.uerShopdiscounts = uerShopdiscounts;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDisvalue() {
        return disvalue;
    }

    public void setDisvalue(Float disvalue) {
        this.disvalue = disvalue;
    }

    public Integer getNowamout() {
        return nowamout;
    }

    public void setNowamout(Integer nowamout) {
        this.nowamout = nowamout;
    }

    public Integer getTotalamout() {
        return totalamout;
    }

    public void setTotalamout(Integer totalamout) {
        this.totalamout = totalamout;
    }

    public Float getRestriction() {
        return restriction;
    }

    public void setRestriction(Float restriction) {
        this.restriction = restriction;
    }

    public LocalDate getExpirytime() {
        return expirytime;
    }

    public void setExpirytime(LocalDate expirytime) {
        this.expirytime = expirytime;
    }

    public Float getDischarge() {
        return discharge;
    }

    public void setDischarge(Float discharge) {
        this.discharge = discharge;
    }
}
