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

public class UpdateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserDAO userDAO = UserDAO.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeID = request.getParameter("employeeID");
        if (employeeID != null && !employeeID.isEmpty()) {
            Employee existingUser = userDAO.selectEmployeeByID(Integer.parseInt(employeeID));
            if (existingUser != null) {
                request.setAttribute("user", existingUser);
                request.setAttribute("selectedDepartment", existingUser.getDepartment_id());
            }
        }
        List<Departments> departments = userDAO.selectAllDepartments();
        request.setAttribute("departments", departments);

        RequestDispatcher dispatcher = request.getRequestDispatcher("update-user.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user details from the request
        int id = Integer.parseInt(request.getParameter("employee_id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String birthDate = request.getParameter("birth_date");
        String jobTitle = request.getParameter("job_title");
        int department_id = Integer.parseInt(request.getParameter("department"));

        // Create an Employee object with the new details
        Employee employee = new Employee(id, firstName, lastName, email, birthDate, jobTitle, department_id);
        
        // Update the user in the database
        userDAO.updateEmployee(employee);

        // Redirect to the user list page
        response.sendRedirect("list");
    }
}
