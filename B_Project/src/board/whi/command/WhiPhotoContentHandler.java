package board.whi.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.whi.dao.WhiPhotoDAO;
import board.whi.model.WhiPhotoArticle;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
//작업자 : 조중현
public class WhiPhotoContentHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//파라미터 수령
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		
		//커넥션생성
		Connection conn = ConnectionProvider.getConnection();

		//커넥션과 파라미터로 해당하는 게시글 수령
		WhiPhotoArticle article = new WhiPhotoDAO().selectArticle(conn, articleNo);
		//어트리뷰트로 지정
		request.setAttribute("CONTENT", article);
		return request.getContextPath()+"/view/CJH/whi_photo/whi_photo_content.jsp";
	}

}
