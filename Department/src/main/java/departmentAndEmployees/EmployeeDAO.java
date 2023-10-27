
package departmentAndEmployees;



import java.sql.SQLException;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

public interface EmployeeDAO {

	void deleteEmployee(int id) throws SQLException;

	void updateEmployee(Employee emp) throws SQLException;

	void createEmployee(Employee emp) throws SQLException;

	List<Employee> getAllEmployees() throws SQLException;

	Employee getEmployee(int id) throws SQLException;
	
	public long count() throws SQLException;

	int firstId() throws SQLException;
	
	int lastId() throws SQLException;
	
	int nextId(int id) throws SQLException;
	
	int previousId(int id) throws SQLException;
	
	List<Employee> getEmployeeAgeBetween(int min, int max) throws SQLException;

	void getListOfEmployeeinEachDepartment() throws SQLException;

	List<Employee> getEmployeeByDeptLocation(String name) throws SQLException;

	List<Employee> getEmployeeByDeptName(String name) throws SQLException;

	List<Employee> getEmployeeBySeniority(int limitVal) throws SQLException;

	List<Employee> getEmployeeSalaryLesser(int amount) throws SQLException;

	List<Employee> getEmployeeSalaryGreater(int amount) throws SQLException;
	
	List<Employee> getEmployeeByDeptId(int deptId) throws SQLException;

}