package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.Departments;
import bean.Employee;
import bean.Event;


public class UserQueryDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/company";
    private String jdbcUserName = "root";
    private String jdbcPassword = "password";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String SELECT_DEPARTMENT_BY_ID = "SELECT department_id, department_name FROM departments WHERE department_id=?";
    private static final String SELECT_DEPARTMENT_BY_NAME = "SELECT department_id, department_name FROM departments WHERE department_name=?";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT employee_id, first_name, last_name, email, birth_date, job_title, department_id FROM employees WHERE employee_id=?";
    private static final String SELECT_ALL_DEPARTMENT = "SELECT * FROM departments";
    private static final String SELECT_ALL_EMPLOYEE = "SELECT * FROM employees";
    private static final String SELECT_ALL_EVENTS = "SELECT * FROM events";

    private static UserQueryDAO instance;

    private UserQueryDAO() {}

    public static UserQueryDAO getInstance() {
        if (instance == null) {
            synchronized (UserQueryDAO.class) {
                if (instance == null) {
                    instance = new UserQueryDAO();
                }
            }
        }
        return instance;
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Departments> selectAllDepartments() {
        List<Departments> departments = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_DEPARTMENT);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");
                departments.add(new Departments(id, departmentName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public String selectDepartmentByID(int department_id) {
        String department_name = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DEPARTMENT_BY_ID)) {
            statement.setInt(1, department_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                department_name = resultSet.getString("department_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department_name;
    }

    public Departments selectDepartmentByName(String departmentName) {
        Departments department = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DEPARTMENT_BY_NAME)) {
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("department_id");
                department = new Departments(id, departmentName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    public Employee selectEmployeeByID(int employee_id) {
        Employee user = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            statement.setInt(1, employee_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String birthDate = resultSet.getString("birth_date");
                String jobTitle = resultSet.getString("job_title");
                int departmentID = resultSet.getInt("department_id");
                user = new Employee(employee_id, firstName, lastName, email, birthDate, jobTitle, departmentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Employee> selectAllEmployee() {
        List<Employee> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_EMPLOYEE);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String birthDate = resultSet.getString("birth_date");
                String jobTitle = resultSet.getString("job_title");
                int departmentID = resultSet.getInt("department_id");
                users.add(new Employee(id, firstName, lastName, email, birthDate, jobTitle, departmentID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Event> selectAllEvents() {
        List<Event> events = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_EVENTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("event_type");
                String data = resultSet.getString("event_data");
                Timestamp time = resultSet.getTimestamp("created_at");
                events.add(new Event(id, type, data, time));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
}
