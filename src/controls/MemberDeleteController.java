package controls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

public class MemberDeleteController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		MemberDao memberDao = (MemberDao) model.get("memberDao");
	    Integer no = (Integer)model.get("no");
		memberDao.delete(no);
		
		return "redirect:list.do";
	}
}
