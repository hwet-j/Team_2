package hwet.article.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.service.DeleteArticleService;
import mvc.command.CommandHandler;

// /hwet/article/delete.do
/* 게시판 글을 삭제하는 기능 */
public class DeleteHandler implements CommandHandler {
	
	DeleteArticleService deleteArticleService = new DeleteArticleService();
	
	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 삭제할 게시판 번호 받아오기
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		// 삭제 요청 실행
		deleteArticleService.deleteArticle(board_no);
		
		// 삭제 완료 후 성공 알림창을 띄우고 리스트로 돌아가기
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			String redirectURL = request.getContextPath() + "/hwet/article/list.do";
			String script = "<!DOCTYPE html>"
	                + "<html>"
	                + "<head>"
	                + "    <meta charset=\"UTF-8\">"
	                + "    <title>Success</title>"
	                + "    <script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@10\"></script>"
	                + "</head>"
	                + "<body>"
	                + "    <script>"
	                + "        Swal.fire({"
	                + "            position: 'center',"
	                + "            icon: 'success',"
	                + "            title: '글이 성공적으로 삭제되었습니다!',"
	                + "            showConfirmButton: true"
	                + "        }).then(function() {"
	                + "            window.location.href = '" + redirectURL + "';"
	                + "        });"
	                + "    </script>"
	                + "</body>"
	                + "</html>";
			
			response.getWriter().write(script);
			
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return "/hwet/article/list.do";
    }

}
