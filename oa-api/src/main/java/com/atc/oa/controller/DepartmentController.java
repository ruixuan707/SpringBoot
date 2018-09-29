package com.atc.oa.controller;

import com.atc.oa.entity.Department;
import com.atc.oa.pojo.DepartmentQuery;
import com.atc.oa.service.DepartmentService;
import com.atc.oa.vo.BDepartment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DepartmentController - 部门Controller
 *
 * @author Lijin
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/dep")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BDepartment bDepartment) {
        try {
            Department department = new Department();
            BeanUtils.copyProperties(bDepartment, department);
            if(department!=null){
                if(department.getDepType() == 2){
                    department.setBusinessId(department.getParentId());
                }else if(department.getDepType() == 3){
                    department.setDepId(department.getParentId());
                }else if(department.getDepType() == 4){
                    department.setOrganizationId(department.getParentId());
                }
                departmentService.save(department);
            }
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (BeansException e) {
            log.error("保存部门出错:{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("{parentId}")
    public ResponseEntity<List<BDepartment>> get(@PathVariable Long parentId) {
        try {
            List<BDepartment> bDepartments = new ArrayList<>();
            List<Department> departments = departmentService.getByParentId(parentId);
            if (CollectionUtils.isNotEmpty(departments)) {
                for (Department department : departments) {
                    BDepartment bDepartment = new BDepartment();
                    BeanUtils.copyProperties(department, bDepartment);
                    bDepartment.setId(department.getId());
                    bDepartments.add(bDepartment);
                }
            }
            return ResponseEntity.ok(bDepartments);
        } catch (Exception e) {
            log.error("查询部门出错:{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody BDepartment bDepartment) {
        try {
            Department department = new Department();
            BeanUtils.copyProperties(bDepartment, department);
            departmentService.update(department);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } catch (BeansException e) {
            log.error("修改部门出错:{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("{idLists}")
    public ResponseEntity<Void> delete(@PathVariable String idLists) {
        List<Long> idList = Arrays.asList(idLists.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(idList)){
            for (Long id:idList) {
                try {
                    List<Long> ids = new ArrayList<>();
                    ids.add(id);
                    List<Department> childes = departmentService.getByParentId(id);
                    if (CollectionUtils.isNotEmpty(childes)) {
                        for (Department department : childes) {
                            ids.add(department.getId());
                        }
                    }
                    Long[] arrayIds = ids.toArray(new Long[0]);
                    departmentService.delete(arrayIds);
                } catch (BeansException e) {
                    log.error("删除部门出错:{}", e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping()
    public ResponseEntity<Page<Department>> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size", defaultValue = "20") Integer size, DepartmentQuery departmentQuery){
        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        try{
            Page<Department> pageList = departmentService.getDepartmentByDepType(pageable,departmentQuery);
            return ResponseEntity.ok(pageList);
        }catch (Exception e){
            log.error("查询部门列表信息出错" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}