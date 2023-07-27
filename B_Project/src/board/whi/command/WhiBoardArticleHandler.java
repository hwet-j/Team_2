package board.whi.command;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.whi.dao.WhiBoardDAO;
import board.whi.model.WhiBoardArticle;
import board.whi.service.WhiBoardArticleService;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class WhiBoardArticleHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNo = Integer.parseInt(request.getParameter("pageNo")); // 컨텐츠 불러와지는거 확인되면 페이징 처리 할때 쓰자
		Connection conn = ConnectionProvider.getConnection();
		WhiBoardDAO dao = new WhiBoardDAO();
		int articleTotalCnt = dao.countTotalArticle(conn);
		System.out.println("총게시글수 = "+articleTotalCnt);
		int pageCnt = (articleTotalCnt/10)+1;
		request.setAttribute("PAGECNT", pageCnt);
		List<WhiBoardArticle> articleList = new LinkedList<>();
		WhiBoardArticleService whiBoardArticleService = new WhiBoardArticleService();
		articleList = whiBoardArticleService.showPagedArticle(pageNo);
		//3- 카테고리 불러오기
		List<String> categoryList = new WhiBoardDAO().getCategory(conn);
		request.setAttribute("CATEGORY_LIST", categoryList);
		request.setAttribute("WHI_ARTICLE", articleList);
		
		conn.close();
		return request.getContextPath()+"/view/CJH/whi_board/whi_board_list.jsp";
	}

}
