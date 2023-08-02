package angel.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angel.model.Article;
import angel.model.Comment;
import angel.service.ArticleService;
import mvc.command.CommandHandler;

public class DetailHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		
		ArticleService articleService = new ArticleService();
		//게시글 상세조회 내용 + 조회수 증가
		Article article = articleService.selectContent(articleNo);
		request.setAttribute("DETAIL_CONTENT", article);
		
		//댓글 조회
		List<Comment> commentText = articleService.comment(articleNo);
		request.setAttribute("DETAIL_COMMENT", commentText);
		
		return request.getContextPath() + "/view/ANGEL/article/ArticleDetail.jsp";
	}

}
