package init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("AppInitServlet 시작...");
		super.init(config);
		try {
			ServletContext sc = this.getServletContext();
	
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password")); 
			
			sc.setAttribute("conn", conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		System.out.println("AppInitServlet 종료...");
		super.destroy();
		Connection conn = (Connection) this.getServletContext().getAttribute("conn");
		
		try {
			if(conn != null && conn.isClosed() == false) {
				conn.close();
			}
		} catch (SQLException e) {
		} 
				
	}

}
