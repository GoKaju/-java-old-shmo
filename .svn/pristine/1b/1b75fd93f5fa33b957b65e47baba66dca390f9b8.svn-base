/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.valueobjects;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Cacheable(false)
@Entity
@Table(name = "itemfactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemfactura.findAll", query = "SELECT i FROM Itemfactura i"),
    @NamedQuery(name = "Itemfactura.findByItfaId", query = "SELECT i FROM Itemfactura i WHERE i.itfaId = :itfaId"),
    @NamedQuery(name = "Itemfactura.findByItfaCantidad", query = "SELECT i FROM Itemfactura i WHERE i.itfaCantidad = :itfaCantidad"),
    @NamedQuery(name = "Itemfactura.findByIdfaNombre", query = "SELECT i FROM Itemfactura i WHERE i.idfaNombre = :idfaNombre"),
    @NamedQuery(name = "Itemfactura.findByIdfaValor", query = "SELECT i FROM Itemfactura i WHERE i.idfaValor = :idfaValor")})
public class Itemfactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "itfa_id")
    private Integer itfaId;
    @Basic(optional = false)
    @Column(name = "itfa_cantidad")
    private int itfaCantidad;
    @Basic(optional = false)
    @Column(name = "idfa_nombre")
    private String idfaNombre;
    @Basic(optional = false)
    @Column(name = "idfa_valor")
    private int idfaValor;
    @JoinColumn(name = "fact_id", referencedColumnName = "fact_id")
    @ManyToOne(optional = false)
    private Facturas factId;

    
    
    
    public Itemfactura() {
    }

    public Itemfactura(Integer itfaId) {
        this.itfaId = itfaId;
    }

    public Itemfactura(Integer itfaId, int itfaCantidad, String idfaNombre, int idfaValor) {
        this.itfaId = itfaId;
        this.itfaCantidad = itfaCantidad;
        this.idfaNombre = idfaNombre;
        this.idfaValor = idfaValor;
    }

    public Integer getItfaId() {
        return itfaId;
    }

    public void setItfaId(Integer itfaId) {
        this.itfaId = itfaId;
    }

    public int getItfaCantidad() {
        return itfaCantidad;
    }

    public void setItfaCantidad(int itfaCantidad) {
        this.itfaCantidad = itfaCantidad;
    }

    public String getIdfaNombre() {
        return idfaNombre;
    }

    public void setIdfaNombre(String idfaNombre) {
        this.idfaNombre = idfaNombre;
    }

    public int getIdfaValor() {
        return idfaValor;
    }

    public void setIdfaValor(int idfaValor) {
        this.idfaValor = idfaValor;
    }

    public Facturas getFactId() {
        return factId;
    }

    public void setFactId(Facturas factId) {
        this.factId = factId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itfaId != null ? itfaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemfactura)) {
            return false;
        }
        Itemfactura other = (Itemfactura) object;
        if ((this.itfaId == null && other.itfaId != null) || (this.itfaId != null && !this.itfaId.equals(other.itfaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Itemfactura[ itfaId=" + itfaId + " ]";
    }
    
}
