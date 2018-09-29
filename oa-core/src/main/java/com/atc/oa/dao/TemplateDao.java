package com.atc.oa.dao;

import com.atc.oa.entity.Template;
import org.springframework.stereotype.Repository;

/**
 * TemplateDao
 *
 * @author Mengke
 * @version 1.0.0
 */
@Repository
public interface TemplateDao extends BaseDao<Template, Long> {

    /*@Query(value = "select * from fmgt_template_popedom", nativeQuery = true)
    List<Template> getTemplateList();*/
}
