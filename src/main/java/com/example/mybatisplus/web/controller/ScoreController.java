package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.*;
import com.example.mybatisplus.service.GscoreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ScoreService;
import com.example.mybatisplus.model.domain.Score;

import javax.annotation.Resource;
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
@RequestMapping("/api/score")
public class ScoreController {

    private final Logger logger = LoggerFactory.getLogger( ScoreController.class );

    @Autowired
    private ScoreService scoreService;

    @Resource
    private GscoreMapper gscoreMapper;


    @Resource
    private UserGoodsMapper userGoodsMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private ScoreMapper scoreMapper;

    //描述：接收评分并传回计算评分表
    //输入：订单id 评分1-5
    //输出：评分成功
    ///注意：在订单完成后才可以进行评价！
    @PostMapping("/getscore")
    @ResponseBody
    public JsonResponse getscore(@RequestBody Map map)throws Exception{
        //get 订单id
        Long id =Long.parseLong(map.get("id").toString());
        //get score
        float score = Float.parseFloat(map.get("score").toString());

        //根据订单id获取商品id
        Long goodsId=userGoodsMapper.getGidByid(id);

        //根据商品id获取店铺id
        Long shopId =goodsMapper.getSidByid(goodsId);

        //根据店铺id更新score表中的对应店铺评分
        scoreMapper.updatescore(shopId,score);

        //根据订单id把评分存入gscore表
        gscoreMapper.updateGscore(id,score);

        return JsonResponse.success("评分成功！");
    }


    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Score  score =  scoreService.getById(id);
        return JsonResponse.success(score);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        scoreService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateScore(Score  score) throws Exception {
        scoreService.updateById(score);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Score
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Score  score) throws Exception {
        scoreService.save(score);
        return JsonResponse.success(null);
    }
}

