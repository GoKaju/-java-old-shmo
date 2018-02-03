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
@Table(name = "clientes_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientesServicio.findAll", query = "SELECT c FROM ClientesServicio c "),
    @NamedQuery(name = "ClientesServicio.findByClseId", query = "SELECT c FROM ClientesServicio c WHERE c.clseId = :clseId"),
    @NamedQuery(name = "ClientesServicio.findByClseValor", query = "SELECT c FROM ClientesServicio c WHERE c.clseValor = :clseValor"),
    @NamedQuery(name = "ClientesServicio.findByClseRegistradopor", query = "SELECT c FROM ClientesServicio c WHERE c.clseRegistradopor = :clseRegistradopor"),
    @NamedQuery(name = "ClientesServicio.findByClseFechacambio", query = "SELECT c FROM ClientesServicio c WHERE c.clseFechacambio = :clseFechacambio")})
public class ClientesServicio implements Serializable {
    @Basic(optional = false)
    @Column(name = "clse_estado")
    private String clseEstado;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clse_id")
    private Integer clseId;
    @Basic(optional = false)
    @Column(name = "clse_valor")
    private int clseValor;
   
    @Basic(optional = false)
    @Column(name = "clse_registradopor")
    private String clseRegistradopor;
    @Basic(optional = false)
    @Column(name = "clse_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clseFechacambio;
    @JoinColumn(name = "clie_id", referencedColumnName = "clie_id")
    @ManyToOne(optional = false)
    private Clientes clieId;
    @JoinColumn(name = "serv_id", referencedColumnName = "serv_id")
    @ManyToOne(optional = false)
    private Servicios servId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clseId")
    private List<TicketClienteservicio> ticketClienteservicioList;

    public ClientesServicio() {
    }

    public ClientesServicio(Integer clseId) {
        this.clseId = clseId;
    }

    public ClientesServicio(Integer clseId, int clseValor, String clseRegistradopor, Date clseFechacambio) {
        this.clseId = clseId;
        this.clseValor = clseValor;
        this.clseRegistradopor = clseRegistradopor;
        this.clseFechacambio = clseFechacambio;
    }

    public Integer getClseId() {
        return clseId;
    }

    public void setClseId(Integer clseId) {
        this.clseId = clseId;
    }

    public int getClseValor() {
        return clseValor;
    }

    public void setClseValor(int clseValor) {
        this.clseValor = clseValor;
    }
 

    public String getClseRegistradopor() {
        return clseRegistradopor;
    }

    public void setClseRegistradopor(String clseRegistradopor) {
        this.clseRegistradopor = clseRegistradopor;
    }

    public Date getClseFechacambio() {
        return clseFechacambio;
    }

    public void setClseFechacambio(Date clseFechacambio) {
        this.clseFechacambio = clseFechacambio;
    }

    public Clientes getClieId() {
        return clieId;
    }

    public void setClieId(Clientes clieId) {
        this.clieId = clieId;
    }

    public Servicios getServId() {
        return servId;
    }

    public void setServId(Servicios servId) {
        this.servId = servId;
    }

    @XmlTransient
    public List<TicketClienteservicio> getTicketClienteservicioList() {
        return ticketClienteservicioList;
    }

    public void setTicketClienteservicioList(List<TicketClienteservicio> ticketClienteservicioList) {
        this.ticketClienteservicioList = ticketClienteservicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clseId != null ? clseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientesServicio)) {
            return false;
        }
        ClientesServicio other = (ClientesServicio) object;
        if ((this.clseId == null && other.clseId != null) || (this.clseId != null && !this.clseId.equals(other.clseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.ClientesServicio[ clseId=" + clseId + " ]";
    }

    public String getClseEstado() {
        return clseEstado;
    }

    public void setClseEstado(String clseEstado) {
        this.clseEstado = clseEstado;
    }
    
}
