package com.hrm.user.service;

import java.util.List;

import com.hrm.common.beans.User;
import com.hrm.utils.PageModel;

public interface IUserService {

	User findUser(String loginname, String password);

	List<User> selectAllUser(User user, PageModel pageModel);

	void addUser(User user);

	User findUserById(Integer id);

	void modifyUser(User user);

	void removeUser(String uid);
	

}
