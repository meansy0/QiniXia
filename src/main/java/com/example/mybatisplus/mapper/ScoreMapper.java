package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxy
 * @since 2022-03-08
 */
public interface ScoreMapper extends BaseMapper<Score> {

    void updatescore(Long shopId, float score);

    void insertShopidScore(Long id);

}
