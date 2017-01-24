package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet {

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
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		try {
			ServletContext sc = this.getServletContext();
			
			// AppInitServlet에서 작업
//			Class.forName(sc.getInitParameter("driver"));
//			DriverManager.getConnection(
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password")); 
			
			//Connection conn = (Connection) sc.getAttribute("conn"); 
			
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
		      Member member = memberDao.exist(
		              request.getParameter("email"), 
		              request.getParameter("password"));
		
			//MemberDao에서 처리
//			pstmt = conn.prepareStatement(
//					"SELECT MNAME,EMAIL FROM MEMBERS"
//					+ " WHERE EMAIL=? AND PWD=?");
//			pstmt.setString(1, request.getParameter("email"));
//			pstmt.setString(2, request.getParameter("password"));
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
			if (member != null) {
//				Member member = new Member()
//						.setEmail(rs.getString("EMAIL"))
//						.setName(rs.getString("MNAME"));
				
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
		
//		finally {
//			try {if (rs != null) rs.close();} catch (Exception e) {}
//			try {if (pstmt != null) pstmt.close();} catch (Exception e) {}
//		}


	}

}
