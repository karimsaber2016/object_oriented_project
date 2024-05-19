package web;

import java.io.IOException;

import dao.UserCommandDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserCommandDAO userDAO = UserCommandDAO.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user ID from the request
        String id = request.getParameter("employeeID");
        
        userDAO.deleteEmployee(Integer.parseInt(id));
        response.sendRedirect("list");
    }
}
