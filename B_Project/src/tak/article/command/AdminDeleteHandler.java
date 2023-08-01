package tak.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import tak.article.service.DeleteArticleService;

public class AdminDeleteHandler implements CommandHandler{
	
	private DeleteArticleService deleteArticleService = new DeleteArticleService();
	private String FORM_VIEW = "/view/TAK/adminDeleteForm.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response); //p607 25라인
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
		
		
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("processForm()");
		return request.getContextPath()+FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String delId = request.getParameter("username");
		deleteArticleService.deleteAll(delId);
		response.sendRedirect(request.getContextPath()+"/tak/article/list.do");
		return null;
	}
	
}
