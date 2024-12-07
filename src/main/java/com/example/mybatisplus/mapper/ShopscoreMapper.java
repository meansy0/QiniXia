package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Shopscore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxy
 * @since 2022-03-07
 */
public interface ShopscoreMapper extends BaseMapper<Shopscore> {

    void updatescore(Long shopId, float score);

}
