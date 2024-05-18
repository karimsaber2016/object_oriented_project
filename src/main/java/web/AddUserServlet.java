package web;

import java.io.IOException;

import bean.Employee;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private UserDAO userDao;

    public void init() {
        userDao = new UserDAO();
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("add-user.jsp");
//        dispatcher.forward(request, response);
//    }

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
        userDao.insertEmployee(newUser);

        // Redirect to the user list page
        response.sendRedirect("list");
    }
}
