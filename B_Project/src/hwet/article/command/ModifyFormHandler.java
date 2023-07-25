package hwet.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwet.article.model.HwetArticleDTO;
import hwet.article.service.ReadArticleService;
import mvc.command.CommandHandler;

// /hwet/article/modifyForm.do
/* 수정 폼을 실행하는 기능 */
public class ModifyFormHandler implements CommandHandler {
	// 데이터를 가져와 수정전, 이전 정보를 출력해야 하기때문에 readService 사용 (jsp에서 String 타입 이외에 form으로 데이터를 전송하는 방법은 없다고함)
	private ReadArticleService readService = new ReadArticleService();
	
	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 수정할 글번호
		int board_id = Integer.parseInt(request.getParameter("board_id")); 
		
		// 글 페이지
		int page_no = Integer.parseInt(request.getParameter("page_no"));
		
		// 수정 폼에 수정전 데이터를 출력하기 위해 데이터 가져오기
		HwetArticleDTO data = readService.getDetail(board_id);
		request.setAttribute("data", data);
		// 수정이 완료되고 나서 수정된 글을 다시 볼 수 있도록 설정하기 위해서 페이지번호 가져오기
		request.setAttribute("page_no", page_no);
		
        return "/view/HWET/article/modifyForm.jsp";
    }

}
