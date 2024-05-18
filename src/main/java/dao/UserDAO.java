package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.Departments;
import bean.Employee;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/company";
    private String jdbcUserName = "root";
    private String jdbcPassword = "password";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_DEPARTMENT = "INSERT INTO departments (department_name) VALUES (?);";
    private static final String INSERT_EMPLOYEE = "INSERT INTO employees (first_name, last_name, email, birth_date, job_title, department_id) VALUES (?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_DEPARTMENT = "UPDATE departments SET department_id=?, department_name=?";
    private static final String UPDATE_EMPLOYEE = "update employees set employee_id=?, first_name=?, last_name=?, email=?, birth_date=?, job_title=?, department_id=?";

    private static final String SELECT_ALL_DEPARTMENT = "SELECT * FROM departments";
    private static final String SELECT_DEPARTMENT_BY_NAME = "SELECT department_id, department_name FROM departments WHERE department_name=?";
    private static final String SELECT_EMPLOYEE_BY_ID = "select employee_id, last_name, email, birth_date, job_title, department_id from employees where employee_id=?";
    private static final String SELECT_ALL_EMPLOYEE = "select * from employees";

    private static final String DELETE_DEPARTMENT = "DELETE FROM departments where department_id=?";
    private static final String DELETE_EMPLOYEE = "delete from employees where employee_id=?";

    public UserDAO(){}

    /**
     * This method is used to get the connection
     * @return connection
     */
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
     * This method is used to insert department
     * @param departmentName
     * @return boolean
     */
    public boolean insertDepartment(String departmentName){
        boolean result = false;
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_DEPARTMENT);
            statement.setString(1, departmentName);
            result = statement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * This method is used to insert users
     * @param user
     * @return boolean
     */
    public boolean insertEmployee(Employee user){
        boolean result = false;
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE);
            statement.setString(1, user.getFirst_name());
            statement.setString(2, user.getLast_name());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getBirth_date());
            statement.setString(5, user.getJob_title());
            statement.setInt(6, user.getDepartment_id());
            result = statement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * This method is used to update department
     * @param department
     * @return boolean
     */
    public boolean updateDepartment(Departments department){
        boolean result = false;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_DEPARTMENT);
            statement.setString(1, department.getDepartment_name());
            result = statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * This method is used to update user
     * @param user
     * @return boolean
     */
    public boolean updateEmployee(Employee user){
        boolean result = false;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE);
            statement.setString(2, user.getFirst_name());
            statement.setString(3, user.getLast_name());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getBirth_date());
            statement.setString(6, user.getJob_title());
            statement.setInt(7, user.getDepartment_id());
            result = statement.executeUpdate() >0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * This method is used to select all departments
     * @return departments
     */
    public List<Departments> selectAllDepartments(){
        List<Departments> departments = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_DEPARTMENT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");
                departments.add(new Departments(id, departmentName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return departments;
    }

    /**
     * This method is used to select department by name
     * @param departmentName
     * @return department
     */
    public Departments selectDepartmentByName(String departmentName){
        Departments department = null;
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_DEPARTMENT_BY_NAME);
            statement.setString(1, departmentName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("department_id");
                department = new Departments(id, departmentName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return department;
    }

    /**
     * This method is used to select user by id
     * @param id
     * @return user
     */
    public Employee selectEmployeeByID(int id){
        Employee user = null;
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String birthDate = resultSet.getString("birth_date");
                String jobTitle = resultSet.getString("job_title");
                int departmentID = resultSet.getInt("department_id");
                user = new Employee(id, firstName, lastName, email, birthDate, jobTitle, departmentID);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
    
    /**
     * This method is used to select all users
     * @return users
     */
    public List<Employee> selectAllEmployee(){
        List<Employee> users = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_EMPLOYEE);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String birthDate = resultSet.getString("birth_date");
                String jobTitle = resultSet.getString("job_title");
                int departmentID = resultSet.getInt("department_id");
                users.add(new Employee(id, firstName, lastName, email, birthDate, jobTitle, departmentID));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    /**
     * This method is used to delete department
     * @param id
     * @return boolean
     */
    public boolean deleteDepartment(int id){
        boolean result = false;

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_DEPARTMENT);
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * This method is used to delete user
     * @param id
     * @return boolean
     */
    public boolean deleteUser(int id){
        boolean result = false;

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE);
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

}
