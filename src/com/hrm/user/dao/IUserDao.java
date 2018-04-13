package com.hrm.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hrm.common.beans.User;

public interface IUserDao {

	User selectUser(@Param("loginname")String loginname, @Param("password")String password);

	User selectUserBN(@Param("username")String username, @Param("status")Integer status);

	int selectCount(Map<String, Object> map);

	List<User> selectUserByPage( Map<String, Object> map);

	void insertUser(User user);

	User selectUserById(Integer id);

	void updateUser(User user);

	void deleteUser(String uid);

}
