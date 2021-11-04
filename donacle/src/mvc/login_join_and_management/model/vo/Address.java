package mvc.login_join_and_management.model.vo;

public class Address {
	private String id;
	private String zipCode;
	private String address;
	private String detailAddress;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String id, String zipCode, String address, String detailAddress) {
		super();
		this.id = id;
		this.zipCode = zipCode;
		this.address = address;
		this.detailAddress = detailAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", zipCode=" + zipCode + ", address=" + address + ", detailAddress="
				+ detailAddress + "]";
	}

}
