package com.atc.oa.service;

import com.atc.oa.entity.Template;
import com.atc.oa.pojo.TemplateQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * TemplateService
 *
 * @author Mengke
 * @version 1.0.0
 */
public interface TemplateService extends BaseService<Template, Long> {

    Page<Template> getRoleList(Pageable pageable, TemplateQuery templateQuery);

    List<Template> getTemplate();
}
