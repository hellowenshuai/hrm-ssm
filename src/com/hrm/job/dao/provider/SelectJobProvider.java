package com.hrm.job.dao.provider;

import java.util.HashMap;


public class SelectJobProvider {
	public String selectJob(HashMap<Object, Object> map) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from job_inf ");
		if(map.get("name") != null && !map.get("name").equals(" ")){
			sql.append("where name like '%' #{name} '%' ");
		}
		sql.append("limit #{start},#{pageSize}");
		
		return sql.toString();
	}
	public String selectJobCount(String name) {
		StringBuffer sql=new StringBuffer();
		sql.append("select count(id) from job_inf ");
		if(name != null && !name.equals(" ")){
			sql.append("where name like '%' #{name} '%' ");
		}
		return sql.toString();
	}


}
