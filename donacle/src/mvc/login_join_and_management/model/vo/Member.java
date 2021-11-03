package mvc.login_join_and_management.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String password;
	private String name;
	private String email;
	private String gender;
	private Date birthday;
	private String kind;
	private Date joinDate;

	private Address address;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String password, String name, String email, String gender, Date birthday, String kind,
			Date joinDate, Address address) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		this.kind = kind;
		this.joinDate = joinDate;
		this.address = address;
	}

	public Member(String id, String password, String name, String email, String gender, Date birthday, String kind,
			Date joinDate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		this.kind = kind;
		this.joinDate = joinDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", gender="
				+ gender + ", birthday=" + birthday + ", kind=" + kind + ", joinDate=" + joinDate + ", address="
				+ address + "]";
	}

}
