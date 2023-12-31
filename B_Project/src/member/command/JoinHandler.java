package member.command;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDTO;
import member.service.JoinService;
import mvc.command.CommandHandler;

/* 
Get : 회원가입 창으로 이동하는 핸들러를 실행
Post : 요청 받은 데이터를 기반으로 회원가입을 진행  
*/

/* join.do */
public class JoinHandler implements CommandHandler  {
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {// 요청방식이 get방식이면  FORM_VIEW
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {// 요청방식이 post방식이면 기능
			return processSubmit(request,response);  
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}
	
	//회원가입처리 
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String is_admin = request.getParameter("admin");
		// 관리자 가입 폼에서 요청 받았는지 확인
		if (is_admin == null) {
			// birth의 형 변환을 위한 객체
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date birth = null;
			
			String id = request.getParameter("user_id"); 
			String password = request.getParameter("password");  
			String name = request.getParameter("name");  
			String nickname = request.getParameter("nickname");  
			String gender = request.getParameter("gender"); 
			
			
			// 데이터 베이스의 gender컬럼에 저장가능한 값이 "남성, 여성" 두가지 뿐이라 값을 변경 (HTML의 value값 자체를 수정해도 상관없음)
			if (gender.equals("male")) {
				gender = "남성";
	 		} else if (gender.equals("female")) {
	 			gender = "여성";
	 		}
			// birth 정보는 String 타입으로 받아들여지며 형식도 "YYYY-MM-DD"이기 때문에 데이터 형변환이 필요하여 형변환
			try {
				birth = format.parse(request.getParameter("birth"));
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			String phonenum = request.getParameter("phonenum");   
			
			
			// 얻어진 Parameter들을 MemberDTO 객체에 저장 
			MemberDTO member = new MemberDTO(id, password, name, birth, nickname, gender, phonenum);
			
			// Service 객체 생성 
			JoinService service = new JoinService();
			
			// 회원가입 진행
			service.join(member);
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				String redirectURL = request.getContextPath() + "/login.do";
				String script = "<!DOCTYPE html>"
		                + "<html>"
		                + "<head>"
		                + "    <meta charset=\"UTF-8\">"
		                + "    <title>Success</title>"
		                + "    <script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@10\"></script>"
		                + "</head>"
		                + "<body>"
		                + "    <script>"
		                + "        Swal.fire({"
		                + "            position: 'center',"
		                + "            icon: 'success',"
		                + "            title: '회원가입이 성공적으로 이루어졌습니다!',"
		                + "            text: '로그인 페이지로 이동합니다.',"
		                + "            showConfirmButton: true"
		                + "        }).then(function() {"
		                + "            window.location.href = '" + redirectURL + "';"
		                + "        });"
		                + "    </script>"
		                + "</body>"
		                + "</html>";
				
				response.getWriter().write(script);
				
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			return null;
		} else {
			String id = request.getParameter("user_id"); 
			String password = request.getParameter("password");  
			
			JoinService service = new JoinService();
			
			boolean isDuplicate = service.idDuplicateCheck(id);
			if (isDuplicate) {
		        try {
		        	response.setContentType("text/plain");
		        	response.setCharacterEncoding("UTF-8");
					response.getWriter().write(String.valueOf(isDuplicate));	// boolean형 데이터를 String으로  변환하여 전송
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				int result = service.joinAdmin(id, password);
			}
			
			
			return null;
		}
		
		
	}


	//회원가입폼을 보여주기 
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return "/joinForm.do";
	}
	
}



