package controls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

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
public class MemberListController implements Controller {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		MemberDao memberDao = (MemberDao) model.get("memberDao");
		model.put("members", memberDao.selectList());
		return "/member/MemberList.jsp";

	}

}
