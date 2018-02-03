/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.NonexistentEntityException;
import formularios.controlers.exceptions.PreexistingEntityException;
import formularios.entidades.Cie;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author D4V3
 */
public class CieJpaController implements Serializable {

    public CieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cie cie) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cie);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCie(cie.getCieId()) != null) {
                throw new PreexistingEntityException("Cie " + cie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cie cie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cie = em.merge(cie);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cie.getCieId();
                if (findCie(id) == null) {
                    throw new NonexistentEntityException("The cie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cie cie;
            try {
                cie = em.getReference(Cie.class, id);
                cie.getCieId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cie with id " + id + " no longer exists.", enfe);
            }
            em.remove(cie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cie> findCieEntities() {
        return findCieEntities(true, -1, -1);
    }

    public List<Cie> findCieEntities(int maxResults, int firstResult) {
        return findCieEntities(false, maxResults, firstResult);
    }

    private List<Cie> findCieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cie.class));
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

    public Cie findCie(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cie.class, id);
        } finally {
            em.close();
        }
    }

    public int getCieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cie> rt = cq.from(Cie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
