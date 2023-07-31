package angel.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.model.Article;
import angel.service.ArticleService;
import mvc.command.CommandHandler;

public class DetailHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		
		ArticleService articleService = new ArticleService();
		Article article = articleService.selectContent(articleNo);
		request.setAttribute("DETAIL_CONTENT", article);
		
		return request.getContextPath() + "/view/ANGEL/article/ArticleDetail.jsp";
	}

}
