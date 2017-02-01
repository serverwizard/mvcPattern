package controls;

import java.io.File;
import java.util.Map;

import com.oreilly.servlet.MultipartRequest;

import dao.MemberDao;
import vo.Member;

//브라우저로부터 전달받은 파일 저장.
public class FileUploadController implements Controller{
	
	MemberDao memberDao;
	
	public FileUploadController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// D:\javaide\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVCPattern\
		String contextRootPath = (String) model.get("contextRootPath"); // 파일 저장경로
		
		MultipartRequest multipartRequest = (MultipartRequest) model.get("multipartRequest");
		// 파일 이름을 받아올때는 MultipartRequest 의 getFileSystemName 메서드를 사용한다.
		String fileName = multipartRequest.getFilesystemName("photo");
		
		String path = contextRootPath + fileName;
		
		File file = new File(path);
		memberDao.imageUpload(fileName);
		
		return "redirect:read.do";
	}
}


