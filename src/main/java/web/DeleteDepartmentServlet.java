package web;

import java.io.IOException;
import java.util.List;

import bean.Departments;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserDAO userDAO = UserDAO.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("modify-department.jsp");
        List<Departments> departmentsList = userDAO.selectAllDepartments();
        
        request.setAttribute("departmentsList", departmentsList);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int departmentId = Integer.parseInt(idParam);
        userDAO.deleteDepartment(departmentId);
        response.sendRedirect("modifydepartment");
    }
}
