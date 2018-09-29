package com.atc.oa.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Employee - 用户表
 *
 * @author Lijin
 * @version 1.0.0
 */
@Entity
@Table(name = "fmgt_employee")
public class Employee extends BaseEntity<Long> {

    private static final long serialVersionUID = -6217646830420027288L;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;

    /** 名字 */
    private String name;

    /** 备注 */
    private String remarks;

    /** 所属角色 */
    private Set<Role> roleSet = new HashSet<>();

    /** 所属部门 */
    private Department department;

    /** 是否启用 */
    private Boolean isEnabled;

    /** 创建信息 */
    private OperateInfo createdInfo;

    /** 修改信息 */
    private OperateInfo updatedInfo;

    /** token */
    private String token;

    /** 首次登录是否修改密码 */
    private byte firstUpdate;

    /** 岗位 */
    private String post;

    public Employee() {

    }

    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "namespace", column = @Column(name = "created_opera_ns")),
            @AttributeOverride(name = "id", column = @Column(name = "created_opera_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "created_opera_name"))
    })
    public OperateInfo getCreatedInfo() {
        return createdInfo;
    }

    public void setCreatedInfo(OperateInfo createdInfo) {
        this.createdInfo = createdInfo;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "namespace", column = @Column(name = "updated_opera_ns")),
            @AttributeOverride(name = "id", column = @Column(name = "updated_opera_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "updated_opera_name"))
    })
    public OperateInfo getUpdatedInfo() {
        return updatedInfo;
    }

    public void setUpdatedInfo(OperateInfo updatedInfo) {
        this.updatedInfo = updatedInfo;
    }

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(name = "fmgt_employee_role",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}