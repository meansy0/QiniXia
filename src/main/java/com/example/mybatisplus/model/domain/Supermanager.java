package com.example.mybatisplus.model.domain;

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
@ApiModel(value="Supermanager对象", description="")
public class Supermanager extends Model<Supermanager> {

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String smpass;

    private String smtel;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSmpass() {
        return smpass;
    }

    public void setSmpass(String smpass) {
        this.smpass = smpass;
    }

    public String getSmtel() {
        return smtel;
    }

    public void setSmtel(String smtel) {
        this.smtel = smtel;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
