package day3;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

	void deleteEmployee();

	void createEmployee(Employee emp) throws SQLException;

	void updateEmployee(Employee emp) throws SQLException, ClassNotFoundException;

	List<Employee> getAllEmployees() throws SQLException;

	Employee getEmployee(int id) throws SQLException;

	public long count() throws SQLException;
}