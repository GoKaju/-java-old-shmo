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
@Table(name = "centrocostos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Centrocostos.findAll", query = "SELECT c FROM Centrocostos c"),
    @NamedQuery(name = "Centrocostos.findByCecoId", query = "SELECT c FROM Centrocostos c WHERE c.cecoId = :cecoId"),
    @NamedQuery(name = "Centrocostos.findByCecoObservacion", query = "SELECT c FROM Centrocostos c WHERE c.cecoObservacion = :cecoObservacion"),
    @NamedQuery(name = "Centrocostos.findByCecoEstado", query = "SELECT c FROM Centrocostos c WHERE c.cecoEstado = :cecoEstado"),
    @NamedQuery(name = "Centrocostos.findByCecoFechacambio", query = "SELECT c FROM Centrocostos c WHERE c.cecoFechacambio = :cecoFechacambio"),
    @NamedQuery(name = "Centrocostos.findByCecoRegistradopor", query = "SELECT c FROM Centrocostos c WHERE c.cecoRegistradopor = :cecoRegistradopor")})
public class Centrocostos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ceco_id")
    private Integer cecoId;
    @Column(name = "ceco_observacion")
    private String cecoObservacion;
    @Column(name = "ceco_estado")
    private String cecoEstado;
    @Basic(optional = false)
    @Column(name = "ceco_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cecoFechacambio;
    @Basic(optional = false)
    @Column(name = "ceco_registradopor")
    private String cecoRegistradopor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cecoId")
    private List<Facturas> facturasList;
    @JoinColumn(name = "clie_id", referencedColumnName = "clie_id")
    @ManyToOne(optional = false)
    private Clientes clieId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cecoId")
    private List<Ticket> ticketList;

    public Centrocostos() {
    }

    public Centrocostos(Integer cecoId) {
        this.cecoId = cecoId;
    }

    public Centrocostos(Integer cecoId, Date cecoFechacambio, String cecoRegistradopor) {
        this.cecoId = cecoId;
        this.cecoFechacambio = cecoFechacambio;
        this.cecoRegistradopor = cecoRegistradopor;
    }

    public Integer getCecoId() {
        return cecoId;
    }

    public void setCecoId(Integer cecoId) {
        this.cecoId = cecoId;
    }

    public String getCecoObservacion() {
        return cecoObservacion;
    }

    public void setCecoObservacion(String cecoObservacion) {
        this.cecoObservacion = cecoObservacion;
    }

    public String getCecoEstado() {
        return cecoEstado;
    }

    public void setCecoEstado(String cecoEstado) {
        this.cecoEstado = cecoEstado;
    }

    public Date getCecoFechacambio() {
        return cecoFechacambio;
    }

    public void setCecoFechacambio(Date cecoFechacambio) {
        this.cecoFechacambio = cecoFechacambio;
    }

    public String getCecoRegistradopor() {
        return cecoRegistradopor;
    }

    public void setCecoRegistradopor(String cecoRegistradopor) {
        this.cecoRegistradopor = cecoRegistradopor;
    }

    @XmlTransient
    public List<Facturas> getFacturasList() {
        return facturasList;
    }

    public void setFacturasList(List<Facturas> facturasList) {
        this.facturasList = facturasList;
    }

    public Clientes getClieId() {
        return clieId;
    }

    public void setClieId(Clientes clieId) {
        this.clieId = clieId;
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
        hash += (cecoId != null ? cecoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centrocostos)) {
            return false;
        }
        Centrocostos other = (Centrocostos) object;
        if ((this.cecoId == null && other.cecoId != null) || (this.cecoId != null && !this.cecoId.equals(other.cecoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Centrocostos[ cecoId=" + cecoId + " ]";
    }
    
}
