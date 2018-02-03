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
import formularios.entidades.Campos;
import formularios.entidades.Respuestas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author D4V3
 */
public class RespuestasJpaController implements Serializable {

    public RespuestasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Respuestas respuestas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Anotaciones anotId = respuestas.getAnotId();
            if (anotId != null) {
                anotId = em.getReference(anotId.getClass(), anotId.getAnotId());
                respuestas.setAnotId(anotId);
            }
            Campos campId = respuestas.getCampId();
            if (campId != null) {
                campId = em.getReference(campId.getClass(), campId.getCampId());
                respuestas.setCampId(campId);
            }
            em.persist(respuestas);
            if (anotId != null) {
                anotId.getRespuestasList().add(respuestas);
                anotId = em.merge(anotId);
            }
            if (campId != null) {
                campId.getRespuestasList().add(respuestas);
                campId = em.merge(campId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public boolean create(List<Respuestas> respuestas) {
        boolean e = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            for (Respuestas respuesta : respuestas) {
            Anotaciones anotId = respuesta.getAnotId();
            if (anotId != null) {
                anotId = em.getReference(anotId.getClass(), anotId.getAnotId());
                respuesta.setAnotId(anotId);
            }
            Campos campId = respuesta.getCampId();
            if (campId != null) {
                campId = em.getReference(campId.getClass(), campId.getCampId());
                respuesta.setCampId(campId);
            }
            em.persist(respuesta);
//            if (anotId != null) {
//                anotId.getRespuestasList().add(respuesta);
//                anotId = em.merge(anotId);
//            }
//            if (campId != null) {
//                campId.getRespuestasList().add(respuesta);
//                campId = em.merge(campId);
//            }
            }
            em.getTransaction().commit();
            e=true;
        }catch(Exception ex){
           e= false;
        ex.printStackTrace();
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
        return e;
    }

    public void edit(Respuestas respuestas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestas persistentRespuestas = em.find(Respuestas.class, respuestas.getRespId());
            Anotaciones anotIdOld = persistentRespuestas.getAnotId();
            Anotaciones anotIdNew = respuestas.getAnotId();
            Campos campIdOld = persistentRespuestas.getCampId();
            Campos campIdNew = respuestas.getCampId();
            if (anotIdNew != null) {
                anotIdNew = em.getReference(anotIdNew.getClass(), anotIdNew.getAnotId());
                respuestas.setAnotId(anotIdNew);
            }
            if (campIdNew != null) {
                campIdNew = em.getReference(campIdNew.getClass(), campIdNew.getCampId());
                respuestas.setCampId(campIdNew);
            }
            respuestas = em.merge(respuestas);
            if (anotIdOld != null && !anotIdOld.equals(anotIdNew)) {
                anotIdOld.getRespuestasList().remove(respuestas);
                anotIdOld = em.merge(anotIdOld);
            }
            if (anotIdNew != null && !anotIdNew.equals(anotIdOld)) {
                anotIdNew.getRespuestasList().add(respuestas);
                anotIdNew = em.merge(anotIdNew);
            }
            if (campIdOld != null && !campIdOld.equals(campIdNew)) {
                campIdOld.getRespuestasList().remove(respuestas);
                campIdOld = em.merge(campIdOld);
            }
            if (campIdNew != null && !campIdNew.equals(campIdOld)) {
                campIdNew.getRespuestasList().add(respuestas);
                campIdNew = em.merge(campIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = respuestas.getRespId();
                if (findRespuestas(id) == null) {
                    throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.");
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
            Respuestas respuestas;
            try {
                respuestas = em.getReference(Respuestas.class, id);
                respuestas.getRespId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.", enfe);
            }
            Anotaciones anotId = respuestas.getAnotId();
            if (anotId != null) {
                anotId.getRespuestasList().remove(respuestas);
                anotId = em.merge(anotId);
            }
            Campos campId = respuestas.getCampId();
            if (campId != null) {
                campId.getRespuestasList().remove(respuestas);
                campId = em.merge(campId);
            }
            em.remove(respuestas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Respuestas> findRespuestasEntities() {
        return findRespuestasEntities(true, -1, -1);
    }

    public List<Respuestas> findRespuestasEntities(int maxResults, int firstResult) {
        return findRespuestasEntities(false, maxResults, firstResult);
    }

    private List<Respuestas> findRespuestasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Respuestas.class));
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

    public Respuestas findRespuestas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Respuestas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Respuestas> rt = cq.from(Respuestas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
