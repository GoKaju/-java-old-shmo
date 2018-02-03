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
@Table(name = "empleadoexamen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleadoexamen.findAll", query = "SELECT e FROM Empleadoexamen e"),
    @NamedQuery(name = "Empleadoexamen.validar", query = "SELECT e FROM Empleadoexamen e WHERE e.emplId = :emplId AND e.examId = :examId"),
    @NamedQuery(name = "Empleadoexamen.findByEmexId", query = "SELECT e FROM Empleadoexamen e WHERE e.emexId = :emexId"),
    @NamedQuery(name = "Empleadoexamen.findByEmexRegistradopor", query = "SELECT e FROM Empleadoexamen e WHERE e.emexRegistradopor = :emexRegistradopor"),
    @NamedQuery(name = "Empleadoexamen.findByEmexFechacambio", query = "SELECT e FROM Empleadoexamen e WHERE e.emexFechacambio = :emexFechacambio")})
public class Empleadoexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emex_id")
    private Integer emexId;
    @Column(name = "emex_registradopor")
    private String emexRegistradopor;
    @Column(name = "emex_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emexFechacambio;
    @JoinColumn(name = "empl_id", referencedColumnName = "empl_id")
    @ManyToOne
    private Empleados emplId;
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id")
    @ManyToOne
    private Examenes examId;

    public Empleadoexamen() {
    }

    public Empleadoexamen(Integer emexId) {
        this.emexId = emexId;
    }

    public Integer getEmexId() {
        return emexId;
    }

    public void setEmexId(Integer emexId) {
        this.emexId = emexId;
    }

    public String getEmexRegistradopor() {
        return emexRegistradopor;
    }

    public void setEmexRegistradopor(String emexRegistradopor) {
        this.emexRegistradopor = emexRegistradopor;
    }

    public Date getEmexFechacambio() {
        return emexFechacambio;
    }

    public void setEmexFechacambio(Date emexFechacambio) {
        this.emexFechacambio = emexFechacambio;
    }

    public Empleados getEmplId() {
        return emplId;
    }

    public void setEmplId(Empleados emplId) {
        this.emplId = emplId;
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
        hash += (emexId != null ? emexId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleadoexamen)) {
            return false;
        }
        Empleadoexamen other = (Empleadoexamen) object;
        if ((this.emexId == null && other.emexId != null) || (this.emexId != null && !this.emexId.equals(other.emexId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Empleadoexamen[ emexId=" + emexId + " ]";
    }
    
}
