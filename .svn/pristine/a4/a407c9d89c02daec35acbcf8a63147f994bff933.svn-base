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
import ocupacional.JPA.valueobjects.Proveedores;
import ocupacional.JPA.valueobjects.Examenes;
import ocupacional.JPA.valueobjects.ProveedoresExamenes;

/**
 *
 * @author D4V3
 */
public class ProveedoresExamenesJpaController implements Serializable {

    public ProveedoresExamenesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProveedoresExamenes proveedoresExamenes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores provId = proveedoresExamenes.getProvId();
            if (provId != null) {
                provId = em.getReference(provId.getClass(), provId.getProvId());
                proveedoresExamenes.setProvId(provId);
            }
            Examenes examId = proveedoresExamenes.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getExamId());
                proveedoresExamenes.setExamId(examId);
            }
            em.persist(proveedoresExamenes);
            if (provId != null) {
                provId.getProveedoresExamenesList().add(proveedoresExamenes);
                provId = em.merge(provId);
            }
            if (examId != null) {
                examId.getProveedoresExamenesList().add(proveedoresExamenes);
                examId = em.merge(examId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProveedoresExamenes proveedoresExamenes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProveedoresExamenes persistentProveedoresExamenes = em.find(ProveedoresExamenes.class, proveedoresExamenes.getPrexId());
            Proveedores provIdOld = persistentProveedoresExamenes.getProvId();
            Proveedores provIdNew = proveedoresExamenes.getProvId();
            Examenes examIdOld = persistentProveedoresExamenes.getExamId();
            Examenes examIdNew = proveedoresExamenes.getExamId();
            if (provIdNew != null) {
                provIdNew = em.getReference(provIdNew.getClass(), provIdNew.getProvId());
                proveedoresExamenes.setProvId(provIdNew);
            }
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getExamId());
                proveedoresExamenes.setExamId(examIdNew);
            }
            proveedoresExamenes = em.merge(proveedoresExamenes);
            if (provIdOld != null && !provIdOld.equals(provIdNew)) {
                provIdOld.getProveedoresExamenesList().remove(proveedoresExamenes);
                provIdOld = em.merge(provIdOld);
            }
            if (provIdNew != null && !provIdNew.equals(provIdOld)) {
                provIdNew.getProveedoresExamenesList().add(proveedoresExamenes);
                provIdNew = em.merge(provIdNew);
            }
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getProveedoresExamenesList().remove(proveedoresExamenes);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getProveedoresExamenesList().add(proveedoresExamenes);
                examIdNew = em.merge(examIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedoresExamenes.getPrexId();
                if (findProveedoresExamenes(id) == null) {
                    throw new NonexistentEntityException("The proveedoresExamenes with id " + id + " no longer exists.");
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
            ProveedoresExamenes proveedoresExamenes;
            try {
                proveedoresExamenes = em.getReference(ProveedoresExamenes.class, id);
                proveedoresExamenes.getPrexId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedoresExamenes with id " + id + " no longer exists.", enfe);
            }
            Proveedores provId = proveedoresExamenes.getProvId();
            if (provId != null) {
                provId.getProveedoresExamenesList().remove(proveedoresExamenes);
                provId = em.merge(provId);
            }
            Examenes examId = proveedoresExamenes.getExamId();
            if (examId != null) {
                examId.getProveedoresExamenesList().remove(proveedoresExamenes);
                examId = em.merge(examId);
            }
            em.remove(proveedoresExamenes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProveedoresExamenes> findProveedoresExamenesEntities() {
        return findProveedoresExamenesEntities(true, -1, -1);
    }

    public List<ProveedoresExamenes> findProveedoresExamenesEntities(int maxResults, int firstResult) {
        return findProveedoresExamenesEntities(false, maxResults, firstResult);
    }

    private List<ProveedoresExamenes> findProveedoresExamenesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProveedoresExamenes.class));
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

    public ProveedoresExamenes findProveedoresExamenes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProveedoresExamenes.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedoresExamenesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProveedoresExamenes> rt = cq.from(ProveedoresExamenes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
