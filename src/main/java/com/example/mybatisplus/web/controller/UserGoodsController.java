package com.example.mybatisplus.web.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.common.utls.PayUtil;
import com.example.mybatisplus.mapper.*;
import com.example.mybatisplus.model.domain.Indent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.UserGoodsService;
import com.example.mybatisplus.model.domain.UserGoods;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;


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
@RequestMapping("/api/userGoods")
public class UserGoodsController {

    private final Logger logger = LoggerFactory.getLogger( UserGoodsController.class );

    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserGoodsMapper userGoodsMapper;
    @Autowired
    private UerShopdiscountMapper uerShopdiscountMapper;
    @Autowired
    private UserAppdiscountMapper userAppdiscountMapper;

    @Resource
    private GscoreMapper gscoreMapper;
    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        UserGoods  userGoods =  userGoodsService.getById(id);
        return JsonResponse.success(userGoods);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        userGoodsService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateUserGoods(UserGoods  userGoods) throws Exception {
        userGoodsService.updateById(userGoods);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建UserGoods
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(UserGoods  userGoods) throws Exception {
        userGoodsService.save(userGoods);
        return JsonResponse.success(null);
    }
    /*
    生成订单，完成支付，若无法支付则不会生成订单
    前端发送数据
    openid 商品id 订单生成时间 should pay 是否完成
    优惠卷id、
    * */
    @RequestMapping("/pay")
    @ResponseBody
    public JsonResponse pay(@RequestBody Map userGood){
        //首先查新商品有无余量
        Long goo_id=Long.parseLong(userGood.get("goo_id").toString());
        int goodsLeft = goodsMapper.queryCount(goo_id);
        if(goodsLeft>0){
        //获取用户id
        String openid =  userGood.get("openid").toString();
        Long id = userMapper.getIdByOpenid(openid);
        System.out.println(id);
        //获取用户支付方式
        int payway = Integer.parseInt(userGood.get("payway").toString());
        Float actualpay = Float.valueOf(userGood.get("actualpay").toString());
        boolean isPaid = false;
        Long orderId= Long.parseLong(userGood.get("id").toString());
        //支付，并判断支付过程是否完成
            if(payway<=3) {
                isPaid = PayUtil.pay(openid, actualpay);
            }
            else{
                isPaid = PayUtil.weChatPay(openid,actualpay);
            }
        if(userGood.get("dicountId")!=null&&userGood.get("distype")!=null){
        Long disid = Long.parseLong(userGood.get("dicountId").toString());
        int distype = Integer.parseInt(userGood.get("distype").toString());
        if(disid!=null){
            if(distype == 1){//shop
                uerShopdiscountMapper.UpdateUseDis(disid,id);
            }
            if(distype==2){
                userAppdiscountMapper.UpdateUseDis(disid,id);
            }
        }}
        //获取订单id，若从未有订单此项为-1
        UserGoods orderExist = userGoodsMapper.queryIf(orderId);

        //订单从未存在，则商品数量-1,同时生成新订单
        if(orderExist==null){
            goodsMapper.subCount(goo_id,1);
            //生成新订单
            if(id!=null&&isPaid==true){
                UserGoods userGoods =new UserGoods();
                userGoods.setUserId(id);
                userGoods.setGooId(goo_id);
                Date date = new Date(); // this object contains the current date value
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println();
                userGoods.setOtime(formatter.format(date));
                userGoods.setPayway(payway);
                userGoods.setIsover(false);
                userGoods.setIsdiscount(Boolean.parseBoolean(userGood.get("isdiscount").toString()));
                userGoods.setShouldpay(Float.valueOf(userGood.get("shouldpay").toString()));
                userGoods.setActualpay(actualpay);
                userGoodsService.save(userGoods);
                return JsonResponse.success("true");



            }
        }
        userGoodsMapper.setStatus(payway,orderId);
        return JsonResponse.success("true");
        }
        return JsonResponse.success("true");
    }
    //完成订单
    @RequestMapping("/finishOrder")
    @ResponseBody
    public Boolean finishOrder(@RequestBody Map status){
        Long orderId = Long.parseLong(status.get("id").toString());
        userGoodsMapper.finishOrder(orderId);

        //生成对应的评分记录
        gscoreMapper.InsertidScore(orderId);
        return true;
    }
    //点进订单列表查看订单详情
    @RequestMapping("/selectOrder")
    @ResponseBody
    public UserGoods select(@RequestBody Map goods){
        String openid = goods.get("openid").toString();
        Long id = Long.parseLong(goods.get("id").toString());
        UserGoods goods1 = userGoodsMapper.selectOrder(id);
        return goods1;
    }
    //查询用户订单
    //重写代码：描述：查询未完成的订单
    @RequestMapping("/queryOrder")
    @ResponseBody
    public List<UserGoods> queryOrder(@RequestBody Map user){
        String openid = user.get("openid").toString();
        //用户id
        Long id = userMapper.getIdByOpenid(openid);
//        List<UserGoods> userGoods = new HashMap<>();
//        userGoodsMapper.selectOrder(id);
        List<UserGoods> userGoods = userGoodsMapper.queryOrder(id);
//        List<Book>list = bookService.list(wrapper);
        return userGoods;
    }


    //描述：查询已完成的的订单
    @RequestMapping("/queryFinishOrder")
    @ResponseBody
    public List<Indent> queryFinishOrder(@RequestBody Map map){
        //用户id
        String openid = map.get("openid").toString();
        Long id = userMapper.getIdByOpenid(openid);
        //查询订单情况//查询评分情况
        List<Indent> indents = userGoodsMapper.queryFinishList(id);
        return indents;


    }
}

