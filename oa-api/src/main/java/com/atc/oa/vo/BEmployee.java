package com.atc.oa.vo;

import java.io.Serializable;

/**
 * BEmployee 用户页面对象
 *
 * @author Mengke
 * @version 1.0.0
 */
public class BEmployee implements Serializable {

    private static final long serialVersionUID = 2720027332108002467L;

    /** 名字 */
    private String name;

    /** 备注 */
    private String remarks;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 所属角色 */
    private String roleIds;

    /** 所属小组 */
    private Long groupId;

    /** 是否启用 */
    private Boolean isEnabled;

    /** token */
    private String token;

    /** 首次登录是否修改密码 */
    private byte firstUpdate;

    /** 岗位 */
    private String post;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public byte getFirstUpdate() {
        return firstUpdate;
    }

    public void setFirstUpdate(byte firstUpdate) {
        this.firstUpdate = firstUpdate;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}