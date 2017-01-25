package listeners;


import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import dao.MemberDao;

public class ContextLoaderListener implements ServletContextListener {
//	DBConnectionPool connPool;
	BasicDataSource ds;
	
	  @Override
	  public void contextInitialized(ServletContextEvent event) {
		System.out.println("WebApplication 시작...");
		try {
			
			ServletContext sc = event.getServletContext();
//			Class.forName(sc.getInitParameter("driver"));

//			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),
//					sc.getInitParameter("password"));

			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup(
					"java:comp/env/jdbc/daoudb");
			
			
			// 톰캣 서버에 설정으로 대체
//			ds = new BasicDataSource();
//			
//			ds.setDriverClassName(sc.getInitParameter("driver"));
//			ds.setUrl(sc.getInitParameter("url"));
//			ds.setUsername(sc.getInitParameter("username"));
//			ds.setPassword(sc.getInitParameter("password"));
	/*		connPool = new DBConnectionPool(
			          sc.getInitParameter("driver"),
			          sc.getInitParameter("url"), 
			          sc.getInitParameter("username"),
			          sc.getInitParameter("password"));
	*/		
	
			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
//			memberDao.setDbConnectionPool(connPool);
			memberDao.setDataSource(ds);
			sc.setAttribute("memberDao", memberDao);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
//		System.out.println("WebApplication 종료...");
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		connPool.closeAll();
//		try { if (ds != null) ds.close(); } catch (SQLException e) {}
	}

}
