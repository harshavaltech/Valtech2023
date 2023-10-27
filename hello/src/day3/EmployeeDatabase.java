package day3;

public class EmployeeDatabase {
	
	private int id;
	private String name;
	private int age;
	private String seniority;
	private int salary;
	private int experience;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
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
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
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
	public EmployeeDatabase(int id, String name, int age, String seniority, int salary, int experience) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.seniority = seniority;
		this.salary = salary;
		this.experience = experience;
	}
	public EmployeeDatabase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDatabase(String name, int age, String seniority, int salary, int experience) {
		super();
		this.name = name;
		this.age = age;
		this.seniority = seniority;
		this.salary = salary;
		this.experience = experience;
	}
}