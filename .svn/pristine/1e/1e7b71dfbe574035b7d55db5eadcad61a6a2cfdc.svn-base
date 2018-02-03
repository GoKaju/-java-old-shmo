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
import ocupacional.JPA.valueobjects.Centrocostos;
import ocupacional.JPA.valueobjects.Movimientosfacturacion;
import ocupacional.JPA.valueobjects.Itemfactura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ocupacional.JPA.controlers.exceptions.IllegalOrphanException;
import ocupacional.JPA.controlers.exceptions.NonexistentEntityException;
import ocupacional.JPA.valueobjects.Facturas;

/**
 *
 * @author D4V3
 */
public class FacturasJpaController implements Serializable {

    public FacturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facturas facturas) {
        if (facturas.getItemfacturaList() == null) {
            facturas.setItemfacturaList(new ArrayList<Itemfactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centrocostos cecoId = facturas.getCecoId();
            if (cecoId != null) {
                cecoId = em.getReference(cecoId.getClass(), cecoId.getCecoId());
                facturas.setCecoId(cecoId);
            }
            Movimientosfacturacion mofaId = facturas.getMofaId();
            if (mofaId != null) {
                mofaId = em.getReference(mofaId.getClass(), mofaId.getMofaId());
                facturas.setMofaId(mofaId);
            }
            List<Itemfactura> attachedItemfacturaList = new ArrayList<Itemfactura>();
            for (Itemfactura itemfacturaListItemfacturaToAttach : facturas.getItemfacturaList()) {
                itemfacturaListItemfacturaToAttach = em.getReference(itemfacturaListItemfacturaToAttach.getClass(), itemfacturaListItemfacturaToAttach.getItfaId());
                attachedItemfacturaList.add(itemfacturaListItemfacturaToAttach);
            }
            facturas.setItemfacturaList(attachedItemfacturaList);
            em.persist(facturas);
            if (cecoId != null) {
                cecoId.getFacturasList().add(facturas);
                cecoId = em.merge(cecoId);
            }
            if (mofaId != null) {
                mofaId.getFacturasList().add(facturas);
                mofaId = em.merge(mofaId);
            }
            for (Itemfactura itemfacturaListItemfactura : facturas.getItemfacturaList()) {
                Facturas oldFactIdOfItemfacturaListItemfactura = itemfacturaListItemfactura.getFactId();
                itemfacturaListItemfactura.setFactId(facturas);
                itemfacturaListItemfactura = em.merge(itemfacturaListItemfactura);
                if (oldFactIdOfItemfacturaListItemfactura != null) {
                    oldFactIdOfItemfacturaListItemfactura.getItemfacturaList().remove(itemfacturaListItemfactura);
                    oldFactIdOfItemfacturaListItemfactura = em.merge(oldFactIdOfItemfacturaListItemfactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturas facturas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturas persistentFacturas = em.find(Facturas.class, facturas.getFactId());
            Centrocostos cecoIdOld = persistentFacturas.getCecoId();
            Centrocostos cecoIdNew = facturas.getCecoId();
            Movimientosfacturacion mofaIdOld = persistentFacturas.getMofaId();
            Movimientosfacturacion mofaIdNew = facturas.getMofaId();
            List<Itemfactura> itemfacturaListOld = persistentFacturas.getItemfacturaList();
            List<Itemfactura> itemfacturaListNew = facturas.getItemfacturaList();
            List<String> illegalOrphanMessages = null;
            for (Itemfactura itemfacturaListOldItemfactura : itemfacturaListOld) {
                if (!itemfacturaListNew.contains(itemfacturaListOldItemfactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itemfactura " + itemfacturaListOldItemfactura + " since its factId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cecoIdNew != null) {
                cecoIdNew = em.getReference(cecoIdNew.getClass(), cecoIdNew.getCecoId());
                facturas.setCecoId(cecoIdNew);
            }
            if (mofaIdNew != null) {
                mofaIdNew = em.getReference(mofaIdNew.getClass(), mofaIdNew.getMofaId());
                facturas.setMofaId(mofaIdNew);
            }
            List<Itemfactura> attachedItemfacturaListNew = new ArrayList<Itemfactura>();
            for (Itemfactura itemfacturaListNewItemfacturaToAttach : itemfacturaListNew) {
                itemfacturaListNewItemfacturaToAttach = em.getReference(itemfacturaListNewItemfacturaToAttach.getClass(), itemfacturaListNewItemfacturaToAttach.getItfaId());
                attachedItemfacturaListNew.add(itemfacturaListNewItemfacturaToAttach);
            }
            itemfacturaListNew = attachedItemfacturaListNew;
            facturas.setItemfacturaList(itemfacturaListNew);
            facturas = em.merge(facturas);
            if (cecoIdOld != null && !cecoIdOld.equals(cecoIdNew)) {
                cecoIdOld.getFacturasList().remove(facturas);
                cecoIdOld = em.merge(cecoIdOld);
            }
            if (cecoIdNew != null && !cecoIdNew.equals(cecoIdOld)) {
                cecoIdNew.getFacturasList().add(facturas);
                cecoIdNew = em.merge(cecoIdNew);
            }
            if (mofaIdOld != null && !mofaIdOld.equals(mofaIdNew)) {
                mofaIdOld.getFacturasList().remove(facturas);
                mofaIdOld = em.merge(mofaIdOld);
            }
            if (mofaIdNew != null && !mofaIdNew.equals(mofaIdOld)) {
                mofaIdNew.getFacturasList().add(facturas);
                mofaIdNew = em.merge(mofaIdNew);
            }
            for (Itemfactura itemfacturaListNewItemfactura : itemfacturaListNew) {
                if (!itemfacturaListOld.contains(itemfacturaListNewItemfactura)) {
                    Facturas oldFactIdOfItemfacturaListNewItemfactura = itemfacturaListNewItemfactura.getFactId();
                    itemfacturaListNewItemfactura.setFactId(facturas);
                    itemfacturaListNewItemfactura = em.merge(itemfacturaListNewItemfactura);
                    if (oldFactIdOfItemfacturaListNewItemfactura != null && !oldFactIdOfItemfacturaListNewItemfactura.equals(facturas)) {
                        oldFactIdOfItemfacturaListNewItemfactura.getItemfacturaList().remove(itemfacturaListNewItemfactura);
                        oldFactIdOfItemfacturaListNewItemfactura = em.merge(oldFactIdOfItemfacturaListNewItemfactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturas.getFactId();
                if (findFacturas(id) == null) {
                    throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.");
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
            Facturas facturas;
            try {
                facturas = em.getReference(Facturas.class, id);
                facturas.getFactId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Itemfactura> itemfacturaListOrphanCheck = facturas.getItemfacturaList();
            for (Itemfactura itemfacturaListOrphanCheckItemfactura : itemfacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Facturas (" + facturas + ") cannot be destroyed since the Itemfactura " + itemfacturaListOrphanCheckItemfactura + " in its itemfacturaList field has a non-nullable factId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Centrocostos cecoId = facturas.getCecoId();
            if (cecoId != null) {
                cecoId.getFacturasList().remove(facturas);
                cecoId = em.merge(cecoId);
            }
            Movimientosfacturacion mofaId = facturas.getMofaId();
            if (mofaId != null) {
                mofaId.getFacturasList().remove(facturas);
                mofaId = em.merge(mofaId);
            }
            em.remove(facturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturas> findFacturasEntities() {
        return findFacturasEntities(true, -1, -1);
    }

    public List<Facturas> findFacturasEntities(int maxResults, int firstResult) {
        return findFacturasEntities(false, maxResults, firstResult);
    }

    private List<Facturas> findFacturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturas.class));
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

    public Facturas findFacturas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturas> rt = cq.from(Facturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
