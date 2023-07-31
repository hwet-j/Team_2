package board.whi.command;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.whi.dao.WhiPhotoDAO;
import board.whi.model.WhiPhotoArticle;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

//작업자 : 조중현
public class WhiPhotoListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터 수신
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = Integer.parseInt(pageNoStr);
		
		//Connection 생성
		Connection conn = ConnectionProvider.getConnection();
		
		//페이징처리에 맞는 게시글 10개 가져오기
		List<WhiPhotoArticle> articleList =new WhiPhotoDAO().photoArticleList(conn, pageNo);
		request.setAttribute("ARTICLE", articleList);
		
		//페이징처리를 위한 게시글의 전체 개수 수신 후 어트리뷰트 지정
		int PageCnt = new WhiPhotoDAO().pageCnt(conn);
		request.setAttribute("PAGE_CNT", PageCnt);
		
		conn.close();
		return request.getContextPath()+"/view/CJH/whi_photo/whi_photo_list.jsp";
	}

}
