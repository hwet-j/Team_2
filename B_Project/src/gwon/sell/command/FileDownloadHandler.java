package gwon.sell.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class FileDownloadHandler implements CommandHandler {


	private static String fileRepository = "C:\\board\\fileRepository"; 

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String sell_no = request.getParameter("fileNo");
		String sell_file = request.getParameter("sell_file");
		
		//File: 경로 설정
		String path = fileRepository+"\\"+sell_no+"\\"+sell_file;
		File file = new File(path);

		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;file=" + sell_file);
		
		FileInputStream in = new FileInputStream(file);
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
