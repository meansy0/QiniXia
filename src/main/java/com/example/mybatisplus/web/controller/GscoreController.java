package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.GscoreService;
import com.example.mybatisplus.model.domain.Gscore;


/**
 *
 *  前端控制器
 *
 *
 * @author gxy
 * @since 2022-03-11
 * @version v1.0
 */
@Controller
@RequestMapping("/api/gscore")
public class GscoreController {

    private final Logger logger = LoggerFactory.getLogger( GscoreController.class );

    @Autowired
    private GscoreService gscoreService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Gscore  gscore =  gscoreService.getById(id);
        return JsonResponse.success(gscore);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        gscoreService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateGscore(Gscore  gscore) throws Exception {
        gscoreService.updateById(gscore);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Gscore
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Gscore  gscore) throws Exception {
        gscoreService.save(gscore);
        return JsonResponse.success(null);
    }
}

