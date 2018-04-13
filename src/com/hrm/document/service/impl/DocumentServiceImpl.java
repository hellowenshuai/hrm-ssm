package com.hrm.document.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.common.beans.Document;
import com.hrm.document.dao.IDocumentDao;
import com.hrm.document.service.IDocumentService;
import com.hrm.utils.PageModel;

@Service
public class DocumentServiceImpl implements IDocumentService {
	
	@Autowired
	private IDocumentDao documentDao;
	
	@Override
	public int addDocument(Document document) {
		return documentDao.insertDocument(document);
	}

	@Override
	public List<Document> findDocument(String title, PageModel pageModel) {
		HashMap<Object, Object> map=new HashMap<>();
		
		map.put("title", title);
		int recordCount=documentDao.selectCount(title);
		pageModel.setRecordCount(recordCount);
		if(recordCount >0){
			map.put("start", pageModel.getFirstLimitParam());
			map.put("pageSize", pageModel.getPageSize());
		}
		List<Document> documents=documentDao.selectDocument(map);
		return documents;
	}

	@Override
	public Document findDocumentById(Integer id) {
		return documentDao.selectDocumentById(id);
	}

	@Override
	public int updateDocument(Document document) {
		return documentDao.updateDocument(document);
	}

	@Override
	public void removeDocument(String did) {
		documentDao.deleteDocument(did);
	}

	@Override
	public String findFileNameById(Integer id) {
		return documentDao.selectFileNameById(id);
	}

	

}
