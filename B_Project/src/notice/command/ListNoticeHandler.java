package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//목록보기 요청 담당 컨트롤러이다
//요청주소  http://localhost/notice/list.do?
import mvc.command.CommandHandler;
import notice.service.NoticeService;
import notice.service.NoticePage;

public class ListNoticeHandler implements CommandHandler {
	
	private String FORM_VIEW = "/view/notice/listNotice.jsp";
	public NoticeService NoticeService = new NoticeService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListNoticeHandler-process()진입");
		//1.파라미터받기
		String strPageNo = request.getParameter("pageNo");
		
		
		
		int pageNo =1;//user가 선택안했을 때 기본적으로 보여지는 페이지번호
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		//2.비즈니스로직<->Service<->DAO<->DB
				/*파라미터  int pageNo : 보고싶은 페이지(=>현재 페이지)*/
		NoticePage noticePage = NoticeService.getNoticePage(pageNo);
		
		//3.Model
	    /*NoticePage noticePage에는  
	      총 게시글수포함(getTotal()호출)
		  notice목록포함(getContent()호출) 
		  int  totalPages;	//총페이지수   
		  int  startPage;	//시작페이지  
		  int  endPage;	//끝페이지*/
		request.setAttribute("noticePage", noticePage); //notice목록,총페이지수 등
		request.setAttribute("nowPage", pageNo);//현재 페이지
		
		//4.View
		return request.getContextPath()+FORM_VIEW;
	}
}
