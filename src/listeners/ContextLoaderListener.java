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
	
	  @Override
	  public void contextInitialized(ServletContextEvent event) {
		System.out.println("WebApplication 시작...");
		try {
			
			ServletContext sc = event.getServletContext();

			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup(
					"java:comp/env/jdbc/daoudb");

			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds);
			sc.setAttribute("memberDao", memberDao);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}
