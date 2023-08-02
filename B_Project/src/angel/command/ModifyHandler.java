package angel.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import angel.model.Article;
import angel.service.ArticleService;
import mvc.command.CommandHandler;

public class ModifyHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Article article = new Article(articleNo, title, content);
		
		ArticleService articleService = new ArticleService();
		int row = articleService.modify(article);

//		JSONObject jsonResponse1 = new JSONObject();
//		
//		String message1 = "";
//		if (row > 0) {
//			message1 = "success";
//		} else {
//			message1 = "failed";
//		}
//		
//		jsonResponse1.put("message1", message1);
//		
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		
//		response.getWriter().write(jsonResponse1.toString());
//		
//		return null;
		
		return request.getContextPath() + "/Angel/detail.do?articleNo=" + articleNo;
	}
}
