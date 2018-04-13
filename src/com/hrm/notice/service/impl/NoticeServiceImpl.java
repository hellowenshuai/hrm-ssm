package com.hrm.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.common.beans.Notice;
import com.hrm.notice.dao.INoticeDao;
import com.hrm.notice.service.INoticeService;
import com.hrm.utils.PageModel;

@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Autowired
	private INoticeDao noticeDao;

	@Override
	public List<Notice> findAllNotice(Notice Notice, PageModel pageModel) {
		Map<String,Object> map=new HashMap<>();
		map.put("title", Notice.getTitle());
		map.put("content", Notice.getContent());
		
		int recordCount=noticeDao.selectCount(map);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount>0){
			map.put("start", pageModel.getFirstLimitParam());
			map.put("pageSize", pageModel.getPageSize());		
		}
		List<Notice> notices=noticeDao.findAllNotice(map);
		return notices;
	}

	@Override
	public void addNotice(Notice Notice) {
		noticeDao.insertNotice(Notice);
	}

	@Override
	public Notice findNoticeById(Integer id) {
		return noticeDao.selectNoticeById(id);
	}

	@Override
	public void modifyNotice(Notice Notice) {
		noticeDao.updateNotice(Notice);
	}

	@Override
	public void removeNotice(String uid) {
		noticeDao.deleteNotice(uid);
	}


}
