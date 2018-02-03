/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.controlers;

import formularios.controlers.exceptions.IllegalOrphanException;
import formularios.controlers.exceptions.NonexistentEntityException;
import formularios.entidades.Anotaciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import formularios.entidades.Pacientes;
import formularios.entidades.Formularios;
import formularios.entidades.Documentos;
import java.util.ArrayList;
import java.util.List;
import formularios.entidades.Respuestas;
import formularios.entidades.Huellafirma;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author D4V3
 */
public class AnotacionesJpaController implements Serializable {

    public AnotacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Anotaciones anotaciones) {
        if (anotaciones.getDocumentosList() == null) {
            anotaciones.setDocumentosList(new ArrayList<Documentos>());
        }
        if (anotaciones.getRespuestasList() == null) {
            anotaciones.setRespuestasList(new ArrayList<Respuestas>());
        }
        if (anotaciones.getHuellafirmaList() == null) {
            anotaciones.setHuellafirmaList(new ArrayList<Huellafirma>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pacientes paciId = anotaciones.getPaciId();
            if (paciId != null) {
                paciId = em.getReference(paciId.getClass(), paciId.getPaciId());
                anotaciones.setPaciId(paciId);
            }
            Formularios formId = anotaciones.getFormId();
            if (formId != null) {
                formId = em.getReference(formId.getClass(), formId.getFormId());
                anotaciones.setFormId(formId);
            }
            List<Documentos> attachedDocumentosList = new ArrayList<Documentos>();
            for (Documentos documentosListDocumentosToAttach : anotaciones.getDocumentosList()) {
                documentosListDocumentosToAttach = em.getReference(documentosListDocumentosToAttach.getClass(), documentosListDocumentosToAttach.getDocuId());
                attachedDocumentosList.add(documentosListDocumentosToAttach);
            }
            anotaciones.setDocumentosList(attachedDocumentosList);
            List<Respuestas> attachedRespuestasList = new ArrayList<Respuestas>();
            for (Respuestas respuestasListRespuestasToAttach : anotaciones.getRespuestasList()) {
                respuestasListRespuestasToAttach = em.getReference(respuestasListRespuestasToAttach.getClass(), respuestasListRespuestasToAttach.getRespId());
                attachedRespuestasList.add(respuestasListRespuestasToAttach);
            }
            anotaciones.setRespuestasList(attachedRespuestasList);
            List<Huellafirma> attachedHuellafirmaList = new ArrayList<Huellafirma>();
            for (Huellafirma huellafirmaListHuellafirmaToAttach : anotaciones.getHuellafirmaList()) {
                huellafirmaListHuellafirmaToAttach = em.getReference(huellafirmaListHuellafirmaToAttach.getClass(), huellafirmaListHuellafirmaToAttach.getHufiId());
                attachedHuellafirmaList.add(huellafirmaListHuellafirmaToAttach);
            }
            anotaciones.setHuellafirmaList(attachedHuellafirmaList);
            em.persist(anotaciones);
            if (paciId != null) {
                paciId.getAnotacionesList().add(anotaciones);
                paciId = em.merge(paciId);
            }
            if (formId != null) {
                formId.getAnotacionesList().add(anotaciones);
                formId = em.merge(formId);
            }
            for (Documentos documentosListDocumentos : anotaciones.getDocumentosList()) {
                Anotaciones oldAnotIdOfDocumentosListDocumentos = documentosListDocumentos.getAnotId();
                documentosListDocumentos.setAnotId(anotaciones);
                documentosListDocumentos = em.merge(documentosListDocumentos);
                if (oldAnotIdOfDocumentosListDocumentos != null) {
                    oldAnotIdOfDocumentosListDocumentos.getDocumentosList().remove(documentosListDocumentos);
                    oldAnotIdOfDocumentosListDocumentos = em.merge(oldAnotIdOfDocumentosListDocumentos);
                }
            }
            for (Respuestas respuestasListRespuestas : anotaciones.getRespuestasList()) {
                Anotaciones oldAnotIdOfRespuestasListRespuestas = respuestasListRespuestas.getAnotId();
                respuestasListRespuestas.setAnotId(anotaciones);
                respuestasListRespuestas = em.merge(respuestasListRespuestas);
                if (oldAnotIdOfRespuestasListRespuestas != null) {
                    oldAnotIdOfRespuestasListRespuestas.getRespuestasList().remove(respuestasListRespuestas);
                    oldAnotIdOfRespuestasListRespuestas = em.merge(oldAnotIdOfRespuestasListRespuestas);
                }
            }
            for (Huellafirma huellafirmaListHuellafirma : anotaciones.getHuellafirmaList()) {
                Anotaciones oldAnotIdOfHuellafirmaListHuellafirma = huellafirmaListHuellafirma.getAnotId();
                huellafirmaListHuellafirma.setAnotId(anotaciones);
                huellafirmaListHuellafirma = em.merge(huellafirmaListHuellafirma);
                if (oldAnotIdOfHuellafirmaListHuellafirma != null) {
                    oldAnotIdOfHuellafirmaListHuellafirma.getHuellafirmaList().remove(huellafirmaListHuellafirma);
                    oldAnotIdOfHuellafirmaListHuellafirma = em.merge(oldAnotIdOfHuellafirmaListHuellafirma);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Anotaciones anotaciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Anotaciones persistentAnotaciones = em.find(Anotaciones.class, anotaciones.getAnotId());
            Pacientes paciIdOld = persistentAnotaciones.getPaciId();
            Pacientes paciIdNew = anotaciones.getPaciId();
            Formularios formIdOld = persistentAnotaciones.getFormId();
            Formularios formIdNew = anotaciones.getFormId();
            List<Documentos> documentosListOld = persistentAnotaciones.getDocumentosList();
            List<Documentos> documentosListNew = anotaciones.getDocumentosList();
            List<Respuestas> respuestasListOld = persistentAnotaciones.getRespuestasList();
            List<Respuestas> respuestasListNew = anotaciones.getRespuestasList();
            List<Huellafirma> huellafirmaListOld = persistentAnotaciones.getHuellafirmaList();
            List<Huellafirma> huellafirmaListNew = anotaciones.getHuellafirmaList();
            List<String> illegalOrphanMessages = null;
            for (Huellafirma huellafirmaListOldHuellafirma : huellafirmaListOld) {
                if (!huellafirmaListNew.contains(huellafirmaListOldHuellafirma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Huellafirma " + huellafirmaListOldHuellafirma + " since its anotId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (paciIdNew != null) {
                paciIdNew = em.getReference(paciIdNew.getClass(), paciIdNew.getPaciId());
                anotaciones.setPaciId(paciIdNew);
            }
            if (formIdNew != null) {
                formIdNew = em.getReference(formIdNew.getClass(), formIdNew.getFormId());
                anotaciones.setFormId(formIdNew);
            }
            List<Documentos> attachedDocumentosListNew = new ArrayList<Documentos>();
            for (Documentos documentosListNewDocumentosToAttach : documentosListNew) {
                documentosListNewDocumentosToAttach = em.getReference(documentosListNewDocumentosToAttach.getClass(), documentosListNewDocumentosToAttach.getDocuId());
                attachedDocumentosListNew.add(documentosListNewDocumentosToAttach);
            }
            documentosListNew = attachedDocumentosListNew;
            anotaciones.setDocumentosList(documentosListNew);
            List<Respuestas> attachedRespuestasListNew = new ArrayList<Respuestas>();
            for (Respuestas respuestasListNewRespuestasToAttach : respuestasListNew) {
                respuestasListNewRespuestasToAttach = em.getReference(respuestasListNewRespuestasToAttach.getClass(), respuestasListNewRespuestasToAttach.getRespId());
                attachedRespuestasListNew.add(respuestasListNewRespuestasToAttach);
            }
            respuestasListNew = attachedRespuestasListNew;
            anotaciones.setRespuestasList(respuestasListNew);
            List<Huellafirma> attachedHuellafirmaListNew = new ArrayList<Huellafirma>();
            for (Huellafirma huellafirmaListNewHuellafirmaToAttach : huellafirmaListNew) {
                huellafirmaListNewHuellafirmaToAttach = em.getReference(huellafirmaListNewHuellafirmaToAttach.getClass(), huellafirmaListNewHuellafirmaToAttach.getHufiId());
                attachedHuellafirmaListNew.add(huellafirmaListNewHuellafirmaToAttach);
            }
            huellafirmaListNew = attachedHuellafirmaListNew;
            anotaciones.setHuellafirmaList(huellafirmaListNew);
            anotaciones = em.merge(anotaciones);
            if (paciIdOld != null && !paciIdOld.equals(paciIdNew)) {
                paciIdOld.getAnotacionesList().remove(anotaciones);
                paciIdOld = em.merge(paciIdOld);
            }
            if (paciIdNew != null && !paciIdNew.equals(paciIdOld)) {
                paciIdNew.getAnotacionesList().add(anotaciones);
                paciIdNew = em.merge(paciIdNew);
            }
            if (formIdOld != null && !formIdOld.equals(formIdNew)) {
                formIdOld.getAnotacionesList().remove(anotaciones);
                formIdOld = em.merge(formIdOld);
            }
            if (formIdNew != null && !formIdNew.equals(formIdOld)) {
                formIdNew.getAnotacionesList().add(anotaciones);
                formIdNew = em.merge(formIdNew);
            }
            for (Documentos documentosListOldDocumentos : documentosListOld) {
                if (!documentosListNew.contains(documentosListOldDocumentos)) {
                    documentosListOldDocumentos.setAnotId(null);
                    documentosListOldDocumentos = em.merge(documentosListOldDocumentos);
                }
            }
            for (Documentos documentosListNewDocumentos : documentosListNew) {
                if (!documentosListOld.contains(documentosListNewDocumentos)) {
                    Anotaciones oldAnotIdOfDocumentosListNewDocumentos = documentosListNewDocumentos.getAnotId();
                    documentosListNewDocumentos.setAnotId(anotaciones);
                    documentosListNewDocumentos = em.merge(documentosListNewDocumentos);
                    if (oldAnotIdOfDocumentosListNewDocumentos != null && !oldAnotIdOfDocumentosListNewDocumentos.equals(anotaciones)) {
                        oldAnotIdOfDocumentosListNewDocumentos.getDocumentosList().remove(documentosListNewDocumentos);
                        oldAnotIdOfDocumentosListNewDocumentos = em.merge(oldAnotIdOfDocumentosListNewDocumentos);
                    }
                }
            }
            for (Respuestas respuestasListOldRespuestas : respuestasListOld) {
                if (!respuestasListNew.contains(respuestasListOldRespuestas)) {
                    respuestasListOldRespuestas.setAnotId(null);
                    respuestasListOldRespuestas = em.merge(respuestasListOldRespuestas);
                }
            }
            for (Respuestas respuestasListNewRespuestas : respuestasListNew) {
                if (!respuestasListOld.contains(respuestasListNewRespuestas)) {
                    Anotaciones oldAnotIdOfRespuestasListNewRespuestas = respuestasListNewRespuestas.getAnotId();
                    respuestasListNewRespuestas.setAnotId(anotaciones);
                    respuestasListNewRespuestas = em.merge(respuestasListNewRespuestas);
                    if (oldAnotIdOfRespuestasListNewRespuestas != null && !oldAnotIdOfRespuestasListNewRespuestas.equals(anotaciones)) {
                        oldAnotIdOfRespuestasListNewRespuestas.getRespuestasList().remove(respuestasListNewRespuestas);
                        oldAnotIdOfRespuestasListNewRespuestas = em.merge(oldAnotIdOfRespuestasListNewRespuestas);
                    }
                }
            }
            for (Huellafirma huellafirmaListNewHuellafirma : huellafirmaListNew) {
                if (!huellafirmaListOld.contains(huellafirmaListNewHuellafirma)) {
                    Anotaciones oldAnotIdOfHuellafirmaListNewHuellafirma = huellafirmaListNewHuellafirma.getAnotId();
                    huellafirmaListNewHuellafirma.setAnotId(anotaciones);
                    huellafirmaListNewHuellafirma = em.merge(huellafirmaListNewHuellafirma);
                    if (oldAnotIdOfHuellafirmaListNewHuellafirma != null && !oldAnotIdOfHuellafirmaListNewHuellafirma.equals(anotaciones)) {
                        oldAnotIdOfHuellafirmaListNewHuellafirma.getHuellafirmaList().remove(huellafirmaListNewHuellafirma);
                        oldAnotIdOfHuellafirmaListNewHuellafirma = em.merge(oldAnotIdOfHuellafirmaListNewHuellafirma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = anotaciones.getAnotId();
                if (findAnotaciones(id) == null) {
                    throw new NonexistentEntityException("The anotaciones with id " + id + " no longer exists.");
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
            Anotaciones anotaciones;
            try {
                anotaciones = em.getReference(Anotaciones.class, id);
                anotaciones.getAnotId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The anotaciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Huellafirma> huellafirmaListOrphanCheck = anotaciones.getHuellafirmaList();
            for (Huellafirma huellafirmaListOrphanCheckHuellafirma : huellafirmaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Anotaciones (" + anotaciones + ") cannot be destroyed since the Huellafirma " + huellafirmaListOrphanCheckHuellafirma + " in its huellafirmaList field has a non-nullable anotId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pacientes paciId = anotaciones.getPaciId();
            if (paciId != null) {
                paciId.getAnotacionesList().remove(anotaciones);
                paciId = em.merge(paciId);
            }
            Formularios formId = anotaciones.getFormId();
            if (formId != null) {
                formId.getAnotacionesList().remove(anotaciones);
                formId = em.merge(formId);
            }
            List<Documentos> documentosList = anotaciones.getDocumentosList();
            for (Documentos documentosListDocumentos : documentosList) {
                documentosListDocumentos.setAnotId(null);
                documentosListDocumentos = em.merge(documentosListDocumentos);
            }
            List<Respuestas> respuestasList = anotaciones.getRespuestasList();
            for (Respuestas respuestasListRespuestas : respuestasList) {
                respuestasListRespuestas.setAnotId(null);
                respuestasListRespuestas = em.merge(respuestasListRespuestas);
            }
            em.remove(anotaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Anotaciones> findAnotacionesEntities() {
        return findAnotacionesEntities(true, -1, -1);
    }

    public List<Anotaciones> findAnotacionesEntities(int maxResults, int firstResult) {
        return findAnotacionesEntities(false, maxResults, firstResult);
    }

    private List<Anotaciones> findAnotacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Anotaciones.class));
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

    public Anotaciones findAnotaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Anotaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnotacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Anotaciones> rt = cq.from(Anotaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
