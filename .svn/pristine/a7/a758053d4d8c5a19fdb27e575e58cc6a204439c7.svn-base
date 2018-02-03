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
import ocupacional.JPA.valueobjects.Examenes;
import ocupacional.JPA.valueobjects.Servicios;
import ocupacional.JPA.valueobjects.ServiciosExamenes;

/**
 *
 * @author D4V3
 */
public class ServiciosExamenesJpaController implements Serializable {

    public ServiciosExamenesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiciosExamenes serviciosExamenes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Examenes examId = serviciosExamenes.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getExamId());
                serviciosExamenes.setExamId(examId);
            }
            Servicios servId = serviciosExamenes.getServId();
            if (servId != null) {
                servId = em.getReference(servId.getClass(), servId.getServId());
                serviciosExamenes.setServId(servId);
            }
            em.persist(serviciosExamenes);
            if (examId != null) {
                examId.getServiciosExamenesList().add(serviciosExamenes);
                examId = em.merge(examId);
            }
            if (servId != null) {
                servId.getServiciosExamenesList().add(serviciosExamenes);
                servId = em.merge(servId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiciosExamenes serviciosExamenes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiciosExamenes persistentServiciosExamenes = em.find(ServiciosExamenes.class, serviciosExamenes.getSeexId());
            Examenes examIdOld = persistentServiciosExamenes.getExamId();
            Examenes examIdNew = serviciosExamenes.getExamId();
            Servicios servIdOld = persistentServiciosExamenes.getServId();
            Servicios servIdNew = serviciosExamenes.getServId();
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getExamId());
                serviciosExamenes.setExamId(examIdNew);
            }
            if (servIdNew != null) {
                servIdNew = em.getReference(servIdNew.getClass(), servIdNew.getServId());
                serviciosExamenes.setServId(servIdNew);
            }
            serviciosExamenes = em.merge(serviciosExamenes);
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getServiciosExamenesList().remove(serviciosExamenes);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getServiciosExamenesList().add(serviciosExamenes);
                examIdNew = em.merge(examIdNew);
            }
            if (servIdOld != null && !servIdOld.equals(servIdNew)) {
                servIdOld.getServiciosExamenesList().remove(serviciosExamenes);
                servIdOld = em.merge(servIdOld);
            }
            if (servIdNew != null && !servIdNew.equals(servIdOld)) {
                servIdNew.getServiciosExamenesList().add(serviciosExamenes);
                servIdNew = em.merge(servIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = serviciosExamenes.getSeexId();
                if (findServiciosExamenes(id) == null) {
                    throw new NonexistentEntityException("The serviciosExamenes with id " + id + " no longer exists.");
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
            ServiciosExamenes serviciosExamenes;
            try {
                serviciosExamenes = em.getReference(ServiciosExamenes.class, id);
                serviciosExamenes.getSeexId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviciosExamenes with id " + id + " no longer exists.", enfe);
            }
            Examenes examId = serviciosExamenes.getExamId();
            if (examId != null) {
                examId.getServiciosExamenesList().remove(serviciosExamenes);
                examId = em.merge(examId);
            }
            Servicios servId = serviciosExamenes.getServId();
            if (servId != null) {
                servId.getServiciosExamenesList().remove(serviciosExamenes);
                servId = em.merge(servId);
            }
            em.remove(serviciosExamenes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiciosExamenes> findServiciosExamenesEntities() {
        return findServiciosExamenesEntities(true, -1, -1);
    }

    public List<ServiciosExamenes> findServiciosExamenesEntities(int maxResults, int firstResult) {
        return findServiciosExamenesEntities(false, maxResults, firstResult);
    }

    private List<ServiciosExamenes> findServiciosExamenesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiciosExamenes.class));
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

    public ServiciosExamenes findServiciosExamenes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiciosExamenes.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosExamenesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiciosExamenes> rt = cq.from(ServiciosExamenes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
