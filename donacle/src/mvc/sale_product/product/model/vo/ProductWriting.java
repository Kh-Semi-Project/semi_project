package mvc.sale_product.product.model.vo;

import java.io.Serializable;
import java.sql.Date;

//판매글은 제품과 자식 부모 관계
public class ProductWriting extends Product implements Serializable{
	private int product_writing_code; // 판매글 코드 -> 코드로 게시글 구분
	private String product_writing_yn; // 판매글 삭제 여부
	private Date product_writing_date; // 판매글 작성시간
	private int read_count; // 판매글을 읽은 사용자 수
	private String category_name;
	private String admin_check;
	
	private String img_1;
	private String img_2;
	private String img_3;
	public ProductWriting() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductWriting(int product_code, int category_code, String product_name, int product_price,
			String product_img, String product_content, int product_count, int shipping_fee, String id) {
		super(product_code, category_code, product_name, product_price, product_img, product_content, product_count,
				shipping_fee, id);
		// TODO Auto-generated constructor stub
	}
	public ProductWriting(int product_writing_code, String product_writing_yn, Date product_writing_date,
			int read_count, String category_name, String admin_check, String img_1, String img_2, String img_3) {
		super();
		this.product_writing_code = product_writing_code;
		this.product_writing_yn = product_writing_yn;
		this.product_writing_date = product_writing_date;
		this.read_count = read_count;
		this.category_name = category_name;
		this.admin_check = admin_check;
		this.img_1 = img_1;
		this.img_2 = img_2;
		this.img_3 = img_3;
	}
	public int getProduct_writing_code() {
		return product_writing_code;
	}
	public void setProduct_writing_code(int product_writing_code) {
		this.product_writing_code = product_writing_code;
	}
	public String getProduct_writing_yn() {
		return product_writing_yn;
	}
	public void setProduct_writing_yn(String product_writing_yn) {
		this.product_writing_yn = product_writing_yn;
	}
	public Date getProduct_writing_date() {
		return product_writing_date;
	}
	public void setProduct_writing_date(Date product_writing_date) {
		this.product_writing_date = product_writing_date;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getAdmin_check() {
		return admin_check;
	}
	public void setAdmin_check(String admin_check) {
		this.admin_check = admin_check;
	}
	public String getImg_1() {
		return img_1;
	}
	public void setImg_1(String img_1) {
		this.img_1 = img_1;
	}
	public String getImg_2() {
		return img_2;
	}
	public void setImg_2(String img_2) {
		this.img_2 = img_2;
	}
	public String getImg_3() {
		return img_3;
	}
	public void setImg_3(String img_3) {
		this.img_3 = img_3;
	}
	@Override
	public String toString() {
		return "ProductWriting [product_writing_code=" + product_writing_code + ", product_writing_yn="
				+ product_writing_yn + ", product_writing_date=" + product_writing_date + ", read_count=" + read_count
				+ ", category_name=" + category_name + ", admin_check=" + admin_check + ", img_1=" + img_1 + ", img_2="
				+ img_2 + ", img_3=" + img_3 + "]";
	}
	
}
