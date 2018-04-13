package com.hrm.notice.service;

import java.util.List;

import com.hrm.common.beans.Notice;
import com.hrm.utils.PageModel;

public interface INoticeService {

	List<Notice> findAllNotice(Notice notice, PageModel pageModel);

	void addNotice(Notice notice);

	Notice findNoticeById(Integer id);

	void modifyNotice(Notice notice);

	void removeNotice(String nid);


}
