package angel.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.model.Comment;
import angel.service.ArticleService;
import mvc.command.CommandHandler;

//댓글 입력, 조회 핸들러
public class CommentHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//댓글 입력
		request.setCharacterEncoding("utf-8");
		
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		String name = request.getParameter("name");
		String comment = request.getParameter("comment");
		
		ArticleService articleService = new ArticleService();
		Comment writeComment = new Comment(articleNo, name, comment);
		int row = articleService.writeComment(writeComment);
		
		//댓글 조회
		List<Comment> commentText = articleService.comment(articleNo);
		request.setAttribute("DETAIL_COMMENT", commentText);
		
		return request.getContextPath() + "/Angel/detail.do?articleNo=" + articleNo;
	}

}
