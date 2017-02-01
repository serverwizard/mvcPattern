package listeners;


import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import controls.FileReadController;
import controls.FileUploadController;
import controls.LogInController;
import controls.LogOutController;
import controls.MemberAddController;
import controls.MemberDeleteController;
import controls.MemberListController;
import controls.MemberUpdateController;
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
			
			//페이지 컨트롤러 객체를 ContextLoaderListener에서 준비 (DI 개념)
			sc.setAttribute("/auth/login.do", 
					new LogInController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", new LogOutController());
			sc.setAttribute("/member/list.do", 
					new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/add.do",
					new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", 
					new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do",
					new MemberDeleteController().setMemberDao(memberDao));
			sc.setAttribute("/file/upload.do", 
					new FileUploadController().setMemberDao(memberDao));
			sc.setAttribute("/file/read.do", 
					new FileReadController().setMemberDao(memberDao));
			
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}
