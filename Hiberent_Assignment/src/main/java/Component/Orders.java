package Component;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.crypto.Data;


//import com.valtech.training.hibernate.Tx;
@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int order_id;
	@Basic
	Date order_date;
	
	@OneToMany(targetEntity = OrderDes.class,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY,mappedBy = "orders")

	private Set<OrderDes> orderdes;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public Set<OrderDes> getOrderdes() {
		return orderdes;
	}

	public void setOrderdes(Set<OrderDes> orderdes) {
		this.orderdes = orderdes;
	}

	public Orders(int order_id, Date order_date, Set<OrderDes> orderdes) {
		super();
		this.order_id = order_id;
		this.order_date = order_date;
		this.orderdes = orderdes;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", order_date=" + order_date + ", orderdes=" + orderdes + "]";
	}

	public Orders(int order_id, Date order_date) {
		super();
		this.order_id = order_id;
		this.order_date = order_date;
	}

	public void add(OrderDes od1) {
		
			if(getOrderdes()==null) {
				setOrderdes(new HashSet<OrderDes>());
			}
			getOrderdes().add(od1);
			od1.setOrders(this);
		}
	

	@Override
	public int hashCode() {
		return Objects.hash(order_date, order_id, orderdes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(order_date, other.order_date) && order_id == other.order_id
				&& Objects.equals(orderdes, other.orderdes);
	}
	
	
	

}
