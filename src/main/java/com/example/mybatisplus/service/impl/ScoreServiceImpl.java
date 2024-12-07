package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Score;
import com.example.mybatisplus.mapper.ScoreMapper;
import com.example.mybatisplus.service.ScoreService;
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
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

}
