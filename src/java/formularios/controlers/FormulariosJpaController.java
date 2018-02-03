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
import formularios.entidades.Categorias;
import java.util.ArrayList;
import java.util.List;
import formularios.entidades.Anotaciones;
import formularios.entidades.Formularios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author D4V3
 */
public class FormulariosJpaController implements Serializable {

    public FormulariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formularios formularios) {
        if (formularios.getCategoriasList() == null) {
            formularios.setCategoriasList(new ArrayList<Categorias>());
        }
        if (formularios.getAnotacionesList() == null) {
            formularios.setAnotacionesList(new ArrayList<Anotaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Categorias> attachedCategoriasList = new ArrayList<Categorias>();
            for (Categorias categoriasListCategoriasToAttach : formularios.getCategoriasList()) {
                categoriasListCategoriasToAttach = em.getReference(categoriasListCategoriasToAttach.getClass(), categoriasListCategoriasToAttach.getCateId());
                attachedCategoriasList.add(categoriasListCategoriasToAttach);
            }
            formularios.setCategoriasList(attachedCategoriasList);
            List<Anotaciones> attachedAnotacionesList = new ArrayList<Anotaciones>();
            for (Anotaciones anotacionesListAnotacionesToAttach : formularios.getAnotacionesList()) {
                anotacionesListAnotacionesToAttach = em.getReference(anotacionesListAnotacionesToAttach.getClass(), anotacionesListAnotacionesToAttach.getAnotId());
                attachedAnotacionesList.add(anotacionesListAnotacionesToAttach);
            }
            formularios.setAnotacionesList(attachedAnotacionesList);
            em.persist(formularios);
            for (Categorias categoriasListCategorias : formularios.getCategoriasList()) {
                Formularios oldFormIdOfCategoriasListCategorias = categoriasListCategorias.getFormId();
                categoriasListCategorias.setFormId(formularios);
                categoriasListCategorias = em.merge(categoriasListCategorias);
                if (oldFormIdOfCategoriasListCategorias != null) {
                    oldFormIdOfCategoriasListCategorias.getCategoriasList().remove(categoriasListCategorias);
                    oldFormIdOfCategoriasListCategorias = em.merge(oldFormIdOfCategoriasListCategorias);
                }
            }
            for (Anotaciones anotacionesListAnotaciones : formularios.getAnotacionesList()) {
                Formularios oldFormIdOfAnotacionesListAnotaciones = anotacionesListAnotaciones.getFormId();
                anotacionesListAnotaciones.setFormId(formularios);
                anotacionesListAnotaciones = em.merge(anotacionesListAnotaciones);
                if (oldFormIdOfAnotacionesListAnotaciones != null) {
                    oldFormIdOfAnotacionesListAnotaciones.getAnotacionesList().remove(anotacionesListAnotaciones);
                    oldFormIdOfAnotacionesListAnotaciones = em.merge(oldFormIdOfAnotacionesListAnotaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formularios formularios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formularios persistentFormularios = em.find(Formularios.class, formularios.getFormId());
            List<Categorias> categoriasListOld = persistentFormularios.getCategoriasList();
            List<Categorias> categoriasListNew = formularios.getCategoriasList();
            List<Anotaciones> anotacionesListOld = persistentFormularios.getAnotacionesList();
            List<Anotaciones> anotacionesListNew = formularios.getAnotacionesList();
            List<Categorias> attachedCategoriasListNew = new ArrayList<Categorias>();
            for (Categorias categoriasListNewCategoriasToAttach : categoriasListNew) {
                categoriasListNewCategoriasToAttach = em.getReference(categoriasListNewCategoriasToAttach.getClass(), categoriasListNewCategoriasToAttach.getCateId());
                attachedCategoriasListNew.add(categoriasListNewCategoriasToAttach);
            }
            categoriasListNew = attachedCategoriasListNew;
            formularios.setCategoriasList(categoriasListNew);
            List<Anotaciones> attachedAnotacionesListNew = new ArrayList<Anotaciones>();
            for (Anotaciones anotacionesListNewAnotacionesToAttach : anotacionesListNew) {
                anotacionesListNewAnotacionesToAttach = em.getReference(anotacionesListNewAnotacionesToAttach.getClass(), anotacionesListNewAnotacionesToAttach.getAnotId());
                attachedAnotacionesListNew.add(anotacionesListNewAnotacionesToAttach);
            }
            anotacionesListNew = attachedAnotacionesListNew;
            formularios.setAnotacionesList(anotacionesListNew);
            formularios = em.merge(formularios);
            for (Categorias categoriasListOldCategorias : categoriasListOld) {
                if (!categoriasListNew.contains(categoriasListOldCategorias)) {
                    categoriasListOldCategorias.setFormId(null);
                    categoriasListOldCategorias = em.merge(categoriasListOldCategorias);
                }
            }
            for (Categorias categoriasListNewCategorias : categoriasListNew) {
                if (!categoriasListOld.contains(categoriasListNewCategorias)) {
                    Formularios oldFormIdOfCategoriasListNewCategorias = categoriasListNewCategorias.getFormId();
                    categoriasListNewCategorias.setFormId(formularios);
                    categoriasListNewCategorias = em.merge(categoriasListNewCategorias);
                    if (oldFormIdOfCategoriasListNewCategorias != null && !oldFormIdOfCategoriasListNewCategorias.equals(formularios)) {
                        oldFormIdOfCategoriasListNewCategorias.getCategoriasList().remove(categoriasListNewCategorias);
                        oldFormIdOfCategoriasListNewCategorias = em.merge(oldFormIdOfCategoriasListNewCategorias);
                    }
                }
            }
            for (Anotaciones anotacionesListOldAnotaciones : anotacionesListOld) {
                if (!anotacionesListNew.contains(anotacionesListOldAnotaciones)) {
                    anotacionesListOldAnotaciones.setFormId(null);
                    anotacionesListOldAnotaciones = em.merge(anotacionesListOldAnotaciones);
                }
            }
            for (Anotaciones anotacionesListNewAnotaciones : anotacionesListNew) {
                if (!anotacionesListOld.contains(anotacionesListNewAnotaciones)) {
                    Formularios oldFormIdOfAnotacionesListNewAnotaciones = anotacionesListNewAnotaciones.getFormId();
                    anotacionesListNewAnotaciones.setFormId(formularios);
                    anotacionesListNewAnotaciones = em.merge(anotacionesListNewAnotaciones);
                    if (oldFormIdOfAnotacionesListNewAnotaciones != null && !oldFormIdOfAnotacionesListNewAnotaciones.equals(formularios)) {
                        oldFormIdOfAnotacionesListNewAnotaciones.getAnotacionesList().remove(anotacionesListNewAnotaciones);
                        oldFormIdOfAnotacionesListNewAnotaciones = em.merge(oldFormIdOfAnotacionesListNewAnotaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formularios.getFormId();
                if (findFormularios(id) == null) {
                    throw new NonexistentEntityException("The formularios with id " + id + " no longer exists.");
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
            Formularios formularios;
            try {
                formularios = em.getReference(Formularios.class, id);
                formularios.getFormId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formularios with id " + id + " no longer exists.", enfe);
            }
            List<Categorias> categoriasList = formularios.getCategoriasList();
            for (Categorias categoriasListCategorias : categoriasList) {
                categoriasListCategorias.setFormId(null);
                categoriasListCategorias = em.merge(categoriasListCategorias);
            }
            List<Anotaciones> anotacionesList = formularios.getAnotacionesList();
            for (Anotaciones anotacionesListAnotaciones : anotacionesList) {
                anotacionesListAnotaciones.setFormId(null);
                anotacionesListAnotaciones = em.merge(anotacionesListAnotaciones);
            }
            em.remove(formularios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formularios> findFormulariosEntities() {
        return findFormulariosEntities(true, -1, -1);
    }

    public List<Formularios> findFormulariosEntities(int maxResults, int firstResult) {
        return findFormulariosEntities(false, maxResults, firstResult);
    }

    private List<Formularios> findFormulariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formularios.class));
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

    public Formularios findFormularios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formularios.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormulariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formularios> rt = cq.from(Formularios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
