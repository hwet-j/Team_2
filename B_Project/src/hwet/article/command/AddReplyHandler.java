package hwet.article.command;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.service.AddReplyService;
import mvc.command.CommandHandler;

// /hwet/article/addReply.do
/* 댓글을 추가하는 기능 */
public class AddReplyHandler implements CommandHandler {
	
	AddReplyService replyService = new AddReplyService();
	
	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		String writer = request.getParameter("user_id");
		String nick_name= request.getParameter("user_nickname");
		String content = request.getParameter("reply_content");
		
		String page_no = request.getParameter("page_no");
		String search_type = request.getParameter("search_type");
		String keyword = request.getParameter("keyword");
		String category_info = request.getParameter("category_info");
		
		if(category_info == null) {
			category_info = "전체";
		} else {
			
		}
		
		if (search_type == null) {
			search_type = "";
			keyword = "";
		}
		
		
		replyService.writeReply(board_id, writer,nick_name, content);
		
		// 인코딩 하지 않으면 url에서 읽지 못함 
		String encoded_keyword = URLEncoder.encode(keyword, "UTF-8");
		String encoded_category_info = URLEncoder.encode(category_info, "UTF-8");
		String encoded_search_type = URLEncoder.encode(search_type, "UTF-8");
		String redirectURL = "/hwet/article/read.do?no=" + board_id + "&page_no=" + page_no + "&search_type=" + encoded_search_type + "&keyword=" + encoded_keyword + "&category_info=" + encoded_category_info; 
		response.sendRedirect(redirectURL);
		
        return null;
    }

}
