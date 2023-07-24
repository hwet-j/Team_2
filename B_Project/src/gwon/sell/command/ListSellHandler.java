package gwon.sell.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gwon.sell.service.ListSellService;
import gwon.sell.service.SellPage;
import mvc.command.CommandHandler;

public class ListSellHandler implements CommandHandler {

	
	private String FORM_VIEW = "/view/gwon/sell/listSell.jsp";
	private ListSellService listSellService = new ListSellService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int pageNo = 1;
		String strPageNo = request.getParameter("pageNo");
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		SellPage sellPage = listSellService.getSellPage(pageNo);
		
		
		request.setAttribute("sellPage", sellPage);
		request.setAttribute("nowPage", pageNo); 
		
		return request.getContextPath()+FORM_VIEW;
	}

}












