package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Indent;
import com.example.mybatisplus.model.domain.UserGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.domain.userStat;
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
public interface UserGoodsMapper extends BaseMapper<UserGoods> {

    UserGoods selectOrder(Long id);

    UserGoods queryIf(Long id);

    void finishOrder(Long id);

    void setStatus(int payway,Long orderId);

    List<UserGoods> queryOrder(Long id);

    List<userStat> count();

    List<userStat> countShopUser(Long shopid);

    List<UserGoods> queryShop(Long shopId);

    Float queryAllIncome(Long shopId);

    Float queryMonthIncome(Long shopId);

    Float queryYearIncome(Long shopId);

    List<Float> incomeRank();

    Long getGidByid(Long id);

    List<UserGoods> queryFinishOrder(Long id);

    List<Indent> queryFinishList(Long id);

}
