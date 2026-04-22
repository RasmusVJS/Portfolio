package app.daos;

import app.config.HibernateConfig;
import app.entities.Deck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.*;

public class DeckDAO {

    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    public Deck create(Deck deck){
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {
                em.persist(deck);
                em.getTransaction().commit();
                return deck;
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            }
        }
    }

    public Deck update(Deck deck) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {Deck updated = em.merge(deck);
                em.getTransaction().commit();
                return updated;
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            }
        }
    }

    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {
                Deck deck = em.find(Deck.class, id);
                if (deck != null) em.remove(deck);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            }
        }
    }

    public List<Deck> getAll(){
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT e FROM " + Deck.class.getSimpleName() + " e", Deck.class).getResultList();
        }
    }

    public Deck getById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Deck.class, id);
        }
    }


}
