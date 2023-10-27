package day3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
 
public class DepartmentDAOImpl implements DepartmentDAO {
	@Override
	public long count() throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM DEPARTMENT");
		ResultSet rs = ps.executeQuery();
		rs.next();
		long count = rs.getLong(1);
		conn.close();
		return count;	
	}
 
	@Override
	public void createDepartment(Department dept) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO DEPARTMENT (DEPTNO, DEPTNAME,MGRNO,ADMRDEPT,LOCATION) VALUES (?,?,?,?,?)");
		populatePreparedStatementfromDepartment(dept, ps);
		int rowsUpdated = ps.executeUpdate();
		System.out.println("Rows Update "+rowsUpdated);
		conn.close();
	}
 
 
	private void populatePreparedStatementfromDepartment(Department dept, PreparedStatement ps) throws SQLException {
		ps.setString(1, dept.getDeptNO());
		ps.setString(2, dept.getDeptName());
		ps.setString(3, dept.getMGRNO());
		ps.setString(4,dept.getADMRDEPT());
		ps.setString(5,dept.getLocation());
		
	}

 
	@Override
	public void updateDepartment(Department dept) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE DEPARTMENT SET DEPTNO = ?, DEPTNAME = ?,MGRNNO=?,ADMRNO=?,LOCATION = ? WHERE DEPTNO = ?");
		populatePreparedStatementfromDepartment(dept, ps);
		ps.setString(6, dept.getDeptNO());
		int rowsUpdated = ps.executeUpdate();
		System.out.println("Rows Updated "+rowsUpdated);
		conn.close();
	}
	@Override
	public Department getDepartment(String NO) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT DEPTNO, DEPTNAME, MGRNO,ADMRDEPT,LOCATION FROM DEPARTMENT WHERE DEPTNO = ?");
		ps.setString(1, NO);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Department d = mapRowsToDepartment(rs);
			conn.close();
			return d;
		}else {
			System.out.println("No rows with deptNO "+NO+" found...!");
			return null;
		}
	}
 
 
	private Department mapRowsToDepartment(ResultSet rs) throws SQLException {
		Department d = new Department();
		d.setDeptNO(rs.getString(1));
		d.setDeptName(rs.getString(2));
		d.setMGRNO(rs.getString(3));
		d.setADMRDEPT(rs.getString(4));
		d.setLocation(rs.getString(5));
		return d;
	}
	@Override
	public void deleteDepartment(String id) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM DEPARTMENT WHERE DEPTNO=?");
		ps.setString(1, id);
		int rowsUpdated = ps.executeUpdate();
		System.out.println("Rows Updated "+rowsUpdated);
		conn.close();
	}
	@Override
	public List<Department> getAllDepartment() throws SQLException{
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT DEPTNO, DEPTNAME,MGRNO,ADMRDEPT, LOCATION FROM DEPARTMENT");
		ResultSet rs = ps.executeQuery();
		List<Department> depts = new ArrayList<>();
		while(rs.next()) {
			depts.add(mapRowsToDepartment(rs));
		}
		conn.close();
		return depts;
	}

 
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/valtech2023?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
	}

	public static void main(String[] args) throws SQLException {
		DepartmentDAO dao = new DepartmentDAOImpl();
//		dao.createDepartment(new Department("d004","Tester","Gurgoan"));
//		dao.createDepartment(new Department("d002","Testers","Bangalore"));
//		System.out.println(dao.getDepartment("d002"));
//		Department d = new Department();
//		d= dao.getDepartment("d003");
//		d.setDeptName("Devops");
//		d.setDeptLocation("Pune");
//		dao.updateDepartment(d);
//		dao.deleteDepartment("d003");
 
		System.out.println(dao.count());
		System.out.println(dao.getAllDepartment());
	}
}