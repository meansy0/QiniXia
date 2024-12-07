package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.AppdiscountMapper;
import com.example.mybatisplus.mapper.UserAppdiscountMapper;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.UserAppdiscount;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.AppdiscountService;
import com.example.mybatisplus.model.domain.Appdiscount;
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
@RequestMapping("/api/appdiscount")
public class AppdiscountController {

    private final Logger logger = LoggerFactory.getLogger( AppdiscountController.class );


    @Autowired
    private AppdiscountService appdiscountService;
    @Resource
    private AppdiscountMapper appdiscountMapper;
    @Resource
    private UserMapper usermapper;
    @Resource
    private UserAppdiscountMapper userAppdiscountMapper;







    @PostMapping("/hasget")
    @ResponseBody
    //描述：展示已领取+未使用的优惠券列表
    //输入：openid
    //输出：用户已领取的平台优惠券列表

    private JsonResponse hasGetAppDis(@RequestBody Map hasget){
        if (hasget.get("openid").toString().length()==0){
//            return("未获取到openid");
            return JsonResponse.failure("未获取到openid").setCode(201);
        }
        if(usermapper.getOpenid(hasget.get("openid").toString())==null){
//            return("openid无效");
            return JsonResponse.failure("openid无效").setCode(201);
        }
        String opid=hasget.get("openid").toString();
        opid=usermapper.getbyopenid(opid);

        List<Appdiscount> list = appdiscountMapper.hasget1(opid);
        if(list.size()==0){
//            return("暂无已领取的平台优惠券(●'◡'●)");
            return JsonResponse.failure("暂无已领取的平台优惠券(●'◡'●)").setCode(201);
        }
//        return JSON.toJSONString(list);
        return JsonResponse.success(list);
    }



    //描述：返回已失效的平台优惠券
    //输入：null
    //输出：用户已领取中已过期的平台优惠券列表
    @PostMapping("/hasout")
    @ResponseBody
    public JsonResponse hasOutAppDis(@RequestBody Map map)throws Exception {

        String openid=map.get("openid").toString();
        String opid=usermapper.getbyopenid(openid);
        List<Appdiscount> appDiscount = appdiscountMapper.getAllOut(opid);

        if(appDiscount.size()==0){
            return JsonResponse.failure("暂无已领取的失效的平台优惠券(●'◡'●)").setCode(201);
        }
        //成功返回
        return JsonResponse.success(appDiscount);
    }


    //描述：返回用户未领取的平台优惠券
    //输入：null
    //输出：用户未领取的平台优惠券list
    @PostMapping("/hasnot")
    @ResponseBody
    public JsonResponse hasnotAppDis(@RequestBody Map map){
        String openid=map.get("openid").toString();
        String opid=usermapper.getbyopenid(openid);

        List<Appdiscount> appdiscounts=appdiscountMapper.getHasnot(opid);

        if(appdiscounts.size()==0){
            return JsonResponse.failure("sorry，暂无未领取的平台优惠券(●'◡'●)").setCode(201);
        }
        return JsonResponse.success(appdiscounts);
    }





    //描述：用户领取平台优惠券
    //传入：优惠券id
    //输出：领取情况 success|fail
    @PostMapping("hasnot/getDis")
    @ResponseBody
    public JsonResponse getDis(@RequestBody Map map)throws Exception {
        //openid
        String openid=map.get("openid").toString();
        String opid=usermapper.getbyopenid(openid);

        //id: 优惠券id
        String id=map.get("id").toString();
        //1 判断用户是否已经领取过优惠券
        //如果是 则return 已经领取
        //2 如果否 将数据插入

//        opid="1";

        System.out.println(appdiscountMapper.hasget2(opid,id).size());
        if(appdiscountMapper.hasget2(opid,id).size()!= 0){
            return JsonResponse.failure("已经领取过啦！不能再次领取哦").setCode(201);
        }

        //List<UserAppdiscount> userAppdiscounts=
        //领取优惠券
        userAppdiscountMapper.insertUserDis(opid,id);
        //-1
        appdiscountMapper.updateAmout(id);


        return JsonResponse.success("已成功领取优惠券！");


    }

    //描述：提供openid 返回平台优惠券列表
    //功能模块：优惠券---显示平台优惠券
    //返回：json数据 平台优惠券列表 只会返回没有超期的优惠券
//    @GetMapping("/list")
//    @ResponseBody
//    public JsonResponse AppDiscountList(@RequestBody Map appDis)throws Exception {
//        if(usermapper.getOpenid(appDis.get("openid").toString())==null){
//            return JsonResponse.failure("openid无效").setCode(201);
//        }
//
//        List<Appdiscount> appDiscount = appdiscountMapper.getAll();
//
//        if(appDiscount.size()==0){
//            return JsonResponse.failure("暂无未超出使用日期的的平台优惠券").setCode(201);
//        }
//        //成功返回
//        return JsonResponse.success(appDiscount);
//    }


    //描述：超级管理员发放平台优惠券
    //输入：发放的优惠券信息 包括 Float disvalue;（面值）（Integer nowamout;）
    // Integer totalamout;Float restriction（满多少）;LocalDate expirytime;Float discharge;
    //nowamount=total
    //输出 ：是否成功发放

    @PostMapping("/IssueAppCoupon")

    @ResponseBody
    public JsonResponse IssueCoupon(@RequestBody Map map){

        //数据类型转换
        float disvalue =Float.parseFloat(map.get("disvalue").toString());
        System.out.println(disvalue);
        int totalamout=Integer.parseInt(map.get("totalamout").toString());
        float restriction=Float.parseFloat(map.get("restriction").toString());
        LocalDate expirytime=LocalDate.parse(map.get("expirytime").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //LocalDate beginDateTime = LocalDate.parse(beginDate, DateTimeFormatter.ofPattern(“yyyy-MM-dd”));
        float discharge=Float.parseFloat(map.get("discharge").toString());

        appdiscountMapper.insertAppDis(disvalue,totalamout,restriction,expirytime,discharge);

        return JsonResponse.success("success");


    }



    //描述：超级管理员统计平台优惠券情况
    //输入：null
    //输出 ：返回所有发放的优惠券领取情况
    @PostMapping("/statisticalDis")
    @ResponseBody
    public JsonResponse statisticalDis(){

        List<Appdiscount> appdiscounts=appdiscountMapper.getAppAll();

        if(appdiscounts.size()==0){
            return JsonResponse.failure("暂无已发放的平台优惠券信息").setCode(201);
        }
        return JsonResponse.success(appdiscounts);
    }




    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Appdiscount  appdiscount =  appdiscountService.getById(id);
        return JsonResponse.success(appdiscount);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        appdiscountService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateAppdiscount(Appdiscount  appdiscount) throws Exception {
        appdiscountService.updateById(appdiscount);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Appdiscount
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Appdiscount  appdiscount) throws Exception {
        appdiscountService.save(appdiscount);
        return JsonResponse.success(null);
    }
}

