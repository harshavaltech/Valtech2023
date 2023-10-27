package day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Employee {
private int id;
private String name;
private int age;
private int salary;
private  int experience;
private String senioraity;


public Employee(String name,int age,int salary,int experience,String senioraity){
	
	this.id=id;
	this.name=name;
	this.age=age;
	this.salary=salary;
	this.experience=experience;
	this.senioraity=senioraity;
	
}
//Employee EL=new Employee();
public Employee() {
	// TODO Auto-generated constructor stub
}
public int getId() {
	return id;
}

public void setId(int id) {
	this.id= id;
}


public void setName(String name) {
	this.name = name;
}


public int getAge() {
	return age;
}


public void setAge(int age) {
	this.age = age;
}


public int getSalary() {
	return salary;
}


public void setSalary(int salary) {
	this.salary = salary;
}


public int getExperience() {
	return experience;
}


public void setExperience(int experience) {
	this.experience = experience;
}


public String getSenioraity() {
	return senioraity;
}


public void setSenioraity(String i) {
	this.senioraity = i;
}


public static void sortingByName(List<Employee>e) {
	Collections.sort(e,new Comparator<Employee>() {

		@Override
		public int compare(Employee o1, Employee o2) {
			// TODO Auto-generated method stub
			return o1.name.compareTo(o2.name);
		}
	});
}
private void sortingBYSeniority(List<Employee>e) {
	Collections.sort(e,new Comparator<Employee>() {
		@Override
		public int compare(Employee o1, Employee o2) {
			// TODO Auto-generated method stub
			return o1.senioraity.compareTo(o2.name);
		}
		
	});
	// TODO Auto-generated method stub

}


public static void main(String[] args) {
	//String[] EL=new String[6];
	
//System.out.println(EL);
	Employee EL=new Employee();	
 ArrayList<Employee> e=new ArrayList<Employee>();
 e.add(new Employee("harsha", 22, 100000, 1, "deveper"));
 e.add(new Employee("govind", 22, 200000, 2, "team lead"));
 e.add(new Employee("lakshman", 22, 300000, 3, "manager"));
 e.add(new Employee("gagana", 22, 400000, 4, "deveper"));
 e.add(new Employee("g1", 22, 500000, 5, "employee"));
 e.add(new Employee("vidya", 22, 600000, 6, "senior manager"));
 
EL.sortingByName(e);

 System.out.println(e);

}


@Override
public String toString() {
	return "Employee [name=" + name + ", age=" + age + ", salary=" + salary + ", experience=" + experience
			+ ", senioraity=" + senioraity + "]";
}
public String getName() {
	// TODO Auto-generated method stub
	return name;
}


}





