package angel.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.model.Article;
import angel.service.ArticleService;
import mvc.command.CommandHandler;

public class WriteHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("memberid");
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		
		Article article = new Article(id, title, name, content);
		ArticleService articleService = new ArticleService();
		int row = articleService.write(article);
		
		return request.getContextPath() + "/Angel/article.do?pageNo=1";
	}

}
