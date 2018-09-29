package com.atc.oa.pojo;

import com.atc.oa.entity.Employee;

import java.util.Date;

/**
 * EmployeeQuery - 用户查询
 *
 * @author Mengke
 * @version 1.0.0
 */
public class EmployeeQuery extends Employee {

    private static final long serialVersionUID = 3491815708714358313L;

    private Date startDate;

    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}