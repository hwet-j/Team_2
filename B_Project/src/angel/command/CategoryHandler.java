package angel.command;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.dao.ArticleDAO;
import angel.model.Article;
import angel.service.PageService;
import jdbc.JDBCUtil;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class CategoryHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String category = request.getParameter("category");
		request.setAttribute("NAME", category);
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = Integer.parseInt(pageNoStr);
		
		Connection conn = ConnectionProvider.getConnection();
		ArticleDAO articleDAO = new ArticleDAO();
		
		//카테고리별 총 게시물 가져오기
		List<Article> article = articleDAO.category(conn, category);

		//카테고리별 총 게시물 개수
		int categoryTotal = articleDAO.categoryTotal(conn, category);
		//pageCount 페이징처리시 페이지 개수
		int pageCount = (categoryTotal/5) + 1;
		if(categoryTotal%5==0) {pageCount-=1;}
		request.setAttribute("PAGE_COUNT", pageCount);

		PageService pageService = new PageService();
		//페이지 번호마다 보여질 게시물 목록 가져오기
		List<Article> pageNoCategory = pageService.pageNoCategory(category, pageNo);
		request.setAttribute("ARTICLE", pageNoCategory);
		
		JDBCUtil.close(conn);
		
		return request.getContextPath() + "/view/ANGEL/article/Article.jsp";
	}

}
