package sn.jgotechmaker.ejbmodule.entities;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class TestEntityBean {

    @PersistenceContext
    private EntityManager em;

    public void saveTestEntity(String name) {
        TestEntity entity = new TestEntity(name);
        em.persist(entity);
    }

    public List<TestEntity> getAllTestEntities() {
        return em.createQuery("SELECT t FROM TestEntity t", TestEntity.class).getResultList();
    }
}
