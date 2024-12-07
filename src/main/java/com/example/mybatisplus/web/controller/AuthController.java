package com.example.mybatisplus.web.controller;


import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.dto.UserInfoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @GetMapping("/userInfo")
    public JsonResponse<UserInfoDTO> getUserInfo() {
        return JsonResponse.success(SecurityUtils.getUserInfo());
    }
}
