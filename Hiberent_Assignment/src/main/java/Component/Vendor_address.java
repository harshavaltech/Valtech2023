package Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//import com.valtech.training.hibernate.Customer;

@Entity
public class Vendor_address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int v_id;
	private String V_street;
	private String V_city;
	private int V_zipCode;
	@OneToOne(targetEntity = Vendor.class,cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="vid",referencedColumnName = "vendor_id")
	private Vendor vendor;
	public int getV_id() {
		return v_id;
	}
	
	public Vendor_address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vendor_address(int v_id, String v_street, String v_city, int v_zipCode) {
		super();
		this.v_id = v_id;
		V_street = v_street;
		V_city = v_city;
		V_zipCode = v_zipCode;
	}

	public void setV_id(int v_id) {
		this.v_id = v_id;
	}
	public String getV_street() {
		return V_street;
	}
	public void setV_street(String v_street) {
		V_street = v_street;
	}
	public String getV_city() {
		return V_city;
	}
	public void setV_city(String v_city) {
		V_city = v_city;
	}
	public int getV_zipCode() {
		return V_zipCode;
	}
	public void setV_zipCode(int v_zipCode) {
		V_zipCode = v_zipCode;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public Vendor getVendor() {
		return vendor;
	}

	public Vendor_address(Vendor vendor) {
		super();
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Vendor_address [v_id=" + v_id + ", V_street=" + V_street + ", V_city=" + V_city + ", V_zipCode="
				+ V_zipCode + "]";
	}
	

}
