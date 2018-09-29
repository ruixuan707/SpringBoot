package com.atc.oa.service;

import com.atc.oa.entity.Popedom;

import java.util.List;

/**
 * PopedomService - 权限业务层接口
 *
 * @author Lijin
 * @version 1.0.0
 */
public interface PopedomService extends BaseService<Popedom, Long> {

    List<Popedom> findAllByOrder();

    List<Popedom> findByPid(Long pId);
}