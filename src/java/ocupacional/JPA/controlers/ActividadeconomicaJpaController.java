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
import ocupacional.JPA.valueobjects.Actividadeconomica;

/**
 *
 * @author D4V3
 */
public class ActividadeconomicaJpaController implements Serializable {

    public ActividadeconomicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividadeconomica actividadeconomica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(actividadeconomica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividadeconomica actividadeconomica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            actividadeconomica = em.merge(actividadeconomica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actividadeconomica.getAcecId();
                if (findActividadeconomica(id) == null) {
                    throw new NonexistentEntityException("The actividadeconomica with id " + id + " no longer exists.");
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
            Actividadeconomica actividadeconomica;
            try {
                actividadeconomica = em.getReference(Actividadeconomica.class, id);
                actividadeconomica.getAcecId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadeconomica with id " + id + " no longer exists.", enfe);
            }
            em.remove(actividadeconomica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividadeconomica> findActividadeconomicaEntities() {
        return findActividadeconomicaEntities(true, -1, -1);
    }

    public List<Actividadeconomica> findActividadeconomicaEntities(int maxResults, int firstResult) {
        return findActividadeconomicaEntities(false, maxResults, firstResult);
    }

    private List<Actividadeconomica> findActividadeconomicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividadeconomica.class));
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

    public Actividadeconomica findActividadeconomica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividadeconomica.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadeconomicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividadeconomica> rt = cq.from(Actividadeconomica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
