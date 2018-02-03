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
import ocupacional.JPA.valueobjects.Departamentos;
import ocupacional.JPA.valueobjects.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.Ciudades;

/**
 *
 * @author D4V3
 */
public class CiudadesJpaController implements Serializable {

    public CiudadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciudades ciudades) {
        if (ciudades.getClientesList() == null) {
            ciudades.setClientesList(new ArrayList<Clientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamentos depaId = ciudades.getDepaId();
            if (depaId != null) {
                depaId = em.getReference(depaId.getClass(), depaId.getDepaId());
                ciudades.setDepaId(depaId);
            }
            List<Clientes> attachedClientesList = new ArrayList<Clientes>();
            for (Clientes clientesListClientesToAttach : ciudades.getClientesList()) {
                clientesListClientesToAttach = em.getReference(clientesListClientesToAttach.getClass(), clientesListClientesToAttach.getClieId());
                attachedClientesList.add(clientesListClientesToAttach);
            }
            ciudades.setClientesList(attachedClientesList);
            em.persist(ciudades);
            if (depaId != null) {
                depaId.getCiudadesList().add(ciudades);
                depaId = em.merge(depaId);
            }
            for (Clientes clientesListClientes : ciudades.getClientesList()) {
                Ciudades oldCiudIdOfClientesListClientes = clientesListClientes.getCiudId();
                clientesListClientes.setCiudId(ciudades);
                clientesListClientes = em.merge(clientesListClientes);
                if (oldCiudIdOfClientesListClientes != null) {
                    oldCiudIdOfClientesListClientes.getClientesList().remove(clientesListClientes);
                    oldCiudIdOfClientesListClientes = em.merge(oldCiudIdOfClientesListClientes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciudades ciudades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudades persistentCiudades = em.find(Ciudades.class, ciudades.getCiudId());
            Departamentos depaIdOld = persistentCiudades.getDepaId();
            Departamentos depaIdNew = ciudades.getDepaId();
            List<Clientes> clientesListOld = persistentCiudades.getClientesList();
            List<Clientes> clientesListNew = ciudades.getClientesList();
            if (depaIdNew != null) {
                depaIdNew = em.getReference(depaIdNew.getClass(), depaIdNew.getDepaId());
                ciudades.setDepaId(depaIdNew);
            }
            List<Clientes> attachedClientesListNew = new ArrayList<Clientes>();
            for (Clientes clientesListNewClientesToAttach : clientesListNew) {
                clientesListNewClientesToAttach = em.getReference(clientesListNewClientesToAttach.getClass(), clientesListNewClientesToAttach.getClieId());
                attachedClientesListNew.add(clientesListNewClientesToAttach);
            }
            clientesListNew = attachedClientesListNew;
            ciudades.setClientesList(clientesListNew);
            ciudades = em.merge(ciudades);
            if (depaIdOld != null && !depaIdOld.equals(depaIdNew)) {
                depaIdOld.getCiudadesList().remove(ciudades);
                depaIdOld = em.merge(depaIdOld);
            }
            if (depaIdNew != null && !depaIdNew.equals(depaIdOld)) {
                depaIdNew.getCiudadesList().add(ciudades);
                depaIdNew = em.merge(depaIdNew);
            }
            for (Clientes clientesListOldClientes : clientesListOld) {
                if (!clientesListNew.contains(clientesListOldClientes)) {
                    clientesListOldClientes.setCiudId(null);
                    clientesListOldClientes = em.merge(clientesListOldClientes);
                }
            }
            for (Clientes clientesListNewClientes : clientesListNew) {
                if (!clientesListOld.contains(clientesListNewClientes)) {
                    Ciudades oldCiudIdOfClientesListNewClientes = clientesListNewClientes.getCiudId();
                    clientesListNewClientes.setCiudId(ciudades);
                    clientesListNewClientes = em.merge(clientesListNewClientes);
                    if (oldCiudIdOfClientesListNewClientes != null && !oldCiudIdOfClientesListNewClientes.equals(ciudades)) {
                        oldCiudIdOfClientesListNewClientes.getClientesList().remove(clientesListNewClientes);
                        oldCiudIdOfClientesListNewClientes = em.merge(oldCiudIdOfClientesListNewClientes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ciudades.getCiudId();
                if (findCiudades(id) == null) {
                    throw new NonexistentEntityException("The ciudades with id " + id + " no longer exists.");
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
            Ciudades ciudades;
            try {
                ciudades = em.getReference(Ciudades.class, id);
                ciudades.getCiudId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudades with id " + id + " no longer exists.", enfe);
            }
            Departamentos depaId = ciudades.getDepaId();
            if (depaId != null) {
                depaId.getCiudadesList().remove(ciudades);
                depaId = em.merge(depaId);
            }
            List<Clientes> clientesList = ciudades.getClientesList();
            for (Clientes clientesListClientes : clientesList) {
                clientesListClientes.setCiudId(null);
                clientesListClientes = em.merge(clientesListClientes);
            }
            em.remove(ciudades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ciudades> findCiudadesEntities() {
        return findCiudadesEntities(true, -1, -1);
    }

    public List<Ciudades> findCiudadesEntities(int maxResults, int firstResult) {
        return findCiudadesEntities(false, maxResults, firstResult);
    }

    private List<Ciudades> findCiudadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudades.class));
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

    public Ciudades findCiudades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciudades.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudades> rt = cq.from(Ciudades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
