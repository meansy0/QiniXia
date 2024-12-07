package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Advertise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.domain.Shop;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxy
 * @since 2022-03-05
 */
public interface AdvertiseMapper extends BaseMapper<Advertise> {

    List<Advertise> selectByShopId(Long shopIds);

    void insertShopId(Long shopIds);

    void deleteByShopId(Long deleteId);

    List<Advertise> selectAll();

//    List<Shop> selectAllInfor();

}
