package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.ManagerMapper;
import com.example.mybatisplus.mapper.ShopdiscountMapper;
import com.example.mybatisplus.mapper.UerShopdiscountMapper;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.Appdiscount;
import com.example.mybatisplus.model.domain.Manager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ShopdiscountService;
import com.example.mybatisplus.model.domain.Shopdiscount;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@RequestMapping("/api/shopdiscount")
public class ShopdiscountController {

    private final Logger logger = LoggerFactory.getLogger( ShopdiscountController.class );

    @Autowired
    private ShopdiscountService shopdiscountService;

    @Autowired
    private ShopController shopController;

    @Resource
    private ShopdiscountMapper shopdiscountMapper;
    @Resource
    private UserMapper usermapper;

    @Resource
    private UerShopdiscountMapper uerShopdiscountMapper;

    @Resource
    private ManagerMapper managerMapper;




//    //描述：在进入店铺详情界面惠券列表
//    //    //输入：null后，点击优惠券可以显示优
//    //输出：店铺优惠券list
//    @GetMapping("/shoplist")
//    @ResponseBody
//    public JsonResponse ShopDiscountList(){
//        List<Appdiscount> shopDiscount = shopdiscountMapper.ShopDisAll(shopController.getShopIds());
//
//        if(shopDiscount.size()==0){
//            return JsonResponse.failure("暂无未超出使用日期的该店铺的优惠券").setCode(201);
//        }
//        return  JsonResponse.success(shopDiscount);
//    }


    @PostMapping("/hasget")
    @ResponseBody
    //描述：展示已领取的店铺优惠券列表
    //输入：null
    //调用shopcontroll里的shopid
    //输出：用户已领取的店铺优惠券列表
    private JsonResponse hasGetShopDis(@RequestBody Map map){

        //获取店铺id
        Long shopid=Long.parseLong(map.get("shopid").toString());
//        Long shopid=shopController.getShopIds();
        //获取openid
        String openid=map.get("openid").toString();
//        String openid=shopController.getOpenId();
        //获取用户id
        Long oid=Long.parseLong(usermapper.getbyopenid(openid));

        List<Shopdiscount> list = shopdiscountMapper.hasget1(shopid,oid);

        if(list.size()==0){
            return JsonResponse.failure("暂无已领取的店铺优惠券(●'◡'●)").setCode(201);
        }
        return JsonResponse.success(list);
    }


    //描述：获取未领取的店铺优惠券
    //输入：null
    //输出：用户未领取的店铺优惠券列表
    @PostMapping("/hasnot")
    @ResponseBody
    public JsonResponse hasnotShopDis(@RequestBody Map map){

        //获取店铺id
        Long shopid=Long.parseLong(map.get("shopid").toString());
//        Long shopid=shopController.getShopIds();
        //获取openid
        String openid=map.get("openid").toString();
//        String openid=shopController.getOpenId();
        //获取用户id
        Long oid=Long.parseLong(usermapper.getbyopenid(openid));

//        //获取店铺id
//        Long shopid=shopController.getShopIds();
//        //获取openid
//        String openid=shopController.getOpenId();
//        //获取用户id
//        Long oid=Long.parseLong(usermapper.getbyopenid(openid));

        System.out.println(shopid);
        System.out.println(oid);

        List<Shopdiscount> shopiscounts=shopdiscountMapper.getHasnot(shopid,oid);


        if(shopiscounts.size()==0){
            return JsonResponse.failure("sorry，暂无未领取的店铺优惠券(●'◡'●)").setCode(201);
        }
        return JsonResponse.success(shopiscounts);
    }


    //描述：用户领取店铺优惠券
    //输入：优惠券id
    //输出：领取优惠券情况
    //success or fail

    @PostMapping("/hasnot/details")
    @ResponseBody
    public JsonResponse getShopDis(@RequestBody Map map){

////        //获取店铺id
////        Long shopid=shopController.getShopIds();
//        //获取openid
//        String openid=shopController.getOpenId();
//        //获取用户id
//        Long oid=Long.parseLong(usermapper.getbyopenid(openid));

        //id: 优惠券id
        Long id=Long.parseLong(map.get("id").toString());

        //获取openid
        String openid=map.get("openid").toString();
//        String openid=shopController.getOpenId();
        //获取用户id
        Long oid=Long.parseLong(usermapper.getbyopenid(openid));

        //1 判断用户是否已经领取过优惠券
        //如果是 则return 已经领取
        //2 如果否 将数据插入

        if(shopdiscountMapper.hasget2(oid,id).size()!= 0){
            return JsonResponse.failure("已经领取过啦！不能再次领取哦").setCode(201);
        }

        uerShopdiscountMapper.insertUserDis(oid,id);

        shopdiscountMapper.updateamout(id);

        return JsonResponse.success("已成功领取店铺优惠券！");

    }


    //描述：普通管理员发放店铺优惠券
    //输入：店铺管理员id+发放的优惠券信息 包括 Float disvalue;（面值）（Integer nowamout;）
    // Integer totalamout;Float restriction（满多少）;LocalDate expirytime;Float discharge;
    //nowamount=total
    //输出 ：是否成功发放
    @PostMapping("/IssueShopCoupon")

    @ResponseBody

    public JsonResponse IssueCoupon(@RequestBody Map map){

        //数据类型转换
        float disvalue =Float.parseFloat(map.get("disvalue").toString());
        System.out.println(disvalue);
        int totalamout=Integer.parseInt(map.get("totalamout").toString());
        float restriction=Float.parseFloat(map.get("restriction").toString());
        LocalDate expirytime=LocalDate.parse(map.get("expirytime").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        float discharge=Float.parseFloat(map.get("discharge").toString());

        //根据管理员id获取店铺id
        Long managerId = Long.parseLong(map.get("id").toString());
        Manager manager=managerMapper.getByMid(managerId);

        Long shopId=manager.getShopId();
        shopdiscountMapper.insertShopDis(disvalue,totalamout,restriction,expirytime,discharge,shopId);

        return JsonResponse.success("success");


    }



    //描述：统计店铺优惠券情况
    //输入：管理员id
    //输出 ：返回所有发放的优惠券领取情况

    @PostMapping("/statisticalDis")
    @ResponseBody
    public JsonResponse statisticalDis(@RequestBody Map map){

        //根据管理员id获取店铺id
        Long managerId = Long.parseLong(map.get("id").toString());
        Manager manager=managerMapper.getByMid(managerId);

        Long shopId=manager.getShopId();
        List<Shopdiscount> shopdiscounts=shopdiscountMapper.getByShopId(shopId);

        if(shopdiscounts.size()==0){
            return JsonResponse.failure("暂无已发放的店铺优惠券信息").setCode(201);
        }

        return JsonResponse.success(shopdiscounts);
    }



    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Shopdiscount  shopdiscount =  shopdiscountService.getById(id);
        return JsonResponse.success(shopdiscount);
    }


    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        shopdiscountService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateShopdiscount(Shopdiscount  shopdiscount) throws Exception {
        shopdiscountService.updateById(shopdiscount);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Shopdiscount
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Shopdiscount  shopdiscount) throws Exception {
        shopdiscountService.save(shopdiscount);
        return JsonResponse.success(null);
    }
}

