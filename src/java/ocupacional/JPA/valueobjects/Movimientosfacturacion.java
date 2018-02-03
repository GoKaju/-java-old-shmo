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
@Cacheable(false)
@Entity
@Table(name = "movimientosfacturacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientosfacturacion.findAll", query = "SELECT m FROM Movimientosfacturacion m"),
    @NamedQuery(name = "Movimientosfacturacion.findAllDesc", query = "SELECT m FROM Movimientosfacturacion m ORDER BY m.mofaId desc"),
    @NamedQuery(name = "Movimientosfacturacion.findByMofaId", query = "SELECT m FROM Movimientosfacturacion m WHERE m.mofaId = :mofaId"),
    @NamedQuery(name = "Movimientosfacturacion.findByMofaFechacambio", query = "SELECT m FROM Movimientosfacturacion m WHERE m.mofaFechacambio = :mofaFechacambio"),
    @NamedQuery(name = "Movimientosfacturacion.findByMofaRegistradopor", query = "SELECT m FROM Movimientosfacturacion m WHERE m.mofaRegistradopor = :mofaRegistradopor"),
    @NamedQuery(name = "Movimientosfacturacion.findByMofaEstado", query = "SELECT m FROM Movimientosfacturacion m WHERE m.mofaEstado = :mofaEstado"),
    @NamedQuery(name = "Movimientosfacturacion.findByMofaObservacion", query = "SELECT m FROM Movimientosfacturacion m WHERE m.mofaObservacion = :mofaObservacion"),
    @NamedQuery(name = "Movimientosfacturacion.findByMofaFechacreacion", query = "SELECT m FROM Movimientosfacturacion m WHERE m.mofaFechacreacion = :mofaFechacreacion")})
public class Movimientosfacturacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mofa_id")
    private Integer mofaId;
    @Basic(optional = false)
    @Column(name = "mofa_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mofaFechacambio;
    @Basic(optional = false)
    @Column(name = "mofa_registradopor")
    private String mofaRegistradopor;
    @Basic(optional = false)
    @Column(name = "mofa_estado")
    private String mofaEstado;
    @Column(name = "mofa_observacion")
    private String mofaObservacion;
    @Basic(optional = false)
    @Column(name = "mofa_fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mofaFechacreacion;
    @OneToMany(mappedBy = "mofoId")
    private List<Ticket> ticketList;
    @OneToMany(mappedBy = "mofaId")
    private List<Facturas> facturasList;

    public Movimientosfacturacion() {
    }

    public Movimientosfacturacion(Integer mofaId) {
        this.mofaId = mofaId;
    }

    public Movimientosfacturacion(Integer mofaId, Date mofaFechacambio, String mofaRegistradopor, String mofaEstado, Date mofaFechacreacion) {
        this.mofaId = mofaId;
        this.mofaFechacambio = mofaFechacambio;
        this.mofaRegistradopor = mofaRegistradopor;
        this.mofaEstado = mofaEstado;
        this.mofaFechacreacion = mofaFechacreacion;
    }

    public Integer getMofaId() {
        return mofaId;
    }

    public void setMofaId(Integer mofaId) {
        this.mofaId = mofaId;
    }

    public Date getMofaFechacambio() {
        return mofaFechacambio;
    }

    public void setMofaFechacambio(Date mofaFechacambio) {
        this.mofaFechacambio = mofaFechacambio;
    }

    public String getMofaRegistradopor() {
        return mofaRegistradopor;
    }

    public void setMofaRegistradopor(String mofaRegistradopor) {
        this.mofaRegistradopor = mofaRegistradopor;
    }

    public String getMofaEstado() {
        return mofaEstado;
    }

    public void setMofaEstado(String mofaEstado) {
        this.mofaEstado = mofaEstado;
    }

    public String getMofaObservacion() {
        return mofaObservacion;
    }

    public void setMofaObservacion(String mofaObservacion) {
        this.mofaObservacion = mofaObservacion;
    }

    public Date getMofaFechacreacion() {
        return mofaFechacreacion;
    }

    public void setMofaFechacreacion(Date mofaFechacreacion) {
        this.mofaFechacreacion = mofaFechacreacion;
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @XmlTransient
    public List<Facturas> getFacturasList() {
        return facturasList;
    }

    public void setFacturasList(List<Facturas> facturasList) {
        this.facturasList = facturasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mofaId != null ? mofaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientosfacturacion)) {
            return false;
        }
        Movimientosfacturacion other = (Movimientosfacturacion) object;
        if ((this.mofaId == null && other.mofaId != null) || (this.mofaId != null && !this.mofaId.equals(other.mofaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Movimientosfacturacion[ mofaId=" + mofaId + " ]";
    }
    
}
