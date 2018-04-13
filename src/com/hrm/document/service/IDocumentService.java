package com.hrm.document.service;


import java.util.List;

import com.hrm.common.beans.Document;
import com.hrm.utils.PageModel;

public interface IDocumentService {
	
	
	int addDocument(Document document);

	List<Document> findDocument(String title, PageModel pageModel);

	Document findDocumentById(Integer id);

	int updateDocument(Document document);

	void removeDocument(String did);

	String findFileNameById(Integer id);

}
