package departmentAndEmployees;



public class Employee {
	
	private int id;
	private String name;
	private int age;
	private int experience;
	private int seniority;
	private int salary;
	private int deptId;
	
	

	public Employee() {
		
	}





	@Override
	public String toString() {
		return "\nEmployee [id=" + id + ", name=" + name + ", age=" + age + ", experience=" + experience + ", seniority="
				+ seniority + ", salary=" + salary + ", deptId=" + deptId + "]";
	}











	public Employee(String name, int age, int experience, int seniority, int salary, int deptId) {
		this.name = name;
		this.age = age;
		this.experience = experience;
		this.seniority = seniority;
		this.salary = salary;
		this.deptId = deptId;
	}




	public Employee(int id, String name, int age, int experience, int seniority, int salary, int deptId) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.experience = experience;
		this.seniority = seniority;
		this.salary = salary;
		this.deptId = deptId;
	}







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



	public int getExperience() {
		return experience;
	}



	public void setExperience(int experience) {
		this.experience = experience;
	}



	public int getSeniority() {
		return seniority;
	}



	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}



	public int getSalary() {
		return salary;
	}



	public void setSalary(int salary) {
		this.salary = salary;
	}



	public static void main(String[] args) {
		
	}



	public int getDeptId() {
		return deptId;
	}



	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
}
