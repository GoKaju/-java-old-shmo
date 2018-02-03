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
@Table(name = "ticket_clienteservicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketClienteservicio.findAll", query = "SELECT t FROM TicketClienteservicio t"),
    @NamedQuery(name = "TicketClienteservicio.findByTicsId", query = "SELECT t FROM TicketClienteservicio t WHERE t.ticsId = :ticsId"),
    @NamedQuery(name = "TicketClienteservicio.findByTicsRegistradopor", query = "SELECT t FROM TicketClienteservicio t WHERE t.ticsRegistradopor = :ticsRegistradopor"),
    @NamedQuery(name = "TicketClienteservicio.findByTicsFechacambio", query = "SELECT t FROM TicketClienteservicio t WHERE t.ticsFechacambio = :ticsFechacambio")})
public class TicketClienteservicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tics_id")
    private Integer ticsId;
    @Basic(optional = false)
    @Column(name = "tics_registradopor")
    private String ticsRegistradopor;
    @Basic(optional = false)
    @Column(name = "tics_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ticsFechacambio;
    @JoinColumn(name = "tick_id", referencedColumnName = "tick_id")
    @ManyToOne(optional = false)
    private Ticket tickId;
    @JoinColumn(name = "clse_id", referencedColumnName = "clse_id")
    @ManyToOne(optional = false)
    private ClientesServicio clseId;

    public TicketClienteservicio() {
    }

    public TicketClienteservicio(Integer ticsId) {
        this.ticsId = ticsId;
    }

    public TicketClienteservicio(Integer ticsId, String ticsRegistradopor, Date ticsFechacambio) {
        this.ticsId = ticsId;
        this.ticsRegistradopor = ticsRegistradopor;
        this.ticsFechacambio = ticsFechacambio;
    }

    public Integer getTicsId() {
        return ticsId;
    }

    public void setTicsId(Integer ticsId) {
        this.ticsId = ticsId;
    }

    public String getTicsRegistradopor() {
        return ticsRegistradopor;
    }

    public void setTicsRegistradopor(String ticsRegistradopor) {
        this.ticsRegistradopor = ticsRegistradopor;
    }

    public Date getTicsFechacambio() {
        return ticsFechacambio;
    }

    public void setTicsFechacambio(Date ticsFechacambio) {
        this.ticsFechacambio = ticsFechacambio;
    }

    public Ticket getTickId() {
        return tickId;
    }

    public void setTickId(Ticket tickId) {
        this.tickId = tickId;
    }

    public ClientesServicio getClseId() {
        return clseId;
    }

    public void setClseId(ClientesServicio clseId) {
        this.clseId = clseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticsId != null ? ticsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketClienteservicio)) {
            return false;
        }
        TicketClienteservicio other = (TicketClienteservicio) object;
        if ((this.ticsId == null && other.ticsId != null) || (this.ticsId != null && !this.ticsId.equals(other.ticsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.TicketClienteservicio[ ticsId=" + ticsId + " ]";
    }
    
}
