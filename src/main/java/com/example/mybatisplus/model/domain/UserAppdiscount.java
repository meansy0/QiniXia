package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.mapper.UserMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
@ApiModel(value="UserAppdiscount对象", description="")
public class UserAppdiscount extends Model<UserAppdiscount> {

    private static final long serialVersionUID = 1L;

    private Long useiId;



    private Long appdisId;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Boolean isuse;





    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUseiId() {
        return useiId;
    }

    public void setUseiId(Long useiId) {
        this.useiId = useiId;
    }

    public Long getAppdisId() {
        return appdisId;
    }

    public void setAppdisId(Long appdisId) {
        this.appdisId = appdisId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsuse() {
        return isuse;
    }

    public void setIsuse(Boolean isuse) {
        this.isuse = isuse;
    }
}
