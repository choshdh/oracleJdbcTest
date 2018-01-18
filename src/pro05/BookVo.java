package pro05;

import java.sql.Date;

public class BookVo {

	private int id;
	private String title;
	private String pubs;
	private String pubDate;
	private String authorName;
	private String stateCode;
	
	public BookVo() {
		// TODO Auto-generated constructor stub
		this.stateCode = "1";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public void info() {
		System.out.printf("책제목 : %12s  작가 : %10s  대여유무 : %4s \n", title , authorName, stateCode);
	}
	
	
	
	
}
