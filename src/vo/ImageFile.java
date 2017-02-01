package vo;

import java.util.Date;

//Value Object
public class ImageFile {
	private int no;
	private String url;
	private Date createdDate;
	private Date modifiedDate;

	public int getNo() {
		return no;
	}

	public ImageFile setNo(int no) {
		this.no = no;
		return this;
	}

	public String getURL() {
		return url;
	}

	public ImageFile setURL(String url) {
		this.url = url;
		return this;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public ImageFile setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public ImageFile setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}

}
