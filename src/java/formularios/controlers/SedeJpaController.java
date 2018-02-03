/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.IllegalOrphanException;
import formularios.controlers.exceptions.NonexistentEntityException;
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
import ocupacional.JPA.valueobjects.Empleados;
import ocupacional.JPA.valueobjects.Sede;

/**
 *
 * @author Sebas
 */
public class SedeJpaController implements Serializable {

    public SedeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sede sede) {
        if (sede.getTicketList() == null) {
            sede.setTicketList(new ArrayList<Ticket>());
        }
        if (sede.getEmpleadosList() == null) {
            sede.setEmpleadosList(new ArrayList<Empleados>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : sede.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getTickId());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            sede.setTicketList(attachedTicketList);
            List<Empleados> attachedEmpleadosList = new ArrayList<Empleados>();
            for (Empleados empleadosListEmpleadosToAttach : sede.getEmpleadosList()) {
                empleadosListEmpleadosToAttach = em.getReference(empleadosListEmpleadosToAttach.getClass(), empleadosListEmpleadosToAttach.getEmplId());
                attachedEmpleadosList.add(empleadosListEmpleadosToAttach);
            }
            sede.setEmpleadosList(attachedEmpleadosList);
            em.persist(sede);
            for (Ticket ticketListTicket : sede.getTicketList()) {
                Sede oldSedeIdOfTicketListTicket = ticketListTicket.getSedeId();
                ticketListTicket.setSedeId(sede);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldSedeIdOfTicketListTicket != null) {
                    oldSedeIdOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldSedeIdOfTicketListTicket = em.merge(oldSedeIdOfTicketListTicket);
                }
            }
            for (Empleados empleadosListEmpleados : sede.getEmpleadosList()) {
                Sede oldSedeIdOfEmpleadosListEmpleados = empleadosListEmpleados.getSedeId();
                empleadosListEmpleados.setSedeId(sede);
                empleadosListEmpleados = em.merge(empleadosListEmpleados);
                if (oldSedeIdOfEmpleadosListEmpleados != null) {
                    oldSedeIdOfEmpleadosListEmpleados.getEmpleadosList().remove(empleadosListEmpleados);
                    oldSedeIdOfEmpleadosListEmpleados = em.merge(oldSedeIdOfEmpleadosListEmpleados);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sede sede) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede persistentSede = em.find(Sede.class, sede.getSedeId());
            List<Ticket> ticketListOld = persistentSede.getTicketList();
            List<Ticket> ticketListNew = sede.getTicketList();
            List<Empleados> empleadosListOld = persistentSede.getEmpleadosList();
            List<Empleados> empleadosListNew = sede.getEmpleadosList();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its sedeId field is not nullable.");
                }
            }
            for (Empleados empleadosListOldEmpleados : empleadosListOld) {
                if (!empleadosListNew.contains(empleadosListOldEmpleados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleados " + empleadosListOldEmpleados + " since its sedeId field is not nullable.");
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
            sede.setTicketList(ticketListNew);
            List<Empleados> attachedEmpleadosListNew = new ArrayList<Empleados>();
            for (Empleados empleadosListNewEmpleadosToAttach : empleadosListNew) {
                empleadosListNewEmpleadosToAttach = em.getReference(empleadosListNewEmpleadosToAttach.getClass(), empleadosListNewEmpleadosToAttach.getEmplId());
                attachedEmpleadosListNew.add(empleadosListNewEmpleadosToAttach);
            }
            empleadosListNew = attachedEmpleadosListNew;
            sede.setEmpleadosList(empleadosListNew);
            sede = em.merge(sede);
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Sede oldSedeIdOfTicketListNewTicket = ticketListNewTicket.getSedeId();
                    ticketListNewTicket.setSedeId(sede);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldSedeIdOfTicketListNewTicket != null && !oldSedeIdOfTicketListNewTicket.equals(sede)) {
                        oldSedeIdOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldSedeIdOfTicketListNewTicket = em.merge(oldSedeIdOfTicketListNewTicket);
                    }
                }
            }
            for (Empleados empleadosListNewEmpleados : empleadosListNew) {
                if (!empleadosListOld.contains(empleadosListNewEmpleados)) {
                    Sede oldSedeIdOfEmpleadosListNewEmpleados = empleadosListNewEmpleados.getSedeId();
                    empleadosListNewEmpleados.setSedeId(sede);
                    empleadosListNewEmpleados = em.merge(empleadosListNewEmpleados);
                    if (oldSedeIdOfEmpleadosListNewEmpleados != null && !oldSedeIdOfEmpleadosListNewEmpleados.equals(sede)) {
                        oldSedeIdOfEmpleadosListNewEmpleados.getEmpleadosList().remove(empleadosListNewEmpleados);
                        oldSedeIdOfEmpleadosListNewEmpleados = em.merge(oldSedeIdOfEmpleadosListNewEmpleados);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sede.getSedeId();
                if (findSede(id) == null) {
                    throw new NonexistentEntityException("The sede with id " + id + " no longer exists.");
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
            Sede sede;
            try {
                sede = em.getReference(Sede.class, id);
                sede.getSedeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sede with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ticket> ticketListOrphanCheck = sede.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sede (" + sede + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable sedeId field.");
            }
            List<Empleados> empleadosListOrphanCheck = sede.getEmpleadosList();
            for (Empleados empleadosListOrphanCheckEmpleados : empleadosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sede (" + sede + ") cannot be destroyed since the Empleados " + empleadosListOrphanCheckEmpleados + " in its empleadosList field has a non-nullable sedeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sede);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sede> findSedeEntities() {
        return findSedeEntities(true, -1, -1);
    }

    public List<Sede> findSedeEntities(int maxResults, int firstResult) {
        return findSedeEntities(false, maxResults, firstResult);
    }

    private List<Sede> findSedeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sede.class));
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

    public Sede findSede(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sede.class, id);
        } finally {
            em.close();
        }
    }

    public int getSedeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sede> rt = cq.from(Sede.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
