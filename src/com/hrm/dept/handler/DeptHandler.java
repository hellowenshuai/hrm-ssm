package com.hrm.dept.handler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrm.common.beans.Dept;
import com.hrm.dept.service.IDeptService;
import com.hrm.utils.PageModel;

@Controller
@RequestMapping("/dept")
public class DeptHandler {
	
	@Resource
	private IDeptService dservice;
	
	@RequestMapping("/findAllDept.do")
	public String findDept(@RequestParam(defaultValue = "1") Integer pageIndex,String name, Model model){
		//设置pagemodel属性
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(5);
		//设置查询功能
		List<Dept> depts=dservice.findDept(name,pageModel);
		System.out.println("========================="+name);
		model.addAttribute("name",name);
		
		model.addAttribute("depts",depts);
		model.addAttribute("pageModel", pageModel);
		return "dept/dept";
	}
	
	//添加成功，并返回到查询页面
		@RequestMapping(value = "/addDept.do")
		public String addDept(@ModelAttribute Dept dept,Model model) {
			int rows=dservice.addDept(dept);
			if(rows>0){
			return "redirect:/dept/findAllDept.do";}
			else{
				String message="添加失败，请你再次尝试！";
				model.addAttribute("message",message);
				return "dept/showAddDept";
			}
		}
		//跳转到修改页面
		@RequestMapping(value = "/toUpdateDept.do")
		public String toUpdateDept(Integer id,Model model) {
			Dept dept= dservice.findDeptById(id);
			model.addAttribute("dept",dept);
			return "dept/showUpdateDept";
		}
		
		//修改成功，并返回到查询页面
		@RequestMapping(value = "/updateDept.do")
		public String updatDept(@ModelAttribute Dept dept) {
			dservice.modifyDept(dept);
			return "redirect:/dept/findAllDept.do";
		}

		//批量删除
		@RequestMapping(value = "/removeDept.do")
		public String removeDept(String ids){
			String[] id = ids.split(",");
			for (String did : id) {
				dservice.removeDept(did);
			}
			return "redirect:/dept/findAllDept.do";
		}
	
}
