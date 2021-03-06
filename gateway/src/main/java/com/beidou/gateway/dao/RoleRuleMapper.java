package com.beidou.gateway.dao;


import com.beidou.gateway.entity.RoleRule;
import com.beidou.gateway.entity.RoleRuleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
@Mapper
public interface RoleRuleMapper {
    long countByExample(RoleRuleExample example);

    int deleteByExample(RoleRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleRule record);

    int insertSelective(RoleRule record);

    List<RoleRule> selectByExample(RoleRuleExample example);

    RoleRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleRule record, @Param("example") RoleRuleExample example);

    int updateByExample(@Param("record") RoleRule record, @Param("example") RoleRuleExample example);

    int updateByPrimaryKeySelective(RoleRule record);

    int updateByPrimaryKey(RoleRule record);

    //批量添加
    int batchInsert(List<RoleRule> roleRules);
}