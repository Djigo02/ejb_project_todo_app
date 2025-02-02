package sn.jgotechmaker.warmodule;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.jgotechmaker.ejbmodule.beans.TodoListBean;
import sn.jgotechmaker.ejbmodule.beans.UserBean;
import sn.jgotechmaker.ejbmodule.entities.TodoList;
import sn.jgotechmaker.ejbmodule.entities.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @EJB
    private UserBean userBean;
    @EJB
    private TodoListBean todoListBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userBean.findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            // Récupérer les TodoLists de l'utilisateur connecté
            List<TodoList> todoLists = todoListBean.findTodoListsByUser(user);
            request.setAttribute("todoLists", todoLists);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/todolist-list.jsp");
            dispatcher.forward(request, response);
            response.sendRedirect("todolist-list.jsp");
        } else {
            request.getSession().setAttribute("error", "Identifiant invalide");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
//            response.sendRedirect("index.jsp?error=1");
        }
    }
}
