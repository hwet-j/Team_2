package chat.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import chat.service.SendMessageService;
import member.model.MemberDTO;
import mvc.command.CommandHandler;


/* chat/sendMessage.do */ 
public class SendMessageHandler implements CommandHandler {
	
	SendMessageService sendMessageService = new SendMessageService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if( request.getMethod().equalsIgnoreCase("GET") ) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}
		
	// 채팅쓰기
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO user = (MemberDTO)request.getSession().getAttribute("AUTH_USER"); 
		if (user == null) {
			return "/view/errorPage.jsp";
		}
		int room_id = Integer.parseInt(request.getParameter("room_id"));
		String sender_id = request.getParameter("writer");
		String content = request.getParameter("content");
		
		
		 int result = sendMessageService.sendMessage(room_id, sender_id, content);
		  
		 String message = ""; 
		 if (result > 0 ) { 
			  message = "success"; 
		 } else { 
			  message  = "fail"; 
		 }
		 
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String align = (sender_id.equals(user.getUser_id())) ? "right-bubble" : "left-bubble";
		
		
		String content_input = "<span class=\"chat-bubble " + align + "\">";
		if (!sender_id.equals(sender_id)) {
			content_input += "<span>[" + sender_id + "]</span>";
		}
		content_input += "<span style=\"font-weight: bold;\">" + content + "</span>";
		content_input += "<span style=\"font-size: small;\"> (" + dateFormat.format(currentDate) + ")</span>";
		content_input += "</div>";
		content_input += "</div>";
		
		JSONObject jsonResponse = new JSONObject();
    	jsonResponse.put("input_data", content_input);
    	
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	// JSON 형식으로 응답을 생성합니다.
    	try {
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		
		return null;
	}
		

}
