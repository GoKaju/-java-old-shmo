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
import ocupacional.JPA.valueobjects.ServiciosExamenes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.IllegalOrphanException;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.ProveedoresExamenes;
import ocupacional.JPA.valueobjects.Empleadoexamen;
import ocupacional.JPA.valueobjects.Examenes;

/**
 *
 * @author D4V3
 */
public class ExamenesJpaController implements Serializable {

    public ExamenesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Examenes examenes) {
        if (examenes.getServiciosExamenesList() == null) {
            examenes.setServiciosExamenesList(new ArrayList<ServiciosExamenes>());
        }
        if (examenes.getProveedoresExamenesList() == null) {
            examenes.setProveedoresExamenesList(new ArrayList<ProveedoresExamenes>());
        }
        if (examenes.getEmpleadoexamenList() == null) {
            examenes.setEmpleadoexamenList(new ArrayList<Empleadoexamen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ServiciosExamenes> attachedServiciosExamenesList = new ArrayList<ServiciosExamenes>();
            for (ServiciosExamenes serviciosExamenesListServiciosExamenesToAttach : examenes.getServiciosExamenesList()) {
                serviciosExamenesListServiciosExamenesToAttach = em.getReference(serviciosExamenesListServiciosExamenesToAttach.getClass(), serviciosExamenesListServiciosExamenesToAttach.getSeexId());
                attachedServiciosExamenesList.add(serviciosExamenesListServiciosExamenesToAttach);
            }
            examenes.setServiciosExamenesList(attachedServiciosExamenesList);
            List<ProveedoresExamenes> attachedProveedoresExamenesList = new ArrayList<ProveedoresExamenes>();
            for (ProveedoresExamenes proveedoresExamenesListProveedoresExamenesToAttach : examenes.getProveedoresExamenesList()) {
                proveedoresExamenesListProveedoresExamenesToAttach = em.getReference(proveedoresExamenesListProveedoresExamenesToAttach.getClass(), proveedoresExamenesListProveedoresExamenesToAttach.getPrexId());
                attachedProveedoresExamenesList.add(proveedoresExamenesListProveedoresExamenesToAttach);
            }
            examenes.setProveedoresExamenesList(attachedProveedoresExamenesList);
            List<Empleadoexamen> attachedEmpleadoexamenList = new ArrayList<Empleadoexamen>();
            for (Empleadoexamen empleadoexamenListEmpleadoexamenToAttach : examenes.getEmpleadoexamenList()) {
                empleadoexamenListEmpleadoexamenToAttach = em.getReference(empleadoexamenListEmpleadoexamenToAttach.getClass(), empleadoexamenListEmpleadoexamenToAttach.getEmexId());
                attachedEmpleadoexamenList.add(empleadoexamenListEmpleadoexamenToAttach);
            }
            examenes.setEmpleadoexamenList(attachedEmpleadoexamenList);
            em.persist(examenes);
            for (ServiciosExamenes serviciosExamenesListServiciosExamenes : examenes.getServiciosExamenesList()) {
                Examenes oldExamIdOfServiciosExamenesListServiciosExamenes = serviciosExamenesListServiciosExamenes.getExamId();
                serviciosExamenesListServiciosExamenes.setExamId(examenes);
                serviciosExamenesListServiciosExamenes = em.merge(serviciosExamenesListServiciosExamenes);
                if (oldExamIdOfServiciosExamenesListServiciosExamenes != null) {
                    oldExamIdOfServiciosExamenesListServiciosExamenes.getServiciosExamenesList().remove(serviciosExamenesListServiciosExamenes);
                    oldExamIdOfServiciosExamenesListServiciosExamenes = em.merge(oldExamIdOfServiciosExamenesListServiciosExamenes);
                }
            }
            for (ProveedoresExamenes proveedoresExamenesListProveedoresExamenes : examenes.getProveedoresExamenesList()) {
                Examenes oldExamIdOfProveedoresExamenesListProveedoresExamenes = proveedoresExamenesListProveedoresExamenes.getExamId();
                proveedoresExamenesListProveedoresExamenes.setExamId(examenes);
                proveedoresExamenesListProveedoresExamenes = em.merge(proveedoresExamenesListProveedoresExamenes);
                if (oldExamIdOfProveedoresExamenesListProveedoresExamenes != null) {
                    oldExamIdOfProveedoresExamenesListProveedoresExamenes.getProveedoresExamenesList().remove(proveedoresExamenesListProveedoresExamenes);
                    oldExamIdOfProveedoresExamenesListProveedoresExamenes = em.merge(oldExamIdOfProveedoresExamenesListProveedoresExamenes);
                }
            }
            for (Empleadoexamen empleadoexamenListEmpleadoexamen : examenes.getEmpleadoexamenList()) {
                Examenes oldExamIdOfEmpleadoexamenListEmpleadoexamen = empleadoexamenListEmpleadoexamen.getExamId();
                empleadoexamenListEmpleadoexamen.setExamId(examenes);
                empleadoexamenListEmpleadoexamen = em.merge(empleadoexamenListEmpleadoexamen);
                if (oldExamIdOfEmpleadoexamenListEmpleadoexamen != null) {
                    oldExamIdOfEmpleadoexamenListEmpleadoexamen.getEmpleadoexamenList().remove(empleadoexamenListEmpleadoexamen);
                    oldExamIdOfEmpleadoexamenListEmpleadoexamen = em.merge(oldExamIdOfEmpleadoexamenListEmpleadoexamen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Examenes examenes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Examenes persistentExamenes = em.find(Examenes.class, examenes.getExamId());
            List<ServiciosExamenes> serviciosExamenesListOld = persistentExamenes.getServiciosExamenesList();
            List<ServiciosExamenes> serviciosExamenesListNew = examenes.getServiciosExamenesList();
            List<ProveedoresExamenes> proveedoresExamenesListOld = persistentExamenes.getProveedoresExamenesList();
            List<ProveedoresExamenes> proveedoresExamenesListNew = examenes.getProveedoresExamenesList();
            List<Empleadoexamen> empleadoexamenListOld = persistentExamenes.getEmpleadoexamenList();
            List<Empleadoexamen> empleadoexamenListNew = examenes.getEmpleadoexamenList();
            List<String> illegalOrphanMessages = null;
            for (ServiciosExamenes serviciosExamenesListOldServiciosExamenes : serviciosExamenesListOld) {
                if (!serviciosExamenesListNew.contains(serviciosExamenesListOldServiciosExamenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiciosExamenes " + serviciosExamenesListOldServiciosExamenes + " since its examId field is not nullable.");
                }
            }
            for (ProveedoresExamenes proveedoresExamenesListOldProveedoresExamenes : proveedoresExamenesListOld) {
                if (!proveedoresExamenesListNew.contains(proveedoresExamenesListOldProveedoresExamenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProveedoresExamenes " + proveedoresExamenesListOldProveedoresExamenes + " since its examId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ServiciosExamenes> attachedServiciosExamenesListNew = new ArrayList<ServiciosExamenes>();
            for (ServiciosExamenes serviciosExamenesListNewServiciosExamenesToAttach : serviciosExamenesListNew) {
                serviciosExamenesListNewServiciosExamenesToAttach = em.getReference(serviciosExamenesListNewServiciosExamenesToAttach.getClass(), serviciosExamenesListNewServiciosExamenesToAttach.getSeexId());
                attachedServiciosExamenesListNew.add(serviciosExamenesListNewServiciosExamenesToAttach);
            }
            serviciosExamenesListNew = attachedServiciosExamenesListNew;
            examenes.setServiciosExamenesList(serviciosExamenesListNew);
            List<ProveedoresExamenes> attachedProveedoresExamenesListNew = new ArrayList<ProveedoresExamenes>();
            for (ProveedoresExamenes proveedoresExamenesListNewProveedoresExamenesToAttach : proveedoresExamenesListNew) {
                proveedoresExamenesListNewProveedoresExamenesToAttach = em.getReference(proveedoresExamenesListNewProveedoresExamenesToAttach.getClass(), proveedoresExamenesListNewProveedoresExamenesToAttach.getPrexId());
                attachedProveedoresExamenesListNew.add(proveedoresExamenesListNewProveedoresExamenesToAttach);
            }
            proveedoresExamenesListNew = attachedProveedoresExamenesListNew;
            examenes.setProveedoresExamenesList(proveedoresExamenesListNew);
            List<Empleadoexamen> attachedEmpleadoexamenListNew = new ArrayList<Empleadoexamen>();
            for (Empleadoexamen empleadoexamenListNewEmpleadoexamenToAttach : empleadoexamenListNew) {
                empleadoexamenListNewEmpleadoexamenToAttach = em.getReference(empleadoexamenListNewEmpleadoexamenToAttach.getClass(), empleadoexamenListNewEmpleadoexamenToAttach.getEmexId());
                attachedEmpleadoexamenListNew.add(empleadoexamenListNewEmpleadoexamenToAttach);
            }
            empleadoexamenListNew = attachedEmpleadoexamenListNew;
            examenes.setEmpleadoexamenList(empleadoexamenListNew);
            examenes = em.merge(examenes);
            for (ServiciosExamenes serviciosExamenesListNewServiciosExamenes : serviciosExamenesListNew) {
                if (!serviciosExamenesListOld.contains(serviciosExamenesListNewServiciosExamenes)) {
                    Examenes oldExamIdOfServiciosExamenesListNewServiciosExamenes = serviciosExamenesListNewServiciosExamenes.getExamId();
                    serviciosExamenesListNewServiciosExamenes.setExamId(examenes);
                    serviciosExamenesListNewServiciosExamenes = em.merge(serviciosExamenesListNewServiciosExamenes);
                    if (oldExamIdOfServiciosExamenesListNewServiciosExamenes != null && !oldExamIdOfServiciosExamenesListNewServiciosExamenes.equals(examenes)) {
                        oldExamIdOfServiciosExamenesListNewServiciosExamenes.getServiciosExamenesList().remove(serviciosExamenesListNewServiciosExamenes);
                        oldExamIdOfServiciosExamenesListNewServiciosExamenes = em.merge(oldExamIdOfServiciosExamenesListNewServiciosExamenes);
                    }
                }
            }
            for (ProveedoresExamenes proveedoresExamenesListNewProveedoresExamenes : proveedoresExamenesListNew) {
                if (!proveedoresExamenesListOld.contains(proveedoresExamenesListNewProveedoresExamenes)) {
                    Examenes oldExamIdOfProveedoresExamenesListNewProveedoresExamenes = proveedoresExamenesListNewProveedoresExamenes.getExamId();
                    proveedoresExamenesListNewProveedoresExamenes.setExamId(examenes);
                    proveedoresExamenesListNewProveedoresExamenes = em.merge(proveedoresExamenesListNewProveedoresExamenes);
                    if (oldExamIdOfProveedoresExamenesListNewProveedoresExamenes != null && !oldExamIdOfProveedoresExamenesListNewProveedoresExamenes.equals(examenes)) {
                        oldExamIdOfProveedoresExamenesListNewProveedoresExamenes.getProveedoresExamenesList().remove(proveedoresExamenesListNewProveedoresExamenes);
                        oldExamIdOfProveedoresExamenesListNewProveedoresExamenes = em.merge(oldExamIdOfProveedoresExamenesListNewProveedoresExamenes);
                    }
                }
            }
            for (Empleadoexamen empleadoexamenListOldEmpleadoexamen : empleadoexamenListOld) {
                if (!empleadoexamenListNew.contains(empleadoexamenListOldEmpleadoexamen)) {
                    empleadoexamenListOldEmpleadoexamen.setExamId(null);
                    empleadoexamenListOldEmpleadoexamen = em.merge(empleadoexamenListOldEmpleadoexamen);
                }
            }
            for (Empleadoexamen empleadoexamenListNewEmpleadoexamen : empleadoexamenListNew) {
                if (!empleadoexamenListOld.contains(empleadoexamenListNewEmpleadoexamen)) {
                    Examenes oldExamIdOfEmpleadoexamenListNewEmpleadoexamen = empleadoexamenListNewEmpleadoexamen.getExamId();
                    empleadoexamenListNewEmpleadoexamen.setExamId(examenes);
                    empleadoexamenListNewEmpleadoexamen = em.merge(empleadoexamenListNewEmpleadoexamen);
                    if (oldExamIdOfEmpleadoexamenListNewEmpleadoexamen != null && !oldExamIdOfEmpleadoexamenListNewEmpleadoexamen.equals(examenes)) {
                        oldExamIdOfEmpleadoexamenListNewEmpleadoexamen.getEmpleadoexamenList().remove(empleadoexamenListNewEmpleadoexamen);
                        oldExamIdOfEmpleadoexamenListNewEmpleadoexamen = em.merge(oldExamIdOfEmpleadoexamenListNewEmpleadoexamen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = examenes.getExamId();
                if (findExamenes(id) == null) {
                    throw new NonexistentEntityException("The examenes with id " + id + " no longer exists.");
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
            Examenes examenes;
            try {
                examenes = em.getReference(Examenes.class, id);
                examenes.getExamId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examenes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ServiciosExamenes> serviciosExamenesListOrphanCheck = examenes.getServiciosExamenesList();
            for (ServiciosExamenes serviciosExamenesListOrphanCheckServiciosExamenes : serviciosExamenesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Examenes (" + examenes + ") cannot be destroyed since the ServiciosExamenes " + serviciosExamenesListOrphanCheckServiciosExamenes + " in its serviciosExamenesList field has a non-nullable examId field.");
            }
            List<ProveedoresExamenes> proveedoresExamenesListOrphanCheck = examenes.getProveedoresExamenesList();
            for (ProveedoresExamenes proveedoresExamenesListOrphanCheckProveedoresExamenes : proveedoresExamenesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Examenes (" + examenes + ") cannot be destroyed since the ProveedoresExamenes " + proveedoresExamenesListOrphanCheckProveedoresExamenes + " in its proveedoresExamenesList field has a non-nullable examId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Empleadoexamen> empleadoexamenList = examenes.getEmpleadoexamenList();
            for (Empleadoexamen empleadoexamenListEmpleadoexamen : empleadoexamenList) {
                empleadoexamenListEmpleadoexamen.setExamId(null);
                empleadoexamenListEmpleadoexamen = em.merge(empleadoexamenListEmpleadoexamen);
            }
            em.remove(examenes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Examenes> findExamenesEntities() {
        return findExamenesEntities(true, -1, -1);
    }

    public List<Examenes> findExamenesEntities(int maxResults, int firstResult) {
        return findExamenesEntities(false, maxResults, firstResult);
    }

    private List<Examenes> findExamenesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Examenes.class));
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

    public Examenes findExamenes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Examenes.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Examenes> rt = cq.from(Examenes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
