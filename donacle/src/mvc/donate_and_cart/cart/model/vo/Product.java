package mvc.donate_and_cart.cart.model.vo;

public class Product {
	
	private int product_code;
	private int category_code;
	private String product_name;
	private int product_price;
	private String product_img;
	private int product_count;
	private String id;
	private String product_content;
	private int shipping_fee;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int product_code, int category_code, String product_name, int product_price, String product_img,
			int product_count, String id, String product_content, int shipping_fee) {
		super();
		this.product_code = product_code;
		this.category_code = category_code;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_img = product_img;
		this.product_count = product_count;
		this.id = id;
		this.product_content = product_content;
		this.shipping_fee = shipping_fee;
	}

	public int getProduct_code() {
		return product_code;
	}

	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}

	public int getCategory_code() {
		return category_code;
	}

	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public int getProduct_count() {
		return product_count;
	}

	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct_content() {
		return product_content;
	}

	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}

	public int getShipping_fee() {
		return shipping_fee;
	}

	public void setShipping_fee(int shipping_fee) {
		this.shipping_fee = shipping_fee;
	}

	@Override
	public String toString() {
		return "Product [product_code=" + product_code + ", category_code=" + category_code + ", product_name="
				+ product_name + ", product_price=" + product_price + ", product_img=" + product_img
				+ ", product_count=" + product_count + ", id=" + id + ", product_content=" + product_content
				+ ", shipping_fee=" + shipping_fee + "]";
	}
	
	
	
	

}
