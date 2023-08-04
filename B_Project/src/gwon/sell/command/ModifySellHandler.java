package gwon.sell.command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import gwon.sell.model.SellDTO;
import gwon.sell.service.ModifyRequest;
import gwon.sell.service.ModifySellService;
import gwon.sell.service.ReadSellService;
import gwon.sell.service.SellNotFoundException;
import member.model.MemberDTO;
import mvc.command.CommandHandler;

public class ModifySellHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/GWON/sell/modifySell.jsp";
	
	private ReadSellService readSellService = new ReadSellService();
	private ModifySellService modifySellService = new ModifySellService();
	private static String fileRepository = "C:\\board\\fileRepository";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if( request.getMethod().equalsIgnoreCase("GET") ) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int no = Integer.parseInt(request.getParameter("no"));

		SellDTO sellDTO = readSellService.getSell(no);
		MemberDTO user_data = (MemberDTO)request.getSession().getAttribute("AUTH_USER");
		
		if(!canModify(user_data, sellDTO)) { 
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		//수정을 위해 세션에서 가져온 회원id, 글번호, db에서 가져온 제목과 내용
		ModifyRequest modRequest = 
			new ModifyRequest(user_data.getUser_id(), no, sellDTO.getSell_title(), sellDTO.getSell_category(), sellDTO.getSell_price(), sellDTO.getSell_loc(), sellDTO.getSell_content(), sellDTO.getSell_file());
		
		request.setAttribute("modRequest", modRequest);
		
		return request.getContextPath()+FORM_VIEW;
	}

	private boolean canModify(MemberDTO user_data, SellDTO sellDTO) {
		return   user_data.getUser_id().equals(sellDTO.getUser_id());
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors);

		Map<String, String> sellMap = fileUpload(request,response);
		
		String sell_file = sellMap.get("sell_file");
		
		MemberDTO user_data = (MemberDTO)request.getSession().getAttribute("AUTH_USER");
		
		int sell_no = Integer.parseInt(sellMap.get("sell_no"));
		
		ModifyRequest modRequest = new ModifyRequest(user_data.getUser_id(),
										sell_no,
										sellMap.get("sell_title"), sellMap.get("sell_category"),
										Integer.parseInt(sellMap.get("sell_price")),
										sellMap.get("sell_loc"), sellMap.get("sell_content"),
										sellMap.get("sell_file"));
		
		modRequest.validate(errors); 
		if(!errors.isEmpty()) { 
			return request.getContextPath()+FORM_VIEW;
		}
		
		request.setAttribute("modRequest", modRequest); 
		
		modifySellService.modify(modRequest);
		
		if(sell_file !=null && sell_file.length()!=0) {
			//기존 이미지 삭제
			String originalSell_file = sellMap.get("originalSell_file");
			File originalFile = new File(fileRepository+"\\"+sell_no+"\\"+originalSell_file);
			originalFile.delete(); 

			File rawFile = new File(fileRepository+"\\"+"temp"+"\\"+sell_file);
			File saveDir = new File(fileRepository+"\\"+sell_no);
			saveDir.mkdirs();
			FileUtils.moveFileToDirectory(rawFile, saveDir, true);
		}
		
		//수정 성공하면 판매목록 상세 조회 페이지로 이동
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter printWriter = response.getWriter(); //브라우저에 출력
		String showFile = "<script>alert('modify success!!');location.href="
				     +request.getContextPath()+"'/gwon/sell/sellRead.do?no="+sell_no+"';</script>";		
		printWriter.print(showFile);
		
		return null;
	}
	
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