package sn.jgotechmaker.warmodule;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.jgotechmaker.ejbmodule.beans.TodoListBean;
import sn.jgotechmaker.ejbmodule.entities.TodoList;
import sn.jgotechmaker.ejbmodule.entities.User;

import java.io.IOException;

@WebServlet("/create-todolist")
public class CreateTodoListServlet extends HttpServlet {

    @EJB
    private TodoListBean todoListBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create-todolist.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            TodoList todoList = new TodoList();
            todoList.setName(name);
            todoList.setUser(user);
            todoListBean.register(todoList);

            response.sendRedirect("todolists");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
