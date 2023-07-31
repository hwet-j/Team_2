package board.whi.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class WhiPhotoDownloadHandler implements CommandHandler {

	private static String imageRepository = "C:\\board\\image_repository";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//파라미터 수령
		String articleNo = request.getParameter("articleNo");
		String imageFileName = request.getParameter("imageFileName");
		
		String path = imageRepository +"\\"+articleNo+"\\"+imageFileName;
		File imageFile = new File(path);
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;fileName=" + imageFileName);
		
		FileInputStream in = new FileInputStream(imageFile);
		OutputStream out = response.getOutputStream();
		
		byte[] buffer = new byte[1024*8];
		while(true) {
			int cnt = in.read(buffer);
			if(cnt==-1) break;
			out.write(buffer,0,cnt);
		}
		
		in.close();
		out.close();
		
		return null;
	}

}
