package board.whi.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.whi.dao.WhiPhotoDAO;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class WhiPhotoDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터 수신
		String articleNoStr = request.getParameter("articleNo");
		int articleNo = Integer.parseInt(articleNoStr);
				
		//Connection 생성
		Connection conn = ConnectionProvider.getConnection();
		
		//비즈니스 로직 실행
		int delCk = new WhiPhotoDAO().delete(conn,articleNo);
		if(delCk==0) {
			return null;
		}	else {
			return request.getContextPath()+"/CJH/whi_photo/list.do?pageNo=1";
		}
	}

}
