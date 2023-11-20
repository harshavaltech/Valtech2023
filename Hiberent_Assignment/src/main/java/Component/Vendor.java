package Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

//import com.valtech.training.hibernate.Address;

@Entity
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int vendor_id;
	String v_name;
	@OneToOne(targetEntity = Vendor_address.class,cascade={CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.LAZY,mappedBy = "vendor")
	private Vendor_address v_address;
	
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getV_name() {
		return v_name;
	}
	public void setV_name(String v_name) {
		this.v_name = v_name;
	}
	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vendor(int vendor_id, String v_name) {
		super();
		this.vendor_id = vendor_id;
		this.v_name = v_name;
	}
	
	public Vendor_address getV_address() {
		return v_address;
	}
	public void setV_address(Vendor_address v_address) {
		this.v_address = v_address;
	}
	
	public Vendor(Vendor_address v_address) {
		super();
		this.v_address = v_address;
	}
	@Override
	public String toString() {
		return "Vendor [vendor_id=" + vendor_id + ", v_name=" + v_name + "]";
	}
	

}

