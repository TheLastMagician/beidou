package com.beidou.gateway.controller;

import com.beidou.common.annotation.SysLogger;
import com.beidou.common.entity.ResponseMsg;
import com.beidou.common.util.StringUtil;
import com.beidou.gateway.entity.Rule;
import com.beidou.gateway.service.RuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "RuleController|权限管理操作")
@RestController
@RequestMapping("/api/v1/user")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @SysLogger("添加权限信息")
    @RequiresPermissions("rule:create")
    @ApiOperation(value="添加权限信息", notes="添加权限信息")
    @PostMapping(value = "/rule")
    public ResponseMsg insert(Rule rule){
        return ruleService.insert(rule);
    }



    @SysLogger("获取id对应的权限信息")
    @RequiresPermissions("rule:read")
    @ApiOperation(value="获取id对应的权限信息", notes="获取id对应的权限信息")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "int", paramType="path")
    })
    @GetMapping(value="/rule/{id}")
    public ResponseMsg getById(@PathVariable("id")Integer id){
        return ruleService.getById(id);
    }


    @SysLogger("更新id对应的权限信息")
    @RequiresPermissions("rule:update")
    @ApiOperation(value="更新id对应的权限信息", notes="更新id对应的权限信息")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "int", paramType="path")
    })
    @PutMapping(value="/rule/{id}")
    public ResponseMsg updateById(Rule rule){
        return ruleService.updateById(rule);
    }


    @SysLogger("删除id对应的权限信息")
    @RequiresPermissions("rule:delete")
    @ApiOperation(value="删除id对应的权限信息", notes="删除id对应的权限信息")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "权限ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping(value="/rule/{ids}")
    public ResponseMsg deleteById(@PathVariable("ids")String ids){
        ResponseMsg responseMsg;
        if(!StringUtil.isEmpty(ids)){
            //批量删除
            if(ids.contains(",")){
                List<Integer> del_ids = new ArrayList();
                String[] str_ids = ids.split(",");
                //组装id的集合
                for (String string : str_ids) {
                    del_ids.add(Integer.parseInt(string));
                }
                responseMsg=ruleService.deleteBatch(del_ids);
            }else{
                Integer id = Integer.parseInt(ids);
                responseMsg=ruleService.deleteById(id);
            }
            return responseMsg;
        }else{
            return ResponseMsg.Error("请选择要删除的权限");
        }
    }



    @SysLogger("获取权限信息列表")
    @RequiresPermissions("rules:read")
    @ApiOperation(value="获取权限信息列表", notes="获取权限信息列表")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, dataType = "int", paramType="query")
    })
    @GetMapping(value="/rules")
    public ResponseMsg getList(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum ){
        return ruleService.getList(pageNum);
    }

    @SysLogger("获取角色对应权限")
    @RequiresPermissions("roleRule:read")
    @ApiOperation(value="获取角色对应权限", notes="获取角色对应权限")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "int", paramType="query")
    })
    @GetMapping(value="/roleRule")
    public ResponseMsg getRoleRule(@RequestParam("roleId")Integer roleId ){
        return ruleService.getRoleRule(roleId);
    }


    @SysLogger("查找权限")
    @RequiresPermissions("rule:searchByName")
    @ApiOperation(value="查找权限", notes="查找权限")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码（第一次可以不用传）", required = false, dataType = "int", paramType="query"),
            @ApiImplicitParam(name = "name", value = "权限名", required = true, dataType = "String", paramType="query")
    })
    @GetMapping(value="/rule/searchByName")
    public ResponseMsg searchByName(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,@RequestParam(value = "name")String name ){
        return ruleService.searchByName(pageNum,name);
    }

    @SysLogger("获取所有权限")
    @RequiresPermissions("rule:getAll")
    @ApiOperation(value="获取所有权限", notes="获取所有权限")// 使用该注解描述接口方法信息
    @GetMapping(value="/rule/getAll")
    public ResponseMsg getAll(){
        return ruleService.getAll();
    }

}
