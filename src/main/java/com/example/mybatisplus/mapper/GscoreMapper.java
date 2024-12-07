package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Gscore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxy
 * @since 2022-03-11
 */
public interface GscoreMapper extends BaseMapper<Gscore> {

    void updateGscore(Long id, float score);

    void InsertidScore(Long orderId);


    //void updateGidScore(Long orderId);

}
