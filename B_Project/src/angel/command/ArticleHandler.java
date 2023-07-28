package angel.command;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.dao.ArticleDAO;
import angel.model.Article;
import angel.service.ArticleService;
import angel.service.PageService;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class ArticleHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		ArticleService articleService = new ArticleService();
		List<Article> article = articleService.selectAll();
	
		
		ArticleDAO articleDAO = new ArticleDAO();
		Connection conn = ConnectionProvider.getConnection();
		int articleTotal = articleDAO.articleTotal(conn);
		int pageCount = (articleTotal/5) + 1;
		System.out.println("pageCount : " +pageCount);
		request.setAttribute("PageCount", pageCount);
		
		PageService pageService = new PageService();
		List<Article> pageNoArticle = pageService.pageNoArticle(pageNo);
		request.setAttribute("ARTICLE", pageNoArticle); // 담고다닐정보
//		List<Article> pagePerArticle = new LinkedList<>();
//		pagePerArticle = articleService.pagePerArticle(pageNo);
		
		return request.getContextPath() + "/view/ANGEL/article/Article.jsp";// 이동할페이지
		
	}

}
