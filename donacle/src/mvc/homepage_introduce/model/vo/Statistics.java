package mvc.homepage_introduce.model.vo;

public class Statistics {

	private int de_no;
	private String de_type;
	private String category;
	private double de_rate;
	private int period;
	public Statistics() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getDe_no() {
		return de_no;
	}
	public void setDe_no(int de_no) {
		this.de_no = de_no;
	}
	public String getDe_type() {
		return de_type;
	}
	public void setDe_type(String de_type) {
		this.de_type = de_type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getDe_rate() {
		return de_rate;
	}
	public void setDe_rate(double de_rate) {
		this.de_rate = de_rate;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	
	public Statistics(int de_no, String de_type, String category, double de_rate, int period) {
		super();
		this.de_no = de_no;
		this.de_type = de_type;
		this.category = category;
		this.de_rate = de_rate;
		this.period = period;
	}
	
	@Override
	public String toString() {
		return "Statistics [de_no=" + de_no + ", de_type=" + de_type + ", category=" + category + ", de_rate=" + de_rate
				+ ", period=" + period + "]";
	}

}
