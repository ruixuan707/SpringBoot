package com.atc.oa.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * OperateInfo - 操作信息
 *
 * @author Lijin
 * @version 1.0.0
 */
@Embeddable
public class OperateInfo implements Serializable {

    private static final long serialVersionUID = 1653086128217289260L;

    private Long id;

    private String namespace;

    private String fullName;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OperateInfo that = (OperateInfo) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(namespace, that.namespace)
                .append(fullName, that.fullName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(namespace)
                .append(fullName)
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OperateInfo{");
        sb.append("id=").append(id);
        sb.append(", namespace='").append(namespace).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append('}');
        return sb.toString();
    }


}