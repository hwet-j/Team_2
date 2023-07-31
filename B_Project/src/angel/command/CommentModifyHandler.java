package angel.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.model.Comment;
import angel.service.ArticleService;
import mvc.command.CommandHandler;

public class CommentModifyHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String comment = request.getParameter("comment");
		
		ArticleService articleService = new ArticleService();
		int row = articleService.modifyComment(commentNo, comment);

		return request.getContextPath() + "/Angel/article.do?pageNo=1";
	}

}
