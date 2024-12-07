package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.*;
import com.example.mybatisplus.model.domain.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.SupermanagerService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
@RequestMapping("/api/supermanager")
public class SupermanagerController {

    private final Logger logger = LoggerFactory.getLogger( SupermanagerController.class );

    @Autowired
    private SupermanagerService supermanagerService;
    @Resource
    private ShopMapper shopMapper;

    @Resource
    private ManagerMapper managerMapper;

//    @Resource
//    private AdminMapper adminMapper;


    @Resource
    private AdvertiseMapper advertiseMapper;

    @Autowired
    private UserGoodsMapper userGoodsMapper;
    @Autowired GoodsMapper goodsMapper;

    @Resource
    private ScoreMapper scoreMapper;

    //描述：显示待审核的预备店铺列表
    //输入：null
    //输出：list
    @PostMapping("/mangerlist")
    @ResponseBody
    public JsonResponse mangerList(){

        //ischeck=0--未审核
        List<Shop> shop=shopMapper .getNotCheck();

        if(shop.size()==0){
            return JsonResponse.failure("暂无未审核的预备店铺信息").setCode(201);
        }
        return JsonResponse.success(shop);

    }


    //描述：审核预备管理员和预备店铺信息
    //输入：审核的管理员 是否审核通过？ 审核意见 **预备店铺id
    //如果未通过审核 只需要保存审核建议
    //如果通过审核 修改店铺的ischeck和管理员类型
    //输出：保存成功
    @PostMapping("/mangerlist/check")
    @ResponseBody
    public JsonResponse checkInfor(@RequestBody Map map){

        //获取预备店铺id
        Long shopid= Long.parseLong(map.get("id").toString());

        //根据店铺id获取管理员id
        Long mid=shopMapper.selectMid(shopid);

        //获取是否审核通过
        String ispass=map.get("ispass").toString();
        System.out.println(ispass);
        //获取审核意见
        String advice=map.get("checkadvice").toString();

        if(ispass.equals("0")||ispass.equals("false")){

            //将审核意见写入 不修改其他的
            shopMapper.updateCheckAdvice(advice,shopid);
            return JsonResponse.success("成功写入审核意见");
        }
        if(ispass.equals("1")||ispass.equals("true")){

            //审核通过时候 审核意见：审核通过

            //修改店铺的ischeck
            shopMapper.updateIsCheck(shopid);

            //修改管理员的类型
            managerMapper.updateMtype(shopid);

            //将对应的店铺id和默认评分加入score表
            scoreMapper.insertShopidScore(shopid);

            return JsonResponse.success("成功通过审核");

        }
        return JsonResponse.failure("fail").setCode(400);
    }




    //返回的店铺列表未非轮播广告型
    //######################
    //思路：获取ad表中的shopid
    //判断shop表中不等于shopid的
    @PostMapping("/notdislist")
    @ResponseBody
    public JsonResponse notDisList(){

        List<Shop> shops=shopMapper.selectByShopId();

        if(shops.size()==0){
            return JsonResponse.failure("null").setCode(201);
        }

        return JsonResponse.success(shops);
    }





    //描述：轮播广告管理
    //增加一个轮播广告的店铺
    //提供店铺列表--超级管理员选择
    //输入：需要加入的店铺id
    //返回成功 or 失败
    @PostMapping("/advertising")
    @ResponseBody
    public JsonResponse advertisingAdd(@RequestBody Map map){
        //提取店铺id
        Long shopIds = Long.parseLong(map.get("id").toString());

//        //判断shopid是否已存在在advertise表中
//        if(advertiseMapper.selectByShopId(shopIds).size()!=0){
//            //已经在表中 无法再插入
//        }

        List<Advertise> advertises =advertiseMapper.selectAll();
        if(advertises.size()>=5){
            return JsonResponse.failure("轮播广告位已满 请先删除再加入").setCode(200);
        }

        //将店铺id插入表Advertise
        advertiseMapper.insertShopId(shopIds);

        return JsonResponse.success("成功设置为轮播广告");
    }



    //返回的店铺列表为轮播广告型
    //######################
    //功能：显示轮播广告list
    //输入：null
    //输出：list
    @PostMapping("/Adlist")
    @ResponseBody
    public JsonResponse AdList(){
//        List<Advertise> advertises =advertiseMapper.selectAll();
        List<Shop> shops=shopMapper.selectByShopId2();
        if(shops.size()==0){
            return JsonResponse.failure("null").setCode(201);
        }
        return JsonResponse.success(shops);
    }




    //功能：轮播广告删除

    @PostMapping("/delete")
    @ResponseBody
    public JsonResponse deleteAdvertise(@RequestBody Map map){
        Long deleteId = Long.parseLong(map.get("id").toString());

        List<Advertise> advertises =advertiseMapper.selectAll();
        if(advertises.size()<=3){
            return JsonResponse.failure("轮播广告过少，无法再删除").setCode(200);
        }


        advertiseMapper.deleteByShopId(deleteId);
        return JsonResponse.success("成功删除轮播广告");
    }






    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Supermanager  supermanager =  supermanagerService.getById(id);
        return JsonResponse.success(supermanager);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        supermanagerService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateSupermanager(Supermanager  supermanager) throws Exception {
        supermanagerService.updateById(supermanager);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Supermanager
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Supermanager  supermanager) throws Exception {
        supermanagerService.save(supermanager);
        return JsonResponse.success(null);
    }
    //统计用户作为柱状图
    @RequestMapping("/userStat")
    @ResponseBody
    public List<Integer> userStat() throws Exception{
        List<userStat> userStats = userGoodsMapper.count();
        List<Integer> orders=userStats.stream().map(userStat::getUser_num).collect(Collectors.toList());
        return orders;
    }
    //统计店铺内的商品销售情况
    @RequestMapping("/goodStat")
    @ResponseBody
    public List<goodsStat> goodStat(@RequestBody Map shopid){
        List<goodsStat> goods = goodsMapper.goodStat(Long.parseLong(shopid.get("shopid").toString()));

        return goods;
    }
    //根据商铺类型统计商铺
    @RequestMapping("/shopStat")
    @ResponseBody
    public List<Shop> shopStat(@RequestBody Map shop){
        List<Shop> shops = shopMapper.getAll(shop.get("shoptype").toString());
        return shops;
    }
}

