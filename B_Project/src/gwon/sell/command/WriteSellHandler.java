package gwon.sell.command;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import gwon.sell.model.Writer;
import gwon.sell.service.WriteRequest;
import gwon.sell.service.WriteSellService;
import member.model.MemberDTO;	// 회원 정보에 접근하기
import mvc.command.CommandHandler;

public class WriteSellHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/GWON/sell/writeSell.jsp";
	private WriteSellService writeSellService = new WriteSellService();
	private static String fileRepository = "C:\\board\\fileRepository";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else{
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		
		//파일 업로드
		Map<String, String> sellMap = fileUpload(request,response);
		String sell_file = sellMap.get("sell_file");
		
		MemberDTO user_data = (MemberDTO) request.getSession().getAttribute("AUTH_USER");
		
		WriteRequest writerequest = new WriteRequest(new Writer(user_data.getUser_id(), user_data.getUser_name()),
									sellMap.get("sell_title"), sellMap.get("sell_category"),
									Integer.parseInt(sellMap.get("sell_price")),
									sellMap.get("sell_loc"), sellMap.get("sell_content"),
									sellMap.get("sell_file"));
		
		writerequest.validate(errors); 
		
		if(!errors.isEmpty()) {
			return request.getContextPath() + FORM_VIEW;
		}
		
		int sell_no = writeSellService.write(writerequest);

		if(sell_file !=null && sell_file.length()!=0) {
			File rawFile = new File(fileRepository+"\\"+"temp"+"\\"+sell_file);
			if(!rawFile.exists()) {
				rawFile.mkdirs();
			}
			File saveDir = new File(fileRepository+"\\"+sell_no);
			saveDir.mkdirs();
			FileUtils.moveFileToDirectory(rawFile, saveDir, true);
		}
		
		PrintWriter printWriter = response.getWriter();
		String showFile = "<script> alert('complete'); location.href = '"+request.getContextPath()+"/gwon/sell/sellList.do'; </script>";
		printWriter.print(showFile);
		
		return null;
	}
	
	
	//파일 업로드
	private Map<String, String> fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> saveFileMap = new HashMap<String, String>();		
		
		//DiskFileItemFactory: FileItem 객체 생성
		//File: 경로 설정
		DiskFileItemFactory saveFactory = new DiskFileItemFactory();
		File savePath = new File(fileRepository);
		
		saveFactory.setRepository(savePath);
		saveFactory.setSizeThreshold(1024*1024);
		
		//파일 업로드 수행 서블릿
		ServletFileUpload fileSave = new ServletFileUpload(saveFactory);

		
		try {
			List files = fileSave.parseRequest(request);
			for (int i = 0; i < files.size(); i++) {
				//파일데이터를 관리하기 위한 FileItem 객체
				FileItem fileItem = (FileItem) files.get(i);
				//isFormField: String 입력 파라미터 -> true / file -> false
				if(fileItem.isFormField()) {
					//읽을 때 utf-8 인코딩 처리
					saveFileMap.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
				} else {
					// file name to separate the path from the actual file name using 
					if(fileItem.getSize() > 0) {
						int nameExtract = fileItem.getName().lastIndexOf("\\");
						//lastIndexOf 찾지 못했을 때 -1 return
						if(nameExtract == -1) {
							nameExtract = fileItem.getName().lastIndexOf("/");
						}
						String sell_file = fileItem.getName().substring(nameExtract+1);
						saveFileMap.put(fileItem.getFieldName(), sell_file);
						
						//상대경로에서 절대경로로 저장
						File uploadedFile = new File(fileRepository+"\\temp\\"+sell_file);
						fileItem.write(uploadedFile);
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return saveFileMap;
	}


}