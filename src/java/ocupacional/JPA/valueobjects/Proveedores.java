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

@Table(name = "proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p"),
    @NamedQuery(name = "Proveedores.busqueda", query = "SELECT p FROM Proveedores p , Personageneral pg , Juridicas j  WHERE p.pegeId= pg.pegeId AND pg.pegeId= j.pegeId AND pg.pegeDocumento LIKE :Documento AND j.juriRazonsocial LIKE :Nombres   "),
    @NamedQuery(name = "Proveedores.findByProvId", query = "SELECT p FROM Proveedores p WHERE p.provId = :provId"),
    @NamedQuery(name = "Proveedores.findByPegeId", query = "SELECT p FROM Proveedores p WHERE p.pegeId = :pegeId"),
    @NamedQuery(name = "Proveedores.findByProvRegistradopor", query = "SELECT p FROM Proveedores p WHERE p.provRegistradopor = :provRegistradopor"),
    @NamedQuery(name = "Proveedores.findByProvFechacambio", query = "SELECT p FROM Proveedores p WHERE p.provFechacambio = :provFechacambio"),
    @NamedQuery(name = "Proveedores.findByProvObservacion", query = "SELECT p FROM Proveedores p WHERE p.provObservacion = :provObservacion"),
    @NamedQuery(name = "Proveedores.findByProvTipo", query = "SELECT p FROM Proveedores p WHERE p.provTipo = :provTipo"),
    @NamedQuery(name = "Proveedores.findByProvEstado", query = "SELECT p FROM Proveedores p WHERE p.provEstado = :provEstado")})
public class Proveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prov_id")
    private Long provId;
    @Basic(optional = false)
    @Column(name = "pege_id")
    private int pegeId;
    @Basic(optional = false)
    @Column(name = "prov_registradopor")
    private String provRegistradopor;
    @Basic(optional = false)
    @Column(name = "prov_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date provFechacambio;
    @Column(name = "prov_observacion")
    private String provObservacion;
    @Column(name = "prov_tipo")
    private String provTipo;
    @Basic(optional = false)
    @Column(name = "prov_estado")
    private String provEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provId")
    private List<ProveedoresExamenes> proveedoresExamenesList;

    public Proveedores() {
    }

    public Proveedores(Long provId) {
        this.provId = provId;
    }

    public Proveedores(Long provId, int pegeId, String provRegistradopor, Date provFechacambio, String provEstado) {
        this.provId = provId;
        this.pegeId = pegeId;
        this.provRegistradopor = provRegistradopor;
        this.provFechacambio = provFechacambio;
        this.provEstado = provEstado;
    }

    public Long getProvId() {
        return provId;
    }

    public void setProvId(Long provId) {
        this.provId = provId;
    }

    public int getPegeId() {
        return pegeId;
    }

    public void setPegeId(int pegeId) {
        this.pegeId = pegeId;
    }

    public String getProvRegistradopor() {
        return provRegistradopor;
    }

    public void setProvRegistradopor(String provRegistradopor) {
        this.provRegistradopor = provRegistradopor;
    }

    public Date getProvFechacambio() {
        return provFechacambio;
    }

    public void setProvFechacambio(Date provFechacambio) {
        this.provFechacambio = provFechacambio;
    }

    public String getProvObservacion() {
        return provObservacion;
    }

    public void setProvObservacion(String provObservacion) {
        this.provObservacion = provObservacion;
    }

    public String getProvTipo() {
        return provTipo;
    }

    public void setProvTipo(String provTipo) {
        this.provTipo = provTipo;
    }

    public String getProvEstado() {
        return provEstado;
    }

    public void setProvEstado(String provEstado) {
        this.provEstado = provEstado;
    }

    @XmlTransient
    public List<ProveedoresExamenes> getProveedoresExamenesList() {
        return proveedoresExamenesList;
    }

    public void setProveedoresExamenesList(List<ProveedoresExamenes> proveedoresExamenesList) {
        this.proveedoresExamenesList = proveedoresExamenesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provId != null ? provId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.provId == null && other.provId != null) || (this.provId != null && !this.provId.equals(other.provId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Proveedores[ provId=" + provId + " ]";
    }
    
}
