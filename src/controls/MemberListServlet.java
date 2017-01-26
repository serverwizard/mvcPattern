package controls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.Member;

// 프런트 컨트롤러 
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();
	
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			
			request.setAttribute("members", memberDao.selectList());
			request.setAttribute("viewURL", "/member/MemberList.jsp");

			
		} catch (Exception e) {
			// service() 메소드는 ServletException을 던지도록 설정
			// 기존 예외 객체를 던질 수 없다.
			throw new ServletException(e);
		} 

	}

}
