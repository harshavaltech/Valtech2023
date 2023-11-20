package hiberent.client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import Component.Customer;
import Component.Customer_address;
import Component.Items;
import Component.OrderDes;
import Component.Orders;
import Component.Vendor;
import Component.Vendor_address;



public class Client {
	public static void main(String[] args) throws HibernateException, ParseException {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		AnnotationConfiguration cfg = new AnnotationConfiguration();

		cfg.addAnnotatedClass(Customer.class).addAnnotatedClass(Customer_address.class).addAnnotatedClass(Vendor.class).addAnnotatedClass(Vendor_address.class).
		addAnnotatedClass(Orders.class).addAnnotatedClass(OrderDes.class).addAnnotatedClass(Items.class);
		
		
		SessionFactory sesFac = cfg.buildSessionFactory();
		Session ses = sesFac.openSession();
		Transaction tx = ses.beginTransaction();
//		Customer cus1 = new Customer(1, "Harsha", 12345, "harsha@gamil.com");
//		Customer cus2 = new Customer(2, "Govind", 8569, "govind@gamil.com");
//		Customer cus3 = new Customer(3, "Lakshman", 2596, "lakshman@gamil.com");
//
//
//		
//		Customer_address ca1=new Customer_address("Racecourse", "banglore", 1, 572101);
//		Customer_address ca2=new Customer_address("Jp nagar", "banglore", 2, 573107);
//		Customer_address ca3=new Customer_address("Yelhanka", "banglore", 3, 540106);
//		
//		ca1.setCustomer(cus1);
//		ca2.setCustomer(cus2);
//		ca3.setCustomer(cus3);
//		
//		cus1.setAddress(ca1);
//		cus2.setAddress(ca2);
//		cus3.setAddress(ca3);
//		
//		//ses.save(cus);
//		//ses.save(ca);
		
//		Vendor v1=new Vendor(1, "JioMart");
//		Vendor v2=new Vendor(2,"Amazon");
//		
//		Vendor_address va1=new Vendor_address(1, "Brigade Road", "California", 596254);
//		Vendor_address va2=new Vendor_address(2, "churuch street", "Banglore", 598956);
//		
//		va1.setVendor(v1);
//		va2.setVendor(v2);
//		
//	    v1.setV_address(va1);
//		v2.setV_address(va2);
//
//		
//		ses.save(v1);
//		ses.save(v2);
//		ses.save(va1);
//		ses.save(va2);
		
	Orders o1=new Orders(1,df.parse("25-08-2000"));
Orders o2=new Orders(2,df.parse("2-07-2001"));
//		
	OrderDes od1=new OrderDes(1, 2);
		OrderDes od2=new OrderDes(2,2);
//		OrderDes od3=new OrderDes(3, 3);
//		
//		o1.add(od1);
//		o1.add(od2);
//		o1.add(od3);
//		
//		ses.save(o1);
//		ses.save(od1);
//		ses.save(od2);
//		ses.save(od3);
		
		Items it1=new Items(1,"Lays", 10.0);
		Items it2=new Items(2,"Cake", 50.0);
		Items it3=new Items(4,"biscuit", 40.0);
		
		od1.add(it1);
		od1.add(it2);
		ses.save(od1);
		
		ses.save(it1);
		ses.save(it2);
		ses.save(it3);
		
		tx.commit();
		ses.close();
		sesFac.close();
}
}