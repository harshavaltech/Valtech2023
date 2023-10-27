package departmentAndEmployees;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {

	long count() throws SQLException;

	int firstDept() throws SQLException;

	int lastDept() throws SQLException;

	int nextDept(int deptId) throws SQLException;

	int previousDept(int deptId) throws SQLException;

	void createDepartment(Department dept) throws SQLException;

	void updateDepartment(Department dept) throws SQLException;

	Department getDepartment(int deptId) throws SQLException;

	void deleteDepartment(int deptId) throws SQLException;

	List<Department> getAllDepartment() throws SQLException;
	
//	List<Employee> getEmployeeByDeptId(int deptId) throws SQLException;

}