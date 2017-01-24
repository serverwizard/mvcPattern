package listeners;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.MemberDao;
import util.DBConnectionPool;

public class ContextLoaderListener implements ServletContextListener {
	DBConnectionPool connPool;

	  @Override
	  public void contextInitialized(ServletContextEvent event) {
		System.out.println("WebApplication 시작...");
		try {
			ServletContext sc = event.getServletContext();
//			Class.forName(sc.getInitParameter("driver"));

//			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),
//					sc.getInitParameter("password"));

			connPool = new DBConnectionPool(
			          sc.getInitParameter("driver"),
			          sc.getInitParameter("url"), 
			          sc.getInitParameter("username"),
			          sc.getInitParameter("password"));
			
			
			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
			memberDao.setDbConnectionPool(connPool);

			   sc.setAttribute("memberDao", memberDao);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("WebApplication 종료...");
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		connPool.closeAll();
	}

}
