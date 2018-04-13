package com.hrm.document.dao.provider;

import java.util.HashMap;

public class SelectDocumentProvider {
	
	public String selectDocument(HashMap<Object, Object> map) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from document_inf ");
		if(map.get("title") != null && !map.get("title").equals(" ")){
			sql.append("where title like '%' #{title} '%' ");
		}
		sql.append("limit #{start},#{pageSize}");
		
		return sql.toString();
	}
	
	public String selectDocumentCount(String title) {
		StringBuffer sql=new StringBuffer();
		sql.append("select count(id) from document_inf ");
		if(title != null && !title.equals(" ")){
			sql.append("where title like '%' #{title} '%' ");
		}
		return sql.toString();
	}
}
