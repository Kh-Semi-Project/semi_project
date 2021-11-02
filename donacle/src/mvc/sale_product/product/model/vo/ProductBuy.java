package mvc.sale_product.product.model.vo;

import java.sql.Date;

public class ProductBuy extends Product{
	private int product_buy_code;
	private int product_buy_count;
	private int product_buy_price;
	private int product_donate_price;
	private String buy_writing_yn;
	private String product_shipping_status;
	private String product_receipt_yn;
	private String id;
	private int product_code;
	private int price_sum;
	private Date product_buy_date;
	public ProductBuy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductBuy(int product_code, int category_code, String product_name, int product_price, String product_img,
			String product_content, int product_count, int shipping_fee, String id) {
		super(product_code, category_code, product_name, product_price, product_img, product_content, product_count,
				shipping_fee, id);
		// TODO Auto-generated constructor stub
	}
	public ProductBuy(int product_buy_code, int product_buy_count, int product_buy_price, int product_donate_price,
			String buy_writing_yn, String product_shipping_status, String product_receipt_yn, String id,
			int product_code, int price_sum, Date product_buy_date) {
		super();
		this.product_buy_code = product_buy_code;
		this.product_buy_count = product_buy_count;
		this.product_buy_price = product_buy_price;
		this.product_donate_price = product_donate_price;
		this.buy_writing_yn = buy_writing_yn;
		this.product_shipping_status = product_shipping_status;
		this.product_receipt_yn = product_receipt_yn;
		this.id = id;
		this.product_code = product_code;
		this.price_sum = price_sum;
		this.product_buy_date = product_buy_date;
	}
	public int getProduct_buy_code() {
		return product_buy_code;
	}
	public void setProduct_buy_code(int product_buy_code) {
		this.product_buy_code = product_buy_code;
	}
	public int getProduct_buy_count() {
		return product_buy_count;
	}
	public void setProduct_buy_count(int product_buy_count) {
		this.product_buy_count = product_buy_count;
	}
	public int getProduct_buy_price() {
		return product_buy_price;
	}
	public void setProduct_buy_price(int product_buy_price) {
		this.product_buy_price = product_buy_price;
	}
	public int getProduct_donate_price() {
		return product_donate_price;
	}
	public void setProduct_donate_price(int product_donate_price) {
		this.product_donate_price = product_donate_price;
	}
	public String getBuy_writing_yn() {
		return buy_writing_yn;
	}
	public void setBuy_writing_yn(String buy_writing_yn) {
		this.buy_writing_yn = buy_writing_yn;
	}
	public String getProduct_shipping_status() {
		return product_shipping_status;
	}
	public void setProduct_shipping_status(String product_shipping_status) {
		this.product_shipping_status = product_shipping_status;
	}
	public String getProduct_receipt_yn() {
		return product_receipt_yn;
	}
	public void setProduct_receipt_yn(String product_receipt_yn) {
		this.product_receipt_yn = product_receipt_yn;
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
	public int getPrice_sum() {
		return price_sum;
	}
	public void setPrice_sum(int price_sum) {
		this.price_sum = price_sum;
	}
	public Date getProduct_buy_date() {
		return product_buy_date;
	}
	public void setProduct_buy_date(Date product_buy_date) {
		this.product_buy_date = product_buy_date;
	}
	@Override
	public String toString() {
		return "ProductBuy [product_buy_code=" + product_buy_code + ", product_buy_count=" + product_buy_count
				+ ", product_buy_price=" + product_buy_price + ", product_donate_price=" + product_donate_price
				+ ", buy_writing_yn=" + buy_writing_yn + ", product_shipping_status=" + product_shipping_status
				+ ", product_receipt_yn=" + product_receipt_yn + ", id=" + id + ", product_code=" + product_code
				+ ", price_sum=" + price_sum + ", product_buy_date=" + product_buy_date + "]";
	}

	

}
