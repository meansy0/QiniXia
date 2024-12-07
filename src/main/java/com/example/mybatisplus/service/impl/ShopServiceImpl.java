package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Shop;
import com.example.mybatisplus.mapper.ShopMapper;
import com.example.mybatisplus.service.ShopService;
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
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

}
