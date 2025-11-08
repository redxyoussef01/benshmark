package ma.ws.jaxrs.benchmarkAa.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import ma.ws.jaxrs.benchmarkAa.entities.Category;
import ma.ws.jaxrs.benchmarkAa.util.JPAUtil;

public class CategoryDAO {
    private EntityManager em = JPAUtil.getEntityManager();

    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }
    public Category find(Long id) { return em.find(Category.class, id); }
    public void create(Category c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    public void update(Category c) {
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }
    public void delete(Long id) {
        em.getTransaction().begin();
        Category c = em.find(Category.class, id);
        if (c != null) em.remove(c);
        em.getTransaction().commit();
    }
}
