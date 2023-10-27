package departmentAndEmployees;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//CRUD
//Create
//Read
//Update
//Delete

public class EmployeeDAOImpl implements EmployeeDAO {
	
	@Override
	public int firstId() throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT MIN(ID) FROM EMPLOYEE");
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int id = rs.getInt(1);
			
			conn.close();
			return id;
			
		}
		conn.close();
		return 0;
	}

	@Override
	public int lastId() throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM EMPLOYEE");
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int id = rs.getInt(1);
			
			conn.close();
			return id;
		}
		conn.close();
		return 0;
	}

	public int nextId(int id) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT MIN(ID) FROM EMPLOYEE WHERE ID > ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			id = rs.getInt(1);
			
			
			
		}
		conn.close();
		return id == 0 ? firstId() : id;
	}

	@Override
	public int previousId(int id) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM EMPLOYEE WHERE ID < ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			id = rs.getInt(1);
			
			
		}
		conn.close();
		return id == 0 ? lastId() : id;
	}

	
	public List<Employee> getEmployeeByDeptId(int deptId) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT ID, NAME, AGE, EXPERIENCE, SENIORITY, SALARY, DEPTID FROM EMPLOYEE WHERE DEPTID = ?");
		ps.setInt(1, deptId);
		ResultSet rs = ps.executeQuery();
		List<Employee> emps = new ArrayList<>();
		while(rs.next()) {
			emps.add(mapRowToEmployee(rs));
		}
		conn.close();
//		System.out.println(emps.size());
		return emps;
	}
	
	@Override
	public void getListOfEmployeeinEachDepartment() throws SQLException {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("UPDATE DEPARTMENT D SET LISTOFEMPLOYEES=(SELECT COUNT(*) FROM EMPLOYEE E WHERE E.DEPTID=D.DEPTID)");
		int rowsUpdated=ps.executeUpdate();
		System.out.println("Rows update:"+rowsUpdated);
		con.close();
	}
	
	public List<Employee> getEmployeeByDeptLocation(String name) throws SQLException {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("SELECT * FROM EMPLOYEE WHERE DEPTID IN (SELECT DEPTID FROM DEPARTMENT WHERE DEPTLOC=?)");
		ps.setString(1, name);
		ResultSet rs=ps.executeQuery();
		List<Employee> emps1=new ArrayList<>();
		while(rs.next()) {
			emps1.add(mapRowToEmployee(rs));
		}
		con.close();
		System.out.println(emps1.size());
		return emps1;
	}
	
	@Override
	public List<Employee> getEmployeeByDeptName(String name) throws SQLException {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("SELECT * FROM EMPLOYEE WHERE DEPTID IN (SELECT DEPTID FROM DEPARTMENT WHERE DEPTNAME=?)");
		ps.setString(1, name);
		ResultSet rs=ps.executeQuery();
		List<Employee> emps1=new ArrayList<>();
		while(rs.next()) {
			emps1.add(mapRowToEmployee(rs));
		}
		con.close();
		System.out.println(emps1.size());
		return emps1;
	}
	
	@Override
	public List<Employee> getEmployeeAgeBetween(int min, int max) throws SQLException {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("SELECT * FROM EMPLOYEE WHERE AGE BETWEEN ? AND ?");
		ps.setInt(1, min);
		ps.setInt(2, max);
		ResultSet rs=ps.executeQuery();
		List<Employee> emps1=new ArrayList<>();
		while(rs.next()) {
			emps1.add(mapRowToEmployee(rs));
		}
		con.close();
		return emps1;
	}
	
	@Override
	public List<Employee> getEmployeeBySeniority(int limitVal) throws SQLException{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("SELECT * FROM EMPLOYEE ORDER BY SENIORITY DESC LIMIT ?");
		ps.setInt(1, limitVal);
		ResultSet rs=ps.executeQuery();
		List<Employee> emps1=new ArrayList<>();
		while(rs.next()) {
			emps1.add(mapRowToEmployee(rs));
		}
		con.close();
		return emps1;
	}
	
	@Override
	public List<Employee> getEmployeeSalaryLesser(int amount) throws SQLException {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("SELECT * FROM EMPLOYEE WHERE SALARY<?");
		ps.setInt(1, amount);
		ResultSet rs=ps.executeQuery();
		List<Employee> emps1=new ArrayList<>();
		while(rs.next()) {
			emps1.add(mapRowToEmployee(rs));
		}
		con.close();
		return emps1;
	}
	
	@Override
	public List<Employee> getEmployeeSalaryGreater(int amount) throws SQLException {
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("SELECT * FROM EMPLOYEE WHERE SALARY>=?");
		ps.setInt(1, amount);
		ResultSet rs=ps.executeQuery();
		List<Employee> emps1=new ArrayList<>();
		while(rs.next()) {
			emps1.add(mapRowToEmployee(rs));
		}
		con.close();
		return emps1;
	}
	
	public long count() throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM EMPLOYEE");
		ResultSet rs = ps.executeQuery();
		rs.next();  // need to be called to take it to the data where it starts
		long count = rs.getLong(1);
		conn.close();
		return count;
	}
	
	@Override
	public void deleteEmployee(int id) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM EMPLOYEE WHERE ID = ?");
		ps.setInt(1, id);
		int rowsUpdated = ps.executeUpdate();
		System.out.println("Rows Updated "+rowsUpdated);
		conn.close();
	}
	
	@Override
	public void updateEmployee(Employee emp) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE EMPLOYEE SET NAME = ?, AGE = ?, EXPERIENCE = ?, SENIORITY = ?, SALARY = ? DEPTID = ? WHERE ID = ?");
		populatePreparedStatementfromEmployee(emp, ps);
		ps.setInt(6, emp.getId());
		int rowsUpdated = ps.executeUpdate();
		System.out.println("Rows Updated "+rowsUpdated);
		conn.close();
	}
	
	@Override
	public void createEmployee(Employee emp) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO EMPLOYEE (NAME, AGE, EXPERIENCE, SENIORITY, SALARY, DEPTID) VALUES (?,?,?,?,?,?)");
		populatePreparedStatementfromEmployee(emp, ps);
		int rowsUpdated = ps.executeUpdate();
		System.out.println("Rows Updated "+rowsUpdated);
		conn.close();
	}

	private void populatePreparedStatementfromEmployee(Employee emp, PreparedStatement ps) throws SQLException {
		ps.setString(1,  emp.getName());
		ps.setInt(2, emp.getAge());
		ps.setInt(3,emp.getExperience());
		ps.setInt(4, emp.getSeniority());
		ps.setInt(5, emp.getSalary());
		ps.setInt(6, emp.getDeptId());
	}
	
	
	
	@Override
	public List<Employee> getAllEmployees() throws SQLException{
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT ID, NAME, AGE, EXPERIENCE, SENIORITY, SALARY, DEPTID FROM EMPLOYEE");
		ResultSet rs = ps.executeQuery();
		List<Employee> emps = new ArrayList<>();
		while(rs.next()) {
			emps.add(mapRowToEmployee(rs));
		}
		conn.close();
		return emps;
	}

	@Override
	public Employee getEmployee(int id) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT ID, NAME, AGE, EXPERIENCE, SENIORITY, SALARY, DEPTID FROM EMPLOYEE WHERE ID = ?"); //preferred statement
		ps.setInt(1,  id);   // In sql index always starts with 1
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {  //if rs.next()=false, the there is no row of data at that index
			Employee e = mapRowToEmployee(rs);
			conn.close();
			return e;
		}else {
			System.out.println("No rows with id = "+id+" found...");
			return null;
		}
		
	}

	protected Employee mapRowToEmployee(ResultSet rs) throws SQLException {
		Employee e = new Employee();
		e.setId(rs.getInt(1));
		e.setName(rs.getString(2));
		e.setAge(rs.getInt(3));
		e.setExperience(rs.getInt(4));
		e.setSeniority(rs.getInt(5));
		e.setSalary(rs.getInt(6));
		e.setDeptId(rs.getInt(7));
		return e;
	}
	
	static { //to run code only once after class loaded
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/valtech2?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
	}
	
	public static void main(String[] args) throws Exception {
		EmployeeDAO dao = new EmployeeDAOImpl();
//		dao.createEmployee(new Employee("Murali", 30,8, 2,50000, "d001"));
//		dao.createEmployee(new Employee("Pradeep",35,12, 3,75000, "d001"));
//		dao.createEmployee(new Employee("Shanthakumar", 45, 12, 4,500000, "d002"));
//		dao.createEmployee(new Employee("Harsha", 22, 2, 1,75000, "d002"));
//		dao.createEmployee(new Employee("Laxman", 22, 1, 2,50000, "d003"));
//		dao.createEmployee(new Employee("Kruthik", 23, 2, 3,75000, "d003"));
//		Employee e = dao.getEmployee(2);
//		System.out.println(e);
//		e.setSeniority(5);
//		dao.updateEmployee(e);
//		dao.deleteEmployee(6); 
//		System.out.println(dao.getEmployee(1));
//		System.out.println(dao.getEmployee(2));
//		System.out.println(dao.getEmployee(3));
//		System.out.println(dao.count());
//		System.out.println(dao.getAllEmployees());
		
//		dao.getListOfEmployeeinEachDepartment();
//		System.out.println(dao.getEmployeeByDeptId(2));
		System.out.println(dao.getAllEmployees());
	}

	

}
