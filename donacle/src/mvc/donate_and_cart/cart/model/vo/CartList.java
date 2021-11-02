package mvc.donate_and_cart.cart.model.vo;

public class CartList extends Cart {
	
	private int cartList_no;
	private int product_cart_count;
	public CartList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CartList(int cartList_no, int product_cart_count) {
		super();
		this.cartList_no = cartList_no;
		this.product_cart_count = product_cart_count;
	}
	

	public CartList(int product_code, int category_code, String product_name, int product_price, String product_img,
			int product_count, String id, String product_content, int shipping_fee, int cartList_no,
			int product_cart_count) {
		super(product_code, category_code, product_name, product_price, product_img, product_count, id, product_content,
				shipping_fee);
		this.cartList_no = cartList_no;
		this.product_cart_count = product_cart_count;
	}

	public int getCartList_no() {
		return cartList_no;
	}

	public void setCartList_no(int cartList_no) {
		this.cartList_no = cartList_no;
	}

	public int getProduct_cart_count() {
		return product_cart_count;
	}

	public void setProduct_cart_count(int product_cart_count) {
		this.product_cart_count = product_cart_count;
	}

	@Override
	public String toString() {
		return super.toString() + "CartList [cartList_no=" + cartList_no + ", product_cart_count=" + product_cart_count + "]";
	}

}
