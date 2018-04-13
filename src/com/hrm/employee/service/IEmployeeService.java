package com.hrm.employee.service;

import java.util.List;

import com.hrm.common.beans.Dept;
import com.hrm.common.beans.Employee;
import com.hrm.common.beans.Job;
import com.hrm.utils.PageModel;

public interface IEmployeeService {

	List<Employee> findEmployee(Employee employee, PageModel pageModel);

	int addEmployee(Employee employee);

	Employee findEmployeeById(Integer id);

	int modifyEmployee(Employee employee);

	int removeEmployee(String did);

	List<Job> findAllJob();

	List<Dept> findAllDept();

}
