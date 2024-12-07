package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Manager;
import com.example.mybatisplus.mapper.ManagerMapper;
import com.example.mybatisplus.service.ManagerService;
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
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

}
