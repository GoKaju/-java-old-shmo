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
import ocupacional.JPA.valueobjects.Tipodocumento;
import ocupacional.JPA.valueobjects.Ciudades;
import ocupacional.JPA.valueobjects.ClientesServicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.IllegalOrphanException;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.Centrocostos;
import ocupacional.JPA.valueobjects.Clientes;

/**
 *
 * @author D4V3
 */
public class ClientesJpaController implements Serializable {

    public ClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientes clientes) {
        if (clientes.getClientesServicioList() == null) {
            clientes.setClientesServicioList(new ArrayList<ClientesServicio>());
        }
        if (clientes.getCentrocostosList() == null) {
            clientes.setCentrocostosList(new ArrayList<Centrocostos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipodocumento tidoId = clientes.getTidoId();
            if (tidoId != null) {
                tidoId = em.getReference(tidoId.getClass(), tidoId.getTidoId());
                clientes.setTidoId(tidoId);
            }
            Ciudades ciudId = clientes.getCiudId();
            if (ciudId != null) {
                ciudId = em.getReference(ciudId.getClass(), ciudId.getCiudId());
                clientes.setCiudId(ciudId);
            }
            List<ClientesServicio> attachedClientesServicioList = new ArrayList<ClientesServicio>();
            for (ClientesServicio clientesServicioListClientesServicioToAttach : clientes.getClientesServicioList()) {
                clientesServicioListClientesServicioToAttach = em.getReference(clientesServicioListClientesServicioToAttach.getClass(), clientesServicioListClientesServicioToAttach.getClseId());
                attachedClientesServicioList.add(clientesServicioListClientesServicioToAttach);
            }
            clientes.setClientesServicioList(attachedClientesServicioList);
            List<Centrocostos> attachedCentrocostosList = new ArrayList<Centrocostos>();
            for (Centrocostos centrocostosListCentrocostosToAttach : clientes.getCentrocostosList()) {
                centrocostosListCentrocostosToAttach = em.getReference(centrocostosListCentrocostosToAttach.getClass(), centrocostosListCentrocostosToAttach.getCecoId());
                attachedCentrocostosList.add(centrocostosListCentrocostosToAttach);
            }
            clientes.setCentrocostosList(attachedCentrocostosList);
            em.persist(clientes);
            if (tidoId != null) {
                tidoId.getClientesList().add(clientes);
                tidoId = em.merge(tidoId);
            }
            if (ciudId != null) {
                ciudId.getClientesList().add(clientes);
                ciudId = em.merge(ciudId);
            }
            for (ClientesServicio clientesServicioListClientesServicio : clientes.getClientesServicioList()) {
                Clientes oldClieIdOfClientesServicioListClientesServicio = clientesServicioListClientesServicio.getClieId();
                clientesServicioListClientesServicio.setClieId(clientes);
                clientesServicioListClientesServicio = em.merge(clientesServicioListClientesServicio);
                if (oldClieIdOfClientesServicioListClientesServicio != null) {
                    oldClieIdOfClientesServicioListClientesServicio.getClientesServicioList().remove(clientesServicioListClientesServicio);
                    oldClieIdOfClientesServicioListClientesServicio = em.merge(oldClieIdOfClientesServicioListClientesServicio);
                }
            }
            for (Centrocostos centrocostosListCentrocostos : clientes.getCentrocostosList()) {
                Clientes oldClieIdOfCentrocostosListCentrocostos = centrocostosListCentrocostos.getClieId();
                centrocostosListCentrocostos.setClieId(clientes);
                centrocostosListCentrocostos = em.merge(centrocostosListCentrocostos);
                if (oldClieIdOfCentrocostosListCentrocostos != null) {
                    oldClieIdOfCentrocostosListCentrocostos.getCentrocostosList().remove(centrocostosListCentrocostos);
                    oldClieIdOfCentrocostosListCentrocostos = em.merge(oldClieIdOfCentrocostosListCentrocostos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientes clientes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes persistentClientes = em.find(Clientes.class, clientes.getClieId());
            Tipodocumento tidoIdOld = persistentClientes.getTidoId();
            Tipodocumento tidoIdNew = clientes.getTidoId();
            Ciudades ciudIdOld = persistentClientes.getCiudId();
            Ciudades ciudIdNew = clientes.getCiudId();
            List<ClientesServicio> clientesServicioListOld = persistentClientes.getClientesServicioList();
            List<ClientesServicio> clientesServicioListNew = clientes.getClientesServicioList();
            List<Centrocostos> centrocostosListOld = persistentClientes.getCentrocostosList();
            List<Centrocostos> centrocostosListNew = clientes.getCentrocostosList();
            List<String> illegalOrphanMessages = null;
            for (ClientesServicio clientesServicioListOldClientesServicio : clientesServicioListOld) {
                if (!clientesServicioListNew.contains(clientesServicioListOldClientesServicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClientesServicio " + clientesServicioListOldClientesServicio + " since its clieId field is not nullable.");
                }
            }
            for (Centrocostos centrocostosListOldCentrocostos : centrocostosListOld) {
                if (!centrocostosListNew.contains(centrocostosListOldCentrocostos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Centrocostos " + centrocostosListOldCentrocostos + " since its clieId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tidoIdNew != null) {
                tidoIdNew = em.getReference(tidoIdNew.getClass(), tidoIdNew.getTidoId());
                clientes.setTidoId(tidoIdNew);
            }
            if (ciudIdNew != null) {
                ciudIdNew = em.getReference(ciudIdNew.getClass(), ciudIdNew.getCiudId());
                clientes.setCiudId(ciudIdNew);
            }
            List<ClientesServicio> attachedClientesServicioListNew = new ArrayList<ClientesServicio>();
            for (ClientesServicio clientesServicioListNewClientesServicioToAttach : clientesServicioListNew) {
                clientesServicioListNewClientesServicioToAttach = em.getReference(clientesServicioListNewClientesServicioToAttach.getClass(), clientesServicioListNewClientesServicioToAttach.getClseId());
                attachedClientesServicioListNew.add(clientesServicioListNewClientesServicioToAttach);
            }
            clientesServicioListNew = attachedClientesServicioListNew;
            clientes.setClientesServicioList(clientesServicioListNew);
            List<Centrocostos> attachedCentrocostosListNew = new ArrayList<Centrocostos>();
            for (Centrocostos centrocostosListNewCentrocostosToAttach : centrocostosListNew) {
                centrocostosListNewCentrocostosToAttach = em.getReference(centrocostosListNewCentrocostosToAttach.getClass(), centrocostosListNewCentrocostosToAttach.getCecoId());
                attachedCentrocostosListNew.add(centrocostosListNewCentrocostosToAttach);
            }
            centrocostosListNew = attachedCentrocostosListNew;
            clientes.setCentrocostosList(centrocostosListNew);
            clientes = em.merge(clientes);
            if (tidoIdOld != null && !tidoIdOld.equals(tidoIdNew)) {
                tidoIdOld.getClientesList().remove(clientes);
                tidoIdOld = em.merge(tidoIdOld);
            }
            if (tidoIdNew != null && !tidoIdNew.equals(tidoIdOld)) {
                tidoIdNew.getClientesList().add(clientes);
                tidoIdNew = em.merge(tidoIdNew);
            }
            if (ciudIdOld != null && !ciudIdOld.equals(ciudIdNew)) {
                ciudIdOld.getClientesList().remove(clientes);
                ciudIdOld = em.merge(ciudIdOld);
            }
            if (ciudIdNew != null && !ciudIdNew.equals(ciudIdOld)) {
                ciudIdNew.getClientesList().add(clientes);
                ciudIdNew = em.merge(ciudIdNew);
            }
            for (ClientesServicio clientesServicioListNewClientesServicio : clientesServicioListNew) {
                if (!clientesServicioListOld.contains(clientesServicioListNewClientesServicio)) {
                    Clientes oldClieIdOfClientesServicioListNewClientesServicio = clientesServicioListNewClientesServicio.getClieId();
                    clientesServicioListNewClientesServicio.setClieId(clientes);
                    clientesServicioListNewClientesServicio = em.merge(clientesServicioListNewClientesServicio);
                    if (oldClieIdOfClientesServicioListNewClientesServicio != null && !oldClieIdOfClientesServicioListNewClientesServicio.equals(clientes)) {
                        oldClieIdOfClientesServicioListNewClientesServicio.getClientesServicioList().remove(clientesServicioListNewClientesServicio);
                        oldClieIdOfClientesServicioListNewClientesServicio = em.merge(oldClieIdOfClientesServicioListNewClientesServicio);
                    }
                }
            }
            for (Centrocostos centrocostosListNewCentrocostos : centrocostosListNew) {
                if (!centrocostosListOld.contains(centrocostosListNewCentrocostos)) {
                    Clientes oldClieIdOfCentrocostosListNewCentrocostos = centrocostosListNewCentrocostos.getClieId();
                    centrocostosListNewCentrocostos.setClieId(clientes);
                    centrocostosListNewCentrocostos = em.merge(centrocostosListNewCentrocostos);
                    if (oldClieIdOfCentrocostosListNewCentrocostos != null && !oldClieIdOfCentrocostosListNewCentrocostos.equals(clientes)) {
                        oldClieIdOfCentrocostosListNewCentrocostos.getCentrocostosList().remove(centrocostosListNewCentrocostos);
                        oldClieIdOfCentrocostosListNewCentrocostos = em.merge(oldClieIdOfCentrocostosListNewCentrocostos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clientes.getClieId();
                if (findClientes(id) == null) {
                    throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
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
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getClieId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ClientesServicio> clientesServicioListOrphanCheck = clientes.getClientesServicioList();
            for (ClientesServicio clientesServicioListOrphanCheckClientesServicio : clientesServicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the ClientesServicio " + clientesServicioListOrphanCheckClientesServicio + " in its clientesServicioList field has a non-nullable clieId field.");
            }
            List<Centrocostos> centrocostosListOrphanCheck = clientes.getCentrocostosList();
            for (Centrocostos centrocostosListOrphanCheckCentrocostos : centrocostosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the Centrocostos " + centrocostosListOrphanCheckCentrocostos + " in its centrocostosList field has a non-nullable clieId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tipodocumento tidoId = clientes.getTidoId();
            if (tidoId != null) {
                tidoId.getClientesList().remove(clientes);
                tidoId = em.merge(tidoId);
            }
            Ciudades ciudId = clientes.getCiudId();
            if (ciudId != null) {
                ciudId.getClientesList().remove(clientes);
                ciudId = em.merge(ciudId);
            }
            em.remove(clientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
    }

    private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientes.class));
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

    public Clientes findClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientes> rt = cq.from(Clientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
