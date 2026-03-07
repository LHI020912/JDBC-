package model;

public class PublisherDTO {
	private String pubNo;
	private String pubName;
	
	public PublisherDTO() {}
	
	public PublisherDTO(String pubNo, String pubName) {
		this.pubNo = pubNo;
		this.pubName = pubName;
	}
	
	public String getPubNo() {
		return pubNo;
	}
	public void setPubNo(String pubNo) {
		this.pubNo = pubNo;
	}
	public String getPubName() {
		return pubName;
	}
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
}
