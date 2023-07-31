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

//게시물 목록 조회 /Angel/article.do
public class ArticleHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//게시물 조회할 페이지 번호 가져오기
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		ArticleService articleService = new ArticleService();
		//모든 게시물 목록(articleNo, memberid, name, title, regdate, readCnt, content) 불러오기
		List<Article> article = articleService.selectAll();

		//--------------------------------------------------------
		
		ArticleDAO articleDAO = new ArticleDAO();
		Connection conn = ConnectionProvider.getConnection();
		
		//articleTotal 총 게시물 수
		int articleTotal = articleDAO.articleTotal(conn);
		//pageCount 페이징처리시 페이지 개수
		int pageCount = (articleTotal/10) + 1;
		request.setAttribute("PAGECOUNT", pageCount);
		
		PageService pageService = new PageService();
		//페이지 번호마다 보여질 게시물 목록 가져오기
		List<Article> pageNoArticle = pageService.pageNoArticle(pageNo);
		request.setAttribute("ARTICLE", pageNoArticle);
		
		return request.getContextPath() + "/view/ANGEL/article/Article.jsp";
	}
}
