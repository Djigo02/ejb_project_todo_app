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
import java.util.List;

@WebServlet("/todolists")
public class TodoListServlet extends HttpServlet {

    @EJB
    private TodoListBean todoListBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Récupérer les TodoLists de l'utilisateur connecté
        List<TodoList> todoLists = todoListBean.findTodoListsByUser(user);
        request.setAttribute("todoLists", todoLists);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/todolist-list.jsp");
        dispatcher.forward(request, response);
    }
}
