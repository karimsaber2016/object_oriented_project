package web;

import java.io.IOException;
import java.util.List;

import bean.Event;
import dao.UserQueryDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserQueryDAO queryDAO = UserQueryDAO.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("logs.jsp");
        List<Event> logs = queryDAO.selectAllEvents();
        
        request.setAttribute("logs", logs);
        dispatcher.forward(request, response);
    }
}