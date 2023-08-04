package board.whi.command;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
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

import board.whi.dao.WhiPhotoDAO;
import board.whi.model.WhiPhotoArticle;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class WhiPhotoWriteHandler implements CommandHandler{

	private static String imageRepository = "C:\\board\\image_repository";

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, String> boardMap = upload(request,response);
		
		String imageFileName = boardMap.get("image_src");
		
		WhiPhotoArticle article = new WhiPhotoArticle(boardMap.get("user_id"),boardMap.get("title") ,boardMap.get("content") ,boardMap.get("image_src"));
		
		Connection conn = ConnectionProvider.getConnection();
		
		int articleNo = new WhiPhotoDAO().insertArticle(conn, article);
		
		if(imageFileName!=null && imageFileName.length()!=0) {
			File srcFile = new File(imageRepository+"\\"+"temp"+"\\"+imageFileName);
			if (!srcFile.exists()) {
				srcFile.createNewFile();
			}
			File destDir = new File(imageRepository+"\\"+articleNo);
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer= response.getWriter();
		String msg = "<script>alert('inserted success!!');location.href="
			     +request.getContextPath()+"'/board/list.do';</script>";
		writer.print(msg);
		conn.close();
		return request.getContextPath()+"/CJH/whi_photo/list.do?pageNo=1";
	}

	//업로드 메소드
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> boardMap = new HashMap<String, String>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File dirPath = new File(imageRepository);
		factory.setRepository(dirPath);
		factory.setSizeThreshold(-1);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try{
			List items = upload.parseRequest(request);
			for(int i=0; i<items.size();i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if(fileItem.isFormField()) {
					boardMap.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
				}else{
					if(fileItem.getSize()>0) {
						int idx = fileItem.getName().lastIndexOf("\\"); //추출시작인덱스;
			    		if(idx ==  -1) { //  "\\"가 존재하지않으면
			    			idx = fileItem.getName().lastIndexOf("/");
			    		}
			    		String fileName = fileItem.getName().substring(idx+1);
			    		boardMap.put(fileItem.getFieldName(), fileName);
			    		File uploadFile = new File(imageRepository+"\\temp\\"+fileName);
			    		
			    		fileItem.write(uploadFile);
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardMap;
	}

}
