package socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// WebSocket 호스트 설정
@ServerEndpoint("/broadsocket")
public class BroadSocket {
	// 접속 된 클라이언트 WebSocket session 관리 리스트
	private static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
	// 메시지에서 유저 명을 취득하기 위한 정규식 표현
	private static Pattern pattern = Pattern.compile("^\\{\\{.*?\\}\\}"); // {{로 시작하고 }}로 끝나는 데이터
	// WebSocket으로 브라우저가 접속하면 요청되는 함수
	static String type = null;
	static String content = null;
	static String user = null;
	@OnOpen
	public void handleOpen(Session userSession) {
		// 클라이언트가 접속하면 WebSocket세션을 리스트에 저장한다.
		sessionUsers.add(userSession);
		// 콘솔에 접속 로그를 출력한다.
		System.out.println("client is now connected...");
	}

	// WebSocket으로 메시지가 오면 요청되는 함수
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
		// 메시지 내용을 콘솔에 출력한다.
		
        JSONParser parser = null;
        JSONObject jsonObject = null;
        try {
        	parser = new JSONParser();
			jsonObject = (JSONObject) parser.parse(message);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        type = (String) jsonObject.get("type");
        content = (String) jsonObject.get("content");
        user = (String) jsonObject.get("user");
        System.out.println(type);
        System.out.println(content);
        System.out.println(user);
        

		sessionUsers.forEach(session -> {
			// 리스트에 있는 세션과 메시지를 보낸 세션이 같으면 메시지 송신할 필요없다.
			if (session == userSession) {
				return;
			}
			System.out.println("dafsdfasdfs");
			try {
				// 리스트에 있는 모든 세션(메시지 보낸 유저 제외)에 메시지를 보낸다. (형식: 유저명 => 메시지)
				System.out.println(content);
				session.getBasicRemote().sendText(content);
			} catch (IOException e) {
				// 에러가 발생하면 콘솔에 표시한다.
				e.printStackTrace();
			}
		});
	}

	// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose
	public void handleClose(Session userSession) {
		// session 리스트로 접속 끊은 세션을 제거한다.
		sessionUsers.remove(userSession);
		// 콘솔에 접속 끊김 로그를 출력한다.
		System.out.println("client is now disconnected...");
	}
}

