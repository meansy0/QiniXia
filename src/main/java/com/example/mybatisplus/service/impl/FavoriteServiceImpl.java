package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Favorite;
import com.example.mybatisplus.mapper.FavoriteMapper;
import com.example.mybatisplus.service.FavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gxy
 * @since 2022-03-08
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {

}
