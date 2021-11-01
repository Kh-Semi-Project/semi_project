package mvc.product_review.common.vo;

public class Attach {
	private String originalFilename;
	private String renamedFilename;
	private int reviewNo;
	private int attachNo;
	private String attachmentDate;
	
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getAttachNo() {
		return attachNo;
	}
	public void setAttachNo(int attachNo) {
		this.attachNo = attachNo;
	}
	public String getAttachmentDate() {
		return attachmentDate;
	}
	public void setAttachmentDate(String attachmentDate) {
		this.attachmentDate = attachmentDate;
	}
	
	
	
}
