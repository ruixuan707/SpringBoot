package com.atc.oa.service.impl;

import com.atc.oa.dao.BaseDao;
import com.atc.oa.entity.BaseEntity;
import com.atc.oa.service.BaseService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * BaseServiceImpl - 业务层基类实现
 *
 * @author Lijin
 * @version 1.0.0
 */
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseService<T, ID> {

    private static final String[] UPDATE_IGNORE_PROPERTIES = new String[]{BaseEntity.CREATE_DATE_PROPERTY_NAME, BaseEntity.MODIFY_DATE_PROPERTY_NAME, BaseEntity.VERSION_PROPERTY_NAME};

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected BaseDao<T, ID> baseDao;

    public BaseServiceImpl() {
        ResolvableType resolvableType = ResolvableType.forClass(getClass());
        entityClass = (Class<T>) resolvableType.getSuperType().getGeneric().resolve();
    }

    @Transactional
    public T save(T entity) {
        Assert.notNull(entity, "Entity is not empty");
        Assert.isTrue(entity.isNew(), "Entity is not new");
        return baseDao.save(entity);
    }

    @Transactional
    public List<T> saveCollection(List<T> list) {
        Assert.notEmpty(list, "Collection is not empty");
        for (int i = 0; i < list.size(); i++) {
            save(list.get(i));
            if (i % 30 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        return list;
    }

    @Transactional(readOnly = true)
    public T find(ID id) {
        return baseDao.findById(id).get();
    }

    @Transactional(readOnly = true)
    public T get(ID id) {
        return baseDao.getOne(id);
    }

    @Transactional(readOnly = true)
    public List<T> findList(ID... ids) {
        List<T> result = new ArrayList<T>();
        if (ids != null) {
            for (ID id : ids) {
                T entity = find(id);
                if (entity != null) {
                    result.add(entity);
                }
            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Example<T> example, Sort sort) {
        return baseDao.findAll(example, sort);
    }

    @Transactional(readOnly = true)
    public Page<T> findPage(Example<T> example, Pageable pageable) {
        return baseDao.findAll(example, pageable);
    }

    @Transactional(readOnly = true)
    public long count() {
        return baseDao.count();
    }

    @Transactional(readOnly = true)
    public boolean exists(ID id) {
        return find(id) != null;
    }

    @Transactional
    public T update(T entity) {
        Assert.notNull(entity, "Entity is not empty");
        Assert.isTrue(!entity.isNew(), "Entity is new");
        if (!entityManager.contains(entity)) {
            ID id = (ID) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
            T persistent = entityManager.find(entityClass, id);
            if (persistent != null) {
                copyProperties(entity, persistent, UPDATE_IGNORE_PROPERTIES);
            }
            return persistent;
        }
        return entity;
    }

    @Transactional
    public T update(T entity, String... ignoreProperties) {
        Assert.notNull(entity, "Entity is not empty");
        Assert.isTrue(!entity.isNew(), "Entity is new");
        Assert.isTrue(!entityManager.contains(entity), "Entity not in EntityManager management");
        ID id = (ID) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
        T persistent = entityManager.find(entityClass, id);
        if (persistent != null) {
            copyProperties(entity, persistent, (String[]) ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
        }
        return update(persistent);
    }

    @Transactional
    public List<T> updateList(List<T> list) {
        Assert.notEmpty(list, "Collection is not empty");
        for (T t : list) {
            update(t);
        }
        return list;
    }

    @Transactional
    public void delete(ID id) {
        baseDao.deleteById(id);
    }

    @Transactional
    public void delete(ID... ids) {
        if (ids != null) {
            for (ID id : ids) {
                delete(id);
            }
        }
    }

    @Transactional
    public void delete(T entity) {
        baseDao.delete(entity);
    }

    @Transactional
    public void deleteAll(Collection<T> collections) {
        baseDao.deleteAll(collections);
    }

    /**
     * 拷贝对象属性
     *
     * @param source
     *         源
     * @param target
     *         目标
     * @param ignoreProperties
     *         忽略属性
     */
    private void copyProperties(T source, T target, String... ignoreProperties) {
        Assert.notNull(source, "source is not empty");
        Assert.notNull(target, "target is not empty");
        PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(target);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Method readMethod = propertyDescriptor.getReadMethod();
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (ArrayUtils.contains(ignoreProperties, propertyName)
                    || readMethod == null
                    || writeMethod == null
                    || !entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(source, propertyName)) {
                continue;
            }
            try {
                Object sourceValue = readMethod.invoke(source);
                writeMethod.invoke(target, sourceValue);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

}