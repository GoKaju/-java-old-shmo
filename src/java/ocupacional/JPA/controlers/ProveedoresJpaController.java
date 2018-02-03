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
import ocupacional.JPA.valueobjects.ProveedoresExamenes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.IllegalOrphanException;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.Proveedores;

/**
 *
 * @author D4V3
 */
public class ProveedoresJpaController implements Serializable {

    public ProveedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedores proveedores) {
        if (proveedores.getProveedoresExamenesList() == null) {
            proveedores.setProveedoresExamenesList(new ArrayList<ProveedoresExamenes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ProveedoresExamenes> attachedProveedoresExamenesList = new ArrayList<ProveedoresExamenes>();
            for (ProveedoresExamenes proveedoresExamenesListProveedoresExamenesToAttach : proveedores.getProveedoresExamenesList()) {
                proveedoresExamenesListProveedoresExamenesToAttach = em.getReference(proveedoresExamenesListProveedoresExamenesToAttach.getClass(), proveedoresExamenesListProveedoresExamenesToAttach.getPrexId());
                attachedProveedoresExamenesList.add(proveedoresExamenesListProveedoresExamenesToAttach);
            }
            proveedores.setProveedoresExamenesList(attachedProveedoresExamenesList);
            em.persist(proveedores);
            for (ProveedoresExamenes proveedoresExamenesListProveedoresExamenes : proveedores.getProveedoresExamenesList()) {
                Proveedores oldProvIdOfProveedoresExamenesListProveedoresExamenes = proveedoresExamenesListProveedoresExamenes.getProvId();
                proveedoresExamenesListProveedoresExamenes.setProvId(proveedores);
                proveedoresExamenesListProveedoresExamenes = em.merge(proveedoresExamenesListProveedoresExamenes);
                if (oldProvIdOfProveedoresExamenesListProveedoresExamenes != null) {
                    oldProvIdOfProveedoresExamenesListProveedoresExamenes.getProveedoresExamenesList().remove(proveedoresExamenesListProveedoresExamenes);
                    oldProvIdOfProveedoresExamenesListProveedoresExamenes = em.merge(oldProvIdOfProveedoresExamenesListProveedoresExamenes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedores proveedores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores persistentProveedores = em.find(Proveedores.class, proveedores.getProvId());
            List<ProveedoresExamenes> proveedoresExamenesListOld = persistentProveedores.getProveedoresExamenesList();
            List<ProveedoresExamenes> proveedoresExamenesListNew = proveedores.getProveedoresExamenesList();
            List<String> illegalOrphanMessages = null;
            for (ProveedoresExamenes proveedoresExamenesListOldProveedoresExamenes : proveedoresExamenesListOld) {
                if (!proveedoresExamenesListNew.contains(proveedoresExamenesListOldProveedoresExamenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProveedoresExamenes " + proveedoresExamenesListOldProveedoresExamenes + " since its provId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ProveedoresExamenes> attachedProveedoresExamenesListNew = new ArrayList<ProveedoresExamenes>();
            for (ProveedoresExamenes proveedoresExamenesListNewProveedoresExamenesToAttach : proveedoresExamenesListNew) {
                proveedoresExamenesListNewProveedoresExamenesToAttach = em.getReference(proveedoresExamenesListNewProveedoresExamenesToAttach.getClass(), proveedoresExamenesListNewProveedoresExamenesToAttach.getPrexId());
                attachedProveedoresExamenesListNew.add(proveedoresExamenesListNewProveedoresExamenesToAttach);
            }
            proveedoresExamenesListNew = attachedProveedoresExamenesListNew;
            proveedores.setProveedoresExamenesList(proveedoresExamenesListNew);
            proveedores = em.merge(proveedores);
            for (ProveedoresExamenes proveedoresExamenesListNewProveedoresExamenes : proveedoresExamenesListNew) {
                if (!proveedoresExamenesListOld.contains(proveedoresExamenesListNewProveedoresExamenes)) {
                    Proveedores oldProvIdOfProveedoresExamenesListNewProveedoresExamenes = proveedoresExamenesListNewProveedoresExamenes.getProvId();
                    proveedoresExamenesListNewProveedoresExamenes.setProvId(proveedores);
                    proveedoresExamenesListNewProveedoresExamenes = em.merge(proveedoresExamenesListNewProveedoresExamenes);
                    if (oldProvIdOfProveedoresExamenesListNewProveedoresExamenes != null && !oldProvIdOfProveedoresExamenesListNewProveedoresExamenes.equals(proveedores)) {
                        oldProvIdOfProveedoresExamenesListNewProveedoresExamenes.getProveedoresExamenesList().remove(proveedoresExamenesListNewProveedoresExamenes);
                        oldProvIdOfProveedoresExamenesListNewProveedoresExamenes = em.merge(oldProvIdOfProveedoresExamenesListNewProveedoresExamenes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = proveedores.getProvId();
                if (findProveedores(id) == null) {
                    throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores proveedores;
            try {
                proveedores = em.getReference(Proveedores.class, id);
                proveedores.getProvId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProveedoresExamenes> proveedoresExamenesListOrphanCheck = proveedores.getProveedoresExamenesList();
            for (ProveedoresExamenes proveedoresExamenesListOrphanCheckProveedoresExamenes : proveedoresExamenesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedores (" + proveedores + ") cannot be destroyed since the ProveedoresExamenes " + proveedoresExamenesListOrphanCheckProveedoresExamenes + " in its proveedoresExamenesList field has a non-nullable provId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proveedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedores> findProveedoresEntities() {
        return findProveedoresEntities(true, -1, -1);
    }

    public List<Proveedores> findProveedoresEntities(int maxResults, int firstResult) {
        return findProveedoresEntities(false, maxResults, firstResult);
    }

    private List<Proveedores> findProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedores.class));
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

    public Proveedores findProveedores(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedores> rt = cq.from(Proveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
