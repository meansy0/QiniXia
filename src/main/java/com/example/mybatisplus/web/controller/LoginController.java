package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.service.LoginService;
import com.example.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author gxy
 * @date 2022/3/1$
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    @ResponseBody
    public String login(String code) throws Exception{
        try {
            //访问登录服务接口
            String openId = userService.getOpenId(code);
            loginService.login(openId);
            return openId;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
}
