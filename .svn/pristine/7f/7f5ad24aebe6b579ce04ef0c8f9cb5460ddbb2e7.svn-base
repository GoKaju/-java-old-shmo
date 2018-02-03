/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.NonexistentEntityException;
import formularios.entidades.SincroPacientes;
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
public class SincroPacientesJpaController implements Serializable {

    public SincroPacientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SincroPacientes sincroPacientes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sincroPacientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SincroPacientes sincroPacientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sincroPacientes = em.merge(sincroPacientes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sincroPacientes.getSipaId();
                if (findSincroPacientes(id) == null) {
                    throw new NonexistentEntityException("The sincroPacientes with id " + id + " no longer exists.");
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
            SincroPacientes sincroPacientes;
            try {
                sincroPacientes = em.getReference(SincroPacientes.class, id);
                sincroPacientes.getSipaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sincroPacientes with id " + id + " no longer exists.", enfe);
            }
            em.remove(sincroPacientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SincroPacientes> findSincroPacientesEntities() {
        return findSincroPacientesEntities(true, -1, -1);
    }

    public List<SincroPacientes> findSincroPacientesEntities(int maxResults, int firstResult) {
        return findSincroPacientesEntities(false, maxResults, firstResult);
    }

    private List<SincroPacientes> findSincroPacientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SincroPacientes.class));
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

    public SincroPacientes findSincroPacientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SincroPacientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getSincroPacientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SincroPacientes> rt = cq.from(SincroPacientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
