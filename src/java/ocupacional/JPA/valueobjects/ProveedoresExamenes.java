/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.valueobjects;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)

@Table(name = "proveedores_examenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedoresExamenes.findAll", query = "SELECT p FROM ProveedoresExamenes p"),
    @NamedQuery(name = "ProveedoresExamenes.validar", query = "SELECT p FROM ProveedoresExamenes p WHERE p.provId= :provId AND p.examId= :examId"),
    @NamedQuery(name = "ProveedoresExamenes.findByPrexId", query = "SELECT p FROM ProveedoresExamenes p WHERE p.prexId = :prexId"),
    @NamedQuery(name = "ProveedoresExamenes.findByPrexObservacion", query = "SELECT p FROM ProveedoresExamenes p WHERE p.prexObservacion = :prexObservacion"),
    @NamedQuery(name = "ProveedoresExamenes.findByPrexRegistradopor", query = "SELECT p FROM ProveedoresExamenes p WHERE p.prexRegistradopor = :prexRegistradopor"),
    @NamedQuery(name = "ProveedoresExamenes.findByPrexFechacambio", query = "SELECT p FROM ProveedoresExamenes p WHERE p.prexFechacambio = :prexFechacambio"),
    @NamedQuery(name = "ProveedoresExamenes.findByPrexEstado", query = "SELECT p FROM ProveedoresExamenes p WHERE p.prexEstado = :prexEstado")})
public class ProveedoresExamenes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prex_id")
    private Integer prexId;
    @Column(name = "prex_observacion")
    private String prexObservacion;
    @Basic(optional = false)
    @Column(name = "prex_registradopor")
    private String prexRegistradopor;
    @Basic(optional = false)
    @Column(name = "prex_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prexFechacambio;
    @Basic(optional = false)
    @Column(name = "prex_estado")
    private String prexEstado;
    @JoinColumn(name = "prov_id", referencedColumnName = "prov_id")
    @ManyToOne(optional = false)
    private Proveedores provId;
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id")
    @ManyToOne(optional = false)
    private Examenes examId;

    public ProveedoresExamenes() {
    }

    public ProveedoresExamenes(Integer prexId) {
        this.prexId = prexId;
    }

    public ProveedoresExamenes(Integer prexId, String prexRegistradopor, Date prexFechacambio, String prexEstado) {
        this.prexId = prexId;
        this.prexRegistradopor = prexRegistradopor;
        this.prexFechacambio = prexFechacambio;
        this.prexEstado = prexEstado;
    }

    public Integer getPrexId() {
        return prexId;
    }

    public void setPrexId(Integer prexId) {
        this.prexId = prexId;
    }

    public String getPrexObservacion() {
        return prexObservacion;
    }

    public void setPrexObservacion(String prexObservacion) {
        this.prexObservacion = prexObservacion;
    }

    public String getPrexRegistradopor() {
        return prexRegistradopor;
    }

    public void setPrexRegistradopor(String prexRegistradopor) {
        this.prexRegistradopor = prexRegistradopor;
    }

    public Date getPrexFechacambio() {
        return prexFechacambio;
    }

    public void setPrexFechacambio(Date prexFechacambio) {
        this.prexFechacambio = prexFechacambio;
    }

    public String getPrexEstado() {
        return prexEstado;
    }

    public void setPrexEstado(String prexEstado) {
        this.prexEstado = prexEstado;
    }

    public Proveedores getProvId() {
        return provId;
    }

    public void setProvId(Proveedores provId) {
        this.provId = provId;
    }

    public Examenes getExamId() {
        return examId;
    }

    public void setExamId(Examenes examId) {
        this.examId = examId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prexId != null ? prexId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedoresExamenes)) {
            return false;
        }
        ProveedoresExamenes other = (ProveedoresExamenes) object;
        if ((this.prexId == null && other.prexId != null) || (this.prexId != null && !this.prexId.equals(other.prexId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.ProveedoresExamenes[ prexId=" + prexId + " ]";
    }
    
}
