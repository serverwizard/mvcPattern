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
import controls.FileUploadController;
import controls.LogInController;
import controls.LogOutController;
import controls.MemberAddController;
import controls.MemberDeleteController;
import controls.MemberListController;
import controls.MemberUpdateController;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import vo.Member;

// 프런트 컨트롤러 역할: 클라이언트 요청을 적절한 페이지 컨트롤러에게 전달
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 응답 데이터의 문자 집합(공통 작업)
			response.setContentType("text/html; charset=UTF-8");
	
			// url : http://localhost:8080/MVCPattern/member/list.do
			// servletPath : /member/list.do
			String servletPath = request.getServletPath();
		try {
			ServletContext sc = this.getServletContext();
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			
			Controller pageController = (Controller) sc.getAttribute(servletPath);
			if ("/member/add.do".equals(servletPath)) {
				if (request.getParameter("email") != null) {
					// request 매개변수 로부터 vo객체 준비
					model.put("member", new Member()
							.setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password"))
							.setName(request.getParameter("name")));
				}
			} else if ("/member/update.do".equals(servletPath)) {
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
				model.put("no", new Integer(request.getParameter("no")));
			} else if ("/auth/login.do".equals(servletPath)) {
				 if (request.getParameter("email") != null) {
			          model.put("loginInfo", new Member()
			            .setEmail(request.getParameter("email"))
			            .setPassword(request.getParameter("password")));
			        }
			} else if ("/file/upload.do".equals(servletPath)) {
				
				String contextRootPath = this.getServletContext().getRealPath("/"); // 파일 저장경로
				model.put("contextRootPath", contextRootPath);
				
				int maxSize = 3 * 1024 * 1024; // 파일업로드 용량 제한(3MB) - 기본단위 Byte
				String format = "UTF-8";
				
				// DefaultFileRenamePolicy객체는 파일이 중복되면 이름을 바꿔 1 2 3 으로 파일이름을 변경
				MultipartRequest multiObj = new MultipartRequest(request, contextRootPath, maxSize, format,
						new DefaultFileRenamePolicy());
				
				model.put("multiObj", multiObj);
						
			} 

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
				RequestDispatcher rd = request.getRequestDispatcher(viewURL);
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
