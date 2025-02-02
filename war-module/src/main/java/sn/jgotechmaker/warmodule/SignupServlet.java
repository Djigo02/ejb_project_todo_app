package sn.jgotechmaker.warmodule;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.jgotechmaker.ejbmodule.beans.UserBean;
import sn.jgotechmaker.ejbmodule.entities.User;

import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    @EJB
    private UserBean userBean;

    // Gestion de la méthode GET pour afficher la page d'inscription
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers signup.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
        dispatcher.forward(request, response);
    }

    // Gestion de la méthode POST pour traiter le formulaire d'inscription
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Vérifier si l'utilisateur existe déjà
        if (userBean.findUser(username) != null) {
            request.setAttribute("error", "L'utilisateur existe déjà.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Logique d'inscription : vérifier que les mots de passe correspondent
        if (password.equals(confirmPassword)) {
            // Créer un nouvel utilisateur
            User user = new User();
            user.setUsername(username);
            user.setPassword(password); // Tu peux crypter le mot de passe ici (ex : bcrypt)

            // Enregistrer l'utilisateur dans la base de données
            userBean.register(user);

            // Redirection vers la page de connexion
            response.sendRedirect("index.jsp?success=1");
        } else {
            // Si les mots de passe ne correspondent pas, renvoyer l'utilisateur à la page d'inscription avec un message d'erreur
            request.setAttribute("error", "Les mots de passe ne correspondent pas.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
            dispatcher.forward(request, response);
        }
    }
}
