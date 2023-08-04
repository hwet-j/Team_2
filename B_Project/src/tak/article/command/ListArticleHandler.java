package tak.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import tak.article.service.ArticlePage;
import tak.article.service.ListArticleService;

public class ListArticleHandler implements CommandHandler {

	private String FORM_VIEW = "/view/TAK/listArticle.jsp";
	private ListArticleService listArticleService = new ListArticleService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strPageNo = request.getParameter("pageNo"); 
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		ArticlePage articlePage = listArticleService.getArticlePage(pageNo);
		
		request.setAttribute("articlePage", articlePage);
		request.setAttribute("nowPage", pageNo); 
		
		return request.getContextPath()+FORM_VIEW;
	}

}












