package notice.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.Member;
import mvc.command.CommandHandler;
import notice.service.WriteNoticeService;
import notice.service.WriteRequest;

public class WriteNoticeHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/notice/noticeForm.jsp";
	private WriteNoticeService writeService = new WriteNoticeService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteNoticeHandler의 process()진입");
		System.out.println("request.getMethod()="+request.getMethod());
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;		
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors);
		
		Member member = (Member)request.getSession(false).getAttribute("AUTH_USER");
		
		WriteRequest writeRequest=createWriteRequest(member,request);
		writeRequest.validate(errors);
		
		if(!errors.isEmpty()) {
			return request.getContextPath()+FORM_VIEW;
		}
		
		int newNoticeNo=writeService.write(writeRequest);
		
		request.setAttribute("newNoticeNo", newNoticeNo);
		
		return request.getContextPath()+"/view/notice/newNoticeSuccess.jsp";
		
	}
	private WriteRequest createWriteRequest(Member member, HttpServletRequest request) {
		String title = request.getParameter("title");
		String content =request.getParameter("content");
		
		return new WriteRequest(
				 new String(member.getId()),
				title,
				content
				);
	}
	
}
