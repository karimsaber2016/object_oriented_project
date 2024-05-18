package web;

import java.io.IOException;

import bean.Employee;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DeleteUserServlet extends HttpServlet {
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
        // Set user as a request attribute and forward to delete-user.jsp
        request.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("delete-user.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user ID from the request
        int id = Integer.parseInt(request.getParameter("id"));

        // Delete the user from the database
        userDao.deleteUser(id);

        // Redirect to the user list page
        response.sendRedirect("list");
    }
}
