package sn.jgotechmaker.ejbmodule;

import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.jgotechmaker.ejbmodule.entities.TestEntity;
import sn.jgotechmaker.ejbmodule.entities.TestEntityBean;

import java.io.IOException;
import java.util.List;


@Path("/hello-world")
public class HelloResource {

    @EJB
    private TestEntityBean testEntityBean;

    @GET
    @Produces("text/plain")
    public String hello() {
        // Insère une nouvelle entrée dans la base
        testEntityBean.saveTestEntity("Test " + System.currentTimeMillis());

        // Récupère toutes les entrées de la table
        List<TestEntity> entities = testEntityBean.getAllTestEntities();

        String message = "";

        // Affichage des résultats dans la réponse HTTP
//        response.setContentType("text/html");
//        response.getWriter().println("<h2>Liste des entités enregistrées :</h2>");
        for (TestEntity entity : entities) {
            message += "<p>ID: " + entity.getId() + " | Nom: " + entity.getName() + "</p>" +"\n";
        }
        return message;
    }


}