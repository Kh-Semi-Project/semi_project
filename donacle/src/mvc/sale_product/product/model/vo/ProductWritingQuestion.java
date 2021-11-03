package mvc.sale_product.product.model.vo;

import java.sql.Date;

public class ProductWritingQuestion {
	private int product_question_code;
	private int comment_level;
//	private String comment_delete_yn; //query문의 삭제 여부
	private Date comment_date;
	private String comment_secret; //비밀여부
	private int comment_ref; // 부모/자식 인지 구분
	private String id; //작성자
	private int product_writing_code;
	private String comment_content; // 문의 내용
	public ProductWritingQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductWritingQuestion(int product_question_code, int comment_level, Date comment_date,
			String comment_secret, int comment_ref, String id, int product_writing_code, String comment_content) {
		super();
		this.product_question_code = product_question_code;
		this.comment_level = comment_level;
		this.comment_date = comment_date;
		this.comment_secret = comment_secret;
		this.comment_ref = comment_ref;
		this.id = id;
		this.product_writing_code = product_writing_code;
		this.comment_content = comment_content;
	}
	public int getProduct_question_code() {
		return product_question_code;
	}
	public void setProduct_question_code(int product_question_code) {
		this.product_question_code = product_question_code;
	}
	public int getComment_level() {
		return comment_level;
	}
	public void setComment_level(int comment_level) {
		this.comment_level = comment_level;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_secret() {
		return comment_secret;
	}
	public void setComment_secret(String comment_secret) {
		this.comment_secret = comment_secret;
	}
	public int getComment_ref() {
		return comment_ref;
	}
	public void setComment_ref(int comment_ref) {
		this.comment_ref = comment_ref;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getProduct_writing_code() {
		return product_writing_code;
	}
	public void setProduct_writing_code(int product_writing_code) {
		this.product_writing_code = product_writing_code;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	@Override
	public String toString() {
		return "ProductWritingQuestion [product_question_code=" + product_question_code + ", comment_level="
				+ comment_level + ", comment_date=" + comment_date + ", comment_secret=" + comment_secret
				+ ", comment_ref=" + comment_ref + ", id=" + id + ", product_writing_code=" + product_writing_code
				+ ", comment_content=" + comment_content + "]";
	}

	
	
}
