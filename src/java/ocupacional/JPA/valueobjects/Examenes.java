/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.valueobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)

@Table(name = "examenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examenes.findAll", query = "SELECT e FROM Examenes e"),
    @NamedQuery(name = "Examenes.findByExamId", query = "SELECT e FROM Examenes e WHERE e.examId = :examId"),
    @NamedQuery(name = "Examenes.findByExamDescripcion", query = "SELECT e FROM Examenes e WHERE e.examDescripcion = :examDescripcion"),
    @NamedQuery(name = "Examenes.findByExamRegistradopor", query = "SELECT e FROM Examenes e WHERE e.examRegistradopor = :examRegistradopor"),
    @NamedQuery(name = "Examenes.findByExamFechacambio", query = "SELECT e FROM Examenes e WHERE e.examFechacambio = :examFechacambio"),
    @NamedQuery(name = "Examenes.findByExamObservacion", query = "SELECT e FROM Examenes e WHERE e.examObservacion = :examObservacion"),
    @NamedQuery(name = "Examenes.findByExamTipo", query = "SELECT e FROM Examenes e WHERE e.examTipo = :examTipo"),
    @NamedQuery(name = "Examenes.findByExamEstado", query = "SELECT e FROM Examenes e WHERE e.examEstado = :examEstado")})
public class Examenes implements Serializable {
    @Basic(optional = false)
    @Column(name = "exam_visiblePorClie")
    private boolean examvisiblePorClie;
    @OneToMany(mappedBy = "examId")
    private List<Empleadoexamen> empleadoexamenList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exam_id")
    private Integer examId;
    @Basic(optional = false)
    @Column(name = "exam_descripcion")
    private String examDescripcion;
    @Basic(optional = false)
    @Column(name = "exam_registradopor")
    private String examRegistradopor;
    @Basic(optional = false)
    @Column(name = "exam_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date examFechacambio;
    @Basic(optional = false)
    @Column(name = "exam_observacion")
    private String examObservacion;
    @Basic(optional = false)
    @Column(name = "exam_tipo")
    private String examTipo;
    @Column(name = "exam_estado")
    private String examEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private List<ServiciosExamenes> serviciosExamenesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private List<ProveedoresExamenes> proveedoresExamenesList;

    public Examenes() {
    }

    public Examenes(Integer examId) {
        this.examId = examId;
    }

    public Examenes(Integer examId, String examDescripcion, String examRegistradopor, Date examFechacambio, String examObservacion, String examTipo) {
        this.examId = examId;
        this.examDescripcion = examDescripcion;
        this.examRegistradopor = examRegistradopor;
        this.examFechacambio = examFechacambio;
        this.examObservacion = examObservacion;
        this.examTipo = examTipo;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamDescripcion() {
        return examDescripcion;
    }

    public void setExamDescripcion(String examDescripcion) {
        this.examDescripcion = examDescripcion;
    }

    public String getExamRegistradopor() {
        return examRegistradopor;
    }

    public void setExamRegistradopor(String examRegistradopor) {
        this.examRegistradopor = examRegistradopor;
    }

    public Date getExamFechacambio() {
        return examFechacambio;
    }

    public void setExamFechacambio(Date examFechacambio) {
        this.examFechacambio = examFechacambio;
    }

    public String getExamObservacion() {
        return examObservacion;
    }

    public void setExamObservacion(String examObservacion) {
        this.examObservacion = examObservacion;
    }

    public String getExamTipo() {
        return examTipo;
    }

    public void setExamTipo(String examTipo) {
        this.examTipo = examTipo;
    }

    public String getExamEstado() {
        return examEstado;
    }

    public void setExamEstado(String examEstado) {
        this.examEstado = examEstado;
    }

    @XmlTransient
    public List<ServiciosExamenes> getServiciosExamenesList() {
        return serviciosExamenesList;
    }

    public void setServiciosExamenesList(List<ServiciosExamenes> serviciosExamenesList) {
        this.serviciosExamenesList = serviciosExamenesList;
    }

    @XmlTransient
    public List<ProveedoresExamenes> getProveedoresExamenesList() {
        return proveedoresExamenesList;
    }

    public void setProveedoresExamenesList(List<ProveedoresExamenes> proveedoresExamenesList) {
        this.proveedoresExamenesList = proveedoresExamenesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examId != null ? examId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examenes)) {
            return false;
        }
        Examenes other = (Examenes) object;
        if ((this.examId == null && other.examId != null) || (this.examId != null && !this.examId.equals(other.examId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Examenes[ examId=" + examId + " ]";
    }

    @XmlTransient
    public List<Empleadoexamen> getEmpleadoexamenList() {
        return empleadoexamenList;
    }

    public void setEmpleadoexamenList(List<Empleadoexamen> empleadoexamenList) {
        this.empleadoexamenList = empleadoexamenList;
    }

    public boolean getExamvisiblePorClie() {
        return examvisiblePorClie;
    }

    public void setExamvisiblePorClie(boolean examvisiblePorClie) {
        this.examvisiblePorClie = examvisiblePorClie;
    }
    
}
