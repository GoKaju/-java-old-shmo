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
import ocupacional.JPA.valueobjects.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.valueobjects.Empleados;
import ocupacional.JPA.valueobjects.Tipodocumento;

/**
 *
 * @author Sebas
 */
public class TipodocumentoJpaController implements Serializable {

    public TipodocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipodocumento tipodocumento) {
        if (tipodocumento.getClientesList() == null) {
            tipodocumento.setClientesList(new ArrayList<Clientes>());
        }
        if (tipodocumento.getEmpleadosList() == null) {
            tipodocumento.setEmpleadosList(new ArrayList<Empleados>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Clientes> attachedClientesList = new ArrayList<Clientes>();
            for (Clientes clientesListClientesToAttach : tipodocumento.getClientesList()) {
                clientesListClientesToAttach = em.getReference(clientesListClientesToAttach.getClass(), clientesListClientesToAttach.getClieId());
                attachedClientesList.add(clientesListClientesToAttach);
            }
            tipodocumento.setClientesList(attachedClientesList);
            List<Empleados> attachedEmpleadosList = new ArrayList<Empleados>();
            for (Empleados empleadosListEmpleadosToAttach : tipodocumento.getEmpleadosList()) {
                empleadosListEmpleadosToAttach = em.getReference(empleadosListEmpleadosToAttach.getClass(), empleadosListEmpleadosToAttach.getEmplId());
                attachedEmpleadosList.add(empleadosListEmpleadosToAttach);
            }
            tipodocumento.setEmpleadosList(attachedEmpleadosList);
            em.persist(tipodocumento);
            for (Clientes clientesListClientes : tipodocumento.getClientesList()) {
                Tipodocumento oldTidoIdOfClientesListClientes = clientesListClientes.getTidoId();
                clientesListClientes.setTidoId(tipodocumento);
                clientesListClientes = em.merge(clientesListClientes);
                if (oldTidoIdOfClientesListClientes != null) {
                    oldTidoIdOfClientesListClientes.getClientesList().remove(clientesListClientes);
                    oldTidoIdOfClientesListClientes = em.merge(oldTidoIdOfClientesListClientes);
                }
            }
            for (Empleados empleadosListEmpleados : tipodocumento.getEmpleadosList()) {
                Tipodocumento oldTidoIdOfEmpleadosListEmpleados = empleadosListEmpleados.getTidoId();
                empleadosListEmpleados.setTidoId(tipodocumento);
                empleadosListEmpleados = em.merge(empleadosListEmpleados);
                if (oldTidoIdOfEmpleadosListEmpleados != null) {
                    oldTidoIdOfEmpleadosListEmpleados.getEmpleadosList().remove(empleadosListEmpleados);
                    oldTidoIdOfEmpleadosListEmpleados = em.merge(oldTidoIdOfEmpleadosListEmpleados);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipodocumento tipodocumento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipodocumento persistentTipodocumento = em.find(Tipodocumento.class, tipodocumento.getTidoId());
            List<Clientes> clientesListOld = persistentTipodocumento.getClientesList();
            List<Clientes> clientesListNew = tipodocumento.getClientesList();
            List<Empleados> empleadosListOld = persistentTipodocumento.getEmpleadosList();
            List<Empleados> empleadosListNew = tipodocumento.getEmpleadosList();
            List<String> illegalOrphanMessages = null;
            for (Clientes clientesListOldClientes : clientesListOld) {
                if (!clientesListNew.contains(clientesListOldClientes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Clientes " + clientesListOldClientes + " since its tidoId field is not nullable.");
                }
            }
            for (Empleados empleadosListOldEmpleados : empleadosListOld) {
                if (!empleadosListNew.contains(empleadosListOldEmpleados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleados " + empleadosListOldEmpleados + " since its tidoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Clientes> attachedClientesListNew = new ArrayList<Clientes>();
            for (Clientes clientesListNewClientesToAttach : clientesListNew) {
                clientesListNewClientesToAttach = em.getReference(clientesListNewClientesToAttach.getClass(), clientesListNewClientesToAttach.getClieId());
                attachedClientesListNew.add(clientesListNewClientesToAttach);
            }
            clientesListNew = attachedClientesListNew;
            tipodocumento.setClientesList(clientesListNew);
            List<Empleados> attachedEmpleadosListNew = new ArrayList<Empleados>();
            for (Empleados empleadosListNewEmpleadosToAttach : empleadosListNew) {
                empleadosListNewEmpleadosToAttach = em.getReference(empleadosListNewEmpleadosToAttach.getClass(), empleadosListNewEmpleadosToAttach.getEmplId());
                attachedEmpleadosListNew.add(empleadosListNewEmpleadosToAttach);
            }
            empleadosListNew = attachedEmpleadosListNew;
            tipodocumento.setEmpleadosList(empleadosListNew);
            tipodocumento = em.merge(tipodocumento);
            for (Clientes clientesListNewClientes : clientesListNew) {
                if (!clientesListOld.contains(clientesListNewClientes)) {
                    Tipodocumento oldTidoIdOfClientesListNewClientes = clientesListNewClientes.getTidoId();
                    clientesListNewClientes.setTidoId(tipodocumento);
                    clientesListNewClientes = em.merge(clientesListNewClientes);
                    if (oldTidoIdOfClientesListNewClientes != null && !oldTidoIdOfClientesListNewClientes.equals(tipodocumento)) {
                        oldTidoIdOfClientesListNewClientes.getClientesList().remove(clientesListNewClientes);
                        oldTidoIdOfClientesListNewClientes = em.merge(oldTidoIdOfClientesListNewClientes);
                    }
                }
            }
            for (Empleados empleadosListNewEmpleados : empleadosListNew) {
                if (!empleadosListOld.contains(empleadosListNewEmpleados)) {
                    Tipodocumento oldTidoIdOfEmpleadosListNewEmpleados = empleadosListNewEmpleados.getTidoId();
                    empleadosListNewEmpleados.setTidoId(tipodocumento);
                    empleadosListNewEmpleados = em.merge(empleadosListNewEmpleados);
                    if (oldTidoIdOfEmpleadosListNewEmpleados != null && !oldTidoIdOfEmpleadosListNewEmpleados.equals(tipodocumento)) {
                        oldTidoIdOfEmpleadosListNewEmpleados.getEmpleadosList().remove(empleadosListNewEmpleados);
                        oldTidoIdOfEmpleadosListNewEmpleados = em.merge(oldTidoIdOfEmpleadosListNewEmpleados);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipodocumento.getTidoId();
                if (findTipodocumento(id) == null) {
                    throw new NonexistentEntityException("The tipodocumento with id " + id + " no longer exists.");
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
            Tipodocumento tipodocumento;
            try {
                tipodocumento = em.getReference(Tipodocumento.class, id);
                tipodocumento.getTidoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipodocumento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Clientes> clientesListOrphanCheck = tipodocumento.getClientesList();
            for (Clientes clientesListOrphanCheckClientes : clientesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipodocumento (" + tipodocumento + ") cannot be destroyed since the Clientes " + clientesListOrphanCheckClientes + " in its clientesList field has a non-nullable tidoId field.");
            }
            List<Empleados> empleadosListOrphanCheck = tipodocumento.getEmpleadosList();
            for (Empleados empleadosListOrphanCheckEmpleados : empleadosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipodocumento (" + tipodocumento + ") cannot be destroyed since the Empleados " + empleadosListOrphanCheckEmpleados + " in its empleadosList field has a non-nullable tidoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipodocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipodocumento> findTipodocumentoEntities() {
        return findTipodocumentoEntities(true, -1, -1);
    }

    public List<Tipodocumento> findTipodocumentoEntities(int maxResults, int firstResult) {
        return findTipodocumentoEntities(false, maxResults, firstResult);
    }

    private List<Tipodocumento> findTipodocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipodocumento.class));
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

    public Tipodocumento findTipodocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipodocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipodocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipodocumento> rt = cq.from(Tipodocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
