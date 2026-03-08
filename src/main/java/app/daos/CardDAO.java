package app.daos;

import app.config.HibernateConfig;
import app.entities.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.*;

public class CardDAO {

    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    public Card create(Card card){
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {
                em.persist(card);
                em.getTransaction().commit();
                return card;
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            }
        }
    }

    public Card update(Card card) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {Card updated = em.merge(card);
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
                Card card = em.find(Card.class, id);
                if (card != null) em.remove(card);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            }
        }
    }

    public List<Card> getAll(){
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT e FROM " + Card.class.getSimpleName() + " e", Card.class).getResultList();
        }
    }

    public Card getById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Card.class, id);
        }
    }


}
