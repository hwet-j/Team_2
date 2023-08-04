package tak.article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tak.article.model.Writer;
import tak.article.service.WriteArticleService;
import tak.article.service.WriteRequest;
//import auth.service.LoginFailException;
//import auth.service.User;
import member.model.MemberDTO;
import mvc.command.CommandHandler;


public class WriteArticleHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/TAK/newArticleForm.jsp";
	private WriteArticleService writeService = new WriteArticleService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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

	
		
		
		MemberDTO user = (MemberDTO)request.getSession(false).getAttribute("AUTH_USER");
		
		WriteRequest writeRequest = createWriteRequst(user, request);
		writeRequest.validate(errors); 

		if(!errors.isEmpty()) { 
			return request.getContextPath()+FORM_VIEW;
		}

		int newArticleNo = writeService.write(writeRequest);
		
		request.setAttribute("newArticleNo", newArticleNo);
		
		return request.getContextPath()+"/tak/article/list.do";
		
	}

	private WriteRequest createWriteRequst(MemberDTO user, HttpServletRequest request) {

		String title = request.getParameter("title"); 
		String content = request.getParameter("content"); 
		return  new WriteRequest(
					new Writer( user.getUser_id(), user.getUser_name() ),
					request.getParameter("title"),
					request.getParameter("content")
				);
		
	
	}
}










