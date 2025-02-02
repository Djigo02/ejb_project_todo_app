package sn.jgotechmaker.warmodule;


import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.jgotechmaker.ejbmodule.beans.TaskBean;
import sn.jgotechmaker.ejbmodule.beans.TodoListBean;
import sn.jgotechmaker.ejbmodule.entities.Task;
import sn.jgotechmaker.ejbmodule.entities.TodoList;

import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    @EJB
    private TaskBean taskBean;

    @EJB
    private TodoListBean todoListBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todolistIdStr = request.getParameter("todolistId");

        if (todolistIdStr != null) {
            Long todolistId = Long.parseLong(todolistIdStr);
            List<Task> tasks = taskBean.getTasksByTodoList(todolistId);
            TodoList todoList = todoListBean.findTodoListById(todolistId);

            request.setAttribute("tasks", tasks);
            request.setAttribute("todoList", todoList);
        }
        request.getRequestDispatcher("tasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            Long todolistId = Long.parseLong(request.getParameter("todolistId"));
            String description = request.getParameter("description");

            Task task = new Task();
            task.setDescription(description);
            task.setTodoList(todoListBean.findTodoListById(todolistId));

            taskBean.addTask(task);
        } else if ("delete".equals(action)) {
            Long taskId = Long.parseLong(request.getParameter("taskId"));
            taskBean.removeTask(taskId);
        } else if ("update".equals(action)) {
            Long taskId = Long.parseLong(request.getParameter("taskId"));
            String description = request.getParameter("description");

            Task task = taskBean.findById(taskId);
            task.setDescription(description);
            taskBean.updateTask(task);
        }

        response.sendRedirect("tasks?todolistId=" + request.getParameter("todolistId"));
    }
}
