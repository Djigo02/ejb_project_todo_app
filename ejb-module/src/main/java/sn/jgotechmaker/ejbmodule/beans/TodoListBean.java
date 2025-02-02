package sn.jgotechmaker.ejbmodule.beans;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.jgotechmaker.ejbmodule.entities.TodoList;
import sn.jgotechmaker.ejbmodule.entities.User;

import java.util.List;

@Stateless // Assure-toi que cette annotation est bien présente
public class TodoListBean {
    @PersistenceContext
    private EntityManager em;

    public void register(TodoList todoList) {
        em.persist(todoList);
    }

    public TodoList findTodoListById(Long id) {
        return em.find(TodoList.class, id);
    }

    public List<TodoList> findTodoListsByUser(User user) {
        return em.createQuery("SELECT t FROM TodoList t WHERE t.user = :user", TodoList.class)
                .setParameter("user", user)
                .getResultList();
    }

    public void remove(TodoList todoList) {
        if (em.contains(todoList)) {
            em.remove(todoList);
        } else {
            TodoList managedTodoList = em.merge(todoList);
            em.remove(managedTodoList);
        }
    }
}
