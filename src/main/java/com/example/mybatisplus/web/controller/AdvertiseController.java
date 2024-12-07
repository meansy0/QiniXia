package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.AdvertiseMapper;
import com.example.mybatisplus.mapper.GoodsMapper;
import com.example.mybatisplus.mapper.ShopMapper;
import com.example.mybatisplus.model.domain.Goods;
import com.example.mybatisplus.model.domain.Shop;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.AdvertiseService;
import com.example.mybatisplus.model.domain.Advertise;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 *  前端控制器
 *
 *
 * @author gxy
 * @since 2022-03-05
 * @version v1.0
 */
@Controller
@RequestMapping("/api/advertise")
public class AdvertiseController {

    private final Logger logger = LoggerFactory.getLogger( AdvertiseController.class );

    @Autowired
    private AdvertiseService advertiseService;


//    @Resource
//    private AdvertiseMapper advertiseMapper;

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private GoodsMapper goodsMapper;


    //描述：用户首页显示轮播广告和热点推荐
    //#################################

    //输入：null
    //输出：轮播广告list+热点推荐list
    @PostMapping("/list")
    @ResponseBody
    public JsonResponse returnList(){
        //轮播广告
        List<Shop> shops=shopMapper.selectAllInfor();

        //热点推荐
        List<Goods> goods = goodsMapper.selectHot10();

//        return JsonResponse.success(goods);
        return JsonResponse.success(goods).setMessage(shops.toString());
    }

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Advertise  advertise =  advertiseService.getById(id);
        return JsonResponse.success(advertise);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        advertiseService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateAdvertise(Advertise  advertise) throws Exception {
        advertiseService.updateById(advertise);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Advertise
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Advertise  advertise) throws Exception {
        advertiseService.save(advertise);
        return JsonResponse.success(null);
    }
}

