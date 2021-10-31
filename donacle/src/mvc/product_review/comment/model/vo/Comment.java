package mvc.product_review.comment.model.vo;

public class Comment {
	
	private int reviewNo;
	private int commentsNo;
	private String id;
	private String commentsType;
	private String commentsTitle;
	private String commentsDeleteYn;
	private String commentsDate;
	private int pCommentsNo;
	private String name;
	
	public Comment() {}
	
	public Comment(int reviewNo, String id, String commentsType, String commentsTitle, int pCommentsNo) {
		this.reviewNo = reviewNo;
		this.id = id;
		this.commentsType = commentsType;
		this.commentsTitle = commentsTitle;
		this.pCommentsNo = pCommentsNo;
	}
	
	public Comment(int commentsNo, String commentsTitle) {
		this.commentsTitle = commentsTitle;
		this.commentsNo = commentsNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getCommentsNo() {
		return commentsNo;
	}
	public void setCommentsNo(int commentsNo) {
		this.commentsNo = commentsNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommentsType() {
		return commentsType;
	}
	public void setCommentsType(String commentsType) {
		this.commentsType = commentsType;
	}
	public String getCommentsTitle() {
		return commentsTitle;
	}
	public void setCommentsTitle(String commentsTitle) {
		this.commentsTitle = commentsTitle;
	}
	public String getCommentsDeleteYn() {
		return commentsDeleteYn;
	}
	public void setCommentsDeleteYn(String commentsDeleteYn) {
		this.commentsDeleteYn = commentsDeleteYn;
	}
	public String getCommentsDate() {
		return commentsDate;
	}
	public void setCommentsDate(String commentsDate) {
		this.commentsDate = commentsDate;
	}
	public int getpCommentsNo() {
		return pCommentsNo;
	}
	public void setpCommentsNo(int pCommentsNo) {
		this.pCommentsNo = pCommentsNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
