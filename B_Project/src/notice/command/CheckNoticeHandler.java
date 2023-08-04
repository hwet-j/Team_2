package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.model.Notice;
import notice.service.NoticeData;
import notice.service.NoticeNotFoundException;
import notice.service.NoticeService;


//상세보기 요청 컨트롤러
//요청주소 http://localhost/notice/check.do?no=1
public class CheckNoticeHandler implements CommandHandler {
	private NoticeService NoticeService = new NoticeService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//1.파라미터받기
		String strNo = request.getParameter("no");//상세하게 보고싶은 공지글번호   pageNo는 페이지번호, no는 글번호
		int no = Integer.parseInt(strNo);//String->int 변환
		//2.비즈니스로직처리<->Service<->DAO<->DB
		try {
			//파라미터 int no : 상세조회할 글 번호
			//리턴 Notice notice: 글번호, 작성자id,제목,내용,작성일,조회수
			NoticeData nod =NoticeService.getDetail(no);
			//3.Model
			request.setAttribute("nod", nod);
			//View
			return request.getContextPath()+"/view/notice/checkNotice.jsp";
			
		}catch(NoticeNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404
			return null;
		}
	}

}
