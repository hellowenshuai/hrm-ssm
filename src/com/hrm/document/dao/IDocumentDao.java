package com.hrm.document.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.hrm.common.beans.Document;
import com.hrm.document.dao.provider.SelectDocumentProvider;

public interface IDocumentDao {
	
	@Insert("insert into document_inf (title,filename,remark,create_date,user_id) values (#{title},#{filename},#{remark},#{create_date},#{user_id}) ")
	int insertDocument(Document document);
	
	@SelectProvider(method = "selectDocumentCount", type = SelectDocumentProvider.class)
	int selectCount(String title);

	//@SelectProvider(method = "selectDocument", type = SelectDocumentProvider.class)
	List<Document> selectDocument(HashMap<Object, Object> map);
	
	@Select("select * from document_inf  where id = #{id}")
	Document selectDocumentById(Integer id);
	
	@Update("update document_inf  set title= #{title},filename = #{filename},remark =#{remark} where id=#{id} ")
	int updateDocument(Document document);
	
	@Delete("delete  from document_inf where id=#{did}")
	void deleteDocument(String did);
	
	
	@Select("select filename from document_inf  where id = #{id}")
	String selectFileNameById(Integer id);

}
