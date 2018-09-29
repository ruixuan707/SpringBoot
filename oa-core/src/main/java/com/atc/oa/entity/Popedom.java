package com.atc.oa.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Popedom - 权限表
 *
 * @author Lijin
 * @version 1.0.0
 */
@Entity
@Table(name = "fmgt_popedom",
        indexes = {
                @Index(name = "idx_fmgt_popedom_1", columnList = "pid"),
                @Index(name = "idx_fmgt_popedom_2", columnList = "sequence")
        })
public class Popedom extends BaseEntity<Long> {

    private static final long serialVersionUID = -5701771598419142557L;
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
}
