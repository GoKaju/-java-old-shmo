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
import ocupacional.JPA.valueobjects.Empleados;
import ocupacional.JPA.valueobjects.Tipodocumento;
import ocupacional.JPA.valueobjects.Sede;

/**
 *
 * @author Sebas
 */
public class EmpleadosJpaController implements Serializable {

    public EmpleadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleados empleados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipodocumento tidoId = empleados.getTidoId();
            if (tidoId != null) {
                tidoId = em.getReference(tidoId.getClass(), tidoId.getTidoId());
                empleados.setTidoId(tidoId);
            }
            Sede sedeId = empleados.getSedeId();
            if (sedeId != null) {
                sedeId = em.getReference(sedeId.getClass(), sedeId.getSedeId());
                empleados.setSedeId(sedeId);
            }
            em.persist(empleados);
            if (tidoId != null) {
                tidoId.getEmpleadosList().add(empleados);
                tidoId = em.merge(tidoId);
            }
            if (sedeId != null) {
                sedeId.getEmpleadosList().add(empleados);
                sedeId = em.merge(sedeId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleados empleados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados persistentEmpleados = em.find(Empleados.class, empleados.getEmplId());
            Tipodocumento tidoIdOld = persistentEmpleados.getTidoId();
            Tipodocumento tidoIdNew = empleados.getTidoId();
            Sede sedeIdOld = persistentEmpleados.getSedeId();
            Sede sedeIdNew = empleados.getSedeId();
            if (tidoIdNew != null) {
                tidoIdNew = em.getReference(tidoIdNew.getClass(), tidoIdNew.getTidoId());
                empleados.setTidoId(tidoIdNew);
            }
            if (sedeIdNew != null) {
                sedeIdNew = em.getReference(sedeIdNew.getClass(), sedeIdNew.getSedeId());
                empleados.setSedeId(sedeIdNew);
            }
            empleados = em.merge(empleados);
            if (tidoIdOld != null && !tidoIdOld.equals(tidoIdNew)) {
                tidoIdOld.getEmpleadosList().remove(empleados);
                tidoIdOld = em.merge(tidoIdOld);
            }
            if (tidoIdNew != null && !tidoIdNew.equals(tidoIdOld)) {
                tidoIdNew.getEmpleadosList().add(empleados);
                tidoIdNew = em.merge(tidoIdNew);
            }
            if (sedeIdOld != null && !sedeIdOld.equals(sedeIdNew)) {
                sedeIdOld.getEmpleadosList().remove(empleados);
                sedeIdOld = em.merge(sedeIdOld);
            }
            if (sedeIdNew != null && !sedeIdNew.equals(sedeIdOld)) {
                sedeIdNew.getEmpleadosList().add(empleados);
                sedeIdNew = em.merge(sedeIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleados.getEmplId();
                if (findEmpleados(id) == null) {
                    throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.");
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
            Empleados empleados;
            try {
                empleados = em.getReference(Empleados.class, id);
                empleados.getEmplId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.", enfe);
            }
            Tipodocumento tidoId = empleados.getTidoId();
            if (tidoId != null) {
                tidoId.getEmpleadosList().remove(empleados);
                tidoId = em.merge(tidoId);
            }
            Sede sedeId = empleados.getSedeId();
            if (sedeId != null) {
                sedeId.getEmpleadosList().remove(empleados);
                sedeId = em.merge(sedeId);
            }
            em.remove(empleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleados> findEmpleadosEntities() {
        return findEmpleadosEntities(true, -1, -1);
    }

    public List<Empleados> findEmpleadosEntities(int maxResults, int firstResult) {
        return findEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<Empleados> findEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleados.class));
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

    public Empleados findEmpleados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleados> rt = cq.from(Empleados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
