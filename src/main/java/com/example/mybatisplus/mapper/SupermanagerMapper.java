package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Supermanager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gxy
 * @since 2022-03-01
 */
@Repository
public interface SupermanagerMapper extends BaseMapper<Supermanager> {

    Supermanager selectByTelPass(String tel, String pass);

    List<Supermanager> selectByPass(Long id,String oldpass);

    void updatePass(Long id,String newpass);



//    void selectByPass(String oldpass);

}
