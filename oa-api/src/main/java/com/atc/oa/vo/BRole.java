package com.atc.oa.vo;

import java.io.Serializable;

/**
 * BRole 角色的页面对象
 *
 * @author Mengke
 * @version 1.0.0
 */
public class BRole implements Serializable {

    private static final long serialVersionUID = -8555243399798375804L;

    private Long id;
    private String roleName;
    private String roleCode;
    private String popedomSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getPopedomSet() {
        return popedomSet;
    }

    public void setPopedomSet(String popedomSet) {
        this.popedomSet = popedomSet;
    }
}