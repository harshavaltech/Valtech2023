package Component;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//import com.valtech.training.hibernate.Customer;

@Entity
@Embeddable
public class Customer_address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 int address_id;
	String street;
	String city;
	int pincode;
	int zipcode;
	@OneToOne(targetEntity = Customer.class,cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id",referencedColumnName = "c_id")
	private Customer customer;
	
	public Customer_address(Customer customer) {
		super();
		this.customer = customer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Customer_address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer_address(String street, String city, int pincode, int zipcode) {
		super();
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.zipcode = zipcode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "Customer_address [street=" + street + ", city=" + city + ", pincode=" + pincode + ", zipcode=" + zipcode
				+ "]";
	}
	
	
}

