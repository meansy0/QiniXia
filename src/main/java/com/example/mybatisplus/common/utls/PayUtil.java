package com.example.mybatisplus.common.utls;

import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author gxy
 * @date 2022/3/3$
 */
@Component
public class PayUtil {
    private static PayUtil payUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    public static boolean weChatPay(String openid, Float actualpay) {
        return true;
    }

    @PostConstruct
    public void init() {
        payUtil = this;
        payUtil.userMapper = this.userMapper;
    }
    public static boolean pay(String openId,float goodsValue){

        System.out.println(openId);
        System.out.println(goodsValue);

        User user = payUtil.userMapper.getUremainByOpenId(openId);
        System.out.println(user.getUremain());
        if(user.getUremain()>goodsValue){
            payUtil.userMapper.sub(openId, user.getUremain()-goodsValue);
            System.out.println(user.getUremain()-goodsValue);
            return true;
        }
        return false;
    }

}
