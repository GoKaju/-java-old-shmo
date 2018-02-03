/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import formularios.entidades.Anotaciones;
import formularios.entidades.Pacientes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author D4V3
 */
public class PacientesJpaController implements Serializable {

    public PacientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pacientes pacientes) {
        if (pacientes.getAnotacionesList() == null) {
            pacientes.setAnotacionesList(new ArrayList<Anotaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Anotaciones> attachedAnotacionesList = new ArrayList<Anotaciones>();
            for (Anotaciones anotacionesListAnotacionesToAttach : pacientes.getAnotacionesList()) {
                anotacionesListAnotacionesToAttach = em.getReference(anotacionesListAnotacionesToAttach.getClass(), anotacionesListAnotacionesToAttach.getAnotId());
                attachedAnotacionesList.add(anotacionesListAnotacionesToAttach);
            }
            pacientes.setAnotacionesList(attachedAnotacionesList);
            em.persist(pacientes);
            for (Anotaciones anotacionesListAnotaciones : pacientes.getAnotacionesList()) {
                Pacientes oldPaciIdOfAnotacionesListAnotaciones = anotacionesListAnotaciones.getPaciId();
                anotacionesListAnotaciones.setPaciId(pacientes);
                anotacionesListAnotaciones = em.merge(anotacionesListAnotaciones);
                if (oldPaciIdOfAnotacionesListAnotaciones != null) {
                    oldPaciIdOfAnotacionesListAnotaciones.getAnotacionesList().remove(anotacionesListAnotaciones);
                    oldPaciIdOfAnotacionesListAnotaciones = em.merge(oldPaciIdOfAnotacionesListAnotaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pacientes pacientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pacientes persistentPacientes = em.find(Pacientes.class, pacientes.getPaciId());
            List<Anotaciones> anotacionesListOld = persistentPacientes.getAnotacionesList();
            List<Anotaciones> anotacionesListNew = pacientes.getAnotacionesList();
            List<Anotaciones> attachedAnotacionesListNew = new ArrayList<Anotaciones>();
            for (Anotaciones anotacionesListNewAnotacionesToAttach : anotacionesListNew) {
                anotacionesListNewAnotacionesToAttach = em.getReference(anotacionesListNewAnotacionesToAttach.getClass(), anotacionesListNewAnotacionesToAttach.getAnotId());
                attachedAnotacionesListNew.add(anotacionesListNewAnotacionesToAttach);
            }
            anotacionesListNew = attachedAnotacionesListNew;
            pacientes.setAnotacionesList(anotacionesListNew);
            pacientes = em.merge(pacientes);
            for (Anotaciones anotacionesListOldAnotaciones : anotacionesListOld) {
                if (!anotacionesListNew.contains(anotacionesListOldAnotaciones)) {
                    anotacionesListOldAnotaciones.setPaciId(null);
                    anotacionesListOldAnotaciones = em.merge(anotacionesListOldAnotaciones);
                }
            }
            for (Anotaciones anotacionesListNewAnotaciones : anotacionesListNew) {
                if (!anotacionesListOld.contains(anotacionesListNewAnotaciones)) {
                    Pacientes oldPaciIdOfAnotacionesListNewAnotaciones = anotacionesListNewAnotaciones.getPaciId();
                    anotacionesListNewAnotaciones.setPaciId(pacientes);
                    anotacionesListNewAnotaciones = em.merge(anotacionesListNewAnotaciones);
                    if (oldPaciIdOfAnotacionesListNewAnotaciones != null && !oldPaciIdOfAnotacionesListNewAnotaciones.equals(pacientes)) {
                        oldPaciIdOfAnotacionesListNewAnotaciones.getAnotacionesList().remove(anotacionesListNewAnotaciones);
                        oldPaciIdOfAnotacionesListNewAnotaciones = em.merge(oldPaciIdOfAnotacionesListNewAnotaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pacientes.getPaciId();
                if (findPacientes(id) == null) {
                    throw new NonexistentEntityException("The pacientes with id " + id + " no longer exists.");
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
            Pacientes pacientes;
            try {
                pacientes = em.getReference(Pacientes.class, id);
                pacientes.getPaciId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pacientes with id " + id + " no longer exists.", enfe);
            }
            List<Anotaciones> anotacionesList = pacientes.getAnotacionesList();
            for (Anotaciones anotacionesListAnotaciones : anotacionesList) {
                anotacionesListAnotaciones.setPaciId(null);
                anotacionesListAnotaciones = em.merge(anotacionesListAnotaciones);
            }
            em.remove(pacientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pacientes> findPacientesEntities() {
        return findPacientesEntities(true, -1, -1);
    }

    public List<Pacientes> findPacientesEntities(int maxResults, int firstResult) {
        return findPacientesEntities(false, maxResults, firstResult);
    }

    private List<Pacientes> findPacientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pacientes.class));
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

    public Pacientes findPacientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pacientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pacientes> rt = cq.from(Pacientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
