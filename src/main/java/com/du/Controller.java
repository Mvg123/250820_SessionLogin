package com.du;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = null;  // 이동할 페이지 경로를 저장할 변수
        String uri = request.getRequestURI();      // 요청된 전체 URI (/프로젝트명/요청경로)
        String conPath = request.getContextPath(); // 프로젝트 ContextPath (/프로젝트명)
        String com = uri.substring(conPath.length()); // ContextPath 이후의 요청 경로만 추출 (/login, /logout 등)

        HttpSession session = request.getSession(); // 세션 객체 가져오기

        // 메인 페이지 접근 시 처리 ("/" 또는 "/main")
        if (com.equals("/") || com.equals("/main")) {
            String userId = (String)session.getAttribute("userId"); // 세션에 저장된 로그인 사용자 ID 확인

            if (userId != null) { // 이미 로그인한 상태라면
                request.setAttribute("userId", userId); // JSP에서 사용자 ID를 쓸 수 있도록 request에 저장
                view = "loginOk.jsp"; // 로그인 성공 화면으로 이동
            } else { // 로그인하지 않은 상태라면
                view = "redirect:loginForm.jsp"; // 로그인 폼으로 리다이렉트
            }
        } 
        
        // 로그인 요청 처리 ("/login")
        else if(com.equals("/login")){
            String id = request.getParameter("id"); // 입력받은 ID
            String pw = request.getParameter("pw"); // 입력받은 비밀번호

            // 아이디/비밀번호가 admin/1234 일치하면 로그인 성공
            if (id.equals("admin") && pw.equals("1234")) {
                session.setAttribute("userId", id); // 세션에 userId 저장
                view ="loginOk.jsp"; // 로그인 성공 페이지
            } else { // 로그인 실패
                view = "redirect:loginFail.jsp"; // 로그인 실패 페이지로 리다이렉트
            }
        } 
        
        // 로그아웃 요청 처리 ("/logout")
        else if(com.equals("/logout")){	   
            session.invalidate(); // 세션 초기화 (로그아웃 처리)
            view = "redirect:loginForm.jsp"; // 로그인 화면으로 리다이렉트
        }
        
        // 최종적으로 페이지 이동 처리
        if (view.startsWith("redirect:")) {
            // "redirect:" 접두어가 붙어 있으면 sendRedirect 실행
            response.sendRedirect(view.substring(9)); // redirect: 문자열 제거 후 리다이렉트
        } else {
            // 그렇지 않으면 forward 방식으로 JSP에 요청 전달
            request.getRequestDispatcher(view).forward(request, response);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
