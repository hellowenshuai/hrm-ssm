package com.hrm.job.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.hrm.common.beans.Job;
import com.hrm.job.dao.provider.SelectJobProvider;

public interface IJobDao {
	@SelectProvider(method = "selectJob", type = SelectJobProvider.class)
	List<Job> selectJob(HashMap<Object, Object> map);
	
	@SelectProvider(method = "selectJobCount", type = SelectJobProvider.class)
	int selectJobCount(String name);
	
	@Insert("insert into job_inf(name,remark) values(#{name},#{remark})")
	int insertJob(Job job);
	
	@Select("select * from job_inf where id = #{id}")
	Job selectJobById(Integer id);
	
	@Update("update job_inf set name=#{name},remark=#{remark} where id=#{id}")
	int updateJob(Job job);
	
	@Delete("delete from job_inf where id = #{did}")
	void deleteJob(String did);

}
