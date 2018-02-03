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

@Table(name = "tipoexamen_medico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoexamenMedico.findAll", query = "SELECT t FROM TipoexamenMedico t"),
    @NamedQuery(name = "TipoexamenMedico.findByTemeId", query = "SELECT t FROM TipoexamenMedico t WHERE t.temeId = :temeId"),
    @NamedQuery(name = "TipoexamenMedico.findByTemeDescripcion", query = "SELECT t FROM TipoexamenMedico t WHERE t.temeDescripcion = :temeDescripcion"),
    @NamedQuery(name = "TipoexamenMedico.findByTemeEstado", query = "SELECT t FROM TipoexamenMedico t WHERE t.temeEstado = :temeEstado"),
    @NamedQuery(name = "TipoexamenMedico.findByTemeFechacambio", query = "SELECT t FROM TipoexamenMedico t WHERE t.temeFechacambio = :temeFechacambio"),
    @NamedQuery(name = "TipoexamenMedico.findByTemeRegistradopor", query = "SELECT t FROM TipoexamenMedico t WHERE t.temeRegistradopor = :temeRegistradopor")})
public class TipoexamenMedico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "teme_id")
    private Integer temeId;
    @Basic(optional = false)
    @Column(name = "teme_descripcion")
    private String temeDescripcion;
    @Column(name = "teme_estado")
    private String temeEstado;
    @Basic(optional = false)
    @Column(name = "teme_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date temeFechacambio;
    @Basic(optional = false)
    @Column(name = "teme_registradopor")
    private String temeRegistradopor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "temeId")
    private List<Ticket> ticketList;

    public TipoexamenMedico() {
    }

    public TipoexamenMedico(Integer temeId) {
        this.temeId = temeId;
    }

    public TipoexamenMedico(Integer temeId, String temeDescripcion, Date temeFechacambio, String temeRegistradopor) {
        this.temeId = temeId;
        this.temeDescripcion = temeDescripcion;
        this.temeFechacambio = temeFechacambio;
        this.temeRegistradopor = temeRegistradopor;
    }

    public Integer getTemeId() {
        return temeId;
    }

    public void setTemeId(Integer temeId) {
        this.temeId = temeId;
    }

    public String getTemeDescripcion() {
        return temeDescripcion;
    }

    public void setTemeDescripcion(String temeDescripcion) {
        this.temeDescripcion = temeDescripcion;
    }

    public String getTemeEstado() {
        return temeEstado;
    }

    public void setTemeEstado(String temeEstado) {
        this.temeEstado = temeEstado;
    }

    public Date getTemeFechacambio() {
        return temeFechacambio;
    }

    public void setTemeFechacambio(Date temeFechacambio) {
        this.temeFechacambio = temeFechacambio;
    }

    public String getTemeRegistradopor() {
        return temeRegistradopor;
    }

    public void setTemeRegistradopor(String temeRegistradopor) {
        this.temeRegistradopor = temeRegistradopor;
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
        hash += (temeId != null ? temeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoexamenMedico)) {
            return false;
        }
        TipoexamenMedico other = (TipoexamenMedico) object;
        if ((this.temeId == null && other.temeId != null) || (this.temeId != null && !this.temeId.equals(other.temeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.TipoexamenMedico[ temeId=" + temeId + " ]";
    }
    
}
