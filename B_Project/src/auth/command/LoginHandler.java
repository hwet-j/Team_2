package auth.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginService;
import member.model.MemberDTO;
import mvc.command.CommandHandler;



// login.do
public class LoginHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginHandler의 process()진입");
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("user_id"); 
		String password = request.getParameter("password");  
		
		
		System.out.println(id);
		System.out.println(password);
		LoginService service = new LoginService();
		
		MemberDTO user_data = service.login(id, password);
		
		HttpSession session = request.getSession();
		session.setAttribute("AUTH_USER", user_data);
		
		return "/view/HWET/logintest1.jsp";
		
		/* AJAX사용으로 사전에 검사하여 필요없을듯
		if(user_data == null) {
			try {
				// redirect 방식
				//response.sendRedirect(request.getContextPath()+"/view/loginError.jsp");
				
				// forward 방식이 주소 변경없이 경고문구를 작성해주고 페이지 이동을 하기 때문에 좀더 좋아 보이지만, 비밀번호가 유출된거같다는 경고문구가 나옴
				// RequestDispatcher dispatcher = request.getRequestDispatcher("/view/loginError.jsp");
				// dispatcher.forward(request, response);
				
				// 페이지 이동없이 진행 (forward와 동일한 작업을 java코드로 진행)
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				String message = "아이디 또는 비밀번호가 일치하지 않습니다.";
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"UTF-8\">");
				out.println("<title>Login Failed!!</title>");
				out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@9\"></script>");
				out.println("</head>");
				out.println("<body>");
				out.println("<script>");
				out.println("Swal.fire({");
				out.println("    icon: 'error',");
				out.println("    title: '로그인 실패',");
				out.println("    text: '" + message + "',");
				out.println("}).then(function() {");
				out.println("    window.location.href = '" + request.getContextPath() + "/login.do';");
				out.println("});");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("AUTH_USER", user_data);
			
			System.out.println(user_data.getUserName());
			
			System.out.println("에러 핸들러2");
			return "/view/HWET/logintest1.jsp";
		}
		*/
	}
	

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		// Get방식으로 요청받으면 로그인 창을 띄우는 핸들러 실행
		return "/loginForm.do";
	}
	
}
