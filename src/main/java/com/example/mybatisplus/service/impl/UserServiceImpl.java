package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.common.utls.WxOpenIdUtil;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gxy
 * @since 2022-03-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public String getOpenId(String code) throws Exception {
        String openId;
        openId = WxOpenIdUtil.getOpenId(code);
        return openId;
    }
}
