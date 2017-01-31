package controls;

import java.awt.Image;
import java.io.File;
import java.util.Map;

import javax.imageio.ImageIO;

import dao.MemberDao;

public class FileReadController implements Controller {
	
	MemberDao memberDao;
	
	public FileReadController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {

		File sourceimage = new File("c:\\mypic.jpg");
		Image image = ImageIO.read(sourceimage);
		
		return null;
	}

}
