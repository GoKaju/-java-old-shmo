/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.valueobjects;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author D4V3
 */
@Entity
@Table(name = "parametrofactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametrofactura.findAll", query = "SELECT p FROM Parametrofactura p"),
    @NamedQuery(name = "Parametrofactura.findByPafaId", query = "SELECT p FROM Parametrofactura p WHERE p.pafaId = :pafaId"),
    @NamedQuery(name = "Parametrofactura.findByPafaNit", query = "SELECT p FROM Parametrofactura p WHERE p.pafaNit = :pafaNit"),
    @NamedQuery(name = "Parametrofactura.findByPafaActividadeco", query = "SELECT p FROM Parametrofactura p WHERE p.pafaActividadeco = :pafaActividadeco"),
    @NamedQuery(name = "Parametrofactura.findByPafaResolucionfacturacion", query = "SELECT p FROM Parametrofactura p WHERE p.pafaResolucionfacturacion = :pafaResolucionfacturacion"),
    @NamedQuery(name = "Parametrofactura.findByPafaDescripcion1", query = "SELECT p FROM Parametrofactura p WHERE p.pafaDescripcion1 = :pafaDescripcion1"),
    @NamedQuery(name = "Parametrofactura.findByPafaDirecciones", query = "SELECT p FROM Parametrofactura p WHERE p.pafaDirecciones = :pafaDirecciones")})
public class Parametrofactura implements Serializable {
    @Basic(optional = false)
    @Column(name = "pafa_iva")
    private int pafaIva;
    @Basic(optional = false)
    @Column(name = "pafa_prefijo")
    private String pafaPrefijo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pafa_id")
    private Integer pafaId;
    @Column(name = "pafa_nit")
    private String pafaNit;
    @Column(name = "pafa_actividadeco")
    private String pafaActividadeco;
    @Column(name = "pafa_resolucionfacturacion")
    private String pafaResolucionfacturacion;
    @Column(name = "pafa_descripcion1")
    private String pafaDescripcion1;
    @Column(name = "pafa_direcciones")
    private String pafaDirecciones;

    public Parametrofactura() {
    }

    public Parametrofactura(Integer pafaId) {
        this.pafaId = pafaId;
    }

    public Integer getPafaId() {
        return pafaId;
    }

    public void setPafaId(Integer pafaId) {
        this.pafaId = pafaId;
    }

    public String getPafaNit() {
        return pafaNit;
    }

    public void setPafaNit(String pafaNit) {
        this.pafaNit = pafaNit;
    }

    public String getPafaActividadeco() {
        return pafaActividadeco;
    }

    public void setPafaActividadeco(String pafaActividadeco) {
        this.pafaActividadeco = pafaActividadeco;
    }

    public String getPafaResolucionfacturacion() {
        return pafaResolucionfacturacion;
    }

    public void setPafaResolucionfacturacion(String pafaResolucionfacturacion) {
        this.pafaResolucionfacturacion = pafaResolucionfacturacion;
    }

    public String getPafaDescripcion1() {
        return pafaDescripcion1;
    }

    public void setPafaDescripcion1(String pafaDescripcion1) {
        this.pafaDescripcion1 = pafaDescripcion1;
    }

    public String getPafaDirecciones() {
        return pafaDirecciones;
    }

    public void setPafaDirecciones(String pafaDirecciones) {
        this.pafaDirecciones = pafaDirecciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pafaId != null ? pafaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametrofactura)) {
            return false;
        }
        Parametrofactura other = (Parametrofactura) object;
        if ((this.pafaId == null && other.pafaId != null) || (this.pafaId != null && !this.pafaId.equals(other.pafaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Parametrofactura[ pafaId=" + pafaId + " ]";
    }

    public int getPafaIva() {
        return pafaIva;
    }

    public void setPafaIva(int pafaIva) {
        this.pafaIva = pafaIva;
    }

    public String getPafaPrefijo() {
        return pafaPrefijo;
    }

    public void setPafaPrefijo(String pafaPrefijo) {
        this.pafaPrefijo = pafaPrefijo;
    }
    
}
