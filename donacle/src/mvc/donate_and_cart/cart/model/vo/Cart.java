package mvc.donate_and_cart.cart.model.vo;

public class Cart extends Product {
	
	private int cart_no;
	private String id;
	

	public Cart(int product_code, int category_code, String product_name, int product_price, String product_img,
			int product_count, String id, String product_content, int shipping_fee) {
		super(product_code, category_code, product_name, product_price, product_img, product_count, id, product_content,
				shipping_fee);
		// TODO Auto-generated constructor stub
	}

	public Cart() {
		super();
	}

	public Cart(int cart_no, String id) {
		super();
		this.cart_no = cart_no;
		this.id = id;
	}

	public int getCart_no() {
		return cart_no;
	}

	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return super.toString() + "Cart [cart_no=" + cart_no + ", id=" + id + "]";
	}
	
	
	

}
