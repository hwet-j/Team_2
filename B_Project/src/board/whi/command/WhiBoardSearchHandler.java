package board.whi.command;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.whi.dao.WhiBoardDAO;
import board.whi.model.WhiBoardArticle;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class WhiBoardSearchHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터 받아오기
		request.setCharacterEncoding("utf-8");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String search_type = request.getParameter("search_type");
		String subject = request.getParameter("subject");
		
		//페이징 사이즈 지정 - DAO와 일치시킬것
		int pagingSize =10;
		//페이지 개수
		int pageCnt =0;
		//커넥션 얻기
		Connection conn = ConnectionProvider.getConnection();
		//객체 생성
		List<WhiBoardArticle> articleList = new LinkedList<>();
		
			//비즈니스 로직 실행
		//1 - 검색결과 가져오기
		articleList = new WhiBoardDAO().search_article(conn, search_type, subject, pageNo);
		request.setAttribute("WHI_ARTICLE", articleList);
		//2- 페이지의 개수 가져오기
		int articleCnt = new WhiBoardDAO().search_article_count(conn, search_type, subject);
		if(articleCnt%pagingSize==0) {
			pageCnt = articleCnt/pagingSize;
		}else {
			pageCnt = articleCnt/pagingSize+1;
		}
		request.setAttribute("PAGECNT", pageCnt);
		//3- 카테고리 불러오기
		List<String> categoryList = new WhiBoardDAO().getCategory(conn);
		request.setAttribute("CATEGORY_LIST", categoryList);
		conn.close();
		return request.getContextPath()+"/view/CJH/whi_board/whi_board_list.jsp";
	}

}
