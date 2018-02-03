/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)
@Table(name = "opciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opciones.findAll", query = "SELECT o FROM Opciones o"),
    @NamedQuery(name = "Opciones.findByOpciId", query = "SELECT o FROM Opciones o WHERE o.opciId = :opciId"),
    @NamedQuery(name = "Opciones.findByOpciNombre", query = "SELECT o FROM Opciones o WHERE o.opciNombre = :opciNombre"),
    @NamedQuery(name = "Opciones.findByOpciValue", query = "SELECT o FROM Opciones o WHERE o.opciValue = :opciValue")})
public class Opciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "opci_id")
    private Integer opciId;
    @Column(name = "opci_nombre")
    private String opciNombre;
    @Column(name = "opci_value")
    private String opciValue;
    @JoinColumn(name = "camp_id", referencedColumnName = "camp_id")
    @ManyToOne
    private Campos campId;

    public Opciones() {
    }

    public Opciones(Integer opciId) {
        this.opciId = opciId;
    }

    public Integer getOpciId() {
        return opciId;
    }

    public void setOpciId(Integer opciId) {
        this.opciId = opciId;
    }

    public String getOpciNombre() {
        return opciNombre;
    }

    public void setOpciNombre(String opciNombre) {
        this.opciNombre = opciNombre;
    }

    public String getOpciValue() {
        return opciValue;
    }

    public void setOpciValue(String opciValue) {
        this.opciValue = opciValue;
    }

    public Campos getCampId() {
        return campId;
    }

    public void setCampId(Campos campId) {
        this.campId = campId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opciId != null ? opciId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opciones)) {
            return false;
        }
        Opciones other = (Opciones) object;
        if ((this.opciId == null && other.opciId != null) || (this.opciId != null && !this.opciId.equals(other.opciId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Opciones[ opciId=" + opciId + " ]";
    }
    
}
