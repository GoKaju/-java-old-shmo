/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.controlers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ocupacional.JPA.valueobjects.Sede;
import ocupacional.JPA.valueobjects.Centrocostos;
import ocupacional.JPA.valueobjects.TipoexamenMedico;
import ocupacional.JPA.valueobjects.Movimientosfacturacion;
import ocupacional.JPA.valueobjects.TicketClienteservicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.IllegalOrphanException;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.Ticket;

/**
 *
 * @author D4V3
 */
public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) {
        if (ticket.getTicketClienteservicioList() == null) {
            ticket.setTicketClienteservicioList(new ArrayList<TicketClienteservicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede sedeId = ticket.getSedeId();
            if (sedeId != null) {
                sedeId = em.getReference(sedeId.getClass(), sedeId.getSedeId());
                ticket.setSedeId(sedeId);
            }
            Centrocostos cecoId = ticket.getCecoId();
            if (cecoId != null) {
                cecoId = em.getReference(cecoId.getClass(), cecoId.getCecoId());
                ticket.setCecoId(cecoId);
            }
            TipoexamenMedico temeId = ticket.getTemeId();
            if (temeId != null) {
                temeId = em.getReference(temeId.getClass(), temeId.getTemeId());
                ticket.setTemeId(temeId);
            }
            Movimientosfacturacion mofoId = ticket.getMofoId();
            if (mofoId != null) {
                mofoId = em.getReference(mofoId.getClass(), mofoId.getMofaId());
                ticket.setMofoId(mofoId);
            }
            List<TicketClienteservicio> attachedTicketClienteservicioList = new ArrayList<TicketClienteservicio>();
            for (TicketClienteservicio ticketClienteservicioListTicketClienteservicioToAttach : ticket.getTicketClienteservicioList()) {
                ticketClienteservicioListTicketClienteservicioToAttach = em.getReference(ticketClienteservicioListTicketClienteservicioToAttach.getClass(), ticketClienteservicioListTicketClienteservicioToAttach.getTicsId());
                attachedTicketClienteservicioList.add(ticketClienteservicioListTicketClienteservicioToAttach);
            }
            ticket.setTicketClienteservicioList(attachedTicketClienteservicioList);
            em.persist(ticket);
            if (sedeId != null) {
                sedeId.getTicketList().add(ticket);
                sedeId = em.merge(sedeId);
            }
            if (cecoId != null) {
                cecoId.getTicketList().add(ticket);
                cecoId = em.merge(cecoId);
            }
            if (temeId != null) {
                temeId.getTicketList().add(ticket);
                temeId = em.merge(temeId);
            }
            if (mofoId != null) {
                mofoId.getTicketList().add(ticket);
                mofoId = em.merge(mofoId);
            }
            for (TicketClienteservicio ticketClienteservicioListTicketClienteservicio : ticket.getTicketClienteservicioList()) {
                Ticket oldTickIdOfTicketClienteservicioListTicketClienteservicio = ticketClienteservicioListTicketClienteservicio.getTickId();
                ticketClienteservicioListTicketClienteservicio.setTickId(ticket);
                ticketClienteservicioListTicketClienteservicio = em.merge(ticketClienteservicioListTicketClienteservicio);
                if (oldTickIdOfTicketClienteservicioListTicketClienteservicio != null) {
                    oldTickIdOfTicketClienteservicioListTicketClienteservicio.getTicketClienteservicioList().remove(ticketClienteservicioListTicketClienteservicio);
                    oldTickIdOfTicketClienteservicioListTicketClienteservicio = em.merge(oldTickIdOfTicketClienteservicioListTicketClienteservicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket persistentTicket = em.find(Ticket.class, ticket.getTickId());
            Sede sedeIdOld = persistentTicket.getSedeId();
            Sede sedeIdNew = ticket.getSedeId();
            Centrocostos cecoIdOld = persistentTicket.getCecoId();
            Centrocostos cecoIdNew = ticket.getCecoId();
            TipoexamenMedico temeIdOld = persistentTicket.getTemeId();
            TipoexamenMedico temeIdNew = ticket.getTemeId();
            Movimientosfacturacion mofoIdOld = persistentTicket.getMofoId();
            Movimientosfacturacion mofoIdNew = ticket.getMofoId();
            List<TicketClienteservicio> ticketClienteservicioListOld = persistentTicket.getTicketClienteservicioList();
            List<TicketClienteservicio> ticketClienteservicioListNew = ticket.getTicketClienteservicioList();
            List<String> illegalOrphanMessages = null;
            for (TicketClienteservicio ticketClienteservicioListOldTicketClienteservicio : ticketClienteservicioListOld) {
                if (!ticketClienteservicioListNew.contains(ticketClienteservicioListOldTicketClienteservicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TicketClienteservicio " + ticketClienteservicioListOldTicketClienteservicio + " since its tickId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (sedeIdNew != null) {
                sedeIdNew = em.getReference(sedeIdNew.getClass(), sedeIdNew.getSedeId());
                ticket.setSedeId(sedeIdNew);
            }
            if (cecoIdNew != null) {
                cecoIdNew = em.getReference(cecoIdNew.getClass(), cecoIdNew.getCecoId());
                ticket.setCecoId(cecoIdNew);
            }
            if (temeIdNew != null) {
                temeIdNew = em.getReference(temeIdNew.getClass(), temeIdNew.getTemeId());
                ticket.setTemeId(temeIdNew);
            }
            if (mofoIdNew != null) {
                mofoIdNew = em.getReference(mofoIdNew.getClass(), mofoIdNew.getMofaId());
                ticket.setMofoId(mofoIdNew);
            }
            List<TicketClienteservicio> attachedTicketClienteservicioListNew = new ArrayList<TicketClienteservicio>();
            for (TicketClienteservicio ticketClienteservicioListNewTicketClienteservicioToAttach : ticketClienteservicioListNew) {
                ticketClienteservicioListNewTicketClienteservicioToAttach = em.getReference(ticketClienteservicioListNewTicketClienteservicioToAttach.getClass(), ticketClienteservicioListNewTicketClienteservicioToAttach.getTicsId());
                attachedTicketClienteservicioListNew.add(ticketClienteservicioListNewTicketClienteservicioToAttach);
            }
            ticketClienteservicioListNew = attachedTicketClienteservicioListNew;
            ticket.setTicketClienteservicioList(ticketClienteservicioListNew);
            ticket = em.merge(ticket);
            if (sedeIdOld != null && !sedeIdOld.equals(sedeIdNew)) {
                sedeIdOld.getTicketList().remove(ticket);
                sedeIdOld = em.merge(sedeIdOld);
            }
            if (sedeIdNew != null && !sedeIdNew.equals(sedeIdOld)) {
                sedeIdNew.getTicketList().add(ticket);
                sedeIdNew = em.merge(sedeIdNew);
            }
            if (cecoIdOld != null && !cecoIdOld.equals(cecoIdNew)) {
                cecoIdOld.getTicketList().remove(ticket);
                cecoIdOld = em.merge(cecoIdOld);
            }
            if (cecoIdNew != null && !cecoIdNew.equals(cecoIdOld)) {
                cecoIdNew.getTicketList().add(ticket);
                cecoIdNew = em.merge(cecoIdNew);
            }
            if (temeIdOld != null && !temeIdOld.equals(temeIdNew)) {
                temeIdOld.getTicketList().remove(ticket);
                temeIdOld = em.merge(temeIdOld);
            }
            if (temeIdNew != null && !temeIdNew.equals(temeIdOld)) {
                temeIdNew.getTicketList().add(ticket);
                temeIdNew = em.merge(temeIdNew);
            }
            if (mofoIdOld != null && !mofoIdOld.equals(mofoIdNew)) {
                mofoIdOld.getTicketList().remove(ticket);
                mofoIdOld = em.merge(mofoIdOld);
            }
            if (mofoIdNew != null && !mofoIdNew.equals(mofoIdOld)) {
                mofoIdNew.getTicketList().add(ticket);
                mofoIdNew = em.merge(mofoIdNew);
            }
            for (TicketClienteservicio ticketClienteservicioListNewTicketClienteservicio : ticketClienteservicioListNew) {
                if (!ticketClienteservicioListOld.contains(ticketClienteservicioListNewTicketClienteservicio)) {
                    Ticket oldTickIdOfTicketClienteservicioListNewTicketClienteservicio = ticketClienteservicioListNewTicketClienteservicio.getTickId();
                    ticketClienteservicioListNewTicketClienteservicio.setTickId(ticket);
                    ticketClienteservicioListNewTicketClienteservicio = em.merge(ticketClienteservicioListNewTicketClienteservicio);
                    if (oldTickIdOfTicketClienteservicioListNewTicketClienteservicio != null && !oldTickIdOfTicketClienteservicioListNewTicketClienteservicio.equals(ticket)) {
                        oldTickIdOfTicketClienteservicioListNewTicketClienteservicio.getTicketClienteservicioList().remove(ticketClienteservicioListNewTicketClienteservicio);
                        oldTickIdOfTicketClienteservicioListNewTicketClienteservicio = em.merge(oldTickIdOfTicketClienteservicioListNewTicketClienteservicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ticket.getTickId();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getTickId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TicketClienteservicio> ticketClienteservicioListOrphanCheck = ticket.getTicketClienteservicioList();
            for (TicketClienteservicio ticketClienteservicioListOrphanCheckTicketClienteservicio : ticketClienteservicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ticket (" + ticket + ") cannot be destroyed since the TicketClienteservicio " + ticketClienteservicioListOrphanCheckTicketClienteservicio + " in its ticketClienteservicioList field has a non-nullable tickId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Sede sedeId = ticket.getSedeId();
            if (sedeId != null) {
                sedeId.getTicketList().remove(ticket);
                sedeId = em.merge(sedeId);
            }
            Centrocostos cecoId = ticket.getCecoId();
            if (cecoId != null) {
                cecoId.getTicketList().remove(ticket);
                cecoId = em.merge(cecoId);
            }
            TipoexamenMedico temeId = ticket.getTemeId();
            if (temeId != null) {
                temeId.getTicketList().remove(ticket);
                temeId = em.merge(temeId);
            }
            Movimientosfacturacion mofoId = ticket.getMofoId();
            if (mofoId != null) {
                mofoId.getTicketList().remove(ticket);
                mofoId = em.merge(mofoId);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ticket.class));
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

    public Ticket findTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ticket> rt = cq.from(Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
