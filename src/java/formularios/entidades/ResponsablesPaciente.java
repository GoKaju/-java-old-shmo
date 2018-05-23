/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.entidades;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "responsables_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResponsablesPaciente.findAll", query = "SELECT r FROM ResponsablesPaciente r")
    , @NamedQuery(name = "ResponsablesPaciente.findByRepaId", query = "SELECT r FROM ResponsablesPaciente r WHERE r.repaId = :repaId")
    , @NamedQuery(name = "ResponsablesPaciente.findByPaciId", query = "SELECT  r FROM ResponsablesPaciente r WHERE r.paciId = :paciId order by r.repaId desc ")
    , @NamedQuery(name = "ResponsablesPaciente.findByRepaNombre", query = "SELECT r FROM ResponsablesPaciente r WHERE r.repaNombre = :repaNombre")
    , @NamedQuery(name = "ResponsablesPaciente.findByRepaDocumento", query = "SELECT r FROM ResponsablesPaciente r WHERE r.repaDocumento = :repaDocumento")
    , @NamedQuery(name = "ResponsablesPaciente.findByRepaParentesco", query = "SELECT r FROM ResponsablesPaciente r WHERE r.repaParentesco = :repaParentesco")
    , @NamedQuery(name = "ResponsablesPaciente.findByRepaDireccion", query = "SELECT r FROM ResponsablesPaciente r WHERE r.repaDireccion = :repaDireccion")
    , @NamedQuery(name = "ResponsablesPaciente.findByRepaTelefono", query = "SELECT r FROM ResponsablesPaciente r WHERE r.repaTelefono = :repaTelefono")
    , @NamedQuery(name = "ResponsablesPaciente.findByRepaTipo", query = "SELECT r FROM ResponsablesPaciente r WHERE r.repaTipo = :repaTipo")
    , @NamedQuery(name = "ResponsablesPaciente.findByTickId", query = "SELECT r FROM ResponsablesPaciente r WHERE r.tickId = :tickId  order by r.repaId desc")})
public class ResponsablesPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "repa_id")
    private Integer repaId;
    @Column(name = "paci_id")
    private Integer paciId;
    @Size(max = 100)
    @Column(name = "repa_nombre")
    private String repaNombre;
    @Size(max = 20)
    @Column(name = "repa_documento")
    private String repaDocumento;
    @Size(max = 20)
    @Column(name = "repa_parentesco")
    private String repaParentesco;
    @Size(max = 50)
    @Column(name = "repa_direccion")
    private String repaDireccion;
    @Size(max = 50)
    @Column(name = "repa_telefono")
    private String repaTelefono;
    @Size(max = 20)
    @Column(name = "repa_tipo")
    private String repaTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tick_id")
    private int tickId;

    public ResponsablesPaciente() {
    }

    public ResponsablesPaciente(Integer repaId) {
        this.repaId = repaId;
    }

    public ResponsablesPaciente(Integer repaId, int tickId) {
        this.repaId = repaId;
        this.tickId = tickId;
    }

    public Integer getRepaId() {
        return repaId;
    }

    public void setRepaId(Integer repaId) {
        this.repaId = repaId;
    }

    public Integer getPaciId() {
        return paciId;
    }

    public void setPaciId(Integer paciId) {
        this.paciId = paciId;
    }

    public String getRepaNombre() {
        return repaNombre;
    }

    public void setRepaNombre(String repaNombre) {
        this.repaNombre = repaNombre;
    }

    public String getRepaDocumento() {
        return repaDocumento;
    }

    public void setRepaDocumento(String repaDocumento) {
        this.repaDocumento = repaDocumento;
    }

    public String getRepaParentesco() {
        return repaParentesco;
    }

    public void setRepaParentesco(String repaParentesco) {
        this.repaParentesco = repaParentesco;
    }

    public String getRepaDireccion() {
        return repaDireccion;
    }

    public void setRepaDireccion(String repaDireccion) {
        this.repaDireccion = repaDireccion;
    }

    public String getRepaTelefono() {
        return repaTelefono;
    }

    public void setRepaTelefono(String repaTelefono) {
        this.repaTelefono = repaTelefono;
    }

    public String getRepaTipo() {
        return repaTipo;
    }

    public void setRepaTipo(String repaTipo) {
        this.repaTipo = repaTipo;
    }

    public int getTickId() {
        return tickId;
    }

    public void setTickId(int tickId) {
        this.tickId = tickId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repaId != null ? repaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsablesPaciente)) {
            return false;
        }
        ResponsablesPaciente other = (ResponsablesPaciente) object;
        if ((this.repaId == null && other.repaId != null) || (this.repaId != null && !this.repaId.equals(other.repaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.ResponsablesPaciente[ repaId=" + repaId + " ]";
    }
    
}
