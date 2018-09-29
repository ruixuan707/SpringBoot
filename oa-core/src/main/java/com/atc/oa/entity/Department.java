package com.atc.oa.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Department - 部门
 *
 * @author Lijin
 * @version 1.0.0
 */
@Entity
@Table(name = "fmgt_department", indexes = {
        @Index(name = "idx_fmgt_department_1", columnList = "depCode"),
        @Index(name = "idx_fmgt_department_2", columnList = "parentId"),
        @Index(name = "idx_fmgt_department_3", columnList = "name")
})
public class Department extends BaseEntity<Long> {

    private static final long serialVersionUID = -1454689787051075403L;

    /** 部门编码 */
    private String depCode = "1";
    /** 上级部门 */
    private Long parentId;
    /** 部门类型 */
    private Integer depType;
    /** 员工人数 */
    private Integer count = 0;
    /** 事业部名称 */
    private String businessName;
    /** 事业部Id */
    private Long businessId;
    /** 部门名称 */
    private String depName;
    /** 部门Id */
    private Long depId;
    /** 组织名称 */
    private String organizationName;
    /** 组织Id */
    private Long organizationId;
    /** 名称 */
    private String name;
    /** 创建信息 */
    private OperateInfo createdInfo;
    /** 修改信息 */
    private OperateInfo updatedInfo;

    /** 名称 */
    private String groupName;

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    @Column(nullable = false, updatable = false, length = 20)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getDepType() {
        return depType;
    }

    public void setDepType(Integer depType) {
        this.depType = depType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "namespace", column = @Column(name = "created_opera_ns", nullable = true, length = 30)),
            @AttributeOverride(name = "id", column = @Column(name = "created_opera_id", nullable = true, length = 20)),
            @AttributeOverride(name = "fullName", column = @Column(name = "created_opera_name", nullable = true, length = 30))
    })
    public OperateInfo getCreatedInfo() {
        return createdInfo;
    }

    public void setCreatedInfo(OperateInfo createdInfo) {
        this.createdInfo = createdInfo;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "namespace", column = @Column(name = "updated_opera_ns", length = 30)),
            @AttributeOverride(name = "id", column = @Column(name = "updated_opera_id", length = 20)),
            @AttributeOverride(name = "fullName", column = @Column(name = "updated_opera_name", length = 30))
    })
    public OperateInfo getUpdatedInfo() {
        return updatedInfo;
    }

    public void setUpdatedInfo(OperateInfo updatedInfo) {
        this.updatedInfo = updatedInfo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}