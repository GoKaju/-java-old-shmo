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
import ocupacional.JPA.valueobjects.Ticket;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.IllegalOrphanException;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.TipoexamenMedico;

/**
 *
 * @author D4V3
 */
public class TipoexamenMedicoJpaController implements Serializable {

    public TipoexamenMedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoexamenMedico tipoexamenMedico) {
        if (tipoexamenMedico.getTicketList() == null) {
            tipoexamenMedico.setTicketList(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : tipoexamenMedico.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getTickId());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            tipoexamenMedico.setTicketList(attachedTicketList);
            em.persist(tipoexamenMedico);
            for (Ticket ticketListTicket : tipoexamenMedico.getTicketList()) {
                TipoexamenMedico oldTemeIdOfTicketListTicket = ticketListTicket.getTemeId();
                ticketListTicket.setTemeId(tipoexamenMedico);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldTemeIdOfTicketListTicket != null) {
                    oldTemeIdOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldTemeIdOfTicketListTicket = em.merge(oldTemeIdOfTicketListTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoexamenMedico tipoexamenMedico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoexamenMedico persistentTipoexamenMedico = em.find(TipoexamenMedico.class, tipoexamenMedico.getTemeId());
            List<Ticket> ticketListOld = persistentTipoexamenMedico.getTicketList();
            List<Ticket> ticketListNew = tipoexamenMedico.getTicketList();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its temeId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getTickId());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            tipoexamenMedico.setTicketList(ticketListNew);
            tipoexamenMedico = em.merge(tipoexamenMedico);
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    TipoexamenMedico oldTemeIdOfTicketListNewTicket = ticketListNewTicket.getTemeId();
                    ticketListNewTicket.setTemeId(tipoexamenMedico);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldTemeIdOfTicketListNewTicket != null && !oldTemeIdOfTicketListNewTicket.equals(tipoexamenMedico)) {
                        oldTemeIdOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldTemeIdOfTicketListNewTicket = em.merge(oldTemeIdOfTicketListNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoexamenMedico.getTemeId();
                if (findTipoexamenMedico(id) == null) {
                    throw new NonexistentEntityException("The tipoexamenMedico with id " + id + " no longer exists.");
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
            TipoexamenMedico tipoexamenMedico;
            try {
                tipoexamenMedico = em.getReference(TipoexamenMedico.class, id);
                tipoexamenMedico.getTemeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoexamenMedico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ticket> ticketListOrphanCheck = tipoexamenMedico.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoexamenMedico (" + tipoexamenMedico + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable temeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoexamenMedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoexamenMedico> findTipoexamenMedicoEntities() {
        return findTipoexamenMedicoEntities(true, -1, -1);
    }

    public List<TipoexamenMedico> findTipoexamenMedicoEntities(int maxResults, int firstResult) {
        return findTipoexamenMedicoEntities(false, maxResults, firstResult);
    }

    private List<TipoexamenMedico> findTipoexamenMedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoexamenMedico.class));
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

    public TipoexamenMedico findTipoexamenMedico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoexamenMedico.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoexamenMedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoexamenMedico> rt = cq.from(TipoexamenMedico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
