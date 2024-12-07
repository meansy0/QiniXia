package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.common.utls.WxOpenIdUtil;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.service.LoginService;
import com.example.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gxy
 * @date 2022/3/1$
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Override
    public String login(String openId) throws Exception {
        System.out.println(openId);
         User user =new User();
         user.setOpenid(openId);
         if(null==userMapper.getIdByOpenid(openId)){
             userService.save(user);
             return "success";
         }
         return "success";
    }


}
