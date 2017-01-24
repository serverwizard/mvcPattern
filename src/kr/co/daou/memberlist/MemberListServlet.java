package kr.co.daou.memberlist;

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

import kr.co.daou.dao.MemberDao;
import kr.co.daou.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			ServletContext sc = this.getServletContext();
	
			// AppInitServlet에서 작업
//			Class.forName(sc.getInitParameter("driver"));
//			DriverManager.getConnection(
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password")); 
			
			conn = (Connection) sc.getAttribute("conn"); 
				
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			request.setAttribute("members", memberDao.selectList());
			
			// MemberDao에서 처리
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(
//					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
//					" FROM MEMBERS" +
//					" ORDER BY MNO ASC");
			
			
//			PrintWriter out = response.getWriter();
//			out.println("<html><head><title>회원목록</title></head>");
//			out.println("<body><h1>회원목록</h1>");
//			out.println("<p><a href='add'>신규 회원</a></p>");
//			while(rs.next()) {
//				out.println(
//					rs.getInt("MNO") + "," +
//					"<a href='update?no=" + rs.getInt("MNO") + "'>" +
//					rs.getString("MNAME") + "</a>," +
//					rs.getString("EMAIL") + "," + 
//					rs.getDate("CRE_DATE") + 
//					"<a href='delete?no=" + rs.getInt("MNO") + 
//					"'>[삭제]</a><br>");
//			}
//			out.println("</body></html>");
			
			response.setContentType("text/html; charset=UTF-8");
			
			// MemberDao에서 작업
//			ArrayList<Member> members = new ArrayList<Member>();
//			
//			// 데이터베이스에서 회원 정보를 가져와 Member에 담는다.
//			// 그리고 Member객체를 ArrayList에 추가한다.
//			while (rs.next()) {
//				members.add(
//						new Member()
//						.setNo(rs.getInt("MNO"))
//						.setName(rs.getString("MNAME"))
//						.setEmail(rs.getString("EMAIL"))
//						.setCreatedDate(rs.getDate("CRE_DATE")));
//			}
			
			// request에 회원 목록 데이터 보관한다.
			//request.setAttribute("members", members);
			// JSP로 출력을 위임한다.
			RequestDispatcher rd = request.getRequestDispatcher(
					"/member/MemberList.jsp");
			
			// 해당 부분을 include한 이유?
			rd.include(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		}

	}


}
