package sell.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import sell.model.SellDTO;
import sell.service.ReadSellService;
import sell.service.SellNotFoundException;

public class ReadSellHandler implements CommandHandler {

	ReadSellService readSellService = new ReadSellService(); 

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		int sellNum = Integer.parseInt(request.getParameter("no"));
		
		System.out.println(sellNum);
		try {
			SellDTO sellDTO = readSellService.getSell(sellNum);
			request.setAttribute("sellDTO", sellDTO);
			return request.getContextPath() + "/view/sell/readSell.jsp";
		} catch (SellNotFoundException e){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} 
	}
}