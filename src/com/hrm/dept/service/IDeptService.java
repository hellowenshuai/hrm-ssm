package com.hrm.dept.service;

import java.util.List;

import com.hrm.common.beans.Dept;
import com.hrm.utils.PageModel;

public interface IDeptService {

	List<Dept> findDept(String name, PageModel pageModel);

	int addDept(Dept dept);

	Dept findDeptById(Integer id);

	void modifyDept(Dept dept);

	void removeDept(String did);


}
