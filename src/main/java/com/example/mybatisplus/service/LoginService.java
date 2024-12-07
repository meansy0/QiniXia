package com.example.mybatisplus.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface LoginService {

    String login(String openId) throws Exception;

}
