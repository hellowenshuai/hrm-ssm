package com.hrm.employee.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.common.beans.Dept;
import com.hrm.common.beans.Employee;
import com.hrm.common.beans.Job;
import com.hrm.employee.dao.IEmployeeDao;
import com.hrm.employee.service.IEmployeeService;
import com.hrm.utils.PageModel;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		HashMap<Object, Object> map=new HashMap<>();
		map.put("employee", employee);
		int recordCount=employeeDao.selectEmployeeCount(employee);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0){
			map.put("start", pageModel.getFirstLimitParam());
			map.put("pageSize", pageModel.getPageSize());		
		}
		List<Employee> employees=employeeDao.selectEmployee(map);
		return employees;
	}
	
	@Override
	public int addEmployee(Employee employee) {
		return employeeDao.insertEmployee(employee);
	}

	@Override
	public Employee findEmployeeById(Integer id) {
		return employeeDao.selectEmployeeById(id);
	}

	@Override
	public int modifyEmployee(Employee employee) {
		
		return employeeDao.updateEmployee(employee);
	}

	@Override
	public int removeEmployee(String did) {
		
		return employeeDao.deleteEmployee(did);
	}

	@Override
	public List<Job> findAllJob() {
		// TODO Auto-generated method stub
		return employeeDao.selectAllJob();
	}

	@Override
	public List<Dept> findAllDept() {
		// TODO Auto-generated method stub
		return employeeDao.selectAllDept();
	}

}
