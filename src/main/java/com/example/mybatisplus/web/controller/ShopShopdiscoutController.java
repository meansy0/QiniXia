package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ShopShopdiscoutService;
import com.example.mybatisplus.model.domain.ShopShopdiscout;


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
@RequestMapping("/api/shopShopdiscout")
public class ShopShopdiscoutController {

    private final Logger logger = LoggerFactory.getLogger( ShopShopdiscoutController.class );

    @Autowired
    private ShopShopdiscoutService shopShopdiscoutService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        ShopShopdiscout  shopShopdiscout =  shopShopdiscoutService.getById(id);
        return JsonResponse.success(shopShopdiscout);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        shopShopdiscoutService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateShopShopdiscout(ShopShopdiscout  shopShopdiscout) throws Exception {
        shopShopdiscoutService.updateById(shopShopdiscout);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建ShopShopdiscout
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(ShopShopdiscout  shopShopdiscout) throws Exception {
        shopShopdiscoutService.save(shopShopdiscout);
        return JsonResponse.success(null);
    }
}

