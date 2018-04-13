package com.hrm.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hrm.common.beans.Dept;
import com.hrm.common.beans.User;
import com.hrm.dept.dao.IDeptDao;
import com.hrm.dept.service.IDeptService;
import com.hrm.utils.PageModel;

@Service
public class DeptServiceImpl implements IDeptService {
	
	@Resource
	private IDeptDao ddao;

	@Override
	public List<Dept> findDept(String name, PageModel pageModel) {
		Map<String,Object> map=new HashMap<>();
		map.put("name", name);
		int recordCount=ddao.selectCount(map);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0){
			map.put("start", pageModel.getFirstLimitParam());
			map.put("pageSize", pageModel.getPageSize());		
		}
		List<Dept> depts=ddao.selectDeptByPage(map);
		return depts;
	}

	@Override
	public int addDept(Dept dept) {
		return ddao.insertDept(dept);
	}

	@Override
	public Dept findDeptById(Integer id) {
		return ddao.selectDeptById(id);
	}

	@Override
	public void modifyDept(Dept dept) {
		ddao.updateDept(dept);
	}

	@Override
	public void removeDept(String did) {
		ddao.deleteDept(did);
	}

}
