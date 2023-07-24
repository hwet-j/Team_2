package gwon.sell.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gwon.sell.service.DeleteSellService;
import mvc.command.CommandHandler;

	public class DeleteSellHandler implements CommandHandler {

	private DeleteSellService deleteSellService = new DeleteSellService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		
		deleteSellService.delete(no);
		
		response.sendRedirect(request.getContextPath()+"/veiw/GWON/sell/sellList.do");
		return null;
	}
}
