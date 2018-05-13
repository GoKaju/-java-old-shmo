/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.NonexistentEntityException;
import formularios.entidades.TipoVinculacionEps;
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
 * @author Usuario
 */
public class TipoVinculacionEpsJpaController implements Serializable {

    public TipoVinculacionEpsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoVinculacionEps tipoVinculacionEps) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoVinculacionEps);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoVinculacionEps tipoVinculacionEps) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoVinculacionEps = em.merge(tipoVinculacionEps);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoVinculacionEps.getTvepId();
                if (findTipoVinculacionEps(id) == null) {
                    throw new NonexistentEntityException("The tipoVinculacionEps with id " + id + " no longer exists.");
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
            TipoVinculacionEps tipoVinculacionEps;
            try {
                tipoVinculacionEps = em.getReference(TipoVinculacionEps.class, id);
                tipoVinculacionEps.getTvepId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoVinculacionEps with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoVinculacionEps);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoVinculacionEps> findTipoVinculacionEpsEntities() {
        return findTipoVinculacionEpsEntities(true, -1, -1);
    }

    public List<TipoVinculacionEps> findTipoVinculacionEpsEntities(int maxResults, int firstResult) {
        return findTipoVinculacionEpsEntities(false, maxResults, firstResult);
    }

    private List<TipoVinculacionEps> findTipoVinculacionEpsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoVinculacionEps.class));
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

    public TipoVinculacionEps findTipoVinculacionEps(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoVinculacionEps.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoVinculacionEpsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoVinculacionEps> rt = cq.from(TipoVinculacionEps.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
