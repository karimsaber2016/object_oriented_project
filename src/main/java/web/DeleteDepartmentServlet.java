package web;

import java.io.IOException;
import java.util.List;

import bean.Departments;
import dao.UserCommandDAO;
import dao.UserQueryDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserCommandDAO commandDAO = UserCommandDAO.getInstance();
    UserQueryDAO queryDAO = UserQueryDAO.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("modify-department.jsp");
        List<Departments> departmentsList = queryDAO.selectAllDepartments();
        
        request.setAttribute("departmentsList", departmentsList);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int departmentId = Integer.parseInt(idParam);
        commandDAO.deleteDepartment(departmentId);
        response.sendRedirect("modifydepartment");
    }
}
