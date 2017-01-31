package vo;

import java.util.Date;

//Value Object
public class Image {
	private int no;
	private String url;
	private Date createdDate;
	private Date modifiedDate;

	public int getNo() {
		return no;
	}

	public Image setNo(int no) {
		this.no = no;
		return this;
	}

	public String getURL() {
		return url;
	}

	public Image setURL(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Image setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Image setEmail(String email) {
		this.email = email;
		return this;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Image setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
