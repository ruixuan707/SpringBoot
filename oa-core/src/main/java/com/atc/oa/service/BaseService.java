package com.atc.oa.service;

import com.atc.oa.entity.BaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * BaseService - 业务层基类
 *
 * @author Lijin
 * @version 1.0.0
 */
public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {

    T save(T entity);

    List<T> saveCollection(List<T> list);

    T find(ID id);

    T get(ID id);

    List<T> findList(ID... ids);

    List<T> findAll(Example<T> example, Sort sort);

    Page<T> findPage(Example<T> example, Pageable pageable);

    long count();

    boolean exists(ID id);

    T update(T entity);

    T update(T entity, String... ignoreProperties);

    List<T> updateList(List<T> list);

    void delete(ID id);

    void delete(ID... ids);

    void delete(T entity);

    void deleteAll(Collection<T> collections);

}
