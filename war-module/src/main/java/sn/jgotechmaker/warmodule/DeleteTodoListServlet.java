package sn.jgotechmaker.warmodule;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.jgotechmaker.ejbmodule.beans.TodoListBean;
import sn.jgotechmaker.ejbmodule.entities.TodoList;

import java.io.IOException;

@WebServlet("/delete-todolist")
public class DeleteTodoListServlet extends HttpServlet {

    @EJB
    private TodoListBean todoListBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long todoListId = Long.valueOf(request.getParameter("todolistId"));
        TodoList todoList = todoListBean.findTodoListById(todoListId);

        if (todoList != null) {
            todoListBean.remove(todoList);
        }

        response.sendRedirect("todolists");
    }
}
