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
import ocupacional.JPA.valueobjects.Parametrofactura;

/**
 *
 * @author D4V3
 */
public class ParametrofacturaJpaController implements Serializable {

    public ParametrofacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Parametrofactura parametrofactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(parametrofactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Parametrofactura parametrofactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            parametrofactura = em.merge(parametrofactura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parametrofactura.getPafaId();
                if (findParametrofactura(id) == null) {
                    throw new NonexistentEntityException("The parametrofactura with id " + id + " no longer exists.");
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
            Parametrofactura parametrofactura;
            try {
                parametrofactura = em.getReference(Parametrofactura.class, id);
                parametrofactura.getPafaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parametrofactura with id " + id + " no longer exists.", enfe);
            }
            em.remove(parametrofactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Parametrofactura> findParametrofacturaEntities() {
        return findParametrofacturaEntities(true, -1, -1);
    }

    public List<Parametrofactura> findParametrofacturaEntities(int maxResults, int firstResult) {
        return findParametrofacturaEntities(false, maxResults, firstResult);
    }

    private List<Parametrofactura> findParametrofacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Parametrofactura.class));
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

    public Parametrofactura findParametrofactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Parametrofactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getParametrofacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Parametrofactura> rt = cq.from(Parametrofactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
