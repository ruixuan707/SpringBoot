package com.atc.oa.dao;

import com.atc.oa.entity.Popedom;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PopedomDao - 权限持久层
 *
 * @author Lijin
 * @version 1.0.0
 */
@Repository
public interface PopedomDao extends BaseDao<Popedom, Long> {

    List<Popedom> findAllByOrderByIdAsc();

    List<Popedom> findByPid(Long pid);

}

