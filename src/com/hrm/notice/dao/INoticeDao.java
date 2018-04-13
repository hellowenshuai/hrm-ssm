package com.hrm.notice.dao;

import java.util.List;
import java.util.Map;

import com.hrm.common.beans.Notice;

public interface INoticeDao {

	List<Notice> findAllNotice(Map<String, Object> map);

	int selectCount(Map<String, Object> map);

	void insertNotice(Notice Notice);

	Notice selectNoticeById(Integer id);

	void updateNotice(Notice Notice);

	void deleteNotice(String id);


}
