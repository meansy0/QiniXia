package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.OrderStatistics;
import com.example.mybatisplus.model.domain.User;
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
public interface UserMapper extends BaseMapper<User> {


    User getOpenid(String openid);

    User getUremainByOpenId(String openId);

    Long getIdByOpenid(String openid);

    void sub(String openId, float v);
    String getbyopenid(String opid);


    void updateRemain(String openid, float money);

    Float getRemainByOpenid(String openid);

    OrderStatistics selectCountUser(Long id);



//    void getRemainByOpenid(String openid);

}
