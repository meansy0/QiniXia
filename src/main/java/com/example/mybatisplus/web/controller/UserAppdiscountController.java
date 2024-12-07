package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.UserAppdiscountMapper;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.Shop;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.UserAppdiscountService;
import com.example.mybatisplus.model.domain.UserAppdiscount;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 *
 *  前端控制器
 *
 *
 * @author gxy
 * @since 2022-03-01
 * @version v1.0
 */
@Controller
@RequestMapping("/api/userAppdiscount")
public class UserAppdiscountController {

    private final Logger logger = LoggerFactory.getLogger( UserAppdiscountController.class );


    @Autowired
    private UserAppdiscountService userAppdiscountService;

    @Resource
    private UserAppdiscountMapper userAppdiscountMapper;







    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        UserAppdiscount  userAppdiscount =  userAppdiscountService.getById(id);
        return JsonResponse.success(userAppdiscount);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        userAppdiscountService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateUserAppdiscount(UserAppdiscount  userAppdiscount) throws Exception {
        userAppdiscountService.updateById(userAppdiscount);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建UserAppdiscount
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(UserAppdiscount  userAppdiscount) throws Exception {
        userAppdiscountService.save(userAppdiscount);
        return JsonResponse.success(null);
    }
}

