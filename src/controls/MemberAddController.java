package controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
@WebServlet("/member/add")
public class MemberAddController implements Controller {
	MemberDao memberDao;
	
	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if(model.get("member") == null){ // 입력폼을 요청할 때: GET 요청으로 간주
			return "/member/MemberForm.jsp";
		} else { // 회원 등록을 요청할 때: POST 요청으로 간주
			Member member = (Member) model.get("member");
			memberDao.insert(member);
			
			return "redirect:list.do";
		}
	}
}
