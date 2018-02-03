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

@Table(name = "entidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entidades.findAll", query = "SELECT e FROM Entidades e"),
    @NamedQuery(name = "Entidades.findByEntiId", query = "SELECT e FROM Entidades e WHERE e.entiId = :entiId"),
    @NamedQuery(name = "Entidades.findByEntiTipo", query = "SELECT e FROM Entidades e WHERE e.entiTipo = :entiTipo"),
    @NamedQuery(name = "Entidades.findByEntiNombre", query = "SELECT e FROM Entidades e WHERE e.entiNombre = :entiNombre"),
    @NamedQuery(name = "Entidades.findByEntiFechacambio", query = "SELECT e FROM Entidades e WHERE e.entiFechacambio = :entiFechacambio"),
    @NamedQuery(name = "Entidades.findByEntiRegistradopor", query = "SELECT e FROM Entidades e WHERE e.entiRegistradopor = :entiRegistradopor"),
    @NamedQuery(name = "Entidades.findByEntiEstado", query = "SELECT e FROM Entidades e WHERE e.entiEstado = :entiEstado")})
public class Entidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "enti_id")
    private Integer entiId;
    @Basic(optional = false)
    @Column(name = "enti_tipo")
    private String entiTipo;
    @Basic(optional = false)
    @Column(name = "enti_nombre")
    private String entiNombre;
    @Basic(optional = false)
    @Column(name = "enti_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entiFechacambio;
    @Basic(optional = false)
    @Column(name = "enti_registradopor")
    private int entiRegistradopor;
    @Column(name = "enti_estado")
    private String entiEstado;

    public Entidades() {
    }

    public Entidades(Integer entiId) {
        this.entiId = entiId;
    }

    public Entidades(Integer entiId, String entiTipo, String entiNombre, Date entiFechacambio, int entiRegistradopor) {
        this.entiId = entiId;
        this.entiTipo = entiTipo;
        this.entiNombre = entiNombre;
        this.entiFechacambio = entiFechacambio;
        this.entiRegistradopor = entiRegistradopor;
    }

    public Integer getEntiId() {
        return entiId;
    }

    public void setEntiId(Integer entiId) {
        this.entiId = entiId;
    }

    public String getEntiTipo() {
        return entiTipo;
    }

    public void setEntiTipo(String entiTipo) {
        this.entiTipo = entiTipo;
    }

    public String getEntiNombre() {
        return entiNombre;
    }

    public void setEntiNombre(String entiNombre) {
        this.entiNombre = entiNombre;
    }

    public Date getEntiFechacambio() {
        return entiFechacambio;
    }

    public void setEntiFechacambio(Date entiFechacambio) {
        this.entiFechacambio = entiFechacambio;
    }

    public int getEntiRegistradopor() {
        return entiRegistradopor;
    }

    public void setEntiRegistradopor(int entiRegistradopor) {
        this.entiRegistradopor = entiRegistradopor;
    }

    public String getEntiEstado() {
        return entiEstado;
    }

    public void setEntiEstado(String entiEstado) {
        this.entiEstado = entiEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entiId != null ? entiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidades)) {
            return false;
        }
        Entidades other = (Entidades) object;
        if ((this.entiId == null && other.entiId != null) || (this.entiId != null && !this.entiId.equals(other.entiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Entidades[ entiId=" + entiId + " ]";
    }
    
}
