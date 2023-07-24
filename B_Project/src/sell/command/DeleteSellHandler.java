package sell.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import sell.service.DeleteSellService;

	public class DeleteSellHandler implements CommandHandler {

	private DeleteSellService deleteSellService = new DeleteSellService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		
		deleteSellService.delete(no);
		
		response.sendRedirect(request.getContextPath()+"/sellList.do");
		return null;
	}
}
