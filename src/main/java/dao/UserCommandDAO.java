package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import bean.Event;
import bean.Departments;
import bean.Employee;

public class UserCommandDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/company";
    private String jdbcUserName = "root";
    private String jdbcPassword = "password";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_DEPARTMENT = "INSERT INTO departments (department_name) VALUES (?);";
    private static final String INSERT_EMPLOYEE = "INSERT INTO employees (first_name, last_name, email, birth_date, job_title, department_id) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String INSERT_EVENT = "INSERT INTO events (event_type, event_data, created_at) VALUES (?, ?, NOW())";

    private static final String UPDATE_DEPARTMENT = "UPDATE departments SET department_name=? WHERE department_id=?";
    private static final String UPDATE_EMPLOYEE = "UPDATE employees SET first_name=?, last_name=?, email=?, birth_date=?, job_title=?, department_id=? WHERE employee_id=?";

    private static final String DELETE_DEPARTMENT = "DELETE FROM departments WHERE department_id=?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE employee_id=?";

    private static UserCommandDAO instance;

    private UserCommandDAO() {}

    /**
     *  This function was created to incoroporate the Singleton design pattern
     * @return instance
     */
    public static UserCommandDAO getInstance() {
        if (instance == null) {
            synchronized (UserCommandDAO.class) {
                if (instance == null) {
                    instance = new UserCommandDAO();
                }
            }
        }
        return instance;
    }

    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * This function is used to insert a new department into the database
     * @param departmentName
     * @return result
     */
    public boolean insertDepartment(String departmentName) {
        boolean result = false;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_DEPARTMENT);
            statement.setString(1, departmentName);
            result = statement.executeUpdate() > 0;
            if (result) {
                Event event = new Event("DepartmentCreated", departmentName);
                saveEvent(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This function is used to insert a new employee into the database.
     * @param employee The employee to insert.
     * @return True if the insertion was successful, otherwise false.
     */
    public boolean insertEmployee(Employee employee) {
        boolean result = false;
        String generatedColumns[] = { "ID" };  // Assumes the employee ID is generated automatically
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE, generatedColumns)) {
            
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getBirth_date());
            statement.setString(5, employee.getJob_title());
            statement.setInt(6, employee.getDepartment_id());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int employeeId = generatedKeys.getInt(1);
                        Event event = new Event("EmployeeCreated", "Employee ID: " + employeeId);
                        saveEvent(event);
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * This function is used to update a department in the database
     * @param department
     * @return result
     */
    public boolean updateDepartment(Departments department) {
        boolean result = false;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_DEPARTMENT);
            statement.setString(1, department.getDepartment_name());
            statement.setInt(2, department.getDepartment_id());
            result = statement.executeUpdate() > 0;
            if (result) {
                Event event = new Event("DepartmentUpdated", "Department ID: " + department.getDepartment_id());
                saveEvent(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This function is used to update an employee in the database
     * @param employee
     * @return result
     */
    public boolean updateEmployee(Employee employee) {
        boolean result = false;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE);
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getBirth_date());
            statement.setString(5, employee.getJob_title());
            statement.setInt(6, employee.getDepartment_id());
            statement.setInt(7, employee.getEmployee_id());
            result = statement.executeUpdate() > 0;
            if (result) {
                Event event = new Event("EmployeeUpdated", "Employee ID: " + String.valueOf(employee.getEmployee_id()));
                saveEvent(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This function is used to delete a department from the database
     * @param id
     * @return result
     */
    public boolean deleteDepartment(int id) {
        boolean result = false;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_DEPARTMENT);
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
            if (result) {
                Event event = new Event("DepartmentDeleted", "Department ID: " + id);
                saveEvent(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This function is used to delete an employee from the database
     * @param id
     * @return result
     */
    public boolean deleteEmployee(int id) {
        boolean result = false;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE);
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
            if (result) {
                Event event = new Event("EmployeeDeleted", "Employee ID: " + id);
                saveEvent(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This function is used to save an event to the database
     * @param event
     * @return result
     */
    public boolean saveEvent(Event event) {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EVENT)) {

             statement.setString(1, event.getEventType());
             statement.setString(2, event.getEventData());
             result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
