package sn.jgotechmaker.ejbmodule.beans;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.jgotechmaker.ejbmodule.entities.Task;

import java.util.List;

@Stateless
public class TaskBean {

    @PersistenceContext
    private EntityManager em;

    public void addTask(Task task) {
        em.persist(task);
    }

    public Task findById(Long id) {
        return em.find(Task.class, id);
    }

    public List<Task> getTasksByTodoList(Long todolistId) {
        return em.createQuery("SELECT t FROM Task t WHERE t.todoList.id = :todolistId", Task.class)
                .setParameter("todolistId", todolistId)
                .getResultList();
    }

    public void updateTask(Task task) {
        em.merge(task);
    }

    public void removeTask(Long id) {
        Task task = findById(id);
        if (task != null) {
            em.remove(task);
        }
    }
}
