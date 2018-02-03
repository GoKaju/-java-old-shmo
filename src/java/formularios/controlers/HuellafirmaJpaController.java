/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import formularios.entidades.Anotaciones;
import formularios.entidades.Huellafirma;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author D4V3
 */
public class HuellafirmaJpaController implements Serializable {

    public HuellafirmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Huellafirma huellafirma) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Anotaciones anotId = huellafirma.getAnotId();
            if (anotId != null) {
                anotId = em.getReference(anotId.getClass(), anotId.getAnotId());
                huellafirma.setAnotId(anotId);
            }
            em.persist(huellafirma);
            if (anotId != null) {
                anotId.getHuellafirmaList().add(huellafirma);
                anotId = em.merge(anotId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Huellafirma huellafirma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huellafirma persistentHuellafirma = em.find(Huellafirma.class, huellafirma.getHufiId());
            Anotaciones anotIdOld = persistentHuellafirma.getAnotId();
            Anotaciones anotIdNew = huellafirma.getAnotId();
            if (anotIdNew != null) {
                anotIdNew = em.getReference(anotIdNew.getClass(), anotIdNew.getAnotId());
                huellafirma.setAnotId(anotIdNew);
            }
            huellafirma = em.merge(huellafirma);
            if (anotIdOld != null && !anotIdOld.equals(anotIdNew)) {
                anotIdOld.getHuellafirmaList().remove(huellafirma);
                anotIdOld = em.merge(anotIdOld);
            }
            if (anotIdNew != null && !anotIdNew.equals(anotIdOld)) {
                anotIdNew.getHuellafirmaList().add(huellafirma);
                anotIdNew = em.merge(anotIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = huellafirma.getHufiId();
                if (findHuellafirma(id) == null) {
                    throw new NonexistentEntityException("The huellafirma with id " + id + " no longer exists.");
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
            Huellafirma huellafirma;
            try {
                huellafirma = em.getReference(Huellafirma.class, id);
                huellafirma.getHufiId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The huellafirma with id " + id + " no longer exists.", enfe);
            }
            Anotaciones anotId = huellafirma.getAnotId();
            if (anotId != null) {
                anotId.getHuellafirmaList().remove(huellafirma);
                anotId = em.merge(anotId);
            }
            em.remove(huellafirma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Huellafirma> findHuellafirmaEntities() {
        return findHuellafirmaEntities(true, -1, -1);
    }

    public List<Huellafirma> findHuellafirmaEntities(int maxResults, int firstResult) {
        return findHuellafirmaEntities(false, maxResults, firstResult);
    }

    private List<Huellafirma> findHuellafirmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Huellafirma.class));
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

    public Huellafirma findHuellafirma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Huellafirma.class, id);
        } finally {
            em.close();
        }
    }

    public int getHuellafirmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Huellafirma> rt = cq.from(Huellafirma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
