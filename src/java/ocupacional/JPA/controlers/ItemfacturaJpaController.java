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
import ocupacional.JPA.valueobjects.Facturas;
import ocupacional.JPA.valueobjects.Itemfactura;

/**
 *
 * @author D4V3
 */
public class ItemfacturaJpaController implements Serializable {

    public ItemfacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itemfactura itemfactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturas factId = itemfactura.getFactId();
            if (factId != null) {
                factId = em.getReference(factId.getClass(), factId.getFactId());
                itemfactura.setFactId(factId);
            }
            em.persist(itemfactura);
            if (factId != null) {
                factId.getItemfacturaList().add(itemfactura);
                factId = em.merge(factId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itemfactura itemfactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itemfactura persistentItemfactura = em.find(Itemfactura.class, itemfactura.getItfaId());
            Facturas factIdOld = persistentItemfactura.getFactId();
            Facturas factIdNew = itemfactura.getFactId();
            if (factIdNew != null) {
                factIdNew = em.getReference(factIdNew.getClass(), factIdNew.getFactId());
                itemfactura.setFactId(factIdNew);
            }
            itemfactura = em.merge(itemfactura);
            if (factIdOld != null && !factIdOld.equals(factIdNew)) {
                factIdOld.getItemfacturaList().remove(itemfactura);
                factIdOld = em.merge(factIdOld);
            }
            if (factIdNew != null && !factIdNew.equals(factIdOld)) {
                factIdNew.getItemfacturaList().add(itemfactura);
                factIdNew = em.merge(factIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itemfactura.getItfaId();
                if (findItemfactura(id) == null) {
                    throw new NonexistentEntityException("The itemfactura with id " + id + " no longer exists.");
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
            Itemfactura itemfactura;
            try {
                itemfactura = em.getReference(Itemfactura.class, id);
                itemfactura.getItfaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itemfactura with id " + id + " no longer exists.", enfe);
            }
            Facturas factId = itemfactura.getFactId();
            if (factId != null) {
                factId.getItemfacturaList().remove(itemfactura);
                factId = em.merge(factId);
            }
            em.remove(itemfactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itemfactura> findItemfacturaEntities() {
        return findItemfacturaEntities(true, -1, -1);
    }

    public List<Itemfactura> findItemfacturaEntities(int maxResults, int firstResult) {
        return findItemfacturaEntities(false, maxResults, firstResult);
    }

    private List<Itemfactura> findItemfacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itemfactura.class));
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

    public Itemfactura findItemfactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itemfactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemfacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itemfactura> rt = cq.from(Itemfactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
