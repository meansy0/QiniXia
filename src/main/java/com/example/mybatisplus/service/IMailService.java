package com.example.mybatisplus.service;

public interface IMailService {

    //发送普通邮件
    void sendSimpleMail(String to,String title,String content);
}
