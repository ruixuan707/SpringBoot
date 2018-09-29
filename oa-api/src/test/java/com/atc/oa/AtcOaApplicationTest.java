package com.atc.oa;

import com.atc.oa.pojo.EmployeeQuery;
import com.atc.oa.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * AtcOaApplication - System Test entry
 *
 * @author Lijin
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AtcOaApplication.class)
public class AtcOaApplicationTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void save() {

    }


}
