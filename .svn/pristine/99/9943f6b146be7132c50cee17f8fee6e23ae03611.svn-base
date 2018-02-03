/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.controlers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.Personageneral;

/**
 *
 * @author D4V3
 */
public class PersonageneralJpaController implements Serializable {

    public PersonageneralJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personageneral personageneral) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(personageneral);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personageneral personageneral) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            personageneral = em.merge(personageneral);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personageneral.getPegeId();
                if (findPersonageneral(id) == null) {
                    throw new NonexistentEntityException("The personageneral with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personageneral personageneral;
            try {
                personageneral = em.getReference(Personageneral.class, id);
                personageneral.getPegeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personageneral with id " + id + " no longer exists.", enfe);
            }
            em.remove(personageneral);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personageneral> findPersonageneralEntities() {
        return findPersonageneralEntities(true, -1, -1);
    }

    public List<Personageneral> findPersonageneralEntities(int maxResults, int firstResult) {
        return findPersonageneralEntities(false, maxResults, firstResult);
    }

    private List<Personageneral> findPersonageneralEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personageneral.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Personageneral findPersonageneral(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personageneral.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonageneralCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personageneral> rt = cq.from(Personageneral.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
