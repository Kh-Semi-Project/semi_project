package mvc.sale_product.product.model.vo;

import java.sql.Date;

public class BuyAddress extends ProductBuy{
	private int product_buy_address_no;
	private String zip_code;
	private String address;
	private String detail_address;
	public BuyAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BuyAddress(int product_buy_code, int product_buy_count, int product_buy_price, int product_donate_price,
			String buy_writing_yn, String product_shipping_status, String product_receipt_yn, String id,
			int product_code, int price_sum, Date product_buy_date) {
		super(product_buy_code, product_buy_count, product_buy_price, product_donate_price, buy_writing_yn,
				product_shipping_status, product_receipt_yn, id, product_code, price_sum, product_buy_date);
		// TODO Auto-generated constructor stub
	}
	public BuyAddress(int product_code, int category_code, String product_name, int product_price, String product_img,
			String product_content, int product_count, int shipping_fee, String id) {
		super(product_code, category_code, product_name, product_price, product_img, product_content, product_count,
				shipping_fee, id);
		// TODO Auto-generated constructor stub
	}
	public BuyAddress(int product_buy_address_no, String zip_code, String address, String detail_address) {
		super();
		this.product_buy_address_no = product_buy_address_no;
		this.zip_code = zip_code;
		this.address = address;
		this.detail_address = detail_address;
	}
	public int getProduct_buy_address_no() {
		return product_buy_address_no;
	}
	public void setProduct_buy_address_no(int product_buy_address_no) {
		this.product_buy_address_no = product_buy_address_no;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	@Override
	public String toString() {
		return "BuyAddress [product_buy_address_no=" + product_buy_address_no + ", zip_code=" + zip_code + ", address="
				+ address + ", detail_address=" + detail_address + "]";
	}
	
}
