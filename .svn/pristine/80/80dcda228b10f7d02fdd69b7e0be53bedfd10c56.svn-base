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
import formularios.entidades.Opciones;
import java.util.ArrayList;
import java.util.List;
import formularios.entidades.Ayudas;
import formularios.entidades.Campos;
import formularios.entidades.Respuestas;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author D4V3
 */
public class CamposJpaController implements Serializable {

    public CamposJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Campos campos) {
        if (campos.getOpcionesList() == null) {
            campos.setOpcionesList(new ArrayList<Opciones>());
        }
        if (campos.getAyudasList() == null) {
            campos.setAyudasList(new ArrayList<Ayudas>());
        }
        if (campos.getRespuestasList() == null) {
            campos.setRespuestasList(new ArrayList<Respuestas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias cateId = campos.getCateId();
            if (cateId != null) {
                cateId = em.getReference(cateId.getClass(), cateId.getCateId());
                campos.setCateId(cateId);
            }
            List<Opciones> attachedOpcionesList = new ArrayList<Opciones>();
            for (Opciones opcionesListOpcionesToAttach : campos.getOpcionesList()) {
                opcionesListOpcionesToAttach = em.getReference(opcionesListOpcionesToAttach.getClass(), opcionesListOpcionesToAttach.getOpciId());
                attachedOpcionesList.add(opcionesListOpcionesToAttach);
            }
            campos.setOpcionesList(attachedOpcionesList);
            List<Ayudas> attachedAyudasList = new ArrayList<Ayudas>();
            for (Ayudas ayudasListAyudasToAttach : campos.getAyudasList()) {
                ayudasListAyudasToAttach = em.getReference(ayudasListAyudasToAttach.getClass(), ayudasListAyudasToAttach.getAyudId());
                attachedAyudasList.add(ayudasListAyudasToAttach);
            }
            campos.setAyudasList(attachedAyudasList);
            List<Respuestas> attachedRespuestasList = new ArrayList<Respuestas>();
            for (Respuestas respuestasListRespuestasToAttach : campos.getRespuestasList()) {
                respuestasListRespuestasToAttach = em.getReference(respuestasListRespuestasToAttach.getClass(), respuestasListRespuestasToAttach.getRespId());
                attachedRespuestasList.add(respuestasListRespuestasToAttach);
            }
            campos.setRespuestasList(attachedRespuestasList);
            em.persist(campos);
            if (cateId != null) {
                cateId.getCamposList().add(campos);
                cateId = em.merge(cateId);
            }
            for (Opciones opcionesListOpciones : campos.getOpcionesList()) {
                Campos oldCampIdOfOpcionesListOpciones = opcionesListOpciones.getCampId();
                opcionesListOpciones.setCampId(campos);
                opcionesListOpciones = em.merge(opcionesListOpciones);
                if (oldCampIdOfOpcionesListOpciones != null) {
                    oldCampIdOfOpcionesListOpciones.getOpcionesList().remove(opcionesListOpciones);
                    oldCampIdOfOpcionesListOpciones = em.merge(oldCampIdOfOpcionesListOpciones);
                }
            }
            for (Ayudas ayudasListAyudas : campos.getAyudasList()) {
                Campos oldCampIdOfAyudasListAyudas = ayudasListAyudas.getCampId();
                ayudasListAyudas.setCampId(campos);
                ayudasListAyudas = em.merge(ayudasListAyudas);
                if (oldCampIdOfAyudasListAyudas != null) {
                    oldCampIdOfAyudasListAyudas.getAyudasList().remove(ayudasListAyudas);
                    oldCampIdOfAyudasListAyudas = em.merge(oldCampIdOfAyudasListAyudas);
                }
            }
            for (Respuestas respuestasListRespuestas : campos.getRespuestasList()) {
                Campos oldCampIdOfRespuestasListRespuestas = respuestasListRespuestas.getCampId();
                respuestasListRespuestas.setCampId(campos);
                respuestasListRespuestas = em.merge(respuestasListRespuestas);
                if (oldCampIdOfRespuestasListRespuestas != null) {
                    oldCampIdOfRespuestasListRespuestas.getRespuestasList().remove(respuestasListRespuestas);
                    oldCampIdOfRespuestasListRespuestas = em.merge(oldCampIdOfRespuestasListRespuestas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Campos campos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Campos persistentCampos = em.find(Campos.class, campos.getCampId());
            Categorias cateIdOld = persistentCampos.getCateId();
            Categorias cateIdNew = campos.getCateId();
            List<Opciones> opcionesListOld = persistentCampos.getOpcionesList();
            List<Opciones> opcionesListNew = campos.getOpcionesList();
            List<Ayudas> ayudasListOld = persistentCampos.getAyudasList();
            List<Ayudas> ayudasListNew = campos.getAyudasList();
            List<Respuestas> respuestasListOld = persistentCampos.getRespuestasList();
            List<Respuestas> respuestasListNew = campos.getRespuestasList();
            if (cateIdNew != null) {
                cateIdNew = em.getReference(cateIdNew.getClass(), cateIdNew.getCateId());
                campos.setCateId(cateIdNew);
            }
            List<Opciones> attachedOpcionesListNew = new ArrayList<Opciones>();
            for (Opciones opcionesListNewOpcionesToAttach : opcionesListNew) {
                opcionesListNewOpcionesToAttach = em.getReference(opcionesListNewOpcionesToAttach.getClass(), opcionesListNewOpcionesToAttach.getOpciId());
                attachedOpcionesListNew.add(opcionesListNewOpcionesToAttach);
            }
            opcionesListNew = attachedOpcionesListNew;
            campos.setOpcionesList(opcionesListNew);
            List<Ayudas> attachedAyudasListNew = new ArrayList<Ayudas>();
            for (Ayudas ayudasListNewAyudasToAttach : ayudasListNew) {
                ayudasListNewAyudasToAttach = em.getReference(ayudasListNewAyudasToAttach.getClass(), ayudasListNewAyudasToAttach.getAyudId());
                attachedAyudasListNew.add(ayudasListNewAyudasToAttach);
            }
            ayudasListNew = attachedAyudasListNew;
            campos.setAyudasList(ayudasListNew);
            List<Respuestas> attachedRespuestasListNew = new ArrayList<Respuestas>();
            for (Respuestas respuestasListNewRespuestasToAttach : respuestasListNew) {
                respuestasListNewRespuestasToAttach = em.getReference(respuestasListNewRespuestasToAttach.getClass(), respuestasListNewRespuestasToAttach.getRespId());
                attachedRespuestasListNew.add(respuestasListNewRespuestasToAttach);
            }
            respuestasListNew = attachedRespuestasListNew;
            campos.setRespuestasList(respuestasListNew);
            campos = em.merge(campos);
            if (cateIdOld != null && !cateIdOld.equals(cateIdNew)) {
                cateIdOld.getCamposList().remove(campos);
                cateIdOld = em.merge(cateIdOld);
            }
            if (cateIdNew != null && !cateIdNew.equals(cateIdOld)) {
                cateIdNew.getCamposList().add(campos);
                cateIdNew = em.merge(cateIdNew);
            }
            for (Opciones opcionesListOldOpciones : opcionesListOld) {
                if (!opcionesListNew.contains(opcionesListOldOpciones)) {
                    opcionesListOldOpciones.setCampId(null);
                    opcionesListOldOpciones = em.merge(opcionesListOldOpciones);
                }
            }
            for (Opciones opcionesListNewOpciones : opcionesListNew) {
                if (!opcionesListOld.contains(opcionesListNewOpciones)) {
                    Campos oldCampIdOfOpcionesListNewOpciones = opcionesListNewOpciones.getCampId();
                    opcionesListNewOpciones.setCampId(campos);
                    opcionesListNewOpciones = em.merge(opcionesListNewOpciones);
                    if (oldCampIdOfOpcionesListNewOpciones != null && !oldCampIdOfOpcionesListNewOpciones.equals(campos)) {
                        oldCampIdOfOpcionesListNewOpciones.getOpcionesList().remove(opcionesListNewOpciones);
                        oldCampIdOfOpcionesListNewOpciones = em.merge(oldCampIdOfOpcionesListNewOpciones);
                    }
                }
            }
            for (Ayudas ayudasListOldAyudas : ayudasListOld) {
                if (!ayudasListNew.contains(ayudasListOldAyudas)) {
                    ayudasListOldAyudas.setCampId(null);
                    ayudasListOldAyudas = em.merge(ayudasListOldAyudas);
                }
            }
            for (Ayudas ayudasListNewAyudas : ayudasListNew) {
                if (!ayudasListOld.contains(ayudasListNewAyudas)) {
                    Campos oldCampIdOfAyudasListNewAyudas = ayudasListNewAyudas.getCampId();
                    ayudasListNewAyudas.setCampId(campos);
                    ayudasListNewAyudas = em.merge(ayudasListNewAyudas);
                    if (oldCampIdOfAyudasListNewAyudas != null && !oldCampIdOfAyudasListNewAyudas.equals(campos)) {
                        oldCampIdOfAyudasListNewAyudas.getAyudasList().remove(ayudasListNewAyudas);
                        oldCampIdOfAyudasListNewAyudas = em.merge(oldCampIdOfAyudasListNewAyudas);
                    }
                }
            }
            for (Respuestas respuestasListOldRespuestas : respuestasListOld) {
                if (!respuestasListNew.contains(respuestasListOldRespuestas)) {
                    respuestasListOldRespuestas.setCampId(null);
                    respuestasListOldRespuestas = em.merge(respuestasListOldRespuestas);
                }
            }
            for (Respuestas respuestasListNewRespuestas : respuestasListNew) {
                if (!respuestasListOld.contains(respuestasListNewRespuestas)) {
                    Campos oldCampIdOfRespuestasListNewRespuestas = respuestasListNewRespuestas.getCampId();
                    respuestasListNewRespuestas.setCampId(campos);
                    respuestasListNewRespuestas = em.merge(respuestasListNewRespuestas);
                    if (oldCampIdOfRespuestasListNewRespuestas != null && !oldCampIdOfRespuestasListNewRespuestas.equals(campos)) {
                        oldCampIdOfRespuestasListNewRespuestas.getRespuestasList().remove(respuestasListNewRespuestas);
                        oldCampIdOfRespuestasListNewRespuestas = em.merge(oldCampIdOfRespuestasListNewRespuestas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = campos.getCampId();
                if (findCampos(id) == null) {
                    throw new NonexistentEntityException("The campos with id " + id + " no longer exists.");
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
            Campos campos;
            try {
                campos = em.getReference(Campos.class, id);
                campos.getCampId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The campos with id " + id + " no longer exists.", enfe);
            }
            Categorias cateId = campos.getCateId();
            if (cateId != null) {
                cateId.getCamposList().remove(campos);
                cateId = em.merge(cateId);
            }
            List<Opciones> opcionesList = campos.getOpcionesList();
            for (Opciones opcionesListOpciones : opcionesList) {
                opcionesListOpciones.setCampId(null);
                opcionesListOpciones = em.merge(opcionesListOpciones);
            }
            List<Ayudas> ayudasList = campos.getAyudasList();
            for (Ayudas ayudasListAyudas : ayudasList) {
                ayudasListAyudas.setCampId(null);
                ayudasListAyudas = em.merge(ayudasListAyudas);
            }
            List<Respuestas> respuestasList = campos.getRespuestasList();
            for (Respuestas respuestasListRespuestas : respuestasList) {
                respuestasListRespuestas.setCampId(null);
                respuestasListRespuestas = em.merge(respuestasListRespuestas);
            }
            em.remove(campos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Campos> findCamposEntities() {
        return findCamposEntities(true, -1, -1);
    }

    public List<Campos> findCamposEntities(int maxResults, int firstResult) {
        return findCamposEntities(false, maxResults, firstResult);
    }

    private List<Campos> findCamposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Campos.class));
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

    public Campos findCampos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Campos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCamposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Campos> rt = cq.from(Campos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
