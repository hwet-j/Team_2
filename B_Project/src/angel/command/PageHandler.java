package angel.command;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.dao.ArticleDAO;
import angel.model.Article;
import angel.service.PageService;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class PageHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));

		Connection conn = ConnectionProvider.getConnection();
		ArticleDAO articleDAO = new ArticleDAO();

		int totalPageCount = articleDAO.articleTotal(conn);
		int pageCount = (totalPageCount / 10) + 1;
		request.setAttribute("pageCount", pageCount);
		
		PageService pageService = new PageService();
		List<Article> article = new LinkedList<>();
		article = pageService.pageNoArticle(pageNo);
		request.setAttribute("ARTICLE", article);
		
		return request.getContextPath() + "/view/ANGEL/article/Article.jsp";
	}
}
