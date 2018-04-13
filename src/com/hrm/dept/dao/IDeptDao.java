package com.hrm.dept.dao;

import java.util.List;
import java.util.Map;

import com.hrm.common.beans.Dept;

public interface IDeptDao {

	int selectCount(Map<String, Object> map);

	List<Dept> selectDeptByPage(Map<String, Object> map);

	int insertDept(Dept dept);

	Dept selectDeptById(Integer id);

	void updateDept(Dept dept);

	void deleteDept(String did);

}
