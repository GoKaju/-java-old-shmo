/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)
@Table(name = "formularios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formularios.findAll", query = "SELECT f FROM Formularios f"),
    @NamedQuery(name = "Formularios.findByFormId", query = "SELECT f FROM Formularios f WHERE f.formId = :formId"),
    @NamedQuery(name = "Formularios.findByFormTitulo", query = "SELECT f FROM Formularios f WHERE f.formTitulo = :formTitulo"),
    @NamedQuery(name = "Formularios.findByFormDescripcion", query = "SELECT f FROM Formularios f WHERE f.formDescripcion = :formDescripcion"),
    @NamedQuery(name = "Formularios.findByExamId", query = "SELECT f FROM Formularios f WHERE f.examId = :examId")})
public class Formularios implements Serializable {
    @Column(name = "form_tipo")
    private String formTipo;
    @Column(name = "form_ctr")
    private String formCtr;
    @Column(name = "form_action")
    private String formAction;
    @OneToMany(mappedBy = "formId")
    private List<Anotaciones> anotacionesList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "form_id")
    private Integer formId;
    @Column(name = "form_titulo")
    private String formTitulo;
    @Column(name = "form_descripcion")
    private String formDescripcion;
    @Column(name = "exam_id")
    private Integer examId;
    @OneToMany(mappedBy = "formId")
    private List<Categorias> categoriasList;

    public Formularios() {
    }

    public Formularios(Integer formId) {
        this.formId = formId;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getFormTitulo() {
        return formTitulo;
    }

    public void setFormTitulo(String formTitulo) {
        this.formTitulo = formTitulo;
    }

    public String getFormDescripcion() {
        return formDescripcion;
    }

    public void setFormDescripcion(String formDescripcion) {
        this.formDescripcion = formDescripcion;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    @XmlTransient
    public List<Categorias> getCategoriasList() {
        return categoriasList;
    }

    public void setCategoriasList(List<Categorias> categoriasList) {
        this.categoriasList = categoriasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formId != null ? formId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formularios)) {
            return false;
        }
        Formularios other = (Formularios) object;
        if ((this.formId == null && other.formId != null) || (this.formId != null && !this.formId.equals(other.formId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Formularios[ formId=" + formId + " ]";
    }

    public String getFormTipo() {
        return formTipo;
    }

    public void setFormTipo(String formTipo) {
        this.formTipo = formTipo;
    }

    public String getFormCtr() {
        return formCtr;
    }

    public void setFormCtr(String formCtr) {
        this.formCtr = formCtr;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    @XmlTransient
    public List<Anotaciones> getAnotacionesList() {
        return anotacionesList;
    }

    public void setAnotacionesList(List<Anotaciones> anotacionesList) {
        this.anotacionesList = anotacionesList;
    }
    
}
