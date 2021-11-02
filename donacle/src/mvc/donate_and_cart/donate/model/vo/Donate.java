package mvc.donate_and_cart.donate.model.vo;

import java.sql.Date;

public class Donate {
	
	private int donate_no;
	private int donate_price;
	private Date donate_time;
	private String how_donate;
	private String id;
	private String name;
	
	public Donate() {
		super();
	}

	public Donate(int donate_no, int donate_price, Date donate_time, String how_donate, String id, String name) {
		super();
		this.donate_no = donate_no;
		this.donate_price = donate_price;
		this.donate_time = donate_time;
		this.how_donate = how_donate;
		this.id = id;
		this.name = name;
	}

	public int getDonate_no() {
		return donate_no;
	}

	public void setDonate_no(int donate_no) {
		this.donate_no = donate_no;
	}

	public int getDonate_price() {
		return donate_price;
	}

	public void setDonate_price(int donate_price) {
		this.donate_price = donate_price;
	}

	public Date getDonate_time() {
		return donate_time;
	}

	public void setDonate_time(Date donate_time) {
		this.donate_time = donate_time;
	}

	public String getHow_donate() {
		return how_donate;
	}

	public void setHow_donate(String how_donate) {
		this.how_donate = how_donate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Donate [donate_no=" + donate_no + ", donate_price=" + donate_price + ", donate_time=" + donate_time
				+ ", how_donate=" + how_donate + ", id=" + id + ", name=" + name + "]";
	}

	
	
	

}
