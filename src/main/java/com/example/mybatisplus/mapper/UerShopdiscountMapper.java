package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.UerShopdiscount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxy
 * @since 2022-03-01
 */
@Repository
public interface UerShopdiscountMapper extends BaseMapper<UerShopdiscount> {

    Long insertUserDis(Long oid, Long id);

    //函数描述：使用店铺优惠券
    //输入：优惠券id 用户id
    //输出：null
    void UpdateUseDis(Long DisId,Long UserId);
}
