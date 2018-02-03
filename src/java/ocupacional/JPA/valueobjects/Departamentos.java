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
@Table(name = "departamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d"),
    @NamedQuery(name = "Departamentos.findByDepaId", query = "SELECT d FROM Departamentos d WHERE d.depaId = :depaId"),
    @NamedQuery(name = "Departamentos.findByDepaNombre", query = "SELECT d FROM Departamentos d WHERE d.depaNombre = :depaNombre"),
    @NamedQuery(name = "Departamentos.findByDepaFechacambio", query = "SELECT d FROM Departamentos d WHERE d.depaFechacambio = :depaFechacambio"),
    @NamedQuery(name = "Departamentos.findByDepaRegistradopor", query = "SELECT d FROM Departamentos d WHERE d.depaRegistradopor = :depaRegistradopor")})
public class Departamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "depa_id")
    private Integer depaId;
    @Basic(optional = false)
    @Column(name = "depa_nombre")
    private String depaNombre;
    @Basic(optional = false)
    @Column(name = "depa_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depaFechacambio;
    @Basic(optional = false)
    @Column(name = "depa_registradopor")
    private int depaRegistradopor;
    @JoinColumn(name = "pais_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paises paisId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depaId")
    private List<Ciudades> ciudadesList;

    public Departamentos() {
    }

    public Departamentos(Integer depaId) {
        this.depaId = depaId;
    }

    public Departamentos(Integer depaId, String depaNombre, Date depaFechacambio, int depaRegistradopor) {
        this.depaId = depaId;
        this.depaNombre = depaNombre;
        this.depaFechacambio = depaFechacambio;
        this.depaRegistradopor = depaRegistradopor;
    }

    public Integer getDepaId() {
        return depaId;
    }

    public void setDepaId(Integer depaId) {
        this.depaId = depaId;
    }

    public String getDepaNombre() {
        return depaNombre;
    }

    public void setDepaNombre(String depaNombre) {
        this.depaNombre = depaNombre;
    }

    public Date getDepaFechacambio() {
        return depaFechacambio;
    }

    public void setDepaFechacambio(Date depaFechacambio) {
        this.depaFechacambio = depaFechacambio;
    }

    public int getDepaRegistradopor() {
        return depaRegistradopor;
    }

    public void setDepaRegistradopor(int depaRegistradopor) {
        this.depaRegistradopor = depaRegistradopor;
    }

    public Paises getPaisId() {
        return paisId;
    }

    public void setPaisId(Paises paisId) {
        this.paisId = paisId;
    }

    @XmlTransient
    public List<Ciudades> getCiudadesList() {
        return ciudadesList;
    }

    public void setCiudadesList(List<Ciudades> ciudadesList) {
        this.ciudadesList = ciudadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depaId != null ? depaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.depaId == null && other.depaId != null) || (this.depaId != null && !this.depaId.equals(other.depaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Departamentos[ depaId=" + depaId + " ]";
    }
    
}
