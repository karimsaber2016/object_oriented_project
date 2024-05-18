package web;

import java.io.IOException;
import java.util.List;

import bean.Employee;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeServlet extends HttpServlet {
    UserDAO userDAO = UserDAO.getInstance();
    public EmployeeServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action){
            case "/new":
                showNewForm(request, response);
                break;

            case "/insert":
                insertNewUser(request, response);
                break;

            case "/delete":
                deleteUser(request, response);
                break;

            case "/edit":
                showEditForm(request, response);
                break;

            case "/update":
                updateUser(request, response);
                break;

            default:
                showUserList(request, response);
                break;
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    //inset user
    private void insertNewUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employee employee = new Employee();
        employee.setFirst_name(request.getParameter("first_name"));
        employee.setLast_name(request.getParameter("last_name"));
        employee.setEmail(request.getParameter("email"));
        employee.setBirth_date(request.getParameter("birth_date"));
        employee.setJob_title(request.getParameter("job_title"));
        employee.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));

        userDAO.insertEmployee(employee);
        response.sendRedirect("list");
    }

    // Delete user
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            userDAO.deleteUser(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("list");
    }

    // Show edit user form
    private void showEditForm(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Employee user = userDAO.selectEmployeeByID(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
            request.setAttribute("user", user);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Edit user
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Employee employee = new Employee();
        employee.setEmployee_id(Integer.parseInt(request.getParameter("id")));
        employee.setFirst_name(request.getParameter("first_name"));
        employee.setLast_name(request.getParameter("last_name"));
        employee.setEmail(request.getParameter("email"));
        employee.setBirth_date(request.getParameter("birth_date"));
        employee.setJob_title(request.getParameter("job_title"));
        employee.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));

        userDAO.updateEmployee(employee);

        response.sendRedirect("list");
    }

    private void showUserList(HttpServletRequest request, HttpServletResponse response){
        try {
            List<Employee> users = userDAO.selectAllEmployee();
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}