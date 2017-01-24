package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

//오류 처리 JSP 적용  
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Connection conn = null;
//		Statement stmt = null;

		try {
			ServletContext sc = this.getServletContext();

			// AppInitServlet에서 작업
//			Class.forName(sc.getInitParameter("driver"));
//			DriverManager.getConnection(
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password")); 	
//			Connection conn = (Connection) sc.getAttribute("conn"); 
			
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
			memberDao.delete(Integer.parseInt(request.getParameter("no")));
			
			// MemberDao에서 처리
//			stmt = conn.createStatement();
//			stmt.executeUpdate(
//					"DELETE FROM MEMBERS WHERE MNO=" + 
//					request.getParameter("no"));
//			
			response.sendRedirect("list");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			
		} 
//		finally {
//			try {if (stmt != null) stmt.close();} catch(Exception e) {}
//			try {if (conn != null) conn.close();} catch(Exception e) {}
//		}

	}
}
