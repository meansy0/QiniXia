package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Appdiscount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.domain.UserAppdiscount;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxy
 * @since 2022-03-01
 */
public interface AppdiscountMapper extends BaseMapper<Appdiscount> {




    List<Appdiscount> hasget1(String opid);

    List<Appdiscount> getAllOut(String opid);

    List<Appdiscount> getHasnot(String opid);


    List<Appdiscount> hasget2(String opid, String id);

    void insertAppDis(float disvalue, int totalamout, float restriction, LocalDate expirytime, float discharge);

    List<Appdiscount> getAppAll();

    void updateAmout(String id);


//    void updateAmout();

}
