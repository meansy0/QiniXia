package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Favorite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxy
 * @since 2022-03-08
 */
@Repository
public interface FavoriteMapper extends BaseMapper<Favorite> {

    List<Favorite> selectByIds(Long userid, Long shopid);

    void insertByIds(Long userid, Long shopid);

    //统计某店铺的收藏量
    int countByshopid(Long shopid);

}
