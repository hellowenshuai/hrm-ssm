package com.hrm.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.common.beans.User;
import com.hrm.user.dao.IUserDao;
import com.hrm.user.service.IUserService;
import com.hrm.utils.PageModel;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao udao;

	@Override
	public User findUser(String loginname, String password) {
		return udao.selectUser(loginname,password);
	}

	@Override
	public List<User> selectAllUser(User user, PageModel pageModel) {
		Map<String,Object> map=new HashMap<>();
		map.put("username", user.getUsername());
		map.put("status", user.getStatus());
		int recordCount=udao.selectCount(map);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0){
			map.put("start", pageModel.getFirstLimitParam());
			map.put("pageSize", pageModel.getPageSize());		
		}
		List<User> users=udao.selectUserByPage(map);
		return users;
	}

	@Override
	public void addUser(User user) {
		udao.insertUser(user);
	}

	@Override
	public User findUserById(Integer id) {
		return udao.selectUserById(id);
	}

	@Override
	public void modifyUser(User user) {
		udao.updateUser(user);
	}

	@Override
	public void removeUser(String uid) {
		udao.deleteUser(uid);
	}

}
