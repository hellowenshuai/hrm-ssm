package com.hrm.document.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrm.common.beans.Document;
import com.hrm.common.beans.User;
import com.hrm.document.service.IDocumentService;
import com.hrm.utils.PageModel;


@RequestMapping("/document")
@Controller
public class DocumentHandler {

	@Autowired
	private IDocumentService documentService;
	
	
	@RequestMapping("/findDocument.do")
	public String findDocument(@RequestParam(defaultValue = "1") Integer pageIndex,  String title,
			Model model){
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		// 鏌ヨ鐢ㄦ埛淇℃伅
		List<Document> documents = documentService.findDocument(title, pageModel);
		model.addAttribute("documents", documents);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("title",title);
		return "document/document";
	}

	@ResponseBody
	@RequestMapping(value="/addDocument.do",method = RequestMethod .POST)
	public String addDocument(HttpSession session,Document document) 
			throws IllegalStateException, IOException {
		Date date = new Date();
		document.setCreate_date(date);
		// 涓婁紶鏂囦欢璺緞
		String realPath = session.getServletContext().getRealPath("/xxx/");
		// 鏂囦欢鍚嶇О
		String filename = document.getFile().getOriginalFilename();
		// 灏嗘枃浠朵繚瀛樺埌纭洏涓�
		document.getFile().transferTo(new File(realPath + File.separator + filename));
		User user=(User) session.getAttribute("user");
		Integer user_id=user.getId();
		document.setUser_id(user_id);
		document.setFilename("xxx/" + filename);
		int rows = documentService.addDocument(document);
		if (rows > 0) {
			System.out.println("document:"+document);
			return "OK";
		} else {
			return "FAIL";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateDocument.do",method = RequestMethod .POST)
	public String updateDocument(HttpSession session,Document document) 
			throws IllegalStateException, IOException {
		// 涓婁紶鏂囦欢璺緞
		String realPath = session.getServletContext().getRealPath("/xxx/");
		// 鏂囦欢鍚嶇О
		String filename = document.getFile().getOriginalFilename();
		// 灏嗘枃浠朵繚瀛樺埌纭洏涓�
		document.getFile().transferTo(new File(realPath + File.separator + filename));
		document.setFilename("xxx/" + filename);
		int rows = documentService.updateDocument(document);
		if (rows > 0) {
			System.out.println("document:"+document);
			return "OK";
		} else {
			return "FAIL";
		}
	}
	
	@RequestMapping("/findDocumentById.do")
	public String findDocumentById(Integer id, Model model){
		// 鏌ヨ鐢ㄦ埛淇℃伅
		Document document = documentService.findDocumentById(id);
		model.addAttribute("document", document);
		return "document/showUpdateDocument";
	}
	
	@RequestMapping("/removeDocument.do")
	public String removeDocument(String ids){
		// 鏌ヨ鐢ㄦ埛淇℃伅
		String[] id = ids.split(",");
		for (String did : id) {
			documentService.removeDocument(did);
		}
		return "redirect:/document/findDocument.do";
	}
	
    @RequestMapping("/downLoad.do")  
    public void down(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String file = documentService.findFileNameById(id);
    	System.out.println(file);
        //妯℃嫙鏂囦欢锛宮yfile.txt涓洪渶瑕佷笅杞界殑鏂囦欢  
        String fileName = request.getSession().getServletContext().getRealPath("/")+file;  
        System.out.println(fileName);
        //鑾峰彇杈撳叆娴�  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
        //鍋囧浠ヤ腑鏂囧悕涓嬭浇鐨勮瘽  
        String[] filename1 = file.split("/");
        String filename = filename1[1];  
        //杞爜锛屽厤寰楁枃浠跺悕涓枃涔辩爜  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //璁剧疆鏂囦欢涓嬭浇澶�  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.璁剧疆鏂囦欢ContentType绫诲瀷锛岃繖鏍疯缃紝浼氳嚜鍔ㄥ垽鏂笅杞芥枃浠剁被鍨�    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
    }  
	

}
