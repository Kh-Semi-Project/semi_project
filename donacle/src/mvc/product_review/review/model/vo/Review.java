package mvc.product_review.review.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import mvc.product_review.comment.model.vo.Comment;
import mvc.product_review.common.vo.Attach;

public class Review implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private int reviewNo;
	private int productBuyCode;
	private String id;
	private String reviewTitle;
	private String reviewContent;
	private String reviewPhoto;
	private Date reviewDate;
	private String reviewDeleteYn;
	private int reviewCommentCnt;
	private String userDeleteYn;
	private String name;
	
	private Attach attach;
	
	private List<Comment> commentList;

	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getProductBuyCode() {
		return productBuyCode;
	}

	public void setProductBuyCode(int productBuyCode) {
		this.productBuyCode = productBuyCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewPhoto() {
		return reviewPhoto;
	}

	public void setReviewPhoto(String reviewPhoto) {
		this.reviewPhoto = reviewPhoto;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewDeleteYn() {
		return reviewDeleteYn;
	}

	public void setReviewDeleteYn(String reviewDeleteYn) {
		this.reviewDeleteYn = reviewDeleteYn;
	}

	public int getReviewCommentCnt() {
		return reviewCommentCnt;
	}

	public void setReviewCommentCnt(int reviewCommentCnt) {
		this.reviewCommentCnt = reviewCommentCnt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public List<Comment> getCommentList() {
		return commentList;
	}



	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}



	public String getUserDeleteYn() {
		return userDeleteYn;
	}



	public void setUserDeleteYn(String userDeleteYn) {
		this.userDeleteYn = userDeleteYn;
	}



	public Attach getAttach() {
		return attach;
	}



	public void setAttach(Attach attach) {
		this.attach = attach;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	
}
	
	