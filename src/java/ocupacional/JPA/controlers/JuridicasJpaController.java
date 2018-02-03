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
import ocupacional.JPA.valueobjects.Juridicas;

/**
 *
 * @author D4V3
 */
public class JuridicasJpaController implements Serializable {

    public JuridicasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Juridicas juridicas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(juridicas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJuridicas(juridicas.getPegeId()) != null) {
                throw new PreexistingEntityException("Juridicas " + juridicas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Juridicas juridicas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            juridicas = em.merge(juridicas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = juridicas.getPegeId();
                if (findJuridicas(id) == null) {
                    throw new NonexistentEntityException("The juridicas with id " + id + " no longer exists.");
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
            Juridicas juridicas;
            try {
                juridicas = em.getReference(Juridicas.class, id);
                juridicas.getPegeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The juridicas with id " + id + " no longer exists.", enfe);
            }
            em.remove(juridicas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Juridicas> findJuridicasEntities() {
        return findJuridicasEntities(true, -1, -1);
    }

    public List<Juridicas> findJuridicasEntities(int maxResults, int firstResult) {
        return findJuridicasEntities(false, maxResults, firstResult);
    }

    private List<Juridicas> findJuridicasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Juridicas.class));
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

    public Juridicas findJuridicas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Juridicas.class, id);
        } finally {
            em.close();
        }
    }

    public int getJuridicasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Juridicas> rt = cq.from(Juridicas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
