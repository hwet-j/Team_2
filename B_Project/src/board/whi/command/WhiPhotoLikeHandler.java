package board.whi.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.whi.dao.WhiPhotoDAO;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class WhiPhotoLikeHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터 수령
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		boolean like = true;
		//커넥션생성
		Connection conn = null;
		
		conn = ConnectionProvider.getConnection();
		//db접근
		new WhiPhotoDAO().rating(conn,articleNo,like);
		
		conn.close();
		return request.getContextPath()+"/CJH/whi_photo/content.do?articleNo="+articleNo;
	}

}
