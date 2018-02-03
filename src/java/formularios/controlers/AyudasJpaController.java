/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.NonexistentEntityException;
import formularios.entidades.Ayudas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import formularios.entidades.Campos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author D4V3
 */
public class AyudasJpaController implements Serializable {

    public AyudasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ayudas ayudas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Campos campId = ayudas.getCampId();
            if (campId != null) {
                campId = em.getReference(campId.getClass(), campId.getCampId());
                ayudas.setCampId(campId);
            }
            em.persist(ayudas);
            if (campId != null) {
                campId.getAyudasList().add(ayudas);
                campId = em.merge(campId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ayudas ayudas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ayudas persistentAyudas = em.find(Ayudas.class, ayudas.getAyudId());
            Campos campIdOld = persistentAyudas.getCampId();
            Campos campIdNew = ayudas.getCampId();
            if (campIdNew != null) {
                campIdNew = em.getReference(campIdNew.getClass(), campIdNew.getCampId());
                ayudas.setCampId(campIdNew);
            }
            ayudas = em.merge(ayudas);
            if (campIdOld != null && !campIdOld.equals(campIdNew)) {
                campIdOld.getAyudasList().remove(ayudas);
                campIdOld = em.merge(campIdOld);
            }
            if (campIdNew != null && !campIdNew.equals(campIdOld)) {
                campIdNew.getAyudasList().add(ayudas);
                campIdNew = em.merge(campIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ayudas.getAyudId();
                if (findAyudas(id) == null) {
                    throw new NonexistentEntityException("The ayudas with id " + id + " no longer exists.");
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
            Ayudas ayudas;
            try {
                ayudas = em.getReference(Ayudas.class, id);
                ayudas.getAyudId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ayudas with id " + id + " no longer exists.", enfe);
            }
            Campos campId = ayudas.getCampId();
            if (campId != null) {
                campId.getAyudasList().remove(ayudas);
                campId = em.merge(campId);
            }
            em.remove(ayudas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ayudas> findAyudasEntities() {
        return findAyudasEntities(true, -1, -1);
    }

    public List<Ayudas> findAyudasEntities(int maxResults, int firstResult) {
        return findAyudasEntities(false, maxResults, firstResult);
    }

    private List<Ayudas> findAyudasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ayudas.class));
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

    public Ayudas findAyudas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ayudas.class, id);
        } finally {
            em.close();
        }
    }

    public int getAyudasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ayudas> rt = cq.from(Ayudas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
