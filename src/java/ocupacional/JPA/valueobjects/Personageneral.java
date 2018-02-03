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
@Entity
@Cacheable(false)

@Table(name = "personageneral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personageneral.findAll", query = "SELECT p FROM Personageneral p"),
    @NamedQuery(name = "Personageneral.findByPegeId", query = "SELECT p FROM Personageneral p WHERE p.pegeId = :pegeId"),
    @NamedQuery(name = "Personageneral.findByPegeDocumento", query = "SELECT p FROM Personageneral p WHERE p.pegeDocumento = :pegeDocumento"),
    @NamedQuery(name = "Personageneral.findByTidoId", query = "SELECT p FROM Personageneral p WHERE p.tidoId = :tidoId"),
    @NamedQuery(name = "Personageneral.findByCiudId", query = "SELECT p FROM Personageneral p WHERE p.ciudId = :ciudId"),
    @NamedQuery(name = "Personageneral.findByPegeTelefono", query = "SELECT p FROM Personageneral p WHERE p.pegeTelefono = :pegeTelefono"),
    @NamedQuery(name = "Personageneral.findByPegeEmail", query = "SELECT p FROM Personageneral p WHERE p.pegeEmail = :pegeEmail"),
    @NamedQuery(name = "Personageneral.findByPegeDireccion", query = "SELECT p FROM Personageneral p WHERE p.pegeDireccion = :pegeDireccion"),
    @NamedQuery(name = "Personageneral.findByPegeFechacambio", query = "SELECT p FROM Personageneral p WHERE p.pegeFechacambio = :pegeFechacambio"),
    @NamedQuery(name = "Personageneral.findByPegeRegistradopor", query = "SELECT p FROM Personageneral p WHERE p.pegeRegistradopor = :pegeRegistradopor"),
    @NamedQuery(name = "Personageneral.findByPegeCelular", query = "SELECT p FROM Personageneral p WHERE p.pegeCelular = :pegeCelular")})
public class Personageneral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pege_id")
    private Integer pegeId;
    @Column(name = "pege_documento")
    private String pegeDocumento;
    @Column(name = "tido_id")
    private Integer tidoId;
    @Column(name = "ciud_id")
    private Integer ciudId;
    @Column(name = "pege_telefono")
    private String pegeTelefono;
    @Column(name = "pege_email")
    private String pegeEmail;
    @Column(name = "pege_direccion")
    private String pegeDireccion;
    @Column(name = "pege_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pegeFechacambio;
    @Column(name = "pege_registradopor")
    private Integer pegeRegistradopor;
    @Column(name = "pege_celular")
    private String pegeCelular;

    public Personageneral() {
    }

    public Personageneral(Integer pegeId) {
        this.pegeId = pegeId;
    }

    public Integer getPegeId() {
        return pegeId;
    }

    public void setPegeId(Integer pegeId) {
        this.pegeId = pegeId;
    }

    public String getPegeDocumento() {
        return pegeDocumento;
    }

    public void setPegeDocumento(String pegeDocumento) {
        this.pegeDocumento = pegeDocumento;
    }

    public Integer getTidoId() {
        return tidoId;
    }

    public void setTidoId(Integer tidoId) {
        this.tidoId = tidoId;
    }

    public Integer getCiudId() {
        return ciudId;
    }

    public void setCiudId(Integer ciudId) {
        this.ciudId = ciudId;
    }

    public String getPegeTelefono() {
        return pegeTelefono;
    }

    public void setPegeTelefono(String pegeTelefono) {
        this.pegeTelefono = pegeTelefono;
    }

    public String getPegeEmail() {
        return pegeEmail;
    }

    public void setPegeEmail(String pegeEmail) {
        this.pegeEmail = pegeEmail;
    }

    public String getPegeDireccion() {
        return pegeDireccion;
    }

    public void setPegeDireccion(String pegeDireccion) {
        this.pegeDireccion = pegeDireccion;
    }

    public Date getPegeFechacambio() {
        return pegeFechacambio;
    }

    public void setPegeFechacambio(Date pegeFechacambio) {
        this.pegeFechacambio = pegeFechacambio;
    }

    public Integer getPegeRegistradopor() {
        return pegeRegistradopor;
    }

    public void setPegeRegistradopor(Integer pegeRegistradopor) {
        this.pegeRegistradopor = pegeRegistradopor;
    }

    public String getPegeCelular() {
        return pegeCelular;
    }

    public void setPegeCelular(String pegeCelular) {
        this.pegeCelular = pegeCelular;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pegeId != null ? pegeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personageneral)) {
            return false;
        }
        Personageneral other = (Personageneral) object;
        if ((this.pegeId == null && other.pegeId != null) || (this.pegeId != null && !this.pegeId.equals(other.pegeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Personageneral[ pegeId=" + pegeId + " ]";
    }
    
}
