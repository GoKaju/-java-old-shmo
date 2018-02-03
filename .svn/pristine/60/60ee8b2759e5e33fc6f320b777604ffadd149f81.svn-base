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
import ocupacional.JPA.valueobjects.Ticket;
import ocupacional.JPA.valueobjects.ClientesServicio;
import ocupacional.JPA.valueobjects.TicketClienteservicio;

/**
 *
 * @author D4V3
 */
public class TicketClienteservicioJpaController implements Serializable {

    public TicketClienteservicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TicketClienteservicio ticketClienteservicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket tickId = ticketClienteservicio.getTickId();
            if (tickId != null) {
                tickId = em.getReference(tickId.getClass(), tickId.getTickId());
                ticketClienteservicio.setTickId(tickId);
            }
            ClientesServicio clseId = ticketClienteservicio.getClseId();
            if (clseId != null) {
                clseId = em.getReference(clseId.getClass(), clseId.getClseId());
                ticketClienteservicio.setClseId(clseId);
            }
            em.persist(ticketClienteservicio);
            if (tickId != null) {
                tickId.getTicketClienteservicioList().add(ticketClienteservicio);
                tickId = em.merge(tickId);
            }
            if (clseId != null) {
                clseId.getTicketClienteservicioList().add(ticketClienteservicio);
                clseId = em.merge(clseId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TicketClienteservicio ticketClienteservicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TicketClienteservicio persistentTicketClienteservicio = em.find(TicketClienteservicio.class, ticketClienteservicio.getTicsId());
            Ticket tickIdOld = persistentTicketClienteservicio.getTickId();
            Ticket tickIdNew = ticketClienteservicio.getTickId();
            ClientesServicio clseIdOld = persistentTicketClienteservicio.getClseId();
            ClientesServicio clseIdNew = ticketClienteservicio.getClseId();
            if (tickIdNew != null) {
                tickIdNew = em.getReference(tickIdNew.getClass(), tickIdNew.getTickId());
                ticketClienteservicio.setTickId(tickIdNew);
            }
            if (clseIdNew != null) {
                clseIdNew = em.getReference(clseIdNew.getClass(), clseIdNew.getClseId());
                ticketClienteservicio.setClseId(clseIdNew);
            }
            ticketClienteservicio = em.merge(ticketClienteservicio);
            if (tickIdOld != null && !tickIdOld.equals(tickIdNew)) {
                tickIdOld.getTicketClienteservicioList().remove(ticketClienteservicio);
                tickIdOld = em.merge(tickIdOld);
            }
            if (tickIdNew != null && !tickIdNew.equals(tickIdOld)) {
                tickIdNew.getTicketClienteservicioList().add(ticketClienteservicio);
                tickIdNew = em.merge(tickIdNew);
            }
            if (clseIdOld != null && !clseIdOld.equals(clseIdNew)) {
                clseIdOld.getTicketClienteservicioList().remove(ticketClienteservicio);
                clseIdOld = em.merge(clseIdOld);
            }
            if (clseIdNew != null && !clseIdNew.equals(clseIdOld)) {
                clseIdNew.getTicketClienteservicioList().add(ticketClienteservicio);
                clseIdNew = em.merge(clseIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ticketClienteservicio.getTicsId();
                if (findTicketClienteservicio(id) == null) {
                    throw new NonexistentEntityException("The ticketClienteservicio with id " + id + " no longer exists.");
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
            TicketClienteservicio ticketClienteservicio;
            try {
                ticketClienteservicio = em.getReference(TicketClienteservicio.class, id);
                ticketClienteservicio.getTicsId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticketClienteservicio with id " + id + " no longer exists.", enfe);
            }
            Ticket tickId = ticketClienteservicio.getTickId();
            if (tickId != null) {
                tickId.getTicketClienteservicioList().remove(ticketClienteservicio);
                tickId = em.merge(tickId);
            }
            ClientesServicio clseId = ticketClienteservicio.getClseId();
            if (clseId != null) {
                clseId.getTicketClienteservicioList().remove(ticketClienteservicio);
                clseId = em.merge(clseId);
            }
            em.remove(ticketClienteservicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TicketClienteservicio> findTicketClienteservicioEntities() {
        return findTicketClienteservicioEntities(true, -1, -1);
    }

    public List<TicketClienteservicio> findTicketClienteservicioEntities(int maxResults, int firstResult) {
        return findTicketClienteservicioEntities(false, maxResults, firstResult);
    }

    private List<TicketClienteservicio> findTicketClienteservicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TicketClienteservicio.class));
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

    public TicketClienteservicio findTicketClienteservicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TicketClienteservicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketClienteservicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TicketClienteservicio> rt = cq.from(TicketClienteservicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
