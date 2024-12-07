package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.ManagerMapper;
import com.example.mybatisplus.mapper.ShopMapper;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.Order;
import com.example.mybatisplus.model.domain.OrderStatistics;
import com.example.mybatisplus.model.domain.Shop;
import org.apache.ibatis.javassist.bytecode.ExceptionsAttribute;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.UserService;
import com.example.mybatisplus.model.domain.User;

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
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger( UserController.class );

    @Autowired
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ManagerMapper managerMapper;

    @Resource
    private ShopMapper shopMapper;


    @PostMapping("/apply")
    @ResponseBody
    private JsonResponse apply(@RequestBody Map map){

        //用户信息
        String tel=map.get("mtel").toString();
        String pass=map.get("mpass").toString();
        String email=map.get("email").toString();

        if(tel.length()!=11){
    return JsonResponse.failure("请重新输入正确的11位手机号").setCode(400);
}

        if(managerMapper.hasExist(tel).size()!=0){
        return JsonResponse.failure("该手机号已注册过！请重新输入其他手机号！").setCode(400);
    }


        //店铺信息
        String name=map.get("shopname").toString();
        String type=map.get("shoptype").toString();
        String address=map.get("shopadress").toString();
        String intro=map.get("shopintro").toString();
         //加入图片信息
        String picture=map.get("shoppic").toString();

        //存入个人信息
        managerMapper.insertByTelPass(tel,pass,email);
        //获取管理员id
        Long mid =managerMapper.selectId(tel);

        //存入店铺信息
        shopMapper.insertShopIntro(name, type, address, intro,picture,mid);
        //获取店铺id
        Long shopid =shopMapper.selectShopid(mid);

        //把店铺id存入管理员
        managerMapper.updateShopId(mid,shopid);
        return JsonResponse.success("成功 请等待管理员审核");
    }


//    private Long idReturn;
//
//    //用户信息
//    private String tel;
//    private String pass;
//
//    //店铺信息
//    private String name;
//    private String type;
//    private String address;
//    private String intro;
//    private String picture;
//
//
//    @PostMapping("/apply")
//    @ResponseBody
//    //描述：商家入驻申请
//    //输入信息1：预备管理员个人信息：电话号码，密码
//    //输出：是否成功插入信息
//    //&&code:400 需要刷新页面 用户重新输入数据
//
//    private JsonResponse applyFor1(@RequestBody Map map) throws Exception {
//        //获取用户个人信息
//        tel =map.get("mtel").toString();
//        pass=map.get("mpass").toString();
//
//        if(tel.length()!=11){
//            return JsonResponse.failure("请重新输入正确的11位手机号").setCode(400);
//        }
//
//        if(managerMapper.hasExist(tel).size()!=0){
//
//            return JsonResponse.failure("该手机号已注册过！请重新输入其他手机号！").setCode(400);
//        }
////        idReturn=managerMapper.insertByMessage(tel,pass);
////        if(idReturn==null){
////            return JsonResponse.failure("保存失败,请重新输入！").setCode(400);
////        }
////        //查询预备管理员id
////        idReturn=managerMapper.selectId(tel);
//
//        return JsonResponse.success("保存成功！");
//
//    }
//
//
//    @PostMapping("/apply/shopintro")
//    @ResponseBody
//    //描述：商家入驻申请
//    //输入信息2：预备店铺信息：店铺名 类型 地址 简介 图片
//    //输出：是否成功插入信息
//    //&&code:400 需要刷新页面** 只刷新该界面 预备管理员信息界面不刷新 用户重新输入数据
//    public JsonResponse applyFor2(@RequestBody Map map)throws Exception {
//        //获取预备店铺信息
//         name=map.get("shopname").toString();
//         type=map.get("shoptype").toString();
//         address=map.get("shopadress").toString();
//         intro=map.get("shopintro").toString();
//         //加入图片信息
//        picture=map.get("shoppic").toString();
//
//        if(name==null){
//            return JsonResponse.failure("店铺名不能为空！").setCode(400);
//        }
//
//        //idReturn=2L;
//        //插入
////        Long successid=shopMapper.insertShopIntro(name,type,address,intro,idReturn);
////        if(successid==0){
////            return JsonResponse.failure("插入失败").setCode(400);
////        }
//
//        return JsonResponse.success("申请成功！请等待管理员审核！");
//    }
//
//
//    //描述：保存信息
//    //输入：信号值
//    //将以上两个函数值存入数据库
//    @PostMapping("/save")
//    @ResponseBody
//    public JsonResponse saveInfor(@RequestBody Map map){
//        if(map==null){
//            //直接结束
//            return JsonResponse.failure("");
//        }
//
//        //查询预备管理员id
//        idReturn=managerMapper.selectId(tel);
//        //保存店铺信息
//        shopMapper.insertShopIntro(name,type,address,intro,idReturn,picture);
//        //查询店铺id
//        Long shopid=shopMapper.selectShopid(idReturn);
//        //保存个人信息
//        managerMapper.insertByMessage(tel,pass,shopid);
//        return JsonResponse.success("").setCode(1);
//
//    }


    //描述：显示申请情况
    //包括审核是否成功？ 审核意见
    //输入：手机号（申请成为管理员的手机号）
    //输出：审核信息
    @PostMapping("/applydetails")
    @ResponseBody
    public JsonResponse applyDetail(@RequestBody Map map){
        String tel =map.get("mtel").toString();

        if(tel.length()!=11){
            return JsonResponse.failure("请输入正确的11位手机号！").setCode(400);
        }

        if(managerMapper.hasExist(tel).size()==0){

            //暂无审核
            return JsonResponse.failure("暂无申请信息！").setCode(1);
        }


        //通过电话号码得到管理员id
        Long mid=managerMapper.getByTel(tel);

        //由管理员id获取店铺信息
        Shop shopintro=shopMapper.getByMid(mid);

        if(shopintro.getIscheck()==true){
            //审核通过
            return JsonResponse.failure("审核通过！").setCode(2);
        }

        System.out.println(shopintro.getCheckadvice());
        if(shopintro.getIscheck()==false && shopintro.getCheckadvice()==null){
            //审核中
            return JsonResponse.failure("审核中！").setCode(3);
        }


        System.out.println(shopintro.getIscheck());

        if(shopintro.getIscheck()==false){
            //审核未通过
            return JsonResponse.success(shopintro.getCheckadvice());

        }

        return JsonResponse.success(null);

    }



    //描述：贷币充值
    //输出：openid 充值金额
    @PostMapping("/topup")
    @ResponseBody
    public JsonResponse topUp(@RequestBody Map map){
        String openid= map.get("openid").toString();
        float money=Float.parseFloat(map.get("money").toString());
        float test=1.1f;
        money=money*test;
/////////***********wechatpay
        userMapper.updateRemain(openid,money);

        //显示余额
        float remain=userMapper.getRemainByOpenid(openid);


        return JsonResponse.success(remain);

    }


    //描述：显示余额
    //输入：openid
    @PostMapping("/balance")
    @ResponseBody
    public JsonResponse balance(@RequestBody Map map){
        String openid= map.get("openid").toString();
        User user=new User();

        System.out.println(userMapper.getRemainByOpenid(openid));

        return JsonResponse.success(userMapper.getRemainByOpenid(openid));
    }



    //描述：显示用户消费的情况
    @PostMapping("/static")
    @ResponseBody
    public JsonResponse staticUser(@RequestBody Map map){

        //get openid
        String openid= map.get("openid").toString();

        //getid
        Long id = userMapper.getIdByOpenid(openid);

        //查询订单数
        OrderStatistics orderStatistics =userMapper.selectCountUser(id);
        if(orderStatistics.getUsermoney()==0){
            orderStatistics.setGasStation(0);
            orderStatistics.setAccommodation(0);
            orderStatistics.setFood(0);
            orderStatistics.setShopping(0);
            orderStatistics.setRepair(0);
            orderStatistics.setHelp(0);
            orderStatistics.setAgricultural(0);

            return JsonResponse.success(orderStatistics);
        }

        //

        return JsonResponse.success(orderStatistics);

    }



    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        User  user =  userService.getById(id);
        return JsonResponse.success(user);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        userService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateUser(User  user) throws Exception {
        userService.updateById(user);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建User
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(User  user) throws Exception {
        userService.save(user);
        return JsonResponse.success(null);
    }
}

