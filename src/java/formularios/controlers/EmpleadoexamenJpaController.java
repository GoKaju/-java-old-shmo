/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ocupacional.JPA.valueobjects.Empleadoexamen;
import ocupacional.JPA.valueobjects.Examenes;

/**
 *
 * @author Sebas
 */
public class EmpleadoexamenJpaController implements Serializable {

    public EmpleadoexamenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleadoexamen empleadoexamen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Examenes examId = empleadoexamen.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getExamId());
                empleadoexamen.setExamId(examId);
            }
            em.persist(empleadoexamen);
            if (examId != null) {
                examId.getEmpleadoexamenList().add(empleadoexamen);
                examId = em.merge(examId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleadoexamen empleadoexamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleadoexamen persistentEmpleadoexamen = em.find(Empleadoexamen.class, empleadoexamen.getEmexId());
            Examenes examIdOld = persistentEmpleadoexamen.getExamId();
            Examenes examIdNew = empleadoexamen.getExamId();
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getExamId());
                empleadoexamen.setExamId(examIdNew);
            }
            empleadoexamen = em.merge(empleadoexamen);
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getEmpleadoexamenList().remove(empleadoexamen);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getEmpleadoexamenList().add(empleadoexamen);
                examIdNew = em.merge(examIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleadoexamen.getEmexId();
                if (findEmpleadoexamen(id) == null) {
                    throw new NonexistentEntityException("The empleadoexamen with id " + id + " no longer exists.");
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
            Empleadoexamen empleadoexamen;
            try {
                empleadoexamen = em.getReference(Empleadoexamen.class, id);
                empleadoexamen.getEmexId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleadoexamen with id " + id + " no longer exists.", enfe);
            }
            Examenes examId = empleadoexamen.getExamId();
            if (examId != null) {
                examId.getEmpleadoexamenList().remove(empleadoexamen);
                examId = em.merge(examId);
            }
            em.remove(empleadoexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleadoexamen> findEmpleadoexamenEntities() {
        return findEmpleadoexamenEntities(true, -1, -1);
    }

    public List<Empleadoexamen> findEmpleadoexamenEntities(int maxResults, int firstResult) {
        return findEmpleadoexamenEntities(false, maxResults, firstResult);
    }

    private List<Empleadoexamen> findEmpleadoexamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleadoexamen.class));
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

    public Empleadoexamen findEmpleadoexamen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleadoexamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoexamenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleadoexamen> rt = cq.from(Empleadoexamen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
