package member.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDTO;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler  {

	//필드
	private static final String FORM_VIEW ="/joinForm.do";
	
	//생성자
	
	//담당컨트롤러의 요청메서드
	//요청주소 : http://localhost/join.do
	//파라미터
	//리턴유형 : String client에게 보여주는 jsp문서의 경로와 파일명
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response);  
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}
	
	//회원가입처리 
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date birth = null;
		
		String id = request.getParameter("user_id"); 
		String password = request.getParameter("password");  
		String name = request.getParameter("name");  
		String nickname = request.getParameter("nickname");  
		String gender = request.getParameter("gender");  
		try {
			birth = format.parse(request.getParameter("birth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		String phonenum = request.getParameter("phonenum");   
		
		System.out.println(birth);
		MemberDTO member = new MemberDTO(id, password, name, birth, nickname, gender, phonenum);
		
		System.out.println(id);
		System.out.println(password);
		 
		return FORM_VIEW;
	}


	//회원가입폼을 보여주기 
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	
	
	
}

















