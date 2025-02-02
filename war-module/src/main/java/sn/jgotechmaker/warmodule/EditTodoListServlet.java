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

import java.io.IOException;

@WebServlet("/edit-todolist")
public class EditTodoListServlet extends HttpServlet {

    @EJB
    private TodoListBean todoListBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long todoListId = Long.valueOf(request.getParameter("todolistId"));
        TodoList todoList = todoListBean.findTodoListById(todoListId);
        if (todoList != null) {
            request.setAttribute("todoList", todoList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-todolist.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("todolists");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long todoListId = Long.valueOf(request.getParameter("todolistId"));
        String name = request.getParameter("name");

        TodoList todoList = todoListBean.findTodoListById(todoListId);
        if (todoList != null) {
            todoList.setName(name);
            todoListBean.register(todoList);
            response.sendRedirect("todolists");
        } else {
            response.sendRedirect("todolists");
        }
    }
}
