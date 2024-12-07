package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.UserGoods;
import com.example.mybatisplus.mapper.UserGoodsMapper;
import com.example.mybatisplus.service.UserGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gxy
 * @since 2022-03-01
 */
@Service
public class UserGoodsServiceImpl extends ServiceImpl<UserGoodsMapper, UserGoods> implements UserGoodsService {

}
