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
import ocupacional.JPA.valueobjects.Servicios;
import ocupacional.JPA.valueobjects.TicketClienteservicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.IllegalOrphanException;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.ClientesServicio;

/**
 *
 * @author D4V3
 */
public class ClientesServicioJpaController implements Serializable {

    public ClientesServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClientesServicio clientesServicio) {
        if (clientesServicio.getTicketClienteservicioList() == null) {
            clientesServicio.setTicketClienteservicioList(new ArrayList<TicketClienteservicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clieId = clientesServicio.getClieId();
            if (clieId != null) {
                clieId = em.getReference(clieId.getClass(), clieId.getClieId());
                clientesServicio.setClieId(clieId);
            }
            Servicios servId = clientesServicio.getServId();
            if (servId != null) {
                servId = em.getReference(servId.getClass(), servId.getServId());
                clientesServicio.setServId(servId);
            }
            List<TicketClienteservicio> attachedTicketClienteservicioList = new ArrayList<TicketClienteservicio>();
            for (TicketClienteservicio ticketClienteservicioListTicketClienteservicioToAttach : clientesServicio.getTicketClienteservicioList()) {
                ticketClienteservicioListTicketClienteservicioToAttach = em.getReference(ticketClienteservicioListTicketClienteservicioToAttach.getClass(), ticketClienteservicioListTicketClienteservicioToAttach.getTicsId());
                attachedTicketClienteservicioList.add(ticketClienteservicioListTicketClienteservicioToAttach);
            }
            clientesServicio.setTicketClienteservicioList(attachedTicketClienteservicioList);
            em.persist(clientesServicio);
            if (clieId != null) {
                clieId.getClientesServicioList().add(clientesServicio);
                clieId = em.merge(clieId);
            }
            if (servId != null) {
                servId.getClientesServicioList().add(clientesServicio);
                servId = em.merge(servId);
            }
            for (TicketClienteservicio ticketClienteservicioListTicketClienteservicio : clientesServicio.getTicketClienteservicioList()) {
                ClientesServicio oldClseIdOfTicketClienteservicioListTicketClienteservicio = ticketClienteservicioListTicketClienteservicio.getClseId();
                ticketClienteservicioListTicketClienteservicio.setClseId(clientesServicio);
                ticketClienteservicioListTicketClienteservicio = em.merge(ticketClienteservicioListTicketClienteservicio);
                if (oldClseIdOfTicketClienteservicioListTicketClienteservicio != null) {
                    oldClseIdOfTicketClienteservicioListTicketClienteservicio.getTicketClienteservicioList().remove(ticketClienteservicioListTicketClienteservicio);
                    oldClseIdOfTicketClienteservicioListTicketClienteservicio = em.merge(oldClseIdOfTicketClienteservicioListTicketClienteservicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClientesServicio clientesServicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClientesServicio persistentClientesServicio = em.find(ClientesServicio.class, clientesServicio.getClseId());
            Clientes clieIdOld = persistentClientesServicio.getClieId();
            Clientes clieIdNew = clientesServicio.getClieId();
            Servicios servIdOld = persistentClientesServicio.getServId();
            Servicios servIdNew = clientesServicio.getServId();
            List<TicketClienteservicio> ticketClienteservicioListOld = persistentClientesServicio.getTicketClienteservicioList();
            List<TicketClienteservicio> ticketClienteservicioListNew = clientesServicio.getTicketClienteservicioList();
            List<String> illegalOrphanMessages = null;
            for (TicketClienteservicio ticketClienteservicioListOldTicketClienteservicio : ticketClienteservicioListOld) {
                if (!ticketClienteservicioListNew.contains(ticketClienteservicioListOldTicketClienteservicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TicketClienteservicio " + ticketClienteservicioListOldTicketClienteservicio + " since its clseId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clieIdNew != null) {
                clieIdNew = em.getReference(clieIdNew.getClass(), clieIdNew.getClieId());
                clientesServicio.setClieId(clieIdNew);
            }
            if (servIdNew != null) {
                servIdNew = em.getReference(servIdNew.getClass(), servIdNew.getServId());
                clientesServicio.setServId(servIdNew);
            }
            List<TicketClienteservicio> attachedTicketClienteservicioListNew = new ArrayList<TicketClienteservicio>();
            for (TicketClienteservicio ticketClienteservicioListNewTicketClienteservicioToAttach : ticketClienteservicioListNew) {
                ticketClienteservicioListNewTicketClienteservicioToAttach = em.getReference(ticketClienteservicioListNewTicketClienteservicioToAttach.getClass(), ticketClienteservicioListNewTicketClienteservicioToAttach.getTicsId());
                attachedTicketClienteservicioListNew.add(ticketClienteservicioListNewTicketClienteservicioToAttach);
            }
            ticketClienteservicioListNew = attachedTicketClienteservicioListNew;
            clientesServicio.setTicketClienteservicioList(ticketClienteservicioListNew);
            clientesServicio = em.merge(clientesServicio);
            if (clieIdOld != null && !clieIdOld.equals(clieIdNew)) {
                clieIdOld.getClientesServicioList().remove(clientesServicio);
                clieIdOld = em.merge(clieIdOld);
            }
            if (clieIdNew != null && !clieIdNew.equals(clieIdOld)) {
                clieIdNew.getClientesServicioList().add(clientesServicio);
                clieIdNew = em.merge(clieIdNew);
            }
            if (servIdOld != null && !servIdOld.equals(servIdNew)) {
                servIdOld.getClientesServicioList().remove(clientesServicio);
                servIdOld = em.merge(servIdOld);
            }
            if (servIdNew != null && !servIdNew.equals(servIdOld)) {
                servIdNew.getClientesServicioList().add(clientesServicio);
                servIdNew = em.merge(servIdNew);
            }
            for (TicketClienteservicio ticketClienteservicioListNewTicketClienteservicio : ticketClienteservicioListNew) {
                if (!ticketClienteservicioListOld.contains(ticketClienteservicioListNewTicketClienteservicio)) {
                    ClientesServicio oldClseIdOfTicketClienteservicioListNewTicketClienteservicio = ticketClienteservicioListNewTicketClienteservicio.getClseId();
                    ticketClienteservicioListNewTicketClienteservicio.setClseId(clientesServicio);
                    ticketClienteservicioListNewTicketClienteservicio = em.merge(ticketClienteservicioListNewTicketClienteservicio);
                    if (oldClseIdOfTicketClienteservicioListNewTicketClienteservicio != null && !oldClseIdOfTicketClienteservicioListNewTicketClienteservicio.equals(clientesServicio)) {
                        oldClseIdOfTicketClienteservicioListNewTicketClienteservicio.getTicketClienteservicioList().remove(ticketClienteservicioListNewTicketClienteservicio);
                        oldClseIdOfTicketClienteservicioListNewTicketClienteservicio = em.merge(oldClseIdOfTicketClienteservicioListNewTicketClienteservicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clientesServicio.getClseId();
                if (findClientesServicio(id) == null) {
                    throw new NonexistentEntityException("The clientesServicio with id " + id + " no longer exists.");
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
            ClientesServicio clientesServicio;
            try {
                clientesServicio = em.getReference(ClientesServicio.class, id);
                clientesServicio.getClseId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientesServicio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TicketClienteservicio> ticketClienteservicioListOrphanCheck = clientesServicio.getTicketClienteservicioList();
            for (TicketClienteservicio ticketClienteservicioListOrphanCheckTicketClienteservicio : ticketClienteservicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ClientesServicio (" + clientesServicio + ") cannot be destroyed since the TicketClienteservicio " + ticketClienteservicioListOrphanCheckTicketClienteservicio + " in its ticketClienteservicioList field has a non-nullable clseId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clientes clieId = clientesServicio.getClieId();
            if (clieId != null) {
                clieId.getClientesServicioList().remove(clientesServicio);
                clieId = em.merge(clieId);
            }
            Servicios servId = clientesServicio.getServId();
            if (servId != null) {
                servId.getClientesServicioList().remove(clientesServicio);
                servId = em.merge(servId);
            }
            em.remove(clientesServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ClientesServicio> findClientesServicioEntities() {
        return findClientesServicioEntities(true, -1, -1);
    }

    public List<ClientesServicio> findClientesServicioEntities(int maxResults, int firstResult) {
        return findClientesServicioEntities(false, maxResults, firstResult);
    }

    private List<ClientesServicio> findClientesServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClientesServicio.class));
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

    public ClientesServicio findClientesServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClientesServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientesServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClientesServicio> rt = cq.from(ClientesServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
