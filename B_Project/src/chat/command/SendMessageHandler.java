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
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);// 405
			return null;
		}

	}

	// 채팅쓰기
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO user = (MemberDTO) request.getSession().getAttribute("AUTH_USER");
		
		if (user == null) {
			return "/view/errorPage.jsp";
		}
		int room_id = Integer.parseInt(request.getParameter("room_id"));
		String sender_id = request.getParameter("writer");
		String content = request.getParameter("content");

		int result = sendMessageService.sendMessage(room_id, sender_id, content);
		
		String message = "";
		if (result > 0) {
			message = "success";
		} else {
			message = "fail";
		}
		
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 상대방 출력 내용
		String content_output = "<span class=\"chat-bubble left-bubble\">";
		content_output += "<span>[" + sender_id + "]</span>";
		content_output += "<span style=\"font-weight: bold;\">" + content + "</span>";
		content_output += "<span style=\"font-size: small;\"> (" + dateFormat.format(currentDate) + ")</span>";
		
		// 자신 출력 내용
		String content_input = "<span class=\"chat-bubble right-bubble\">";
		content_input += "<span style=\"font-weight: bold;\">" + content + "</span>";
		content_input += "<span style=\"font-size: small;\"> (" + dateFormat.format(currentDate) + ")</span>";

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("input_data", content_input);
		jsonResponse.put("output_data", content_output);

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
