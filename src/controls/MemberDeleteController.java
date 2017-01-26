package controls;

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

@WebServlet("/member/delete")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();
			
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			memberDao.delete(Integer.parseInt(request.getParameter("no")));
			
			request.setAttribute("viewURL", "redirect:list.do");

			
		} catch (Exception e) {
			throw new ServletException(e);
		} 

	}
}