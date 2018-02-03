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

@Table(name = "estadocivil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadocivil.findAll", query = "SELECT e FROM Estadocivil e"),
    @NamedQuery(name = "Estadocivil.findByEsciId", query = "SELECT e FROM Estadocivil e WHERE e.esciId = :esciId"),
    @NamedQuery(name = "Estadocivil.findByEsciDescripcion", query = "SELECT e FROM Estadocivil e WHERE e.esciDescripcion = :esciDescripcion"),
    @NamedQuery(name = "Estadocivil.findByEsciFechacambio", query = "SELECT e FROM Estadocivil e WHERE e.esciFechacambio = :esciFechacambio"),
    @NamedQuery(name = "Estadocivil.findByEsciRegistradopor", query = "SELECT e FROM Estadocivil e WHERE e.esciRegistradopor = :esciRegistradopor")})
public class Estadocivil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "esci_id")
    private Integer esciId;
    @Basic(optional = false)
    @Column(name = "esci_descripcion")
    private String esciDescripcion;
    @Basic(optional = false)
    @Column(name = "esci_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date esciFechacambio;
    @Basic(optional = false)
    @Column(name = "esci_registradopor")
    private int esciRegistradopor;

    public Estadocivil() {
    }

    public Estadocivil(Integer esciId) {
        this.esciId = esciId;
    }

    public Estadocivil(Integer esciId, String esciDescripcion, Date esciFechacambio, int esciRegistradopor) {
        this.esciId = esciId;
        this.esciDescripcion = esciDescripcion;
        this.esciFechacambio = esciFechacambio;
        this.esciRegistradopor = esciRegistradopor;
    }

    public Integer getEsciId() {
        return esciId;
    }

    public void setEsciId(Integer esciId) {
        this.esciId = esciId;
    }

    public String getEsciDescripcion() {
        return esciDescripcion;
    }

    public void setEsciDescripcion(String esciDescripcion) {
        this.esciDescripcion = esciDescripcion;
    }

    public Date getEsciFechacambio() {
        return esciFechacambio;
    }

    public void setEsciFechacambio(Date esciFechacambio) {
        this.esciFechacambio = esciFechacambio;
    }

    public int getEsciRegistradopor() {
        return esciRegistradopor;
    }

    public void setEsciRegistradopor(int esciRegistradopor) {
        this.esciRegistradopor = esciRegistradopor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (esciId != null ? esciId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadocivil)) {
            return false;
        }
        Estadocivil other = (Estadocivil) object;
        if ((this.esciId == null && other.esciId != null) || (this.esciId != null && !this.esciId.equals(other.esciId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Estadocivil[ esciId=" + esciId + " ]";
    }
    
}
