package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.service.NoticeService;


//삭제요청을 담당하는 컨트롤러
//요청주소 http://localhost/notice/delete.do
public class DeleteNoticeHandler implements CommandHandler {

	private NoticeService noticeService = new NoticeService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		int no =Integer.parseInt(request.getParameter("no"));//삭제할글번호
		
		//2.비즈니스로직<->Service<->DAO<->DB
		//파라미터 no - 삭제할  글번호
		//리턴 int 삭제성공시 삭제된글번호리턴, 실패시 0
		//noticeService.deleteNotice(no);
		int noticeno = noticeService.deleteNotice(no);
		
		//3.Model
		//4.View
		//성공시 목록보기요청
		response.sendRedirect(request.getContextPath()+"/notice/list.do");
		return null;
		
	}

}