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
import ocupacional.JPA.valueobjects.Clientes;
import ocupacional.JPA.valueobjects.Facturas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.IllegalOrphanException;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.Centrocostos;
import ocupacional.JPA.valueobjects.Ticket;

/**
 *
 * @author D4V3
 */
public class CentrocostosJpaController implements Serializable {

    public CentrocostosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Centrocostos centrocostos) {
        if (centrocostos.getFacturasList() == null) {
            centrocostos.setFacturasList(new ArrayList<Facturas>());
        }
        if (centrocostos.getTicketList() == null) {
            centrocostos.setTicketList(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clieId = centrocostos.getClieId();
            if (clieId != null) {
                clieId = em.getReference(clieId.getClass(), clieId.getClieId());
                centrocostos.setClieId(clieId);
            }
            List<Facturas> attachedFacturasList = new ArrayList<Facturas>();
            for (Facturas facturasListFacturasToAttach : centrocostos.getFacturasList()) {
                facturasListFacturasToAttach = em.getReference(facturasListFacturasToAttach.getClass(), facturasListFacturasToAttach.getFactId());
                attachedFacturasList.add(facturasListFacturasToAttach);
            }
            centrocostos.setFacturasList(attachedFacturasList);
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : centrocostos.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getTickId());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            centrocostos.setTicketList(attachedTicketList);
            em.persist(centrocostos);
            if (clieId != null) {
                clieId.getCentrocostosList().add(centrocostos);
                clieId = em.merge(clieId);
            }
            for (Facturas facturasListFacturas : centrocostos.getFacturasList()) {
                Centrocostos oldCecoIdOfFacturasListFacturas = facturasListFacturas.getCecoId();
                facturasListFacturas.setCecoId(centrocostos);
                facturasListFacturas = em.merge(facturasListFacturas);
                if (oldCecoIdOfFacturasListFacturas != null) {
                    oldCecoIdOfFacturasListFacturas.getFacturasList().remove(facturasListFacturas);
                    oldCecoIdOfFacturasListFacturas = em.merge(oldCecoIdOfFacturasListFacturas);
                }
            }
            for (Ticket ticketListTicket : centrocostos.getTicketList()) {
                Centrocostos oldCecoIdOfTicketListTicket = ticketListTicket.getCecoId();
                ticketListTicket.setCecoId(centrocostos);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldCecoIdOfTicketListTicket != null) {
                    oldCecoIdOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldCecoIdOfTicketListTicket = em.merge(oldCecoIdOfTicketListTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Centrocostos centrocostos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centrocostos persistentCentrocostos = em.find(Centrocostos.class, centrocostos.getCecoId());
            Clientes clieIdOld = persistentCentrocostos.getClieId();
            Clientes clieIdNew = centrocostos.getClieId();
            List<Facturas> facturasListOld = persistentCentrocostos.getFacturasList();
            List<Facturas> facturasListNew = centrocostos.getFacturasList();
            List<Ticket> ticketListOld = persistentCentrocostos.getTicketList();
            List<Ticket> ticketListNew = centrocostos.getTicketList();
            List<String> illegalOrphanMessages = null;
            for (Facturas facturasListOldFacturas : facturasListOld) {
                if (!facturasListNew.contains(facturasListOldFacturas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facturas " + facturasListOldFacturas + " since its cecoId field is not nullable.");
                }
            }
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its cecoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clieIdNew != null) {
                clieIdNew = em.getReference(clieIdNew.getClass(), clieIdNew.getClieId());
                centrocostos.setClieId(clieIdNew);
            }
            List<Facturas> attachedFacturasListNew = new ArrayList<Facturas>();
            for (Facturas facturasListNewFacturasToAttach : facturasListNew) {
                facturasListNewFacturasToAttach = em.getReference(facturasListNewFacturasToAttach.getClass(), facturasListNewFacturasToAttach.getFactId());
                attachedFacturasListNew.add(facturasListNewFacturasToAttach);
            }
            facturasListNew = attachedFacturasListNew;
            centrocostos.setFacturasList(facturasListNew);
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getTickId());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            centrocostos.setTicketList(ticketListNew);
            centrocostos = em.merge(centrocostos);
            if (clieIdOld != null && !clieIdOld.equals(clieIdNew)) {
                clieIdOld.getCentrocostosList().remove(centrocostos);
                clieIdOld = em.merge(clieIdOld);
            }
            if (clieIdNew != null && !clieIdNew.equals(clieIdOld)) {
                clieIdNew.getCentrocostosList().add(centrocostos);
                clieIdNew = em.merge(clieIdNew);
            }
            for (Facturas facturasListNewFacturas : facturasListNew) {
                if (!facturasListOld.contains(facturasListNewFacturas)) {
                    Centrocostos oldCecoIdOfFacturasListNewFacturas = facturasListNewFacturas.getCecoId();
                    facturasListNewFacturas.setCecoId(centrocostos);
                    facturasListNewFacturas = em.merge(facturasListNewFacturas);
                    if (oldCecoIdOfFacturasListNewFacturas != null && !oldCecoIdOfFacturasListNewFacturas.equals(centrocostos)) {
                        oldCecoIdOfFacturasListNewFacturas.getFacturasList().remove(facturasListNewFacturas);
                        oldCecoIdOfFacturasListNewFacturas = em.merge(oldCecoIdOfFacturasListNewFacturas);
                    }
                }
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Centrocostos oldCecoIdOfTicketListNewTicket = ticketListNewTicket.getCecoId();
                    ticketListNewTicket.setCecoId(centrocostos);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldCecoIdOfTicketListNewTicket != null && !oldCecoIdOfTicketListNewTicket.equals(centrocostos)) {
                        oldCecoIdOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldCecoIdOfTicketListNewTicket = em.merge(oldCecoIdOfTicketListNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = centrocostos.getCecoId();
                if (findCentrocostos(id) == null) {
                    throw new NonexistentEntityException("The centrocostos with id " + id + " no longer exists.");
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
            Centrocostos centrocostos;
            try {
                centrocostos = em.getReference(Centrocostos.class, id);
                centrocostos.getCecoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The centrocostos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Facturas> facturasListOrphanCheck = centrocostos.getFacturasList();
            for (Facturas facturasListOrphanCheckFacturas : facturasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Centrocostos (" + centrocostos + ") cannot be destroyed since the Facturas " + facturasListOrphanCheckFacturas + " in its facturasList field has a non-nullable cecoId field.");
            }
            List<Ticket> ticketListOrphanCheck = centrocostos.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Centrocostos (" + centrocostos + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable cecoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clientes clieId = centrocostos.getClieId();
            if (clieId != null) {
                clieId.getCentrocostosList().remove(centrocostos);
                clieId = em.merge(clieId);
            }
            em.remove(centrocostos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Centrocostos> findCentrocostosEntities() {
        return findCentrocostosEntities(true, -1, -1);
    }

    public List<Centrocostos> findCentrocostosEntities(int maxResults, int firstResult) {
        return findCentrocostosEntities(false, maxResults, firstResult);
    }

    private List<Centrocostos> findCentrocostosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Centrocostos.class));
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

    public Centrocostos findCentrocostos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Centrocostos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCentrocostosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Centrocostos> rt = cq.from(Centrocostos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
