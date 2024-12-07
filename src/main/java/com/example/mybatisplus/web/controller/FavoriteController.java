package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.FavoriteMapper;
import com.example.mybatisplus.mapper.ShopMapper;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.Shop;
import com.sun.javafx.collections.MappingChange;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.FavoriteService;
import com.example.mybatisplus.model.domain.Favorite;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 *
 *  前端控制器
 *
 *
 * @author gxy
 * @since 2022-03-08
 * @version v1.0
 */
@Controller
@RequestMapping("/api/favorite")
public class FavoriteController {

    private final Logger logger = LoggerFactory.getLogger( FavoriteController.class );

    @Autowired
    private FavoriteService favoriteService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private FavoriteMapper favoriteMapper;


    @Resource
    private ShopMapper shopMapper;


    //描述：普通用户收藏店铺
    //输入：用户：openid 店铺：id
    //输出：收藏成功？
    @PostMapping("/collect")
    @ResponseBody
    public JsonResponse collect(@RequestBody Map map){

        //获取openid
        String openid =map.get("openid").toString();
        //根据openid获取用户id
        Long userid = userMapper.getIdByOpenid(openid);

        //获取店铺id
        Long shopid = Long.parseLong(map.get("id").toString());

        List<Favorite> favorites =favoriteMapper.selectByIds(userid,shopid);
        if(favorites.size()!=0){
            //已经收藏过啦！
           return JsonResponse.failure("已经收藏过la!").setCode(201);
        }

        favoriteMapper.insertByIds(userid,shopid);
        return JsonResponse.success("yes!");
    }




    //描述：普通用户显示我的收藏list
    //输入：openid
    //输出：收藏店铺list
    @PostMapping("/collectlist")
    @ResponseBody
    public JsonResponse collectList(@RequestBody Map map){

        //获取openid
        String openid= map.get("openid").toString();

        //根据openid获取用户id
        Long userid = userMapper.getIdByOpenid(openid);

        List<Shop> shops= shopMapper.selectMyCollect(userid);

        if (shops.size()==0){
            return JsonResponse.failure("暂无收藏店铺信息").setCode(201);
        }

        return JsonResponse.success(shops);


    }





    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Favorite  favorite =  favoriteService.getById(id);
        return JsonResponse.success(favorite);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        favoriteService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateFavorite(Favorite  favorite) throws Exception {
        favoriteService.updateById(favorite);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Favorite
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Favorite  favorite) throws Exception {
        favoriteService.save(favorite);
        return JsonResponse.success(null);
    }
}

