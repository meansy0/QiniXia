package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;

@Service
public class MailServiceImpl implements IMailService {

    @Value("${spring.mail.username}")
    
    private String from;

    @Resource
    private JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //发送普通邮件
    @Override
    public void sendSimpleMail(String to, String title, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
        logger.info("邮件发送成功");

    }


}