package controls;

import java.io.File;
import java.util.Map;

import com.oreilly.servlet.MultipartRequest;

//브라우저로부터 전달받은 파일 저장.
public class FileUploadController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		String contextRootPath = (String) model.get("contextRootPath"); // 파일 저장경로
		// System.out.println(contextRootPath);
		// D:\javaide\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVCPattern\
	
		MultipartRequest multi = (MultipartRequest) model.get("multiObj");
		// 파일 이름을 받아올때는 MultipartRequest 의 getFileSystemName 메서드를 사용한다.
		String uploadFile = multi.getFilesystemName("photo");
		
		File file = new File(contextRootPath + uploadFile);

		return "redirect:../index.html";
	}
}


