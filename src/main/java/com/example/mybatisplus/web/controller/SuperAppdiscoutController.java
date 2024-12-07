package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.SuperAppdiscoutService;
import com.example.mybatisplus.model.domain.SuperAppdiscout;


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
@RequestMapping("/api/superAppdiscout")
public class SuperAppdiscoutController {

    private final Logger logger = LoggerFactory.getLogger( SuperAppdiscoutController.class );

    @Autowired
    private SuperAppdiscoutService superAppdiscoutService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        SuperAppdiscout  superAppdiscout =  superAppdiscoutService.getById(id);
        return JsonResponse.success(superAppdiscout);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        superAppdiscoutService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateSuperAppdiscout(SuperAppdiscout  superAppdiscout) throws Exception {
        superAppdiscoutService.updateById(superAppdiscout);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建SuperAppdiscout
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(SuperAppdiscout  superAppdiscout) throws Exception {
        superAppdiscoutService.save(superAppdiscout);
        return JsonResponse.success(null);
    }
}

