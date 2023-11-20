package Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



//import com.valtech.training.hibernate.Account;

@Entity
public class OrderDes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int desc_id;
	int quantity;
	@OneToMany(targetEntity = Items.class,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY,mappedBy = "orderDes")
  private Set<Items> items;
	@ManyToOne(targetEntity = Orders.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "Orders_id", referencedColumnName = "order_id")	
private Orders orders;
	public int getDesc_id() {
		return desc_id;
	}

	public void setDesc_id(int desc_id) {
		this.desc_id = desc_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

//	public List<Items> getItems() {
//		return items;
//	}
//
//	public void setItems(List<Items> items) {
//		this.items = items;
//	}

	public OrderDes(int desc_id, int quantity) {
		super();
		this.desc_id = desc_id;
		this.quantity = quantity;
		
	}

	public OrderDes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setItems(Set<Items> items) {
		this.items = items;
	}
	public Set<Items> getItems() {
		return items;
	}

	public OrderDes(int desc_id, int quantity, Set<Items> items, Orders orders) {
		super();
		this.desc_id = desc_id;
		this.quantity = quantity;
		this.items = items;
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "OrderDes [desc_id=" + desc_id + ", quantity=" + quantity + ", items=" + items + ", orders=" + orders
				+ "]";
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Orders getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desc_id, items, orders, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDes other = (OrderDes) obj;
		return desc_id == other.desc_id && Objects.equals(items, other.items) && Objects.equals(orders, other.orders)
				&& quantity == other.quantity;
	}

	public void add(Items it1) {
		// TODO Auto-generated method stub
		if(getItems()==null) {
			setItems(new HashSet<Items>());
		}
		getItems().add(it1);
		it1.setOrderDes(this);
	}
	
	
	}

	
	
	

