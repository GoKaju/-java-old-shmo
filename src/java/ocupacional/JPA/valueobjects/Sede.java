/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.valueobjects;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)
@Table(name = "sede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s"),
    @NamedQuery(name = "Sede.findBySedeId", query = "SELECT s FROM Sede s WHERE s.sedeId = :sedeId"),
    @NamedQuery(name = "Sede.findBySedeNombre", query = "SELECT s FROM Sede s WHERE s.sedeNombre = :sedeNombre"),
    @NamedQuery(name = "Sede.findBySedeResponsable", query = "SELECT s FROM Sede s WHERE s.sedeResponsable = :sedeResponsable")})
public class Sede implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sedeId")
    private List<Empleados> empleadosList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sede_id")
    private Integer sedeId;
    @Basic(optional = false)
    @Column(name = "sede_nombre")
    private String sedeNombre;
    @Basic(optional = false)
    @Column(name = "sede_responsable")
    private String sedeResponsable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sedeId")
    private List<Ticket> ticketList;

    public Sede() {
    }

    public Sede(Integer sedeId) {
        this.sedeId = sedeId;
    }

    public Sede(Integer sedeId, String sedeNombre, String sedeResponsable) {
        this.sedeId = sedeId;
        this.sedeNombre = sedeNombre;
        this.sedeResponsable = sedeResponsable;
    }

    public Integer getSedeId() {
        return sedeId;
    }

    public void setSedeId(Integer sedeId) {
        this.sedeId = sedeId;
    }

    public String getSedeNombre() {
        return sedeNombre;
    }

    public void setSedeNombre(String sedeNombre) {
        this.sedeNombre = sedeNombre;
    }

    public String getSedeResponsable() {
        return sedeResponsable;
    }

    public void setSedeResponsable(String sedeResponsable) {
        this.sedeResponsable = sedeResponsable;
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sedeId != null ? sedeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sede)) {
            return false;
        }
        Sede other = (Sede) object;
        if ((this.sedeId == null && other.sedeId != null) || (this.sedeId != null && !this.sedeId.equals(other.sedeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Sede[ sedeId=" + sedeId + " ]";
    }

    @XmlTransient
    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }
    
}
