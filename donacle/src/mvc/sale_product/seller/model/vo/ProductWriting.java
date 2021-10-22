package mvc.sale_product.seller.model.vo;

import java.sql.Date;

public class ProductWriting {
	private int product_writing_code;
	private String product_writing_yn;
	private Date product_writing_date;
	private int read_count;
	private String id;
	private int product_code;
	
	public ProductWriting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductWriting(int product_writing_code, String product_writing_yn, Date product_writing_date,
			int read_count, String id, int product_code) {
		super();
		this.product_writing_code = product_writing_code;
		this.product_writing_yn = product_writing_yn;
		this.product_writing_date = product_writing_date;
		this.read_count = read_count;
		this.id = id;
		this.product_code = product_code;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getProduct_code() {
		return product_code;
	}

	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}

	@Override
	public String toString() {
		return "ProductWriting [product_writing_code=" + product_writing_code + ", product_writing_yn="
				+ product_writing_yn + ", product_writing_date=" + product_writing_date + ", read_count=" + read_count
				+ ", id=" + id + ", product_code=" + product_code + "]";
	}
}
