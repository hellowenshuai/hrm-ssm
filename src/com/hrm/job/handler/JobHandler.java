package com.hrm.job.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrm.common.beans.Job;
import com.hrm.job.service.IJobService;
import com.hrm.utils.PageModel;
@RequestMapping("/job")
@Controller
public class JobHandler {
	@Autowired
	private IJobService jobService;
	
	@RequestMapping("/findJob.do")
	public String findJob(@RequestParam(defaultValue="1")Integer pageIndex,String name,Model model ){
		//设置pageModel属性
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(pageIndex);
		//设置查询功能
		List<Job> jobs= jobService.findJob(name,pageModel);
		model.addAttribute("name",name);
		model.addAttribute("jobs",jobs);
		model.addAttribute("pageModel",pageModel);
		return "job/job";
	}
	
	@RequestMapping("/addJob.do")
	@ResponseBody
	public String addJob(Job job){
		int rows=jobService.addJob(job);
		if(rows>0){
			return "OK";
		}else{
			return "FAIL";
		}
	}
	
	@RequestMapping("/findJobById.do")
	public String findJobById(Integer id,Integer pageIndex,Model model){
		Job job=jobService.findJobById(id);
		model.addAttribute("job",job);
		model.addAttribute("pageIndex",pageIndex);
		return "job/showUpdateJob";
	}
	
	@RequestMapping("/modifyJob.do")
	@ResponseBody
	public String modifyJob(Job job){
		int rows=jobService.modifyJob(job);
		if(rows>0){
			return "OK";
		}else{
			return "FAIL";
		}
	}
	
	@RequestMapping(value = "/removeJob.do")
	public String removeJob(String ids){
		String[] id = ids.split(",");
		for (String did : id) {
			jobService.removeJob(did);
		}
		return "redirect:/job/findJob.do";
	}
}
