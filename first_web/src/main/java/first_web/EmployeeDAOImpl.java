package first_web;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
	public void deleteEmployee(int id) throws SQLException{
		Connection conn=getConnection();
		PreparedStatement ps =conn.prepareStatement("DELETE FROM EMPLOYEE WHERE ID=?");
	ps.setInt(1, id);
	int rowsUpdated=ps.executeUpdate();
	System.out.println("rows updated"+rowsUpdated);
	conn.close();

	
}
	
@Override
public void createEmployee(Employee emp)throws SQLException {
	
	
		Connection conn = getConnection();

		PreparedStatement ps = conn.prepareStatement("INSERT INTO EMPLOYEE (AGE ,EXPERIENCE,SENIORITY,NAME,SALARY,DEPTNO ) VALUES (?,?,?,?,?,?)");
    //   ps.setInt(6,emp.getId());
		populatePrepareStatementEmployee(emp, ps);

		int rowsUpdated = ps.executeUpdate();

		System.out.println("Rows Updated "+rowsUpdated);

		conn.close();

	}


private void populatePrepareStatementEmployee(Employee emp, PreparedStatement ps) throws SQLException {
	ps.setString(4, emp.getName());

	ps.setInt(1, emp.getAge());

	ps.setInt(2,emp.getExperience());

	ps.setInt(3, emp.getSeniority());

	ps.setInt(5, emp.getSalary());
	//ps.setInt(6,emp.getdeptno() );
}
	

@Override
public void updateEmployee(Employee emp)throws SQLException, ClassNotFoundException {

	Connection conn =getConnection();

	PreparedStatement p= conn.prepareStatement("UPDATE EMPLOYEE SET AGE=?,EXPERIENCE=?,SENIORITY=?, NAME =?,SALARY=?,DEPTNO=?,WHERE ID=?");

	populatePrepareStatementEmployee(emp, p);

	PreparedStatement P = conn.prepareStatement("INSERT INTO EMPLOYEE (AGE ,EXPERIENCE,SENIORITY,NAME,SALARY,ID,deptno ) VALUES (?,?,?,?,?,)");
}	


public long count() throws SQLException{
	Connection conn=getConnection();
	PreparedStatement p=conn.prepareStatement("SELECT COUNT(ID) FROM EMPLOYEE");
	ResultSet rs=p.executeQuery();
	rs.next(); 
		long count=rs.getLong(1);
	
		conn.close();	
		
	
	return count;
	
	 
}

@Override
public List<Employee> getAllEmployees()throws SQLException{
	Connection conn = getConnection();
	PreparedStatement ps =conn.prepareStatement("SELECT AGE ,EXPERIENCE,SENIORITY,NAME,SALARY,DEPTNO FROM EMPLOYEE");
	ResultSet rs=ps.executeQuery();
	List<Employee> emps=new ArrayList();
	while(rs.next()) {
		emps.add(mapRowToEmployee( rs));
	}
	conn.close();
	return emps;
	
		
	}
	@Override
	public Employee getEmployee(int id) throws SQLException {
		
		
		Connection conn=getConnection();
		PreparedStatement ps=conn.prepareStatement("SELECT AGE ,EXPERIENCE,SENIORITY,NAME,SALARY,ID,DEPTNO FROM EMPLOYEE WHERE ID=?");
		ps.setInt(1, id);
		ResultSet rs =ps.executeQuery();
		if (rs.next()) {
			Employee e = mapRowToEmployee( rs);
			return e;
			
			
			
		}else {
			System.out.println("NO ROW WITH id="+id +" found...");
				
		}
		return null;
		
		
	}

	private Employee mapRowToEmployee( ResultSet rs) throws SQLException {
		Employee e= new Employee();
		e.setId(rs.getInt(6));
		e.setName(rs.getString(4));
		e.setExperience(rs.getInt(2));
		e.setSeniority(rs.getInt(3));
		e.setSalary(rs.getInt(5));
		e.setAge(rs.getInt(1));
		//conn.close();
		return e;
	}
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/valtech2023","root","root");
		
		
	}
public static void main(String[] args) throws SQLException {
	EmployeeDAO dao=new EmployeeDAOImpl();
	
	//dao.createEmployee(new Employee(
	System.out.println(dao.getEmployee(1));
	//Employee e=dao.getEmployee(1);
	//dao.deleteEmployee(1);
	System.out.println(dao.getEmployee(2));
	System.out.println(dao.getEmployee(3));
	System.out.println(dao.count());
	
	//System.out.println(dao.getAllEmployees());
}



@Override
public void deleteEmployee() {
	// TODO Auto-generated method stub
	
}


	
 
	
 
	


	

@Override
public int LastId() throws SQLException 
{
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



@Override
public int NextId(int id) throws SQLException 
{
	Connection conn = getConnection();
	PreparedStatement ps = conn.prepareStatement("SELECT MIN(ID) FROM EMPLOYEE WHERE ID > ?");
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();
	if(rs.next()) {
		id = rs.getInt(1);
		conn.close();
		return id;
	}
	conn.close();
	return 0;
}

@Override
public int FirstId() throws SQLException {
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
public int PreviousId(int id) throws SQLException {
	Connection conn = getConnection();
	PreparedStatement ps = conn.prepareStatement("SELECT MIN(ID) FROM EMPLOYEE WHERE ID < ?");
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();
	if(rs.next()) {
		id = rs.getInt(1);
		conn.close();
		return id;
	}
	conn.close();
	return id==0 ? FirstId():id;
}

}



