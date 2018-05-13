/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.NonexistentEntityException;
import formularios.entidades.ResponsablesPaciente;
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
public class ResponsablesPacienteJpaController implements Serializable {

    public ResponsablesPacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResponsablesPaciente responsablesPaciente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(responsablesPaciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResponsablesPaciente responsablesPaciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            responsablesPaciente = em.merge(responsablesPaciente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = responsablesPaciente.getRepaId();
                if (findResponsablesPaciente(id) == null) {
                    throw new NonexistentEntityException("The responsablesPaciente with id " + id + " no longer exists.");
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
            ResponsablesPaciente responsablesPaciente;
            try {
                responsablesPaciente = em.getReference(ResponsablesPaciente.class, id);
                responsablesPaciente.getRepaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The responsablesPaciente with id " + id + " no longer exists.", enfe);
            }
            em.remove(responsablesPaciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResponsablesPaciente> findResponsablesPacienteEntities() {
        return findResponsablesPacienteEntities(true, -1, -1);
    }

    public List<ResponsablesPaciente> findResponsablesPacienteEntities(int maxResults, int firstResult) {
        return findResponsablesPacienteEntities(false, maxResults, firstResult);
    }

    private List<ResponsablesPaciente> findResponsablesPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResponsablesPaciente.class));
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

    public ResponsablesPaciente findResponsablesPaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResponsablesPaciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getResponsablesPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResponsablesPaciente> rt = cq.from(ResponsablesPaciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
