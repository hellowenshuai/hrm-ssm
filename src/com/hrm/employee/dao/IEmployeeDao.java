package com.hrm.employee.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hrm.common.beans.Dept;
import com.hrm.common.beans.Employee;
import com.hrm.common.beans.Job;

public interface IEmployeeDao {

	int selectEmployeeCount(@Param("employee")Employee employee);

	List<Employee> selectEmployee(HashMap<Object, Object> map);
	@Select("select * from job_inf ")
	List<Job> selectAllJob();

	@Select("select * from  dept_inf")
	List<Dept> selectAllDept();
	
	int insertEmployee(Employee employee);
	
	Employee selectEmployeeById(Integer id);

	int updateEmployee(Employee employee);
	
	@Delete("delete from employee_inf where id = #{did}")
	int deleteEmployee(String did);
}
