package com.beidou.gateway.controller;


import com.beidou.common.annotation.SysLogger;
import com.beidou.common.entity.ResponseMsg;
import com.beidou.common.util.StringUtil;
import com.beidou.gateway.entity.Role;
import com.beidou.gateway.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "RoleController|角色管理操作")
@RestController
@RequestMapping("/api/v1/user")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @SysLogger("添加角色信息")
    @RequiresPermissions("role:create")
    @ApiOperation(value="添加角色信息", notes="添加角色信息",produces ="application/json")
    @PostMapping(value = "/role")
    public ResponseMsg insert(Role role){
        return roleService.insert(role);
    }

    @SysLogger("获取id对应的角色信息")
    @RequiresPermissions("role:read")
    @ApiOperation(value="获取id对应的角色信息", notes="获取id对应的角色信息")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "int", paramType="path")
    })
    @GetMapping(value="/role/{id}")
    public ResponseMsg getById(@PathVariable("id")Integer id){
        return roleService.getById(id);
    }


    @SysLogger("更新id对应的角色信息")
    @RequiresPermissions("role:update")
    @ApiOperation(value="更新id对应的角色信息", notes="更新id对应的角色信息")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "int", paramType="path")
    })
    @PutMapping(value="/role/{id}")
    public ResponseMsg updateById(Role role){
        return roleService.updateById(role);
    }


    @SysLogger("删除id对应的角色信息")
    @RequiresPermissions("role:delete")
    @ApiOperation(value="删除id对应的角色信息", notes="删除id对应的角色信息")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "角色ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping(value="/role/{ids}")
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
                responseMsg=roleService.deleteBatch(del_ids);
            }else{
                Integer id = Integer.parseInt(ids);
                responseMsg=roleService.deleteById(id);
            }
            return responseMsg;
        }else{
            return ResponseMsg.Error("请选择要删除的角色");
        }
    }


    @SysLogger("获取角色信息列表")
    @RequiresPermissions("roles:read")
    @ApiOperation(value="获取角色信息列表", notes="获取角色信息列表")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, dataType = "int", paramType="query")
    })
    @GetMapping(value="/roles")
    public ResponseMsg getList(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum ){
        return roleService.getList(pageNum);
    }

    @SysLogger("查找角色")
    @RequiresPermissions("role:searchByName")
    @ApiOperation(value="查找角色", notes="查找角色")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码（第一次可以不用传）", required = false, dataType = "int", paramType="query"),
            @ApiImplicitParam(name = "name", value = "角色名", required = true, dataType = "String", paramType="query")
    })
    @GetMapping(value="/role/searchByName")
    public ResponseMsg searchByName(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,@RequestParam(value = "name")String name ){
        return roleService.searchByName(pageNum,name);
    }

    @SysLogger("获取用户对应角色")
    @RequiresPermissions("role:userRole")
    @ApiOperation(value="获取用户对应角色", notes="获取用户对应角色")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int", paramType="query")
    })// 使用该注解描述方法参数信息，此处需要注意的是paramType参数，需要配置成path，否则在UI中访问接口方法时，会报错
    @GetMapping(value="/userRole")
    public ResponseMsg getUserRole(@RequestParam("userId")Integer userId ){
        return roleService.getUserRole(userId);
    }

    @SysLogger("获取所有角色")
    @RequiresPermissions("role:getAll")
    @ApiOperation(value="获取所有角色", notes="获取所有角色")// 使用该注解描述接口方法信息
    @GetMapping(value="/role/getAll")
    public ResponseMsg getAll(){
        return roleService.getAll();
    }

}
