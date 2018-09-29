package com.atc.oa.service.impl;

import com.atc.oa.dao.PopedomDao;
import com.atc.oa.entity.Popedom;
import com.atc.oa.service.PopedomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PopedomServiceImpl - 权限业务层
 *
 * @author Lijin
 * @version 1.0.0
 */
@Service
public class PopedomServiceImpl extends BaseServiceImpl<Popedom, Long> implements PopedomService {

    @Autowired
    private PopedomDao popedomDao;

    @Override
    public List<Popedom> findAllByOrder() {
        return popedomDao.findAllByOrderByIdAsc();
    }

    @Override
    public List<Popedom> findByPid(Long pId) {
        return popedomDao.findByPid(pId);
    }
}