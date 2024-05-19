package web;

import java.io.IOException;
import java.util.List;

import bean.Departments;
import bean.Employee;
import dao.UserCommandDAO;
import dao.UserQueryDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserCommandDAO commandDAO = UserCommandDAO.getInstance();
    UserQueryDAO queryDAO = UserQueryDAO.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-user.jsp");
        List<Departments> departmentsList = queryDAO.selectAllDepartments();
        
        request.setAttribute("departmentsList", departmentsList);
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String birth_date = request.getParameter("birth_date");
        String job_title = request.getParameter("job_title");

        int department = Integer.parseInt(request.getParameter("department"));    
        Employee newUser = new Employee(first_name, last_name, email, birth_date, job_title, department);

        commandDAO.insertEmployee(newUser);
        response.sendRedirect("list");
    }
}
