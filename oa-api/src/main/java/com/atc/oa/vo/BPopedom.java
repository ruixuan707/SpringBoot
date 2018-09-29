package com.atc.oa.vo;

import java.io.Serializable;
import java.util.List;

/**
 * BPopedom 权限页面对象
 *
 * @author Mengke
 * @version 1.0.0
 */
public class BPopedom implements Serializable {

    private static final long serialVersionUID = -4819295218996659051L;

    private Long id;
    /** 父级权限如果已经是根节点则为0 */
    private Long pid;
    /** 权限名称 */
    private String name;
    /** 权限在系统中执行访问地址的URL */
    private String pageUrl;
    /** 左侧菜单名称 */
    private String menuName;
    /** 功能描述 */
    private String description;
    /** 排序（如果是父为0，子从1开始排序） */
    private Integer orderBy;
    /** 是否是系统菜单结构（1,true是菜单，0,false不是菜单） */
    private Boolean isMenu;
    /** 英文序列 */
    private String sequence;
    /** 子集children */
    private List<BPopedom> children;
    /** 标题 */
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getMenu() {
        return isMenu;
    }

    public void setMenu(Boolean menu) {
        isMenu = menu;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public List<BPopedom> getChildren() {
        return children;
    }

    public void setChildren(List<BPopedom> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}