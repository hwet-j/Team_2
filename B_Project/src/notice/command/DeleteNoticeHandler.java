//package notice.command;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import mvc.command.CommandHandler;
//import notice.service.DeleteNoticeService;
//
//public class DeleteNoticeHandler implements CommandHandler {
//
//	@Override
//	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String noticeNoStr = request.getParameter("notice_no");
//		int noticeNo = Integer.parseInt(noticeNoStr);
//		
//		DeleteNoticeService deleteNoticeService = new DeleteNoticeService();
//		int deleteCnt = deleteNoticeService.deleteNotice(noticeNo);
//		
//		if(deleteCnt!=0) {return request.getContextPath()+"/view/notice/deleteSuccess.jsp";}
//		else {{return request.getContextPath()+"/view/notice/deleteFail.jsp";}}
//	}
//
//}