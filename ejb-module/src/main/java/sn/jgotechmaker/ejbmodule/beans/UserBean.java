package sn.jgotechmaker.ejbmodule.beans;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.jgotechmaker.ejbmodule.entities.User;

import java.util.List;

@Stateless
public class UserBean {
    @PersistenceContext
    private EntityManager em;

    public void register(User user) {
        em.persist(user);
    }

    public User findUser(String username) {
        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();

        if (users.isEmpty()) {
            return null; // Aucun utilisateur trouvé
        } else {
            return users.getFirst(); // Retourner le premier utilisateur trouvé
        }
    }
}
