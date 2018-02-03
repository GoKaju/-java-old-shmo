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
import ocupacional.JPA.controlers.exceptions.PreexistingEntityException;
import ocupacional.JPA.valueobjects.Naturales;

/**
 *
 * @author D4V3
 */
public class NaturalesJpaController implements Serializable {

    public NaturalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Naturales naturales) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(naturales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNaturales(naturales.getPegeId()) != null) {
                throw new PreexistingEntityException("Naturales " + naturales + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Naturales naturales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            naturales = em.merge(naturales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = naturales.getPegeId();
                if (findNaturales(id) == null) {
                    throw new NonexistentEntityException("The naturales with id " + id + " no longer exists.");
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
            Naturales naturales;
            try {
                naturales = em.getReference(Naturales.class, id);
                naturales.getPegeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The naturales with id " + id + " no longer exists.", enfe);
            }
            em.remove(naturales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Naturales> findNaturalesEntities() {
        return findNaturalesEntities(true, -1, -1);
    }

    public List<Naturales> findNaturalesEntities(int maxResults, int firstResult) {
        return findNaturalesEntities(false, maxResults, firstResult);
    }

    private List<Naturales> findNaturalesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Naturales.class));
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

    public Naturales findNaturales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Naturales.class, id);
        } finally {
            em.close();
        }
    }

    public int getNaturalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Naturales> rt = cq.from(Naturales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
