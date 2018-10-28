package com.hrm.employee.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrm.common.beans.Dept;
import com.hrm.common.beans.Employee;
import com.hrm.common.beans.Job;
import com.hrm.employee.service.IEmployeeService;
import com.hrm.utils.PageModel;

@RequestMapping("/employee")
@Controller
public class EmployeeHandler {
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping("/findEmployee.do")
	public String findEmployee(@RequestParam(defaultValue="1")Integer pageIndex,
			Integer job_id,Integer dept_id,Employee employee,Model model ){
		//设置员工中的工作和部门的ID
		this.association(job_id,dept_id, employee );
		//设置pageModel属性
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(pageIndex);
		List<Job> jobs= employeeService.findAllJob();
		List<Dept> depts= employeeService.findAllDept();
		//设置查询功能
		List<Employee> employees= employeeService.findEmployee(employee,pageModel);
		model.addAttribute("jobs",jobs);
		model.addAttribute("depts",depts);
		model.addAttribute("employees",employees);
		model.addAttribute("employee",employee);
		model.addAttribute("pageModel",pageModel);
		return "employee/employee";
	}
	
	@RequestMapping("/addEmployee.do")
	public String addEmployee(String flag,Integer job_id,Integer dept_id,Employee employee,Model model){
		//flag=1时，表示进入到添加雇员页面
		if(flag.equals("1") ) {
			List<Job> jobs= employeeService.findAllJob();
			List<Dept> depts= employeeService.findAllDept();
			model.addAttribute("jobs",jobs);
			model.addAttribute("depts",depts);
			return "employee/showAddEmployee";
		}else {
			this.association(job_id,dept_id, employee );
			int rows=employeeService.addEmployee(employee);
			if(rows > 0){
				return "redirect:/employee/findEmployee.do";
			}else{
				model.addAttribute("employee",employee);
				model.addAttribute("fail","员工添加失败");
				return "fail";
			}
			
		}
	}
	
	@RequestMapping("/findEmployeeById.do")
	public String findEmployeeById(Integer id,Integer pageIndex,Model model){
		List<Job> jobs= employeeService.findAllJob();
		List<Dept> depts= employeeService.findAllDept();
		Employee employee=employeeService.findEmployeeById(id);
		model.addAttribute("jobs",jobs);
		model.addAttribute("depts",depts);
		model.addAttribute("employee",employee);
		model.addAttribute("pageIndex",pageIndex);
		return "employee/showUpdateEmployee";
	}
	
	@RequestMapping("/modifyEmployee.do")
	@ResponseBody
	public String modifyEmployee(Integer job_id,Integer dept_id,Employee employee,Model model){
		this.association(job_id, dept_id, employee);
		int rows=employeeService.modifyEmployee(employee);
		if(rows>0){
			return "OK";
		}else{
			
			return "FAIL";
		}
	}
	@ResponseBody
	@RequestMapping(value = "/removeEmployee.do")
	public String removeEmployee(String ids) {
		int rows=0;
		String[] id = ids.split(",");
		for (String did : id) {
		 rows=	employeeService.removeEmployee(did);
		}
		if(rows > 0) {
		return "Ok";
		}else {
			return "fail";
		}
	}
	
	public void association (Integer job_id,Integer dept_id,Employee employee) {
		if(job_id != null) {
			Job job = new Job();
			job.setId(job_id);
			employee.setJob(job);
		}
		
		if(dept_id != null) {
			Dept dept = new Dept();
			dept.setId(dept_id);
			employee.setDept(dept);
		}
	}
	
}
