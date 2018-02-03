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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ciudades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudades.findAll", query = "SELECT c FROM Ciudades c"),
    @NamedQuery(name = "Ciudades.findByCiudId", query = "SELECT c FROM Ciudades c WHERE c.ciudId = :ciudId"),
    @NamedQuery(name = "Ciudades.findByCiudNombre", query = "SELECT c FROM Ciudades c WHERE c.ciudNombre = :ciudNombre"),
    @NamedQuery(name = "Ciudades.findByCiudFechacambio", query = "SELECT c FROM Ciudades c WHERE c.ciudFechacambio = :ciudFechacambio"),
    @NamedQuery(name = "Ciudades.findByCiudRegistradopor", query = "SELECT c FROM Ciudades c WHERE c.ciudRegistradopor = :ciudRegistradopor")})
public class Ciudades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ciud_id")
    private Integer ciudId;
    @Basic(optional = false)
    @Column(name = "ciud_nombre")
    private String ciudNombre;
    @Basic(optional = false)
    @Column(name = "ciud_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ciudFechacambio;
    @Basic(optional = false)
    @Column(name = "ciud_registradopor")
    private int ciudRegistradopor;
    @OneToMany(mappedBy = "ciudId")
    private List<Clientes> clientesList;
    @JoinColumn(name = "depa_id", referencedColumnName = "depa_id")
    @ManyToOne(optional = false)
    private Departamentos depaId;

    public Ciudades() {
    }

    public Ciudades(Integer ciudId) {
        this.ciudId = ciudId;
    }

    public Ciudades(Integer ciudId, String ciudNombre, Date ciudFechacambio, int ciudRegistradopor) {
        this.ciudId = ciudId;
        this.ciudNombre = ciudNombre;
        this.ciudFechacambio = ciudFechacambio;
        this.ciudRegistradopor = ciudRegistradopor;
    }

    public Integer getCiudId() {
        return ciudId;
    }

    public void setCiudId(Integer ciudId) {
        this.ciudId = ciudId;
    }

    public String getCiudNombre() {
        return ciudNombre;
    }

    public void setCiudNombre(String ciudNombre) {
        this.ciudNombre = ciudNombre;
    }

    public Date getCiudFechacambio() {
        return ciudFechacambio;
    }

    public void setCiudFechacambio(Date ciudFechacambio) {
        this.ciudFechacambio = ciudFechacambio;
    }

    public int getCiudRegistradopor() {
        return ciudRegistradopor;
    }

    public void setCiudRegistradopor(int ciudRegistradopor) {
        this.ciudRegistradopor = ciudRegistradopor;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public Departamentos getDepaId() {
        return depaId;
    }

    public void setDepaId(Departamentos depaId) {
        this.depaId = depaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ciudId != null ? ciudId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudades)) {
            return false;
        }
        Ciudades other = (Ciudades) object;
        if ((this.ciudId == null && other.ciudId != null) || (this.ciudId != null && !this.ciudId.equals(other.ciudId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Ciudades[ ciudId=" + ciudId + " ]";
    }
    
}
