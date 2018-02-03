/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.valueobjects;

import formularios.entidades.ClienteSedes;
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
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"),
    @NamedQuery(name = "Clientes.findByClieId", query = "SELECT c FROM Clientes c WHERE c.clieId = :clieId"),
    @NamedQuery(name = "Clientes.findByClieTipo", query = "SELECT c FROM Clientes c WHERE c.clieTipo = :clieTipo"),
    @NamedQuery(name = "Clientes.findByClieTipoYdoc", query = "SELECT c FROM Clientes c WHERE c.clieTipo = :clieTipo AND c.clieDocumento = :clieDocumento"),
    @NamedQuery(name = "Clientes.findByClieDocumento", query = "SELECT c FROM Clientes c WHERE c.clieDocumento = :clieDocumento"),
    @NamedQuery(name = "Clientes.busqueda", query = "SELECT c FROM Clientes c WHERE c.clieDocumento LIKE :Documento AND c.clieNombre LIKE :Nombres"),
    @NamedQuery(name = "Clientes.findByClieNombre", query = "SELECT c FROM Clientes c WHERE c.clieNombre = :clieNombre"),
    @NamedQuery(name = "Clientes.findByClieEstado", query = "SELECT c FROM Clientes c WHERE c.clieEstado = :clieEstado"),
    @NamedQuery(name = "Clientes.findByClieDireccion", query = "SELECT c FROM Clientes c WHERE c.clieDireccion = :clieDireccion"),
    @NamedQuery(name = "Clientes.findByClieTelefonos", query = "SELECT c FROM Clientes c WHERE c.clieTelefonos = :clieTelefonos"),
    @NamedQuery(name = "Clientes.findByClieEmail", query = "SELECT c FROM Clientes c WHERE c.clieEmail = :clieEmail"),
    @NamedQuery(name = "Clientes.findByClierepLegal", query = "SELECT c FROM Clientes c WHERE c.clierepLegal = :clierepLegal"),
    @NamedQuery(name = "Clientes.findByClieObservacion", query = "SELECT c FROM Clientes c WHERE c.clieObservacion = :clieObservacion"),
    @NamedQuery(name = "Clientes.findByClieRegistradopor", query = "SELECT c FROM Clientes c WHERE c.clieRegistradopor = :clieRegistradopor"),
    @NamedQuery(name = "Clientes.findByClieFechacambio", query = "SELECT c FROM Clientes c WHERE c.clieFechacambio = :clieFechacambio")})
public class Clientes implements Serializable {
    @OneToMany(mappedBy = "clieId")
    private List<ClienteSedes> clienteSedesList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clie_id")
    private Integer clieId;
    @Column(name = "clie_tipo")
    private String clieTipo;
    @Column(name = "clie_documento")
    private String clieDocumento;
    @Column(name = "clie_nombre")
    private String clieNombre;
    @Column(name = "clie_estado")
    private String clieEstado;
    @Column(name = "clie_direccion")
    private String clieDireccion;
    @Column(name = "clie_telefonos")
    private String clieTelefonos;
    @Column(name = "clie_email")
    private String clieEmail;
    @Column(name = "clie_repLegal")
    private String clierepLegal;
    @Column(name = "clie_observacion")
    private String clieObservacion;
    @Basic(optional = false)
    @Column(name = "clie_registradopor")
    private String clieRegistradopor;
    @Basic(optional = false)
    @Column(name = "clie_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clieFechacambio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clieId")
    private List<ClientesServicio> clientesServicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clieId")
    private List<Centrocostos> centrocostosList;
    @JoinColumn(name = "tido_id", referencedColumnName = "tido_id")
    @ManyToOne(optional = false)
    private Tipodocumento tidoId;
    @JoinColumn(name = "ciud_id", referencedColumnName = "ciud_id")
    @ManyToOne
    private Ciudades ciudId;

    public Clientes() {
    }

    public Clientes(Integer clieId) {
        this.clieId = clieId;
    }

    public Clientes(Integer clieId, String clieRegistradopor, Date clieFechacambio) {
        this.clieId = clieId;
        this.clieRegistradopor = clieRegistradopor;
        this.clieFechacambio = clieFechacambio;
    }

    public Integer getClieId() {
        return clieId;
    }

    public void setClieId(Integer clieId) {
        this.clieId = clieId;
    }

    public String getClieTipo() {
        return clieTipo;
    }

    public void setClieTipo(String clieTipo) {
        this.clieTipo = clieTipo;
    }

    public String getClieDocumento() {
        return clieDocumento;
    }

    public void setClieDocumento(String clieDocumento) {
        this.clieDocumento = clieDocumento;
    }

    public String getClieNombre() {
        return clieNombre;
    }

    public void setClieNombre(String clieNombre) {
        this.clieNombre = clieNombre;
    }

    public String getClieEstado() {
        return clieEstado;
    }

    public void setClieEstado(String clieEstado) {
        this.clieEstado = clieEstado;
    }

    public String getClieDireccion() {
        return clieDireccion;
    }

    public void setClieDireccion(String clieDireccion) {
        this.clieDireccion = clieDireccion;
    }

    public String getClieTelefonos() {
        return clieTelefonos;
    }

    public void setClieTelefonos(String clieTelefonos) {
        this.clieTelefonos = clieTelefonos;
    }

    public String getClieEmail() {
        return clieEmail;
    }

    public void setClieEmail(String clieEmail) {
        this.clieEmail = clieEmail;
    }

    public String getClierepLegal() {
        return clierepLegal;
    }

    public void setClierepLegal(String clierepLegal) {
        this.clierepLegal = clierepLegal;
    }

    public String getClieObservacion() {
        return clieObservacion;
    }

    public void setClieObservacion(String clieObservacion) {
        this.clieObservacion = clieObservacion;
    }

    public String getClieRegistradopor() {
        return clieRegistradopor;
    }

    public void setClieRegistradopor(String clieRegistradopor) {
        this.clieRegistradopor = clieRegistradopor;
    }

    public Date getClieFechacambio() {
        return clieFechacambio;
    }

    public void setClieFechacambio(Date clieFechacambio) {
        this.clieFechacambio = clieFechacambio;
    }

    @XmlTransient
    public List<ClientesServicio> getClientesServicioList() {
        return clientesServicioList;
    }

    public void setClientesServicioList(List<ClientesServicio> clientesServicioList) {
        this.clientesServicioList = clientesServicioList;
    }

    @XmlTransient
    public List<Centrocostos> getCentrocostosList() {
        return centrocostosList;
    }

    public void setCentrocostosList(List<Centrocostos> centrocostosList) {
        this.centrocostosList = centrocostosList;
    }

    public Tipodocumento getTidoId() {
        return tidoId;
    }

    public void setTidoId(Tipodocumento tidoId) {
        this.tidoId = tidoId;
    }

    public Ciudades getCiudId() {
        return ciudId;
    }

    public void setCiudId(Ciudades ciudId) {
        this.ciudId = ciudId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clieId != null ? clieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.clieId == null && other.clieId != null) || (this.clieId != null && !this.clieId.equals(other.clieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Clientes[ clieId=" + clieId + " ]";
    }

    @XmlTransient
    public List<ClienteSedes> getClienteSedesList() {
        return clienteSedesList;
    }

    public void setClienteSedesList(List<ClienteSedes> clienteSedesList) {
        this.clienteSedesList = clienteSedesList;
    }
    
}
