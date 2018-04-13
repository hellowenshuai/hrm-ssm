package com.hrm.job.service;

import java.util.List;

import com.hrm.common.beans.Job;
import com.hrm.utils.PageModel;

public interface IJobService {

	List<Job> findJob(String name, PageModel pageModel);

	int addJob(Job job);

	Job findJobById(Integer id);

	int modifyJob(Job job);

	void removeJob(String did);

}
