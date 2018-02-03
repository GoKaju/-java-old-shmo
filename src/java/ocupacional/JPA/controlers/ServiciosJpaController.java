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
import ocupacional.JPA.valueobjects.ClientesServicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.IllegalOrphanException;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.Servicios;
import ocupacional.JPA.valueobjects.ServiciosExamenes;

/**
 *
 * @author D4V3
 */
public class ServiciosJpaController implements Serializable {

    public ServiciosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicios servicios) {
        if (servicios.getClientesServicioList() == null) {
            servicios.setClientesServicioList(new ArrayList<ClientesServicio>());
        }
        if (servicios.getServiciosExamenesList() == null) {
            servicios.setServiciosExamenesList(new ArrayList<ServiciosExamenes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ClientesServicio> attachedClientesServicioList = new ArrayList<ClientesServicio>();
            for (ClientesServicio clientesServicioListClientesServicioToAttach : servicios.getClientesServicioList()) {
                clientesServicioListClientesServicioToAttach = em.getReference(clientesServicioListClientesServicioToAttach.getClass(), clientesServicioListClientesServicioToAttach.getClseId());
                attachedClientesServicioList.add(clientesServicioListClientesServicioToAttach);
            }
            servicios.setClientesServicioList(attachedClientesServicioList);
            List<ServiciosExamenes> attachedServiciosExamenesList = new ArrayList<ServiciosExamenes>();
            for (ServiciosExamenes serviciosExamenesListServiciosExamenesToAttach : servicios.getServiciosExamenesList()) {
                serviciosExamenesListServiciosExamenesToAttach = em.getReference(serviciosExamenesListServiciosExamenesToAttach.getClass(), serviciosExamenesListServiciosExamenesToAttach.getSeexId());
                attachedServiciosExamenesList.add(serviciosExamenesListServiciosExamenesToAttach);
            }
            servicios.setServiciosExamenesList(attachedServiciosExamenesList);
            em.persist(servicios);
            for (ClientesServicio clientesServicioListClientesServicio : servicios.getClientesServicioList()) {
                Servicios oldServIdOfClientesServicioListClientesServicio = clientesServicioListClientesServicio.getServId();
                clientesServicioListClientesServicio.setServId(servicios);
                clientesServicioListClientesServicio = em.merge(clientesServicioListClientesServicio);
                if (oldServIdOfClientesServicioListClientesServicio != null) {
                    oldServIdOfClientesServicioListClientesServicio.getClientesServicioList().remove(clientesServicioListClientesServicio);
                    oldServIdOfClientesServicioListClientesServicio = em.merge(oldServIdOfClientesServicioListClientesServicio);
                }
            }
            for (ServiciosExamenes serviciosExamenesListServiciosExamenes : servicios.getServiciosExamenesList()) {
                Servicios oldServIdOfServiciosExamenesListServiciosExamenes = serviciosExamenesListServiciosExamenes.getServId();
                serviciosExamenesListServiciosExamenes.setServId(servicios);
                serviciosExamenesListServiciosExamenes = em.merge(serviciosExamenesListServiciosExamenes);
                if (oldServIdOfServiciosExamenesListServiciosExamenes != null) {
                    oldServIdOfServiciosExamenesListServiciosExamenes.getServiciosExamenesList().remove(serviciosExamenesListServiciosExamenes);
                    oldServIdOfServiciosExamenesListServiciosExamenes = em.merge(oldServIdOfServiciosExamenesListServiciosExamenes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicios servicios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicios persistentServicios = em.find(Servicios.class, servicios.getServId());
            List<ClientesServicio> clientesServicioListOld = persistentServicios.getClientesServicioList();
            List<ClientesServicio> clientesServicioListNew = servicios.getClientesServicioList();
            List<ServiciosExamenes> serviciosExamenesListOld = persistentServicios.getServiciosExamenesList();
            List<ServiciosExamenes> serviciosExamenesListNew = servicios.getServiciosExamenesList();
            List<String> illegalOrphanMessages = null;
            for (ClientesServicio clientesServicioListOldClientesServicio : clientesServicioListOld) {
                if (!clientesServicioListNew.contains(clientesServicioListOldClientesServicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClientesServicio " + clientesServicioListOldClientesServicio + " since its servId field is not nullable.");
                }
            }
            for (ServiciosExamenes serviciosExamenesListOldServiciosExamenes : serviciosExamenesListOld) {
                if (!serviciosExamenesListNew.contains(serviciosExamenesListOldServiciosExamenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiciosExamenes " + serviciosExamenesListOldServiciosExamenes + " since its servId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ClientesServicio> attachedClientesServicioListNew = new ArrayList<ClientesServicio>();
            for (ClientesServicio clientesServicioListNewClientesServicioToAttach : clientesServicioListNew) {
                clientesServicioListNewClientesServicioToAttach = em.getReference(clientesServicioListNewClientesServicioToAttach.getClass(), clientesServicioListNewClientesServicioToAttach.getClseId());
                attachedClientesServicioListNew.add(clientesServicioListNewClientesServicioToAttach);
            }
            clientesServicioListNew = attachedClientesServicioListNew;
            servicios.setClientesServicioList(clientesServicioListNew);
            List<ServiciosExamenes> attachedServiciosExamenesListNew = new ArrayList<ServiciosExamenes>();
            for (ServiciosExamenes serviciosExamenesListNewServiciosExamenesToAttach : serviciosExamenesListNew) {
                serviciosExamenesListNewServiciosExamenesToAttach = em.getReference(serviciosExamenesListNewServiciosExamenesToAttach.getClass(), serviciosExamenesListNewServiciosExamenesToAttach.getSeexId());
                attachedServiciosExamenesListNew.add(serviciosExamenesListNewServiciosExamenesToAttach);
            }
            serviciosExamenesListNew = attachedServiciosExamenesListNew;
            servicios.setServiciosExamenesList(serviciosExamenesListNew);
            servicios = em.merge(servicios);
            for (ClientesServicio clientesServicioListNewClientesServicio : clientesServicioListNew) {
                if (!clientesServicioListOld.contains(clientesServicioListNewClientesServicio)) {
                    Servicios oldServIdOfClientesServicioListNewClientesServicio = clientesServicioListNewClientesServicio.getServId();
                    clientesServicioListNewClientesServicio.setServId(servicios);
                    clientesServicioListNewClientesServicio = em.merge(clientesServicioListNewClientesServicio);
                    if (oldServIdOfClientesServicioListNewClientesServicio != null && !oldServIdOfClientesServicioListNewClientesServicio.equals(servicios)) {
                        oldServIdOfClientesServicioListNewClientesServicio.getClientesServicioList().remove(clientesServicioListNewClientesServicio);
                        oldServIdOfClientesServicioListNewClientesServicio = em.merge(oldServIdOfClientesServicioListNewClientesServicio);
                    }
                }
            }
            for (ServiciosExamenes serviciosExamenesListNewServiciosExamenes : serviciosExamenesListNew) {
                if (!serviciosExamenesListOld.contains(serviciosExamenesListNewServiciosExamenes)) {
                    Servicios oldServIdOfServiciosExamenesListNewServiciosExamenes = serviciosExamenesListNewServiciosExamenes.getServId();
                    serviciosExamenesListNewServiciosExamenes.setServId(servicios);
                    serviciosExamenesListNewServiciosExamenes = em.merge(serviciosExamenesListNewServiciosExamenes);
                    if (oldServIdOfServiciosExamenesListNewServiciosExamenes != null && !oldServIdOfServiciosExamenesListNewServiciosExamenes.equals(servicios)) {
                        oldServIdOfServiciosExamenesListNewServiciosExamenes.getServiciosExamenesList().remove(serviciosExamenesListNewServiciosExamenes);
                        oldServIdOfServiciosExamenesListNewServiciosExamenes = em.merge(oldServIdOfServiciosExamenesListNewServiciosExamenes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicios.getServId();
                if (findServicios(id) == null) {
                    throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.");
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
            Servicios servicios;
            try {
                servicios = em.getReference(Servicios.class, id);
                servicios.getServId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ClientesServicio> clientesServicioListOrphanCheck = servicios.getClientesServicioList();
            for (ClientesServicio clientesServicioListOrphanCheckClientesServicio : clientesServicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicios (" + servicios + ") cannot be destroyed since the ClientesServicio " + clientesServicioListOrphanCheckClientesServicio + " in its clientesServicioList field has a non-nullable servId field.");
            }
            List<ServiciosExamenes> serviciosExamenesListOrphanCheck = servicios.getServiciosExamenesList();
            for (ServiciosExamenes serviciosExamenesListOrphanCheckServiciosExamenes : serviciosExamenesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicios (" + servicios + ") cannot be destroyed since the ServiciosExamenes " + serviciosExamenesListOrphanCheckServiciosExamenes + " in its serviciosExamenesList field has a non-nullable servId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(servicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicios> findServiciosEntities() {
        return findServiciosEntities(true, -1, -1);
    }

    public List<Servicios> findServiciosEntities(int maxResults, int firstResult) {
        return findServiciosEntities(false, maxResults, firstResult);
    }

    private List<Servicios> findServiciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicios.class));
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

    public Servicios findServicios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicios.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicios> rt = cq.from(Servicios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
