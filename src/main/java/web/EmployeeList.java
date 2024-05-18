package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Departments;
import bean.Employee;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = UserDAO.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
	    List<Departments> departmentsList = userDAO.selectAllDepartments();
	    List<Employee> employeesList = userDAO.selectAllEmployee();
	    
	    // Create a map to hold department information
	    Map<Integer, String> departmentMap = new HashMap<>();
	    for (Departments department : departmentsList) {
	        departmentMap.put(department.getDepartment_id(), department.getDepartment_name());
	    }
	    
	    request.setAttribute("employeesList", employeesList);
	    request.setAttribute("departmentMap", departmentMap); // Set the department map attribute
	    dispatcher.forward(request, response);
	}

}