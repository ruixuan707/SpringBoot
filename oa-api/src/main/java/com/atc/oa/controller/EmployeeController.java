package com.atc.oa.controller;

import com.atc.oa.entity.Department;
import com.atc.oa.entity.Employee;
import com.atc.oa.entity.Role;
import com.atc.oa.pojo.EmployeeQuery;
import com.atc.oa.service.DepartmentService;
import com.atc.oa.service.EmployeeService;
import com.atc.oa.service.RoleService;
import com.atc.oa.vo.BEmployee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Mengke
 * @ClassName UserController
 * @Description 用户信息
 * @Date 2018/9/19 13:41
 **/
@Slf4j
@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RoleService roleService;

    /**
     * @param bEmployee
     *         页面员工对象
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BEmployee bEmployee) {
        try {
            Employee employee = new Employee();
            BeanUtils.copyProperties(bEmployee,employee);
            Long groupId = bEmployee.getGroupId();
            Department department = departmentService.find(groupId);
            employee.setDepartment(department);
            Set<Role> roleSet = new HashSet<>();
            String roleIds = bEmployee.getRoleIds();
            String[] ids = roleIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                Role role = roleService.find(Long.parseLong(ids[i]));
                roleSet.add(role);
            }
            employee.setRoleSet(roleSet);
            employeeService.save(employee);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("保存用户出错:{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * @param page
     *         当前页
     * @param size
     *         数量
     * @param employeeQuery
     *         用户查询对象
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<Employee>> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "20") Integer size, EmployeeQuery employeeQuery) {
        try {
            PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
            Page<Employee> pageList = employeeService.getList(pageable, employeeQuery);
            return ResponseEntity.ok(pageList);
        } catch (Exception e) {
            log.error("查询部门列表信息出错" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
