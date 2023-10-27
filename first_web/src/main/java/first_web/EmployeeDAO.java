package first_web;

import java.sql.SQLException;
import java.util.List;



public interface EmployeeDAO {

	
int FirstId() throws SQLException;
int PreviousId(int id) throws SQLException;
int NextId(int id) throws SQLException;
int LastId() throws SQLException;

		void deleteEmployee();

		void createEmployee(Employee emp) throws SQLException;

		void updateEmployee(Employee emp) throws SQLException, ClassNotFoundException;

		List<Employee> getAllEmployees() throws SQLException;

		Employee getEmployee(int id) throws SQLException;

		public long count() throws SQLException;
		
	}

