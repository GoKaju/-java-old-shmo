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
import formularios.entidades.Campos;
import formularios.entidades.Opciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author D4V3
 */
public class OpcionesJpaController implements Serializable {

    public OpcionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opciones opciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Campos campId = opciones.getCampId();
            if (campId != null) {
                campId = em.getReference(campId.getClass(), campId.getCampId());
                opciones.setCampId(campId);
            }
            em.persist(opciones);
            if (campId != null) {
                campId.getOpcionesList().add(opciones);
                campId = em.merge(campId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opciones opciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opciones persistentOpciones = em.find(Opciones.class, opciones.getOpciId());
            Campos campIdOld = persistentOpciones.getCampId();
            Campos campIdNew = opciones.getCampId();
            if (campIdNew != null) {
                campIdNew = em.getReference(campIdNew.getClass(), campIdNew.getCampId());
                opciones.setCampId(campIdNew);
            }
            opciones = em.merge(opciones);
            if (campIdOld != null && !campIdOld.equals(campIdNew)) {
                campIdOld.getOpcionesList().remove(opciones);
                campIdOld = em.merge(campIdOld);
            }
            if (campIdNew != null && !campIdNew.equals(campIdOld)) {
                campIdNew.getOpcionesList().add(opciones);
                campIdNew = em.merge(campIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opciones.getOpciId();
                if (findOpciones(id) == null) {
                    throw new NonexistentEntityException("The opciones with id " + id + " no longer exists.");
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
            Opciones opciones;
            try {
                opciones = em.getReference(Opciones.class, id);
                opciones.getOpciId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opciones with id " + id + " no longer exists.", enfe);
            }
            Campos campId = opciones.getCampId();
            if (campId != null) {
                campId.getOpcionesList().remove(opciones);
                campId = em.merge(campId);
            }
            em.remove(opciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opciones> findOpcionesEntities() {
        return findOpcionesEntities(true, -1, -1);
    }

    public List<Opciones> findOpcionesEntities(int maxResults, int firstResult) {
        return findOpcionesEntities(false, maxResults, firstResult);
    }

    private List<Opciones> findOpcionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opciones.class));
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

    public Opciones findOpciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpcionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opciones> rt = cq.from(Opciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
