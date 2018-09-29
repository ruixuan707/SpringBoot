package com.atc.oa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Popedom - 权限表
 *
 * @author Lijin
 * @version 1.0.0
 */
@Entity
@Table(name = "fmgt_role")
public class Role extends BaseEntity<Long> {

    private static final long serialVersionUID = -2934917015759006133L;
    /** 角色编码 */
    private String roleCode;

    /** 角色名字 */
    private String roleName;

    /** 权限 */
    private Set<Popedom> popedomSet = new HashSet<>();

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(name = "fmgt_role_popedom", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "popedom_id", referencedColumnName = "id"))
    public Set<Popedom> getPopedomSet() {
        return popedomSet;
    }

    public void setPopedomSet(Set<Popedom> popedomSet) {
        this.popedomSet = popedomSet;
    }
}
