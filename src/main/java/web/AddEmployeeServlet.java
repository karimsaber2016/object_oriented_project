package web;

import java.io.IOException;
import java.util.List;

import bean.Departments;
import bean.Employee;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO = UserDAO.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-user.jsp");
        List<Departments> departmentsList = userDAO.selectAllDepartments();
        
        request.setAttribute("departmentsList", departmentsList);
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
        // Retrieve user input from the request
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String birth_date = request.getParameter("birth_date");
        String job_title = request.getParameter("job_title");

        int department = Integer.parseInt(request.getParameter("department"));    

        // Create a new User object
        Employee newUser = new Employee(first_name, last_name, email, birth_date, job_title, department);

        // Insert the new user into the database
        userDAO.insertEmployee(newUser);

        // Redirect to the user list page
        response.sendRedirect("list");
    }
}
