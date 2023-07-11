package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

/**
 * Servlet implementation class ControllerUsingURI
 * @WebServlet("/ControllerUsingURI")
 */

public class ControllerUsingURI extends HttpServlet {
	
	//p540 22라인
	Map<String, CommandHandler> commandHandlerMap = new HashMap<String,CommandHandler>();

	// 요청주소  http://127.0.0.1:8081/cu.do
	//get방식요청시 호출.요청을 처리하고 응답을 생성
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()호출");//콘솔출력

		// process 실행
		process(request,response);
		
	}

	//post방식요청시 호출.요청을 처리하고 응답을 생성
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()호출");
		doGet(request, response);
	}
	
	
	//최초한번호출.설정값 초기화. DB연동 등
	public void init() throws ServletException {
		// web.xml문서 설정부분에서   /WEB-INF/commandHandlerURI.properties를 가져와
		// String타입  configFile변수에 저장
	    String configFile = getInitParameter("configFile");
	    Properties prop = new Properties();//Properties객체
	    String configFilePath = getServletContext().getRealPath(configFile);
        
        //실행동작할 수 있는 파일로 만든다
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        
        Iterator keyIter = prop.keySet().iterator();
        
        while (keyIter.hasNext()) {
            String command = (String) keyIter.next();
            String handlerClassName = prop.getProperty(command);
            try {
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
        
        System.out.println("configFile="+configFile);
        System.out.println("prop="+prop);
        System.out.println("configFilePath="+configFilePath);
        
    }

	//한번만 호출. DB연결해제, 리소스 반환
	public void destroy() {
		System.out.println("destroy()호출");
	}
	
	
	// get, post 방식 요청시 호출
	private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {       
		System.out.println("process()호출");
		String command = request.getRequestURI();//   /cu.do   /경로
		if(command.indexOf(request.getContextPath())==0) {//p541 63라인
			command=command.substring(request.getContextPath().length());
		}
		
		
		//Map에   값추가     : Map참조변수명.put(key,value)
		// -> 별도의 파일을 생성하여 주석처리
		
		
		//Map에서 값가져오기 : Map참조변수명.get(key) 
		CommandHandler handler = null;
		handler = commandHandlerMap.get(command);
		
		System.out.println(handler);
		
		/*
		 * RequestDispatcher는 Java 웹 애플리케이션에서 서블릿이나 JSP로부터 
		 * 다른 자원으로의 요청 전달을 담당하는 인터페이스입니다. 
		 * 이를 통해 개발자는 요청을 다른 서블릿, JSP, HTML 파일 등으로 전송하여
		 * 해당 자원이 요청에 대한 응답을 처리하도록 할 수 있습니다. 
		 * RequestDispatcher를 사용하면 동일한 웹 애플리케이션 내에서 다른 자원으로의 이동이 가능해지며, 
		 * 웹 애플리케이션의 흐름을 제어하고 다양한 작업을 수행할 수 있습니다.
		 * */
		String viewPage = null;
		if (handler != null) {
			try {
				viewPage = handler.process(request, response);
			} catch (Throwable e) {	// 모든 예외 , 모든 에러에 관련한 문제 (Throwable)
				e.printStackTrace();
			}
			// RequestDispatcher의 forward()를 이용하여 view페이지로 강제 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);	// 현재 요청(request)과 응답(response)을 전달하여 해당 자원으로 제어를 넘기는 역할
		} else {
			handler = new NullHandler();
		}
		
	}
	    
	

}
