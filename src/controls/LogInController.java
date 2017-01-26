package controls;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/auth/login")
public class LogInController implements Controller {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();
			
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
		    Member member = memberDao.exist(
		              request.getParameter("email"), 
		              request.getParameter("password"));

		    if (member != null) {	
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				response.sendRedirect("../member/list");
				
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(
						"/auth/LogInFail.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
			
		} 
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		MemberDao memberDao = (MemberDao) model.get("memberDao");
		Member loginInfo = (Member) model.get("loginInfo");
		
		Menber member = memberDao.exist(
				  loginInfo.getEmail(), 
		          loginInfo.getPassword());
		
		return null;
	}

}
