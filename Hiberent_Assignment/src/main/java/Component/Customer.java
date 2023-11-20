package Component;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//import com.valtech.training.hibernate.Address;

@Entity

public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int c_id;
	String c_name;
	int phoneNo;
	String email;
	@OneToOne(targetEntity = Customer_address.class,cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.LAZY,mappedBy = "customer")
	private Customer_address address;//cascade when try to perform on customers what should happen to address//mapped by always on master
	@Override
	public String toString() {
		return "Customer [c_id=" + c_id + ", c_name=" + c_name + ", phoneNo=" + phoneNo + ", email=" + email + "]";
	}
	
	public Customer(int c_id, String c_name, int phoneNo, String email) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public Number getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Customer(int c_id, String c_name, int phoneNo, String email, Customer_address address) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
	}

	public Customer_address getAddress() {
		return address;
	}

	public void setAddress(Customer_address address) {
		this.address = address;
	}

}
