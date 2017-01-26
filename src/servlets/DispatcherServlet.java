package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controls.Controller;
import controls.MemberListController;
import vo.Member;

// 프런트 컨트롤러 역할: 클라이언트 요청을 적절한 페이지 컨트롤러에게 전달
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			// 응답 데이터의 문자 집합
			response.setContentType("text/html; charset=UTF-8");
	
			// url : http://localhost:8080/MVCPattern/member/list.do
			// servletPath : /member/list.do
			String servletPath = request.getServletPath();
		try {
			ServletContext sc = this.getServletContext();
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("memberDao", sc.getAttribute("memberDao"));
			model.put("session", request.getSession());
			
			Controller pageController = null;
			if ("/member/list.do".equals(servletPath)) {

				pageController = new MemberListController();

			} else if ("/member/add.do".equals(servletPath)) {
				pageController = new MemberAddController();

				if (request.getParameter("email") != null) {
					
					// request 매개변수 로부터 vo객체 준비
					model.put("member", new Member()
							.setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password"))
							.setName(request.getParameter("name")));
					
				}
			} else if ("/member/update.do".equals(servletPath)) {
				pageController = new MemberUpdateController();

				if (request.getParameter("email") != null) {
				
					// request 매개변수 로부터 vo객체 준비
					model.put("member", new Member()
							.setNo(Integer.parseInt(request.getParameter("no")))
							.setEmail(request.getParameter("email"))
							.setName(request.getParameter("name")));
					
				} else {
					model.put("no", new Integer(request.getParameter("no")));
				}
			} else if ("/member/delete.do".equals(servletPath)) {
				pageController = new MemberDeleteController();
				model.put("no", new Integer(request.getParameter("no")));
			} else if ("/auth/login.do".equals(servletPath)) {
				pageController = new LogInController();
				 if (request.getParameter("email") != null) {
			          model.put("loginInfo", new Member()
			            .setEmail(request.getParameter("email"))
			            .setPassword(request.getParameter("password")));
			        }
			} else if ("/auth/logout.do".equals(servletPath)) {
				pageController = new LoutOutController();
			}

//			RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
//			rd.include(request, response);
//
//			String viewURL = (String) request.getAttribute("viewURL");
			
			// 페이지 컨트롤러를 실행
			String viewURL = pageController.execute(model);

			// Map 객체에 저장된 값을 ServletRequest에 복사한다.
			for (String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if (viewURL.startsWith("redirect:")) {
				response.sendRedirect(viewURL.substring(9));
				return;
			} else {
				rd = request.getRequestDispatcher(viewURL);
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}

	}
}
