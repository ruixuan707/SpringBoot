package com.atc.oa.listener;

import com.atc.oa.entity.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * EntityListener - 创建日期、修改日期、版本处理
 *
 * @author Lijin
 * @version 1.0.0
 */
public class EntityListener {

    @PrePersist
    public void prePersist(BaseEntity<?> entity) {
        entity.setCreateDate(new Date());
        entity.setModifyDate(new Date());
    }

    @PreUpdate
    public void preUpdate(BaseEntity<?> entity) {
        entity.setModifyDate(new Date());
    }
}