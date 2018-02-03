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
import formularios.entidades.Formularios;
import formularios.entidades.Campos;
import formularios.entidades.Categorias;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author D4V3
 */
public class CategoriasJpaController implements Serializable {

    public CategoriasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categorias categorias) {
        if (categorias.getCamposList() == null) {
            categorias.setCamposList(new ArrayList<Campos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formularios formId = categorias.getFormId();
            if (formId != null) {
                formId = em.getReference(formId.getClass(), formId.getFormId());
                categorias.setFormId(formId);
            }
            List<Campos> attachedCamposList = new ArrayList<Campos>();
            for (Campos camposListCamposToAttach : categorias.getCamposList()) {
                camposListCamposToAttach = em.getReference(camposListCamposToAttach.getClass(), camposListCamposToAttach.getCampId());
                attachedCamposList.add(camposListCamposToAttach);
            }
            categorias.setCamposList(attachedCamposList);
            em.persist(categorias);
            if (formId != null) {
                formId.getCategoriasList().add(categorias);
                formId = em.merge(formId);
            }
            for (Campos camposListCampos : categorias.getCamposList()) {
                Categorias oldCateIdOfCamposListCampos = camposListCampos.getCateId();
                camposListCampos.setCateId(categorias);
                camposListCampos = em.merge(camposListCampos);
                if (oldCateIdOfCamposListCampos != null) {
                    oldCateIdOfCamposListCampos.getCamposList().remove(camposListCampos);
                    oldCateIdOfCamposListCampos = em.merge(oldCateIdOfCamposListCampos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categorias categorias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias persistentCategorias = em.find(Categorias.class, categorias.getCateId());
            Formularios formIdOld = persistentCategorias.getFormId();
            Formularios formIdNew = categorias.getFormId();
            List<Campos> camposListOld = persistentCategorias.getCamposList();
            List<Campos> camposListNew = categorias.getCamposList();
            if (formIdNew != null) {
                formIdNew = em.getReference(formIdNew.getClass(), formIdNew.getFormId());
                categorias.setFormId(formIdNew);
            }
            List<Campos> attachedCamposListNew = new ArrayList<Campos>();
            for (Campos camposListNewCamposToAttach : camposListNew) {
                camposListNewCamposToAttach = em.getReference(camposListNewCamposToAttach.getClass(), camposListNewCamposToAttach.getCampId());
                attachedCamposListNew.add(camposListNewCamposToAttach);
            }
            camposListNew = attachedCamposListNew;
            categorias.setCamposList(camposListNew);
            categorias = em.merge(categorias);
            if (formIdOld != null && !formIdOld.equals(formIdNew)) {
                formIdOld.getCategoriasList().remove(categorias);
                formIdOld = em.merge(formIdOld);
            }
            if (formIdNew != null && !formIdNew.equals(formIdOld)) {
                formIdNew.getCategoriasList().add(categorias);
                formIdNew = em.merge(formIdNew);
            }
            for (Campos camposListOldCampos : camposListOld) {
                if (!camposListNew.contains(camposListOldCampos)) {
                    camposListOldCampos.setCateId(null);
                    camposListOldCampos = em.merge(camposListOldCampos);
                }
            }
            for (Campos camposListNewCampos : camposListNew) {
                if (!camposListOld.contains(camposListNewCampos)) {
                    Categorias oldCateIdOfCamposListNewCampos = camposListNewCampos.getCateId();
                    camposListNewCampos.setCateId(categorias);
                    camposListNewCampos = em.merge(camposListNewCampos);
                    if (oldCateIdOfCamposListNewCampos != null && !oldCateIdOfCamposListNewCampos.equals(categorias)) {
                        oldCateIdOfCamposListNewCampos.getCamposList().remove(camposListNewCampos);
                        oldCateIdOfCamposListNewCampos = em.merge(oldCateIdOfCamposListNewCampos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categorias.getCateId();
                if (findCategorias(id) == null) {
                    throw new NonexistentEntityException("The categorias with id " + id + " no longer exists.");
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
            Categorias categorias;
            try {
                categorias = em.getReference(Categorias.class, id);
                categorias.getCateId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categorias with id " + id + " no longer exists.", enfe);
            }
            Formularios formId = categorias.getFormId();
            if (formId != null) {
                formId.getCategoriasList().remove(categorias);
                formId = em.merge(formId);
            }
            List<Campos> camposList = categorias.getCamposList();
            for (Campos camposListCampos : camposList) {
                camposListCampos.setCateId(null);
                camposListCampos = em.merge(camposListCampos);
            }
            em.remove(categorias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categorias> findCategoriasEntities() {
        return findCategoriasEntities(true, -1, -1);
    }

    public List<Categorias> findCategoriasEntities(int maxResults, int firstResult) {
        return findCategoriasEntities(false, maxResults, firstResult);
    }

    private List<Categorias> findCategoriasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categorias.class));
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

    public Categorias findCategorias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categorias.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categorias> rt = cq.from(Categorias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
