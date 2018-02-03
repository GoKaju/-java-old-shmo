/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.NonexistentEntityException;
import formularios.entidades.ClienteSedes;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ocupacional.JPA.valueobjects.Clientes;

/**
 *
 * @author D4V3
 */
public class ClienteSedesJpaController implements Serializable {

    public ClienteSedesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClienteSedes clienteSedes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clieId = clienteSedes.getClieId();
            if (clieId != null) {
                clieId = em.getReference(clieId.getClass(), clieId.getClieId());
                clienteSedes.setClieId(clieId);
            }
            em.persist(clienteSedes);
            if (clieId != null) {
                clieId.getClienteSedesList().add(clienteSedes);
                clieId = em.merge(clieId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClienteSedes clienteSedes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClienteSedes persistentClienteSedes = em.find(ClienteSedes.class, clienteSedes.getClsedId());
            Clientes clieIdOld = persistentClienteSedes.getClieId();
            Clientes clieIdNew = clienteSedes.getClieId();
            if (clieIdNew != null) {
                clieIdNew = em.getReference(clieIdNew.getClass(), clieIdNew.getClieId());
                clienteSedes.setClieId(clieIdNew);
            }
            clienteSedes = em.merge(clienteSedes);
            if (clieIdOld != null && !clieIdOld.equals(clieIdNew)) {
                clieIdOld.getClienteSedesList().remove(clienteSedes);
                clieIdOld = em.merge(clieIdOld);
            }
            if (clieIdNew != null && !clieIdNew.equals(clieIdOld)) {
                clieIdNew.getClienteSedesList().add(clienteSedes);
                clieIdNew = em.merge(clieIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clienteSedes.getClsedId();
                if (findClienteSedes(id) == null) {
                    throw new NonexistentEntityException("The clienteSedes with id " + id + " no longer exists.");
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
            ClienteSedes clienteSedes;
            try {
                clienteSedes = em.getReference(ClienteSedes.class, id);
                clienteSedes.getClsedId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clienteSedes with id " + id + " no longer exists.", enfe);
            }
            Clientes clieId = clienteSedes.getClieId();
            if (clieId != null) {
                clieId.getClienteSedesList().remove(clienteSedes);
                clieId = em.merge(clieId);
            }
            em.remove(clienteSedes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ClienteSedes> findClienteSedesEntities() {
        return findClienteSedesEntities(true, -1, -1);
    }

    public List<ClienteSedes> findClienteSedesEntities(int maxResults, int firstResult) {
        return findClienteSedesEntities(false, maxResults, firstResult);
    }

    private List<ClienteSedes> findClienteSedesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClienteSedes.class));
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

    public ClienteSedes findClienteSedes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClienteSedes.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteSedesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClienteSedes> rt = cq.from(ClienteSedes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
