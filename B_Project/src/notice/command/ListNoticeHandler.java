package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.service.ListNoticeService;
import notice.service.NoticePage;

public class ListNoticeHandler implements CommandHandler {
	
	private String FORM_VIEW = "/view/notice/listNotice.jsp";
	public ListNoticeService listNoticeService = new ListNoticeService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListNoticeHandler-process()진입");
		String strPageNo = request.getParameter("pageNo");
		int pageNo =1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		NoticePage noticePage = listNoticeService.getNoticePage(pageNo);
		
		request.setAttribute("noticePage", noticePage);
		request.setAttribute("nowPage", pageNo);
		
		return request.getContextPath()+FORM_VIEW;
	}
}
