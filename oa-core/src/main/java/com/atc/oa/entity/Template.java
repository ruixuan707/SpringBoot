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
 * RoleTemplate 角色模版
 *
 * @author Mengke
 * @version 1.0.0
 */
@Entity
@Table(name = "fmgt_template")
public class Template extends BaseEntity<Long>{

    /** 模版名称 */
    private String templateName;

    /** 权限 */
    private Set<Popedom> popedomSet = new HashSet<>();


    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(name = "fmgt_template_popedom", joinColumns = @JoinColumn(name = "template_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "popedom_id", referencedColumnName = "id"))
    public Set<Popedom> getPopedomSet() {
        return popedomSet;
    }

    public void setPopedomSet(Set<Popedom> popedomSet) {
        this.popedomSet = popedomSet;
    }
}