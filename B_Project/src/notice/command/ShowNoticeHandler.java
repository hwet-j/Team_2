//package notice.command;
//
//import java.sql.Connection;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import jdbc.connection.ConnectionProvider;
//import mvc.command.CommandHandler;
//import notice.dao.NoticeDAO_temp;
//import notice.model.Notice;
//import notice.service.ShowNoticeService;
//
////목록보기 요청 담당 컨트롤러이다 listNotice로 바꾸기?
//// 요청주소 /notice/showNotice.do?num=1 ->//요청주소  http://localhost/notice/listNotice.do?
//
//public class ShowNoticeHandler implements CommandHandler{
//
//	@Override
//	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String strPageNo = request.getParameter("num"); //user가 선택한 페이지번호
//		int pageNo =1; //user가 선택안했을 때 기본적으로 보여지는 페이지번호
//		if(strPageNo!=null) {
//			pageNo = Integer.parseInt(strPageNo);		
//		}
//		
//		NoticeDAO_temp dao = new NoticeDAO_temp();
//		int pagingUnit=5;
//		Connection conn = ConnectionProvider.getConnection();
//		int pageCnt = dao.noticeCnt(conn)/pagingUnit +1;
//		conn.close();	
//		System.out.println(pageCnt);
//		ShowNoticeService showNoticeService = new ShowNoticeService();
//		List<Notice>noticeList =showNoticeService.showNoticePaged(pageNo);
//		HttpSession session3 = request.getSession();
//		session3.setAttribute("NOTICE", noticeList);
//		session3.setAttribute("PAGE_COUNT", pageCnt);
//		return request.getContextPath()+"/view/notice/showNotice.jsp";
//	}
//	
//}
