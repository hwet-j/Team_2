package min.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.service.PolArticleData;
import min.service.PolModifyArticleService;
import min.service.PolModifyRequest;
import min.service.ReadPolArticleService;
import member.model.MemberDTO;
import mvc.command.CommandHandler;

//p669
//수정폼보여주기 요청 및 수정처리 요청에 대한 담당컨트롤러이다
//요청주소  http://localhost:/article/modify.do
public class PolModifyArticleHandler implements CommandHandler{

	private static final String FORM_VIEW = "/view/min/polmodifyForm.jsp";
			
	//수정폼보여줄 때 상세내용을 가져오기위한 Service
	private ReadPolArticleService readPolArticleService= new ReadPolArticleService();
	
	//수정처리를 위한 Service
	private PolModifyArticleService polModifyArticleService=new PolModifyArticleService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response); //p669 31라인
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
}//process


	//수정폼을 보여주기-p669 38라인
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1.파라미터받기
		int no = Integer.parseInt( request.getParameter("no") );//수정하고 싶은 글번호
		
		//2.비즈니스로직<->Service<->DAO<->DB
		//파라미터 int no : 수정하고 싶은 글번호
		PolArticleData pad = readPolArticleService.getDetail(no);
		MemberDTO user = (MemberDTO)request.getSession().getAttribute("AUTH_USER"); //세션로그인유저정보
		if(!canPolModify(user, pad)) { //수정불가하면
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		//수정을 위해 세션에서 가져온 회원id, 글번호, db에서 가져온 제목과 내용
		PolModifyRequest modReq=
				new PolModifyRequest(user.getUser_id(), no, pad.getTitle(), pad.getContent());
		
		//3.Model-p670 53라인
		request.setAttribute("modReq", modReq);
		
		
		//4.View 
		return request.getContextPath()+FORM_VIEW;
		
	}
		
	//수정가능여부체크-p670 61라인
		// 로그인한 user의 id가 글작성자id와 일치하는 비교하여 동일하면 수정가능     
		//리턴 boolean - 수정할 수 있으면 true반환, 불가하면 false반환
		
	private boolean canPolModify(MemberDTO user, PolArticleData pad) {
		return user.getUser_id().equals(pad.getWriter_id());
	}	
		
	
		
	//수정처리-p670 66라인	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1.파라미터받기
		int no = Integer.parseInt(request.getParameter("no"));//글번호
		String title = request.getParameter("title");//제목
		String content = request.getParameter("content");//내용
		String writerId = request.getParameter("writerId");//작성자id(여기에서는 db의 수정대상이 아니다)
		MemberDTO authUser = (MemberDTO)request.getSession().getAttribute("AUTH_USER");//교재에서는 작성자id를 세션에서 가져옴-p670 68라인
		
		//수정처리를 위한 데이터를 ModifyRequest객체로 생성-P670 72라인
		PolModifyRequest modReq = new PolModifyRequest(authUser.getUser_id(), no, title, content);
		request.setAttribute("modReq", modReq); //P670 75라인

		//유효성검사
		//에러정보가 담기는 Map- p670 77라인
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors);
		if(!errors.isEmpty()) { //에러가 있으면  수정폼페이지로 이동
			return request.getContextPath()+FORM_VIEW;
		}
		
		//2.비즈니스로직<->Service<->DAO<->DB
		//파라미터 ModifyRequest modReq-수정처리를 위한 세션에서 가져온 회원id, 글번호,제목,내용
		polModifyArticleService.modify(modReq);
		
		//3.Model

		//4.View - 성공시 /view/article/modifySuccess.jsp (p670 85)
		response.sendRedirect(request.getContextPath()+"/min/article/polList.do");
		return null;
	}

	
	
}