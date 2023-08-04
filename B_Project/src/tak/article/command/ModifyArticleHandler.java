package tak.article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tak.article.service.ModifyArticleService;
import tak.article.service.ModifyRequest;
import tak.article.service.OurArticleData;
import tak.article.service.ReadArticleService;
//import auth.service.User;
import member.model.MemberDTO;
import mvc.command.CommandHandler;


public class ModifyArticleHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/TAK/modifyForm.jsp";
	
	private ReadArticleService readArticleService = new ReadArticleService();
	
	private ModifyArticleService modifyArticleService = new ModifyArticleService();
	
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



	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int no = Integer.parseInt( request.getParameter("no") );
		
		
		OurArticleData oad = readArticleService.getDetail(no);
		MemberDTO user = (MemberDTO)request.getSession().getAttribute("AUTH_USER"); 
		if(!canModify(user, oad)) { 
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		
		ModifyRequest modReq = 
			new ModifyRequest(user.getUser_id(), no, oad.getTitle(), oad.getContent());
		
	
		request.setAttribute("modReq", modReq);
		
		
		return request.getContextPath()+FORM_VIEW;
	}

	
	private boolean canModify(MemberDTO user, OurArticleData oad) {
		return   user.getUser_id().equals(oad.getWriter_id());
	}
	
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writerId = request.getParameter("writerId");
		MemberDTO authUser = (MemberDTO)request.getSession().getAttribute("AUTH_USER");
		
		
		ModifyRequest modReq = new ModifyRequest(authUser.getUser_id(), no, title, content);
		request.setAttribute("modReq", modReq); 

		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors);
		if(!errors.isEmpty()) { 
			return request.getContextPath()+FORM_VIEW;
		}
		
		
		modifyArticleService.modify(modReq);
		
		
		response.sendRedirect(request.getContextPath()+"/tak/article/list.do");
		return null;
	}
}













