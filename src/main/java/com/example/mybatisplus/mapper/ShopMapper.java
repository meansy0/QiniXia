package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxy
 * @since 2022-03-01
 */
@Repository
public interface ShopMapper extends BaseMapper<Shop> {


    List<Shop> getAll(String shoptype);


    Long insertShopIntro(String name, String type, String address, String intro,String picture,Long mid);

    Shop getByMid(Long mid);

    List<Shop> getNotCheck();

    void updateCheckAdvice(String advice,Long mid);

    void updateIsCheck(Long mid);

    List<Shop> selectByShopId();

    List<Shop> selectByShopId2();

    List<Shop> selectAllInfor();

    List<Shop> selectMyCollect(Long userid);

    Long selectShopid(Long idReturn);

    Long selectMid(Long shopid);

}
