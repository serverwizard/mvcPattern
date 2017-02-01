package controls;

import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import dao.MemberDao;
import vo.ImageFile;

public class FileReadController implements Controller {
	
	MemberDao memberDao;
	
	public FileReadController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {

		model.put("images", memberDao.imageRead());
		return "/member/MemberPhoto.jsp";

	}

}
