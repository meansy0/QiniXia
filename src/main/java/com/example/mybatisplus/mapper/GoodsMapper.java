package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.domain.Shop;
import com.example.mybatisplus.model.domain.UserGoods;
import com.example.mybatisplus.model.domain.goodsStat;
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
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> selectByShopId(Long id);

    int queryCount(Long goo_id);

    void subCount(Long goo_id,int amount);
    //获取店铺所有商品
    List<Goods> getByShopId(Long shopid);

    List<goodsStat> goodStat(Long shopid);

    void addCount(Long goo_id, int amount);

    Goods getInforById(Long id);

    List<Goods> selectByName(String goodsname,String goodsintro);

    Long getSidByid(Long goodsId);

    List<Goods> selectHot10();

    void deleteGoodsById(Long id);
}
