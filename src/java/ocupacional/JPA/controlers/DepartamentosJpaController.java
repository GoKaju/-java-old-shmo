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
import ocupacional.JPA.valueobjects.Paises;
import ocupacional.JPA.valueobjects.Ciudades;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.IllegalOrphanException;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.Departamentos;

/**
 *
 * @author D4V3
 */
public class DepartamentosJpaController implements Serializable {

    public DepartamentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamentos departamentos) {
        if (departamentos.getCiudadesList() == null) {
            departamentos.setCiudadesList(new ArrayList<Ciudades>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paises paisId = departamentos.getPaisId();
            if (paisId != null) {
                paisId = em.getReference(paisId.getClass(), paisId.getId());
                departamentos.setPaisId(paisId);
            }
            List<Ciudades> attachedCiudadesList = new ArrayList<Ciudades>();
            for (Ciudades ciudadesListCiudadesToAttach : departamentos.getCiudadesList()) {
                ciudadesListCiudadesToAttach = em.getReference(ciudadesListCiudadesToAttach.getClass(), ciudadesListCiudadesToAttach.getCiudId());
                attachedCiudadesList.add(ciudadesListCiudadesToAttach);
            }
            departamentos.setCiudadesList(attachedCiudadesList);
            em.persist(departamentos);
            if (paisId != null) {
                paisId.getDepartamentosList().add(departamentos);
                paisId = em.merge(paisId);
            }
            for (Ciudades ciudadesListCiudades : departamentos.getCiudadesList()) {
                Departamentos oldDepaIdOfCiudadesListCiudades = ciudadesListCiudades.getDepaId();
                ciudadesListCiudades.setDepaId(departamentos);
                ciudadesListCiudades = em.merge(ciudadesListCiudades);
                if (oldDepaIdOfCiudadesListCiudades != null) {
                    oldDepaIdOfCiudadesListCiudades.getCiudadesList().remove(ciudadesListCiudades);
                    oldDepaIdOfCiudadesListCiudades = em.merge(oldDepaIdOfCiudadesListCiudades);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamentos departamentos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamentos persistentDepartamentos = em.find(Departamentos.class, departamentos.getDepaId());
            Paises paisIdOld = persistentDepartamentos.getPaisId();
            Paises paisIdNew = departamentos.getPaisId();
            List<Ciudades> ciudadesListOld = persistentDepartamentos.getCiudadesList();
            List<Ciudades> ciudadesListNew = departamentos.getCiudadesList();
            List<String> illegalOrphanMessages = null;
            for (Ciudades ciudadesListOldCiudades : ciudadesListOld) {
                if (!ciudadesListNew.contains(ciudadesListOldCiudades)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ciudades " + ciudadesListOldCiudades + " since its depaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (paisIdNew != null) {
                paisIdNew = em.getReference(paisIdNew.getClass(), paisIdNew.getId());
                departamentos.setPaisId(paisIdNew);
            }
            List<Ciudades> attachedCiudadesListNew = new ArrayList<Ciudades>();
            for (Ciudades ciudadesListNewCiudadesToAttach : ciudadesListNew) {
                ciudadesListNewCiudadesToAttach = em.getReference(ciudadesListNewCiudadesToAttach.getClass(), ciudadesListNewCiudadesToAttach.getCiudId());
                attachedCiudadesListNew.add(ciudadesListNewCiudadesToAttach);
            }
            ciudadesListNew = attachedCiudadesListNew;
            departamentos.setCiudadesList(ciudadesListNew);
            departamentos = em.merge(departamentos);
            if (paisIdOld != null && !paisIdOld.equals(paisIdNew)) {
                paisIdOld.getDepartamentosList().remove(departamentos);
                paisIdOld = em.merge(paisIdOld);
            }
            if (paisIdNew != null && !paisIdNew.equals(paisIdOld)) {
                paisIdNew.getDepartamentosList().add(departamentos);
                paisIdNew = em.merge(paisIdNew);
            }
            for (Ciudades ciudadesListNewCiudades : ciudadesListNew) {
                if (!ciudadesListOld.contains(ciudadesListNewCiudades)) {
                    Departamentos oldDepaIdOfCiudadesListNewCiudades = ciudadesListNewCiudades.getDepaId();
                    ciudadesListNewCiudades.setDepaId(departamentos);
                    ciudadesListNewCiudades = em.merge(ciudadesListNewCiudades);
                    if (oldDepaIdOfCiudadesListNewCiudades != null && !oldDepaIdOfCiudadesListNewCiudades.equals(departamentos)) {
                        oldDepaIdOfCiudadesListNewCiudades.getCiudadesList().remove(ciudadesListNewCiudades);
                        oldDepaIdOfCiudadesListNewCiudades = em.merge(oldDepaIdOfCiudadesListNewCiudades);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamentos.getDepaId();
                if (findDepartamentos(id) == null) {
                    throw new NonexistentEntityException("The departamentos with id " + id + " no longer exists.");
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
            Departamentos departamentos;
            try {
                departamentos = em.getReference(Departamentos.class, id);
                departamentos.getDepaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamentos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ciudades> ciudadesListOrphanCheck = departamentos.getCiudadesList();
            for (Ciudades ciudadesListOrphanCheckCiudades : ciudadesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Departamentos (" + departamentos + ") cannot be destroyed since the Ciudades " + ciudadesListOrphanCheckCiudades + " in its ciudadesList field has a non-nullable depaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Paises paisId = departamentos.getPaisId();
            if (paisId != null) {
                paisId.getDepartamentosList().remove(departamentos);
                paisId = em.merge(paisId);
            }
            em.remove(departamentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamentos> findDepartamentosEntities() {
        return findDepartamentosEntities(true, -1, -1);
    }

    public List<Departamentos> findDepartamentosEntities(int maxResults, int firstResult) {
        return findDepartamentosEntities(false, maxResults, firstResult);
    }

    private List<Departamentos> findDepartamentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamentos.class));
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

    public Departamentos findDepartamentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamentos> rt = cq.from(Departamentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
