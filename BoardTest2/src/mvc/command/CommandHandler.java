package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 요청처리를 위한 담당컨트롤러의 요청메서드의 이름을 동일하게 하는 용도의 인터페이스 
public interface CommandHandler {
	// 담당 컨트롤러의 요청 메서드
	// 리턴 : String (Client에게 보여주는 jsp문서 정보-경로,파일명)
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
}
