package com.atc.oa.controller;

import com.atc.oa.entity.Popedom;
import com.atc.oa.entity.Role;
import com.atc.oa.pojo.RoleQuery;
import com.atc.oa.service.PopedomService;
import com.atc.oa.service.RoleService;
import com.atc.oa.vo.BRole;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * RoleController 角色
 *
 * @author Mengke
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PopedomService popedomService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BRole bRole){
        try {
            Role role = new Role();
            BeanUtils.copyProperties(bRole, role);
            if(!StringUtils.isBlank(bRole.getPopedomSet())){
                String[] strings = bRole.getPopedomSet().split(",");
                Set<Popedom> popedomSet = new HashSet<>();
                for (int i = 0;i<strings.length;i++){
                    Popedom popedom = popedomService.find(Long.parseLong(strings[i]));
                    popedomSet.add(popedom);
                }
                role.setPopedomSet(popedomSet);
            }
            roleService.save(role);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (BeansException e) {
            log.error("保存角色出错:{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("{idList}")
    public ResponseEntity<Void> delete(@PathVariable String idList) {
        try {
            String[] idLists = idList.split(",");
            Long[] ids = (Long[])ConvertUtils.convert(idLists,Long.class);
            roleService.delete(ids);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (BeansException e) {
            log.error("删除角色出错:{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping
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
    }

    @GetMapping
    public ResponseEntity<Page<Role>> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size", defaultValue = "20") Integer size, RoleQuery roleQuery){
        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        try{
            Page<Role> pageList = roleService.getRoleList(pageable,roleQuery);
            if(pageList !=null && CollectionUtils.isNotEmpty(pageList.getContent())){
                return ResponseEntity.ok(pageList);
            }
            return ResponseEntity.ok(null);
        }catch (Exception e){
            log.error("查询部门列表信息出错" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Role>> list(@PathVariable Long id){
        try{
            List<Role> roleList = roleService.getRole();
            return ResponseEntity.ok(roleList);
        }catch (Exception e){
            log.error("查询角色信息出错" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}