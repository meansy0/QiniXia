package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.GoodsMapper;
import com.example.mybatisplus.mapper.ShopMapper;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.Goods;
import com.example.mybatisplus.model.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ShopService;
import com.example.mybatisplus.model.domain.Shop;

import javax.annotation.Resource;
import java.util.HashMap;
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
@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final Logger logger = LoggerFactory.getLogger( ShopController.class );






    public void setShopIds(Long shopIds) {
        this.shopIds = shopIds;
    }

    public Long shopIds;


    @Autowired
    private ShopService shopService;
    @Resource
    private UserMapper usermapper;

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private GoodsMapper goodsmapper;



    //附近&店铺功能1
    //描述：提供openid和shoptype 返回店铺列表
    //传入数据：以map接收
    //返回信息：店铺id 店铺名称 店铺地址  店铺图片url
    //首页的店铺和附近都可以调用该功能
    //按照评分排名返回店铺信息
    @PostMapping("/nearby")

    public JsonResponse getAll(@RequestBody Map nearby) throws Exception {

        String openId=nearby.get("openid").toString();
        //ObjectMapper mapper = new ObjectMapper();
        if(usermapper.getOpenid(nearby.get("openid").toString())==null){
            return JsonResponse.failure("openid无效").setCode(201);
        }

        //只展示通过审核的店铺
        //根据评分高低排序
        List<Shop> shop = shopMapper.getAll(nearby.get("shoptype").toString());

        if(shop.size()==0){
            return JsonResponse.failure("未找到该店铺类型的店铺").setCode(201);
        }

        return JsonResponse.success(shop);
    }



    //附近&店铺功能2 店铺详情+店铺商品list
    //描述：传店铺入id 返回店铺详情和商品列表
    //传入的数据：用idMap接受店铺id
    //传回的json数据：
    //message：店铺信息（string）  data：店铺所有的商品信息（对象列表）

    @PostMapping("/nearby/details")
    @ResponseBody

    public JsonResponse shopAndGoods(@RequestBody Map idMap)throws Exception{
        //coding here
            Long shopIds=Long.parseLong(idMap.get("id").toString());
            long id=Long.parseLong(idMap.get("id").toString());
            Shop shop1=shopService.getById(id);
            List<Goods> goods=goodsmapper.selectByShopId(id);

            if(shop1==null){
                return JsonResponse.failure("未找到该店铺类型的店铺").setCode(201);
            }
            if(goods==null){
                return JsonResponse.success(shop1);
            }

        return JsonResponse.success(goods).setMessage(shop1.toString());
    }
    /*
    店铺商品统计
     */



    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Shop  shop =  shopService.getById(id);
        return JsonResponse.success(shop);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        shopService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateShop(Shop  shop) throws Exception {
        shopService.updateById(shop);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Shop
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Shop  shop) throws Exception {
        shopService.save(shop);
        return JsonResponse.success(null);
    }
}



