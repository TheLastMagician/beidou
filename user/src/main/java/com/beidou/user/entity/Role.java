package com.beidou.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "角色信息类")
public class Role {

    @ApiModelProperty(value="角色id", hidden=false, required=false, dataType="Integer")
    private Integer id;

    @ApiModelProperty(value="角色名称", hidden=false, required=true, dataType="String")
    private String rolename;

    @ApiModelProperty(value="角色描述", hidden=false, required=true, dataType="String")
    private String description;

    @ApiModelProperty(value="创建时间", hidden=false, required=false, dataType="String")
    private String createdate;

    @ApiModelProperty(value="修改时间", hidden=false, required=false, dataType="String")
    private String modifydate;

    //角色权限
    //private Set<Rule> permissions = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate == null ? null : modifydate.trim();
    }
}