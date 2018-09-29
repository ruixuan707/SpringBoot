package com.atc.oa.controller;

import com.atc.oa.entity.Employee;
import com.atc.oa.entity.Popedom;
import com.atc.oa.entity.Role;
import com.atc.oa.service.EmployeeService;
import com.atc.oa.vo.BEmployee;
import com.atc.oa.vo.BLogin;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * LoginController - 登录
 *
 * @author Lijin
 * @version 1.0.0
 */


@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<BLogin> login(@RequestBody BEmployee bEmployee){
        String username = bEmployee.getName();
        String password = bEmployee.getPassword();
        List<Employee> employeeList = employeeService.login(username,password);
        if(CollectionUtils.isEmpty(employeeList)){
            return ResponseEntity.ok(null);
        }else{
            Employee employee = employeeList.get(0);
            List<Long> list = new ArrayList<>();
            BLogin bLogin = new BLogin();
            bLogin.setName(username);
            bLogin.setToken("");
            Set<Role> roleSet = employee.getRoleSet();
            if(CollectionUtils.isNotEmpty(roleSet)){
                for (Role role:roleSet){
                    Set<Popedom> popedomSet = role.getPopedomSet();
                    if(CollectionUtils.isNotEmpty(popedomSet)){
                        for(Popedom popedom : popedomSet){
                            list.add(popedom.getId());
                        }
                    }
                }
            }
            bLogin.setPopedem(list);
            return ResponseEntity.ok(bLogin);
        }
    }

}
