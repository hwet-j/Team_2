//package notice.command;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import mvc.command.CommandHandler;
//import notice.model.NoticeContent;
//import notice.service.ShowNoticeContentService;
//
//// showNoticeContent.do?num=1
////상세보기 요청담당 컨트롤러이다
//
//public class ShowNoticeContentHandler implements CommandHandler {
//
//	@Override
//	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		
//		String noticeNumstr = request.getParameter("num");
//		int pageNo = Integer.parseInt(noticeNumstr);
//		ShowNoticeContentService showNoticeContentService = new ShowNoticeContentService();
//		NoticeContent noticeContent = showNoticeContentService.showNoticeContentService(pageNo);
//		System.out.println("Handler호출"+noticeContent.toString());
//		HttpSession session = request.getSession();
//		session.setAttribute("NoticeContent", noticeContent);
//		return request.getContextPath()+"/view/notice/showNoticeContent.jsp";
//		
//	}
//
//}