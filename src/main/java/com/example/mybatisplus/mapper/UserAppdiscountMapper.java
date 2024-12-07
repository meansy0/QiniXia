package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.UserAppdiscount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxy
 * @since 2022-03-01
 */
@Repository
public interface UserAppdiscountMapper extends BaseMapper<UserAppdiscount> {



    Long insertUserDis(String opid, String id);

    //函数描述：使用平台优惠券
    //输入：优惠券id 用户id
    //输出：null
    void UpdateUseDis(Long DisId,Long UserId);



}
