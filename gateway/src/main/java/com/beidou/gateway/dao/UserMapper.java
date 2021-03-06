package com.beidou.gateway.dao;


import com.beidou.gateway.entity.User;
import com.beidou.gateway.entity.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
@Mapper
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //读取公司下面用户
    List<User> selectByComId(Integer comId);

    //根据用户名查询
    List<User> searchByUserName(String username);

    //根据用户名、公司编号查询
    List<User> searchByUserNameAndComId(@Param("username") String username, @Param("comId") Integer comId);

    //用户名查重
    User judgeUsername(String username);


}