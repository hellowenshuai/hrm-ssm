package com.hrm.job.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.common.beans.Job;
import com.hrm.job.dao.IJobDao;
import com.hrm.job.service.IJobService;
import com.hrm.utils.PageModel;

@Service
public class JobServieImpl implements IJobService {
	
	@Autowired
	private IJobDao jobDao;
	

	@Override
	public List<Job> findJob(String name, PageModel pageModel) {
		HashMap<Object, Object> map=new HashMap<>();
		map.put("name", name);
		int recordCount=jobDao.selectJobCount(name);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0){
			map.put("start", pageModel.getFirstLimitParam());
			map.put("pageSize", pageModel.getPageSize());		
		}
		List<Job> jobs=jobDao.selectJob(map);
		return jobs;
	}


	@Override
	public int addJob(Job job) {
		return jobDao.insertJob(job);
	}


	@Override
	public Job findJobById(Integer id) {
		return jobDao.selectJobById(id);
	}


	@Override
	public int modifyJob(Job job) {
		return jobDao.updateJob(job);
	}


	@Override
	public void removeJob(String did) {
		jobDao.deleteJob(did);
	}

}
