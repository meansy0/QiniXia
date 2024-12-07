package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.MgrShopdisService;
import com.example.mybatisplus.model.domain.MgrShopdis;


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
@RequestMapping("/api/mgrShopdis")
public class MgrShopdisController {

    private final Logger logger = LoggerFactory.getLogger( MgrShopdisController.class );

    @Autowired
    private MgrShopdisService mgrShopdisService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        MgrShopdis  mgrShopdis =  mgrShopdisService.getById(id);
        return JsonResponse.success(mgrShopdis);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        mgrShopdisService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateMgrShopdis(MgrShopdis  mgrShopdis) throws Exception {
        mgrShopdisService.updateById(mgrShopdis);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建MgrShopdis
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(MgrShopdis  mgrShopdis) throws Exception {
        mgrShopdisService.save(mgrShopdis);
        return JsonResponse.success(null);
    }
}

