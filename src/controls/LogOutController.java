package controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import dao.MemberDao;

public class LogOutController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		// session객체 무효화
		session.invalidate();
		
		return "redirect:login.do";
	}

}
