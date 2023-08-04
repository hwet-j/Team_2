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
		String delId = request.getParameter("username");
		deleteArticleService.deleteAll(delId);
		response.sendRedirect(request.getContextPath()+"/tak/article/list.do");
		return null;
	}
	
}
