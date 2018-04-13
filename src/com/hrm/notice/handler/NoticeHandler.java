package com.hrm.notice.handler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrm.common.beans.Notice;
import com.hrm.common.beans.User;
import com.hrm.notice.service.INoticeService;
import com.hrm.utils.PageModel;

@Controller
@RequestMapping("/notice")
public class NoticeHandler {

	@Autowired
	private INoticeService noticeService;

	
	@RequestMapping("/findAllNotice.do")
	public String findAllNotice(@RequestParam(defaultValue = "1") Integer pageIndex,  Notice notice,
			Model model) {

		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		// 查询用户信息
		List<Notice> notices = noticeService.findAllNotice(notice, pageModel);
		model.addAttribute("notices", notices);
		model.addAttribute("pageModel", pageModel);
		return "notice/notice";
	}
	@RequestMapping(value = "/previewNotice.do")
	public String previewNotice(Integer id,	Model model) {
		
		// 查询用户信息
		Notice notice= noticeService.findNoticeById(id);
		model.addAttribute("notice", notice);
		return "notice/previewNotice";
	}
	//添加成功，并返回到查询页面
	@RequestMapping(value = "/addNotice.do")
	public String addNotice(Notice notice, HttpSession session) {
		User user=(User) session.getAttribute("user");
		Integer user_id=user.getId();
		notice.setUser_id(user_id);;
		noticeService.addNotice(notice);
		return "redirect:/notice/findAllNotice.do";
	}

	//添加成功，并返回到查询页面
	@RequestMapping(value = "/modifyNotice.do")
	public String modifyNotice(String flag, Notice notice,Model model) {
		if(flag.equals("1")) {
			 notice= noticeService.findNoticeById(notice.getId());
			model.addAttribute("notice",notice);
			return "notice/showUpdateNotice";
		}
		noticeService.modifyNotice(notice);
		return "redirect:/notice/findAllNotice.do";
	}

	//批量删除
	@RequestMapping(value = "/removeNotice.do")
	public String removeNotice(String ids) {
		String[] id = ids.split(",");
		for (String nid : id) {
			noticeService.removeNotice(nid);
		}
		return "redirect:/notice/findAllNotice.do";
	}
	
}
