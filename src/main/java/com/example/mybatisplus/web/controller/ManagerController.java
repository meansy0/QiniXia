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
import com.example.mybatisplus.service.ManagerService;
import com.example.mybatisplus.model.domain.userStat;

import java.util.*;
import java.util.stream.Collectors;

import com.example.mybatisplus.mapper.GoodsMapper;


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
@RequestMapping("/api/manager")
public class ManagerController {

    private final Logger logger = LoggerFactory.getLogger( ManagerController.class );

    @Autowired
    private ManagerService managerService;

    @Autowired
    private SupermanagerMapper supermanagerMapper;
    @Autowired
    private UserGoodsMapper userGoodsMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private ShopMapper shopMapper;


    //描述：管理员登录 &普通管理员  超级管理员
    //输出：电话号码&密码
    //输出：登录情况&&管理员id
    //############将普通管理员的id=1的情况锁定  用id可唯一对应具体管理员 id=1 平台管理员
    //其他：店铺管理员
    @PostMapping("/login")
    @ResponseBody
    public JsonResponse login(@RequestBody Map map) throws Exception{
        String tel =map.get("tel").toString();
        String pass = map.get("pass").toString();

//        System.out.println(tel);
        Supermanager supermanagers=supermanagerMapper.selectByTelPass(tel,pass);
//        System.out.println(supermanagers);
        if(supermanagers!=null){
            //超级管理员登录成功
            return JsonResponse.failure(supermanagers.getId().toString()).setCode(1);
        }

        Manager managers=managerMapper.selectByTelPass(tel,pass);



        if(managers!=null){

            //获取管理员类型
            boolean type=managerMapper.selectTypeByTel(tel);

            if(type==true){
                //普通管理员登录成功
                return JsonResponse.failure(managers.getId().toString()).setCode(2);

            }

            else{
                return JsonResponse.failure("尚未通过审核！").setCode(3);
            }

        }
        return JsonResponse.success("电话号码或密码错误 请重新登录!");
    }



    //描述：管理员修改密码
    //输入：tel&pass
    //输出：success？fail
    ///////////////// return0  密码错误 return 1 成功修改
    @PostMapping("/editpass")
    @ResponseBody
    public int editPass(@RequestBody Map map){



        //get infor
        Long id=Long.parseLong(map.get("id").toString());
        String oldpass = map.get("oldpass").toString();
        String newpass = map.get("newpass").toString();

        //超管？
        if(id==1L){
            //超级管理员
            //验证old密码
            List<Supermanager> supermanagers=supermanagerMapper.selectByPass(id,oldpass);

            if(supermanagers.size()==0){
                //密码错误

                return 0;
            }
            //修改密码
            supermanagerMapper.updatePass(id,newpass);
            //修改成功
            return 1;
        }

        //普通管理员
        List<Manager> managers= managerMapper.selectByPass(id,oldpass);
        if(managers.size()==0){
            //密码错误
            return 0;
        }

        //修改密码
        managerMapper.updatePass(id,newpass);
        //修改成功
        return 1;


    }




    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Manager  manager =  managerService.getById(id);
        return JsonResponse.success(manager);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        managerService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateManager(Manager  manager) throws Exception {
        managerService.updateById(manager);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Manager
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Manager  manager) throws Exception {
        managerService.save(manager);
        return JsonResponse.success(null);
    }
    //统计用户作为柱状图
    @RequestMapping("/userStat")
    @ResponseBody
    public List<Integer> userStat(@RequestBody Map shopid) throws Exception{
        Long id = Long.parseLong(shopid.get("id").toString());
        Long shopId = managerMapper.getShopIdById(id);
        List<userStat> userStats = userGoodsMapper.countShopUser(shopId);
        List<Integer> orders=userStats.stream().map(userStat::getUser_num).collect(Collectors.toList());
        return orders;
    }
    //对商品进行增删查改
    //增加
    @RequestMapping("/addGoods")
    @ResponseBody
    public void addGoods(@RequestBody Map goodsinfo) throws Exception{
        Long id = Long.parseLong(goodsinfo.get("id").toString());
        if(id==null){
            System.out.println("无法获取id！");
        }
        Long shopId = managerMapper.getShopIdById(id);
        float price =  Float.valueOf(goodsinfo.get("price").toString());
        String goodsintro = goodsinfo.get("goodsintro").toString();
        int leftamout = Integer.parseInt(goodsinfo.get("totalamout").toString());
        int totalamout = Integer.parseInt(goodsinfo.get("totalamout").toString());
        String goodsname = goodsinfo.get("goodsname").toString();
        String goodspic = goodsinfo.get("goodspic").toString();
        Goods goods =new Goods();
        goods.setShopId(shopId);
        goods.setPrice(price);
        goods.setGoodsintro(goodsintro);
        goods.setLeftamout(leftamout);
        goods.setTotalamout(totalamout);
        goods.setGoodsname(goodsname);
        goods.setGoodspic(goodspic);
        goodsMapper.insert(goods);
    }
    //删除
    @RequestMapping("/deletegoods")
    @ResponseBody
    public void delete(@RequestBody Map goodsinfo) throws Exception{
        //Long shopId = Long.parseLong(goodsinfo.get("shopid").toString());
        Long Id = Long.parseLong(goodsinfo.get("id").toString());
        goodsMapper.deleteGoodsById(Id);

    }
    //查找所有商品
    @RequestMapping("/queryGoods")
    @ResponseBody
    public List<Goods> queryGoods(@RequestBody Map shopid) throws Exception{
        Long id = Long.parseLong(shopid.get("id").toString());
        Long shopId = managerMapper.getShopIdById(id);
        List<Goods> goods = goodsMapper.selectByShopId(shopId);
        return goods;
    }
    //更新商品信息
    @RequestMapping("/updategoods")
    @ResponseBody
    public void update(@RequestBody Map goodsinfo) throws Exception{
        System.out.println(goodsinfo.get("id").toString());
        Long Id = Long.parseLong(goodsinfo.get("id").toString());

        float price =  Float.valueOf(goodsinfo.get("price").toString());
        String goodsintro = goodsinfo.get("goodsintro").toString();
        String goodspic = goodsinfo.get("goodspic").toString();
        String goodsname = goodsinfo.get("goodsname").toString();
        System.out.println(goodsname);
        Goods goods =new Goods();
        System.out.println(goodspic);
        goods.setId(Id);
        goods.setPrice(price);
        goods.setGoodsintro(goodsintro);
        goods.setGoodsname(goodsname);
        goods.setGoodspic(goodspic);

        System.out.println(goodsname);

        //goods.setGoodspic(goodspic);
        goodsMapper.updateById(goods);
    }
    @RequestMapping("/queryOrder")
    @ResponseBody
    public List<UserGoods> queryShopOrder(@RequestBody Map shopinfo){
        Long id = Long.parseLong(shopinfo.get("id").toString());
        Long shopId = managerMapper.getShopIdById(id);
        List<UserGoods> shopgoods = userGoodsMapper.queryShop(shopId);
        return shopgoods;
    }
    //查询总收入 月收入 年收入 位次
    @RequestMapping("/queryIncome")
    @ResponseBody
    public IncomeRank queryAllIncome(@RequestBody Map shopinfo){
        Long id = Long.parseLong(shopinfo.get("id").toString());
        Long shopId = managerMapper.getShopIdById(id);
        List<Float> shopRank = userGoodsMapper.incomeRank();
        System.out.println(shopRank);
        Float allIncome = userGoodsMapper.queryAllIncome(shopId);
        IncomeRank incomeRank = new IncomeRank();
        incomeRank.setAllIncome(allIncome);
        incomeRank.setMonthIncome(userGoodsMapper.queryMonthIncome(shopId));
        incomeRank.setYearIncome(userGoodsMapper.queryYearIncome(shopId));
        incomeRank.setIncomeRank(shopRank.indexOf(allIncome)+1);
        incomeRank.setFavoriteNum(favoriteMapper.countByshopid(shopId));
        System.out.println(incomeRank);
        return incomeRank;
    }
    @RequestMapping("/queryShop")
    @ResponseBody
    public Shop queryShop(@RequestBody Map managerId){
        Long id = Long.parseLong(managerId.get("id").toString());
        Shop shop = shopMapper.getByMid(id);
        return shop;
    }
    @RequestMapping("/updateShop")
    @ResponseBody
    public String updateShop(@RequestBody Map shopinfo){
        Long managerid = Long.parseLong(shopinfo.get("id").toString());
        Long id = managerMapper.getShopIdById(managerid);
        String address = shopinfo.get("shopadress").toString();
        String shopintro = shopinfo.get("shopintro").toString();
        String shopname = shopinfo.get("shopname").toString();
        Shop shop = new Shop();
        shop.setIscheck(false);
        managerMapper.setType(id);
        shop.setShopadress(address);
        shop.setShopname(shopname);
        shop.setShopintro(shopintro);
        shop.setId(id);
        shopMapper.updateById(shop);
        return "success";
    }
    @RequestMapping("/updateAmount")
    @ResponseBody
    public String updateAmount(@RequestBody Map amount){
        Long id = Long.parseLong(amount.get("id").toString());
        int amt=Integer.valueOf(amount.get("amount").toString());
        int left = goodsMapper.queryCount(id);
        if(amt<=left){
            goodsMapper.subCount(id,left-amt);
    }
        else {
            goodsMapper.addCount(id,amt-left);
        }
        return "success";}
}

