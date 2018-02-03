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
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.Facturas;
import ocupacional.JPA.valueobjects.Movimientosfacturacion;

/**
 *
 * @author D4V3
 */
public class MovimientosfacturacionJpaController implements Serializable {

    public MovimientosfacturacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimientosfacturacion movimientosfacturacion) {
        if (movimientosfacturacion.getTicketList() == null) {
            movimientosfacturacion.setTicketList(new ArrayList<Ticket>());
        }
        if (movimientosfacturacion.getFacturasList() == null) {
            movimientosfacturacion.setFacturasList(new ArrayList<Facturas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : movimientosfacturacion.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getTickId());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            movimientosfacturacion.setTicketList(attachedTicketList);
            List<Facturas> attachedFacturasList = new ArrayList<Facturas>();
            for (Facturas facturasListFacturasToAttach : movimientosfacturacion.getFacturasList()) {
                facturasListFacturasToAttach = em.getReference(facturasListFacturasToAttach.getClass(), facturasListFacturasToAttach.getFactId());
                attachedFacturasList.add(facturasListFacturasToAttach);
            }
            movimientosfacturacion.setFacturasList(attachedFacturasList);
            em.persist(movimientosfacturacion);
            for (Ticket ticketListTicket : movimientosfacturacion.getTicketList()) {
                Movimientosfacturacion oldMofoIdOfTicketListTicket = ticketListTicket.getMofoId();
                ticketListTicket.setMofoId(movimientosfacturacion);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldMofoIdOfTicketListTicket != null) {
                    oldMofoIdOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldMofoIdOfTicketListTicket = em.merge(oldMofoIdOfTicketListTicket);
                }
            }
            for (Facturas facturasListFacturas : movimientosfacturacion.getFacturasList()) {
                Movimientosfacturacion oldMofaIdOfFacturasListFacturas = facturasListFacturas.getMofaId();
                facturasListFacturas.setMofaId(movimientosfacturacion);
                facturasListFacturas = em.merge(facturasListFacturas);
                if (oldMofaIdOfFacturasListFacturas != null) {
                    oldMofaIdOfFacturasListFacturas.getFacturasList().remove(facturasListFacturas);
                    oldMofaIdOfFacturasListFacturas = em.merge(oldMofaIdOfFacturasListFacturas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimientosfacturacion movimientosfacturacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimientosfacturacion persistentMovimientosfacturacion = em.find(Movimientosfacturacion.class, movimientosfacturacion.getMofaId());
            List<Ticket> ticketListOld = persistentMovimientosfacturacion.getTicketList();
            List<Ticket> ticketListNew = movimientosfacturacion.getTicketList();
            List<Facturas> facturasListOld = persistentMovimientosfacturacion.getFacturasList();
            List<Facturas> facturasListNew = movimientosfacturacion.getFacturasList();
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getTickId());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            movimientosfacturacion.setTicketList(ticketListNew);
            List<Facturas> attachedFacturasListNew = new ArrayList<Facturas>();
            for (Facturas facturasListNewFacturasToAttach : facturasListNew) {
                facturasListNewFacturasToAttach = em.getReference(facturasListNewFacturasToAttach.getClass(), facturasListNewFacturasToAttach.getFactId());
                attachedFacturasListNew.add(facturasListNewFacturasToAttach);
            }
            facturasListNew = attachedFacturasListNew;
            movimientosfacturacion.setFacturasList(facturasListNew);
            movimientosfacturacion = em.merge(movimientosfacturacion);
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    ticketListOldTicket.setMofoId(null);
                    ticketListOldTicket = em.merge(ticketListOldTicket);
                }
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Movimientosfacturacion oldMofoIdOfTicketListNewTicket = ticketListNewTicket.getMofoId();
                    ticketListNewTicket.setMofoId(movimientosfacturacion);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldMofoIdOfTicketListNewTicket != null && !oldMofoIdOfTicketListNewTicket.equals(movimientosfacturacion)) {
                        oldMofoIdOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldMofoIdOfTicketListNewTicket = em.merge(oldMofoIdOfTicketListNewTicket);
                    }
                }
            }
            for (Facturas facturasListOldFacturas : facturasListOld) {
                if (!facturasListNew.contains(facturasListOldFacturas)) {
                    facturasListOldFacturas.setMofaId(null);
                    facturasListOldFacturas = em.merge(facturasListOldFacturas);
                }
            }
            for (Facturas facturasListNewFacturas : facturasListNew) {
                if (!facturasListOld.contains(facturasListNewFacturas)) {
                    Movimientosfacturacion oldMofaIdOfFacturasListNewFacturas = facturasListNewFacturas.getMofaId();
                    facturasListNewFacturas.setMofaId(movimientosfacturacion);
                    facturasListNewFacturas = em.merge(facturasListNewFacturas);
                    if (oldMofaIdOfFacturasListNewFacturas != null && !oldMofaIdOfFacturasListNewFacturas.equals(movimientosfacturacion)) {
                        oldMofaIdOfFacturasListNewFacturas.getFacturasList().remove(facturasListNewFacturas);
                        oldMofaIdOfFacturasListNewFacturas = em.merge(oldMofaIdOfFacturasListNewFacturas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movimientosfacturacion.getMofaId();
                if (findMovimientosfacturacion(id) == null) {
                    throw new NonexistentEntityException("The movimientosfacturacion with id " + id + " no longer exists.");
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
            Movimientosfacturacion movimientosfacturacion;
            try {
                movimientosfacturacion = em.getReference(Movimientosfacturacion.class, id);
                movimientosfacturacion.getMofaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimientosfacturacion with id " + id + " no longer exists.", enfe);
            }
            List<Ticket> ticketList = movimientosfacturacion.getTicketList();
            for (Ticket ticketListTicket : ticketList) {
                ticketListTicket.setMofoId(null);
                ticketListTicket = em.merge(ticketListTicket);
            }
            List<Facturas> facturasList = movimientosfacturacion.getFacturasList();
            for (Facturas facturasListFacturas : facturasList) {
                facturasListFacturas.setMofaId(null);
                facturasListFacturas = em.merge(facturasListFacturas);
            }
            em.remove(movimientosfacturacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimientosfacturacion> findMovimientosfacturacionEntities() {
        return findMovimientosfacturacionEntities(true, -1, -1);
    }

    public List<Movimientosfacturacion> findMovimientosfacturacionEntities(int maxResults, int firstResult) {
        return findMovimientosfacturacionEntities(false, maxResults, firstResult);
    }

    private List<Movimientosfacturacion> findMovimientosfacturacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimientosfacturacion.class));
//            cq.orderBy();
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

    public Movimientosfacturacion findMovimientosfacturacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimientosfacturacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientosfacturacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimientosfacturacion> rt = cq.from(Movimientosfacturacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
