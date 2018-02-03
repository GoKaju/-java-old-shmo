/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.entidades;

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
import ocupacional.JPA.valueobjects.Clientes;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)

@Table(name = "cliente_sedes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteSedes.findAll", query = "SELECT c FROM ClienteSedes c"),
    @NamedQuery(name = "ClienteSedes.findByClsedId", query = "SELECT c FROM ClienteSedes c WHERE c.clsedId = :clsedId"),
    @NamedQuery(name = "ClienteSedes.findByClsedDescripcion", query = "SELECT c FROM ClienteSedes c WHERE c.clsedDescripcion = :clsedDescripcion"),
    @NamedQuery(name = "ClienteSedes.findByClsedFechacambio", query = "SELECT c FROM ClienteSedes c WHERE c.clsedFechacambio = :clsedFechacambio"),
    @NamedQuery(name = "ClienteSedes.findByClsedRegistradopor", query = "SELECT c FROM ClienteSedes c WHERE c.clsedRegistradopor = :clsedRegistradopor")})
public class ClienteSedes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clsed_id")
    private Integer clsedId;
    @Column(name = "clsed_descripcion")
    private String clsedDescripcion;
    @Column(name = "clsed_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clsedFechacambio;
    @Column(name = "clsed_registradopor")
    private String clsedRegistradopor;
    @JoinColumn(name = "clie_id", referencedColumnName = "clie_id")
    @ManyToOne
    private Clientes clieId;

    public ClienteSedes() {
    }

    public ClienteSedes(Integer clsedId) {
        this.clsedId = clsedId;
    }

    public Integer getClsedId() {
        return clsedId;
    }

    public void setClsedId(Integer clsedId) {
        this.clsedId = clsedId;
    }

    public String getClsedDescripcion() {
        return clsedDescripcion;
    }

    public void setClsedDescripcion(String clsedDescripcion) {
        this.clsedDescripcion = clsedDescripcion;
    }

    public Date getClsedFechacambio() {
        return clsedFechacambio;
    }

    public void setClsedFechacambio(Date clsedFechacambio) {
        this.clsedFechacambio = clsedFechacambio;
    }

    public String getClsedRegistradopor() {
        return clsedRegistradopor;
    }

    public void setClsedRegistradopor(String clsedRegistradopor) {
        this.clsedRegistradopor = clsedRegistradopor;
    }

    public Clientes getClieId() {
        return clieId;
    }

    public void setClieId(Clientes clieId) {
        this.clieId = clieId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clsedId != null ? clsedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteSedes)) {
            return false;
        }
        ClienteSedes other = (ClienteSedes) object;
        if ((this.clsedId == null && other.clsedId != null) || (this.clsedId != null && !this.clsedId.equals(other.clsedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.ClienteSedes[ clsedId=" + clsedId + " ]";
    }
    
}
