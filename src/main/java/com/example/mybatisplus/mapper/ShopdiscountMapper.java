package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Appdiscount;
import com.example.mybatisplus.model.domain.Shopdiscount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface ShopdiscountMapper extends BaseMapper<Shopdiscount> {


    List<Appdiscount> ShopDisAll(String shopIds);

    List<Shopdiscount> hasget1(Long shopid,Long oid);

    List<Shopdiscount> getHasnot(Long shopid, Long oid);


    List<Shopdiscount> hasget2(Long oid, Long id);

    void insertShopDis(float disvalue, int totalamout, float restriction, LocalDate expirytime, float discharge,Long shopId);

    List<Shopdiscount> getByShopId(Long shopId);

    void updateamout(Long id);

}
