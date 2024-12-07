package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Goods;
import com.example.mybatisplus.mapper.GoodsMapper;
import com.example.mybatisplus.service.GoodsService;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

}
