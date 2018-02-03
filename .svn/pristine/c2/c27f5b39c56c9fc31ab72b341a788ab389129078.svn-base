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
import ocupacional.JPA.valueobjects.SincroTicket;

/**
 *
 * @author D4V3
 */
public class SincroTicketJpaController implements Serializable {

    public SincroTicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SincroTicket sincroTicket) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sincroTicket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SincroTicket sincroTicket) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sincroTicket = em.merge(sincroTicket);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sincroTicket.getSincId();
                if (findSincroTicket(id) == null) {
                    throw new NonexistentEntityException("The sincroTicket with id " + id + " no longer exists.");
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
            SincroTicket sincroTicket;
            try {
                sincroTicket = em.getReference(SincroTicket.class, id);
                sincroTicket.getSincId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sincroTicket with id " + id + " no longer exists.", enfe);
            }
            em.remove(sincroTicket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SincroTicket> findSincroTicketEntities() {
        return findSincroTicketEntities(true, -1, -1);
    }

    public List<SincroTicket> findSincroTicketEntities(int maxResults, int firstResult) {
        return findSincroTicketEntities(false, maxResults, firstResult);
    }

    private List<SincroTicket> findSincroTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SincroTicket.class));
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

    public SincroTicket findSincroTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SincroTicket.class, id);
        } finally {
            em.close();
        }
    }

    public int getSincroTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SincroTicket> rt = cq.from(SincroTicket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
