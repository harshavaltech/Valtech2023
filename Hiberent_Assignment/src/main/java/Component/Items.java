package Component;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int item_id;
	String item_name;
	double unit_price;
	
	@ManyToOne(targetEntity = OrderDes.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "OrderDes_id", referencedColumnName = "desc_id")	
	private OrderDes orderDes;
	//private  List<Vendor> vendor;

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

//	public List<Vendor> getVendor() {
//		return vendor;
//	}
//
//	public void setVendor(List<Vendor> vendor) {
//		this.vendor = vendor;
//	}

	public Items(int item_id, String item_name, double unit_price) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.unit_price = unit_price;
		//this.vendor = vendor;
	}
	

	public Items(int item_id, String item_name, double unit_price, OrderDes orderDes) {
	super();
	this.item_id = item_id;
	this.item_name = item_name;
	this.unit_price = unit_price;
	this.orderDes = orderDes;
}

	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setOrderDes(OrderDes orderDes) {
		this.orderDes = orderDes;
	}
	public OrderDes getOrderDes() {
		return orderDes;
	}

	@Override
	public String toString() {
		return "Items [item_id=" + item_id + ", item_name=" + item_name + ", unit_price=" + unit_price + "]";
	}
	
	

}
