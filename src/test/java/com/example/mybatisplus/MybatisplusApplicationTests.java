package com.example.mybatisplus;

import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class MybatisplusApplicationTests {


    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));
    }



}
