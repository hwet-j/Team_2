//package notice.command;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import auth.service.Member;
//import mvc.command.CommandHandler;
//import notice.service.ModifyNoticeService;
//import notice.service.ModifyRequest;
//import notice.service.NoticeData;
//import notice.service.ReadNoticeService;
//
//public class ModifyNoticeHandler implements CommandHandler{
//
//	private static final String FORM_VIEW = "/view/notice/modifyForm.jsp";
//	
//	private ReadNoticeService readNoticeService  = new ReadNoticeService();
//	private ModifyNoticeService modifyNoticeService = new ModifyNoticeService();
//	@Override
//	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("ModifyNoticeHandler의 process진입");
//		
//		if( request.getMethod().equalsIgnoreCase("GET") ) {
//			return processForm(request,response);
//		}else if(request.getMethod().equalsIgnoreCase("POST")) {
//			return processSubmit(request,response); 
//		}else {
//			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//			return null;
//		}
//	
//	}
//	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
//	
//		int no = Integer.parseInt(request.getParameter("no"));
//		NoticeData noc = readNoticeService.getDetail(no);
//		Member member =(Member)request.getSession().getAttribute("AUTH_USER");
//		if(!canModify(member, noc)) {
//			response.sendError(HttpServletResponse.SC_FORBIDDEN);
//			return null;
//		}
//		ModifyRequest modReq = new ModifyRequest(member.getId(), no, noc.getNotice_title(),noc.getNotice_content());
//		request.setAttribute("modReq", modReq);
//		
//		return request.getContextPath()+FORM_VIEW;	
//
//}
//	private boolean canModify(Member member, NoticeData noc) {
//		return member.getId().equals(noc.getUser_id());
//	}
//	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("ModifyHandler의 process()진입");
//		int no =Integer.parseInt(request.getParameter("no"));
//		String title =request.getParameter("notice_title");
//		String content=request.getParameter("notice_content");
//		String user_id=request.getParameter("user_id");
//		Member authMember = (Member)request.getSession().getAttribute("AUTH_USER");
//		
//	ModifyRequest modReq = new ModifyRequest(authMember.getId(), no,title,content);	
//	request.setAttribute("modReq", modReq);
//
//	Map<String, Boolean> errors = new HashMap<String, Boolean>();
//	request.setAttribute("errors", errors);
//	if(!errors.isEmpty()) {
//		return request.getContentType()+FORM_VIEW;
//			
//	}
//	modifyNoticeService.modify(modReq);
//	response.sendRedirect(request.getContextPath()+"/notice/list.do");
//	return null;
//	
//	}
//}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
