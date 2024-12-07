package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.UerShopdiscountService;
import com.example.mybatisplus.model.domain.UerShopdiscount;


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
@RequestMapping("/api/uerShopdiscount")
public class UerShopdiscountController {

    private final Logger logger = LoggerFactory.getLogger( UerShopdiscountController.class );

    @Autowired
    private UerShopdiscountService uerShopdiscountService;




    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        UerShopdiscount  uerShopdiscount =  uerShopdiscountService.getById(id);
        return JsonResponse.success(uerShopdiscount);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        uerShopdiscountService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateUerShopdiscount(UerShopdiscount  uerShopdiscount) throws Exception {
        uerShopdiscountService.updateById(uerShopdiscount);
        return JsonResponse.success(null);
    }

    //描述：用户领取优惠券
    /**
    * 描述:创建UerShopdiscount
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(UerShopdiscount  uerShopdiscount) throws Exception {
        uerShopdiscountService.save(uerShopdiscount);
        return JsonResponse.success(null);
    }
}

