package com.hrm.user.handler;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrm.common.beans.User;
import com.hrm.user.service.IUserService;
import com.hrm.utils.PageModel;

@Controller
@RequestMapping("/user")
public class UserHandler {

	@Autowired
	private IUserService uservice;

	// 登录
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(String loginname, String password, Model model, HttpSession session) {
		User user = uservice.findUser(loginname, password);
		if (user != null) {
			session.setAttribute("user", user);
			return "main";
		}
		model.addAttribute("message", "用户名或者密码错误，请重新输入");
		return "loginForm";
	}

	// 注销
	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login.do";
	}

	// 二次登录非post
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String toLogin(HttpSession session) {
		session.invalidate();
		return "loginForm";
	}

	// 登录验证测试
	@RequestMapping("/toC.do")
	public String toC() {
		return "main";
	}

	/*
	 * // 用户管理模块 增删改查
	 * 
	 * @RequestMapping(value = "/selectAllUser.do") public String
	 * findAllUser(Integer pageIndex, @ModelAttribute User user, Model model) {
	 * 
	 * PageModel pageModel = new PageModel(); if (pageIndex != null) {
	 * pageModel.setPageIndex(pageIndex); } // 查询用户信息 List<User> users =
	 * uservice.selectAllUser(user, pageModel); model.addAttribute("users",
	 * users); model.addAttribute("pageModel", pageModel);
	 * System.out.println(users); return "user/user"; }
	 */
	@RequestMapping(value = "/selectAllUser.do")
	public String findAllUser(@RequestParam(defaultValue = "1") Integer pageIndex, @ModelAttribute User user,
			Model model) {

		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(5);
		// 查询用户信息
		List<User> users = uservice.selectAllUser(user, pageModel);
		model.addAttribute("users", users);
		model.addAttribute("pageModel", pageModel);
		return "user/user";
	}
	//跳转到添加页面
	@RequestMapping(value = "/toAddUser.do")
	public String toAddUser() {
		return "user/showAddUser";
	}
	//添加成功，并返回到查询页面
	@RequestMapping(value = "/addUser.do")
	public String addUser(@ModelAttribute User user) {
		uservice.addUser(user);
		return "redirect:/user/selectAllUser.do";
	}
	//跳转到修改页面
	@RequestMapping(value = "/toUpdateUser.do")
	public String toUpdateUser(Integer id,Model model) {
		User user= uservice.findUserById(id);
		model.addAttribute("user",user);
		return "user/showUpdateUser";
	}
	//添加成功，并返回到查询页面
	@RequestMapping(value = "/updateUser.do")
	public String UpdateUser(@ModelAttribute User user) {
		uservice.modifyUser(user);
		return "redirect:/user/selectAllUser.do";
	}

	//批量删除
	@RequestMapping(value = "/removeUser.do")
	public String removeUser(String ids) {
		String[] id = ids.split(",");
		for (String uid : id) {
			uservice.removeUser(uid);
		}
		return "redirect:/user/selectAllUser.do";
	}
	
}
