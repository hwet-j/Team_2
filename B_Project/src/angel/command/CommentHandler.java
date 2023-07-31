package angel.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.model.Comment;
import angel.service.ArticleService;
import mvc.command.CommandHandler;

public class CommentHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		String name = request.getParameter("name");
		String comment = request.getParameter("comment");
		
		ArticleService articleService = new ArticleService();
		Comment writeComment = new Comment(articleNo, name, comment);
		int row = articleService.writeComment(articleNo, writeComment);
		
		List<Comment> commentText = articleService.comment(articleNo);
		request.setAttribute("DETAIL_COMMENT", commentText);
		
		return request.getContextPath() + "/Angel/detail.do?articleNo=" + articleNo;
	}

}
