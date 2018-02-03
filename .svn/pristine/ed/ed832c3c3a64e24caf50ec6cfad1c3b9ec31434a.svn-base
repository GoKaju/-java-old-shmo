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
import javax.persistence.Lob;
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
@Table(name = "facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturas.findAll", query = "SELECT f FROM Facturas f"),
    @NamedQuery(name = "Facturas.findByFactId", query = "SELECT f FROM Facturas f WHERE f.factId = :factId"),
    @NamedQuery(name = "Facturas.findByFactEstado", query = "SELECT f FROM Facturas f WHERE f.factEstado = :factEstado"),
    @NamedQuery(name = "Facturas.findByFactConsecutivo", query = "SELECT f FROM Facturas f WHERE f.factConsecutivo = :factConsecutivo"),
    @NamedQuery(name = "Facturas.Consecutivo", query = "SELECT f FROM Facturas f WHERE f.factConsecutivo = (select max(x.factConsecutivo) FROM Facturas x)"),
    @NamedQuery(name = "Facturas.findByFactfechaCreacion", query = "SELECT f FROM Facturas f WHERE f.factfechaCreacion = :factfechaCreacion"), 
    @NamedQuery(name = "Facturas.findByFactFechacambio", query = "SELECT f FROM Facturas f WHERE f.factFechacambio = :factFechacambio"),
    @NamedQuery(name = "Facturas.findByFactRegistradopor", query = "SELECT f FROM Facturas f WHERE f.factRegistradopor = :factRegistradopor"),
    @NamedQuery(name = "Facturas.findByFactTotal", query = "SELECT f FROM Facturas f WHERE f.factTotal = :factTotal")})
public class Facturas implements Serializable {
    @JoinColumn(name = "mofa_id", referencedColumnName = "mofa_id")
    @ManyToOne
    private Movimientosfacturacion mofaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factId")
    private List<Itemfactura> itemfacturaList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fact_id")
    private Integer factId;
    @Basic(optional = false)
    @Column(name = "fact_estado")
    private String factEstado;
    @Basic(optional = false)
    @Column(name = "fact_consecutivo")
    private int factConsecutivo;
    @Basic(optional = false)
    @Column(name = "fact_fechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date factfechaCreacion;
    @Basic(optional = false)
    @Column(name = "fact_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date factFechacambio;
    @Basic(optional = false)
    @Column(name = "fact_registradopor")
    private String factRegistradopor;
    @Basic(optional = false)
    @Column(name = "fact_total")
    private int factTotal;
    @Lob
    @Column(name = "tifa_observacion")
    private String tifaObservacion;
    @JoinColumn(name = "ceco_id", referencedColumnName = "ceco_id")
    @ManyToOne(optional = false)
    private Centrocostos cecoId;

    public Facturas() {
    }

    public Facturas(Integer factId) {
        this.factId = factId;
    }

    public Facturas(Integer factId, String factEstado, int factConsecutivo, Date factfechaCreacion, Date factFechacambio, String factRegistradopor, int factTotal) {
        this.factId = factId;
        this.factEstado = factEstado;
        this.factConsecutivo = factConsecutivo;
        this.factfechaCreacion = factfechaCreacion;
        this.factFechacambio = factFechacambio;
        this.factRegistradopor = factRegistradopor;
        this.factTotal = factTotal;
    }

    public Integer getFactId() {
        return factId;
    }

    public void setFactId(Integer factId) {
        this.factId = factId;
    }

    public String getFactEstado() {
        return factEstado;
    }

    public void setFactEstado(String factEstado) {
        this.factEstado = factEstado;
    }

    public int getFactConsecutivo() {
        return factConsecutivo;
    }

    public void setFactConsecutivo(int factConsecutivo) {
        this.factConsecutivo = factConsecutivo;
    }

    public Date getFactfechaCreacion() {
        return factfechaCreacion;
    }

    public void setFactfechaCreacion(Date factfechaCreacion) {
        this.factfechaCreacion = factfechaCreacion;
    }

    public Date getFactFechacambio() {
        return factFechacambio;
    }

    public void setFactFechacambio(Date factFechacambio) {
        this.factFechacambio = factFechacambio;
    }

    public String getFactRegistradopor() {
        return factRegistradopor;
    }

    public void setFactRegistradopor(String factRegistradopor) {
        this.factRegistradopor = factRegistradopor;
    }

    public int getFactTotal() {
        return factTotal;
    }

    public void setFactTotal(int factTotal) {
        this.factTotal = factTotal;
    }

    public String getTifaObservacion() {
        return tifaObservacion;
    }

    public void setTifaObservacion(String tifaObservacion) {
        this.tifaObservacion = tifaObservacion;
    }

    public Centrocostos getCecoId() {
        return cecoId;
    }

    public void setCecoId(Centrocostos cecoId) {
        this.cecoId = cecoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factId != null ? factId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturas)) {
            return false;
        }
        Facturas other = (Facturas) object;
        if ((this.factId == null && other.factId != null) || (this.factId != null && !this.factId.equals(other.factId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Facturas[ factId=" + factId + " ]";
    }

    @XmlTransient
    public List<Itemfactura> getItemfacturaList() {
        return itemfacturaList;
    }

    public void setItemfacturaList(List<Itemfactura> itemfacturaList) {
        this.itemfacturaList = itemfacturaList;
    }

    public Movimientosfacturacion getMofaId() {
        return mofaId;
    }

    public void setMofaId(Movimientosfacturacion mofaId) {
        this.mofaId = mofaId;
    }
    
}
