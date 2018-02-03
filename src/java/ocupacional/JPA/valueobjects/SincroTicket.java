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
@Cacheable(false)
@Entity
@Table(name = "sincro_ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SincroTicket.findAll", query = "SELECT s FROM SincroTicket s"),
    @NamedQuery(name = "SincroTicket.findBySincId", query = "SELECT s FROM SincroTicket s WHERE s.sincId = :sincId"),
    @NamedQuery(name = "SincroTicket.findByTickId", query = "SELECT s FROM SincroTicket s WHERE s.tickId = :tickId"),
    @NamedQuery(name = "SincroTicket.findBySincFechacambio", query = "SELECT s FROM SincroTicket s WHERE s.sincFechacambio = :sincFechacambio")})
public class SincroTicket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sinc_id")
    private Integer sincId;
    @Basic(optional = false)
    @Column(name = "tick_id")
    private int tickId;
    @Column(name = "sinc_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sincFechacambio;

    public SincroTicket() {
    }

    public SincroTicket(Integer sincId) {
        this.sincId = sincId;
    }

    public SincroTicket(Integer sincId, int tickId) {
        this.sincId = sincId;
        this.tickId = tickId;
    }

    public Integer getSincId() {
        return sincId;
    }

    public void setSincId(Integer sincId) {
        this.sincId = sincId;
    }

    public int getTickId() {
        return tickId;
    }

    public void setTickId(int tickId) {
        this.tickId = tickId;
    }

    public Date getSincFechacambio() {
        return sincFechacambio;
    }

    public void setSincFechacambio(Date sincFechacambio) {
        this.sincFechacambio = sincFechacambio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sincId != null ? sincId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SincroTicket)) {
            return false;
        }
        SincroTicket other = (SincroTicket) object;
        if ((this.sincId == null && other.sincId != null) || (this.sincId != null && !this.sincId.equals(other.sincId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.SincroTicket[ sincId=" + sincId + " ]";
    }
    
}
