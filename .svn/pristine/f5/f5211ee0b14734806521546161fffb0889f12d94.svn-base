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
@Table(name = "servicios_examenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiciosExamenes.findAll", query = "SELECT s FROM ServiciosExamenes s"),
    @NamedQuery(name = "ServiciosExamenes.findBySeexId", query = "SELECT s FROM ServiciosExamenes s WHERE s.seexId = :seexId"),
    @NamedQuery(name = "ServiciosExamenes.findBySeexRegistradopor", query = "SELECT s FROM ServiciosExamenes s WHERE s.seexRegistradopor = :seexRegistradopor"),
    @NamedQuery(name = "ServiciosExamenes.findBySeexFechacambio", query = "SELECT s FROM ServiciosExamenes s WHERE s.seexFechacambio = :seexFechacambio")})
public class ServiciosExamenes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "seex_id")
    private Integer seexId;
    @Basic(optional = false)
    @Column(name = "seex_registradopor")
    private String seexRegistradopor;
    @Basic(optional = false)
    @Column(name = "seex_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date seexFechacambio;
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id")
    @ManyToOne(optional = false)
    private Examenes examId;
    @JoinColumn(name = "serv_id", referencedColumnName = "serv_id")
    @ManyToOne(optional = false)
    private Servicios servId;

    public ServiciosExamenes() {
    }

    public ServiciosExamenes(Integer seexId) {
        this.seexId = seexId;
    }

    public ServiciosExamenes(Integer seexId, String seexRegistradopor, Date seexFechacambio) {
        this.seexId = seexId;
        this.seexRegistradopor = seexRegistradopor;
        this.seexFechacambio = seexFechacambio;
    }

    public Integer getSeexId() {
        return seexId;
    }

    public void setSeexId(Integer seexId) {
        this.seexId = seexId;
    }

    public String getSeexRegistradopor() {
        return seexRegistradopor;
    }

    public void setSeexRegistradopor(String seexRegistradopor) {
        this.seexRegistradopor = seexRegistradopor;
    }

    public Date getSeexFechacambio() {
        return seexFechacambio;
    }

    public void setSeexFechacambio(Date seexFechacambio) {
        this.seexFechacambio = seexFechacambio;
    }

    public Examenes getExamId() {
        return examId;
    }

    public void setExamId(Examenes examId) {
        this.examId = examId;
    }

    public Servicios getServId() {
        return servId;
    }

    public void setServId(Servicios servId) {
        this.servId = servId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seexId != null ? seexId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiciosExamenes)) {
            return false;
        }
        ServiciosExamenes other = (ServiciosExamenes) object;
        if ((this.seexId == null && other.seexId != null) || (this.seexId != null && !this.seexId.equals(other.seexId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.ServiciosExamenes[ seexId=" + seexId + " ]";
    }
    
}
