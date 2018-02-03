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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByUsuaId", query = "SELECT u FROM Usuarios u WHERE u.usuaId = :usuaId"),
    @NamedQuery(name = "Usuarios.findByUsuaTipo", query = "SELECT u FROM Usuarios u WHERE u.usuaTipo = :usuaTipo"),
    @NamedQuery(name = "Usuarios.findByUsuaIdpersona", query = "SELECT u FROM Usuarios u WHERE u.usuaIdpersona = :usuaIdpersona"),
    @NamedQuery(name = "Usuarios.findByUsuaUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuaUsuario = :usuaUsuario"),
    @NamedQuery(name = "Usuarios.findByUsuaContrasena", query = "SELECT u FROM Usuarios u WHERE u.usuaContrasena = :usuaContrasena"),
    @NamedQuery(name = "Usuarios.findByUsuaUltimoacceso", query = "SELECT u FROM Usuarios u WHERE u.usuaUltimoacceso = :usuaUltimoacceso"),
    @NamedQuery(name = "Usuarios.findBySedeId", query = "SELECT u FROM Usuarios u WHERE u.sedeId = :sedeId"),
    @NamedQuery(name = "Usuarios.findByUsuaEstado", query = "SELECT u FROM Usuarios u WHERE u.usuaEstado = :usuaEstado"),
    @NamedQuery(name = "Usuarios.findByUsuaRegistradopor", query = "SELECT u FROM Usuarios u WHERE u.usuaRegistradopor = :usuaRegistradopor"),
    @NamedQuery(name = "Usuarios.findByUsuaFechacambio", query = "SELECT u FROM Usuarios u WHERE u.usuaFechacambio = :usuaFechacambio")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usua_id")
    private Integer usuaId;
    @Column(name = "usua_tipo")
    private String usuaTipo;
    @Column(name = "usua_idpersona")
    private Integer usuaIdpersona;
    @Basic(optional = false)
    @Column(name = "usua_usuario")
    private String usuaUsuario;
    @Basic(optional = false)
    @Column(name = "usua_contrasena")
    private String usuaContrasena;
    @Column(name = "usua_ultimoacceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuaUltimoacceso;
    @Column(name = "sede_id")
    private Integer sedeId;
    @Column(name = "usua_estado")
    private String usuaEstado;
    @Basic(optional = false)
    @Column(name = "usua_registradopor")
    private int usuaRegistradopor;
    @Basic(optional = false)
    @Column(name = "usua_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuaFechacambio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuaId")
    private List<Usuariorol> usuariorolList;

    public Usuarios() {
    }

    public Usuarios(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public Usuarios(Integer usuaId, String usuaUsuario, String usuaContrasena, int usuaRegistradopor, Date usuaFechacambio) {
        this.usuaId = usuaId;
        this.usuaUsuario = usuaUsuario;
        this.usuaContrasena = usuaContrasena;
        this.usuaRegistradopor = usuaRegistradopor;
        this.usuaFechacambio = usuaFechacambio;
    }

    public Integer getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public String getUsuaTipo() {
        return usuaTipo;
    }

    public void setUsuaTipo(String usuaTipo) {
        this.usuaTipo = usuaTipo;
    }

    public Integer getUsuaIdpersona() {
        return usuaIdpersona;
    }

    public void setUsuaIdpersona(Integer usuaIdpersona) {
        this.usuaIdpersona = usuaIdpersona;
    }

    public String getUsuaUsuario() {
        return usuaUsuario;
    }

    public void setUsuaUsuario(String usuaUsuario) {
        this.usuaUsuario = usuaUsuario;
    }

    public String getUsuaContrasena() {
        return usuaContrasena;
    }

    public void setUsuaContrasena(String usuaContrasena) {
        this.usuaContrasena = usuaContrasena;
    }

    public Date getUsuaUltimoacceso() {
        return usuaUltimoacceso;
    }

    public void setUsuaUltimoacceso(Date usuaUltimoacceso) {
        this.usuaUltimoacceso = usuaUltimoacceso;
    }

    public Integer getSedeId() {
        return sedeId;
    }

    public void setSedeId(Integer sedeId) {
        this.sedeId = sedeId;
    }

    public String getUsuaEstado() {
        return usuaEstado;
    }

    public void setUsuaEstado(String usuaEstado) {
        this.usuaEstado = usuaEstado;
    }

    public int getUsuaRegistradopor() {
        return usuaRegistradopor;
    }

    public void setUsuaRegistradopor(int usuaRegistradopor) {
        this.usuaRegistradopor = usuaRegistradopor;
    }

    public Date getUsuaFechacambio() {
        return usuaFechacambio;
    }

    public void setUsuaFechacambio(Date usuaFechacambio) {
        this.usuaFechacambio = usuaFechacambio;
    }

    @XmlTransient
    public List<Usuariorol> getUsuariorolList() {
        return usuariorolList;
    }

    public void setUsuariorolList(List<Usuariorol> usuariorolList) {
        this.usuariorolList = usuariorolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuaId != null ? usuaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuaId == null && other.usuaId != null) || (this.usuaId != null && !this.usuaId.equals(other.usuaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Usuarios[ usuaId=" + usuaId + " ]";
    }
    
}
