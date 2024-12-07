package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Manager;
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
public interface ManagerMapper extends BaseMapper<Manager> {


    List<Manager> hasExist(String tel);

    Long insertByMessage(String tel, String pass,Long shopid);

    Long selectId(String tel);

    Long getByTel(String tel);

    void updateMtype(Long id);

    Manager selectByTelPass(String tel, String pass);

    Manager getByMid(Long managerId);

    boolean selectTypeByTel(String tel);

    void setType(Long id);

    Long getShopIdById(Long id);

    List<Manager> selectByPass(Long id, String oldpass);

    void updatePass(Long id, String newpass);

    Manager selectEmailById(String id);

    void updateByEmail(String newpass,String tel);

    List<Manager> selectByTel(String tel);

    void updateCode(String tel,String checkCode);

    String selectCheckcode(String tel);

    void insertByTelPass(String tel, String pass, String email);

    void updateShopId(Long mid, Long shopid);

    List<Manager> selectEmail(String tel);



//    void selectEmailById(Long id);



//    boolean selectTypeById();



//    void updateCheckAdvice();


//    List<Manager> getNotCheck();

}
