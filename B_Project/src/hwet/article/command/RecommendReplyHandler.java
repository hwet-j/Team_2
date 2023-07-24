package hwet.article.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.service.RecommandReplyService;
import mvc.command.CommandHandler;

// /hwet/article/recommendReply.do
/* 추천, 비추천 기능 */
public class RecommendReplyHandler implements CommandHandler {

	RecommandReplyService recommandReplyService = new RecommandReplyService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		int reply_id = Integer.parseInt(request.getParameter("reply_id"));
		String type = request.getParameter("type");
		
		// Update 성공여부를 가져옴
		boolean success = recommandReplyService.recommandReply(board_id, reply_id, type);
		
		// JSON 응답 생성 (성공 여부를 success 변수에 저장하여 JSON 형식으로 반환해준다.)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        out.print("{ \"success\": " + success + " }");
        
        out.flush();
		    
		
		return null;
	}

}
