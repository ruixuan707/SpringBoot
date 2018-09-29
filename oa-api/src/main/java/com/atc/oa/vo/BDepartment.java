package com.atc.oa.vo;

import java.io.Serializable;

/**
 * BDepartment
 *
 * @author Mengke
 * @version 1.0.0
 */
public class BDepartment implements Serializable {

    private static final long serialVersionUID = 5076030741663583029L;
    /** 主键 */
    private Long id;
    /** 部门编码 */
    private String depCode;
    /** 上级部门 */
    private Long parentId;
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
    /** 小组名称 */
    private String groupName;
    /** 部门类型 */
    private Integer depType;
    /** 员工人数 */
    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

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