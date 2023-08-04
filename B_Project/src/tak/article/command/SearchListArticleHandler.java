package tak.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tak.article.service.ArticlePage;
import tak.article.service.ListArticleService;
import mvc.command.CommandHandler;

public class SearchListArticleHandler implements CommandHandler {
	private String FORM_VIEW = "/view/TAK/listArticle.jsp";
	private ListArticleService listArticleService = new ListArticleService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strPageNo = request.getParameter("pageNo"); 
		String search = request.getParameter("searchcontent");
		
		int pageNo = 1; 
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		
		ArticlePage articlePage = listArticleService.getSearchArticlePage(pageNo,search);
		
		
		request.setAttribute("articlePage", articlePage);
		request.setAttribute("nowPage", pageNo);
		
		
		return request.getContextPath()+FORM_VIEW;
	}

		
	

}
