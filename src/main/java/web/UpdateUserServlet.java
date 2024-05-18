package web;


import java.io.IOException;

import bean.Employee;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDao;

    public void init() {
        userDao = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user ID from request
        int id = Integer.parseInt(request.getParameter("id"));
        // Fetch user details from the database
        Employee existingUser = userDao.selectEmployeeByID(id);
        // Set user as a request attribute and forward to update-user.jsp
        request.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update-user.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user details from the request
        int id = Integer.parseInt(request.getParameter("id"));
        String first_name = request.getParameter("First Name");
        String last_name = request.getParameter("Last Name");
        String email = request.getParameter("Email");
        String birth_date = request.getParameter("Birth Date");
        String job_title = request.getParameter("Job Title");
        int department_id = Integer.parseInt(request.getParameter("Department ID"));

        // Create a User object
        Employee updatedUser = new Employee(id, first_name, last_name, email, birth_date, job_title, department_id);

        // Update the user in the database
        userDao.updateEmployee(updatedUser);

        // Redirect to the user list page
        response.sendRedirect("list");
    }
}

