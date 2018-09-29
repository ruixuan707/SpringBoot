package com.atc.oa.controller;

import com.atc.oa.entity.Popedom;
import com.atc.oa.entity.Template;
import com.atc.oa.pojo.TemplateQuery;
import com.atc.oa.service.PopedomService;
import com.atc.oa.service.TemplateService;
import com.atc.oa.vo.BTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TemplateController
 *
 * @author Lijin
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private PopedomService popedomService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BTemplate bTemplate){
        try {
            Template template = new Template();
            BeanUtils.copyProperties(bTemplate, template);
            if(!StringUtils.isBlank(bTemplate.getPopedom())){
                String[] strings = bTemplate.getPopedom().split(",");
                Set<Popedom> popedomSet = new HashSet<>();
                for (int i = 0;i<strings.length;i++){
                    Popedom popedom = popedomService.find(Long.parseLong(strings[i]));
                    popedomSet.add(popedom);
                }
                template.setPopedomSet(popedomSet);
            }
            templateService.save(template);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (BeansException e) {
            log.error("保存模版出错:{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("{idList}")
    public ResponseEntity<Void> delete(@PathVariable String idList) {
        try {
            String[] idLists = idList.split(",");
            Long[] ids = (Long[])ConvertUtils.convert(idLists,Long.class);
            templateService.delete(ids);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (BeansException e) {
            log.error("删除模版出错:{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /*@PutMapping
    public ResponseEntity<Void> update(@RequestBody BRole bRole) {
        try {
            Role role = new Role();
            BeanUtils.copyProperties(bRole, role);
            roleService.update(role);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } catch (BeansException e) {
            log.error("修改角色出错:{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }*/

    @GetMapping
    public ResponseEntity<Page<Template>> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "20") Integer size, TemplateQuery templateQuery){
        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        try{
            Page<Template> pageList = templateService.getRoleList(pageable,templateQuery);
            if(pageList !=null && CollectionUtils.isNotEmpty(pageList.getContent())){
                return ResponseEntity.ok(pageList);
            }
            return ResponseEntity.ok(null);
        }catch (Exception e){
            log.error("查询模版列表信息出错" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Template>> list(@PathVariable Long id){
        try{
            List<Template> templateList = templateService.getTemplate();
            return ResponseEntity.ok(templateList);
        }catch (Exception e){
            log.error("查询模版信息出错" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}