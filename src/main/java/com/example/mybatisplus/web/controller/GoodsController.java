package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.GoodsMapper;
import com.example.mybatisplus.mapper.ShopMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.GoodsService;
import com.example.mybatisplus.model.domain.Goods;

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
@RequestMapping("/api/goods")
public class GoodsController {

    private final Logger logger = LoggerFactory.getLogger( GoodsController.class );

    @Autowired
    private GoodsService goodsService;

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private GoodsMapper goodsMapper;


    //描述：通过商品id返回商品详情
    //输入：商品id
    //输出：对应商品的信息

    @PostMapping("/goodsdetails")
    @ResponseBody
    public JsonResponse getById(@RequestBody Map map)throws Exception {
        Long id=Long.parseLong(map.get("id").toString());
        Goods  goods =  goodsMapper.getInforById(id);
        return JsonResponse.success(goods);
    }



    //描述：查询店铺销量list
    //输入：店铺id
    //输出：list 商品名称-销量-余量
    //身份：超级管理员
    @PostMapping("/saleslist")
    @ResponseBody
    public JsonResponse salesList(@RequestBody Map map){
        Long ShopId =Long.parseLong(map.get("id").toString());
        List<Goods> SuperGoods= goodsMapper.getByShopId(ShopId);

        if(SuperGoods.size()==0){
            return JsonResponse.failure("该店铺暂无商品，无法查询销量").setCode(201);
        }
//        System.out.println(SuperGoods);
        return JsonResponse.success(SuperGoods);
    }



    //描述：模糊查询 搜索功能，对商品进行查询
    //输入：关键字
    //输出：对应的商品信息
    //会对商品的名称和简介进行模糊搜索
    //身份；普通用户
    @PostMapping("/query")
    @ResponseBody
    public JsonResponse fuzzyQuery(@RequestBody Map map){
        String keyword=map.get("keys").toString();
        String goodsname = "%" + keyword + "%";
        String goodintro="%" + keyword + "%";

        List<Goods> goods=goodsMapper.selectByName(goodsname,goodintro);

        if(goods.size()==0){
            return JsonResponse.failure("暂无相关商品").setCode(1);
        }
        return JsonResponse.success(goods);
    }



    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        goodsService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateGoods(Goods  goods) throws Exception {
        goodsService.updateById(goods);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Goods
    *
    */

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Goods  goods) throws Exception {
        goodsService.save(goods);
        return JsonResponse.success(null);
    }

}

