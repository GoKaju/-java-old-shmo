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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tipo_vinculacion_eps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVinculacionEps.findAll", query = "SELECT t FROM TipoVinculacionEps t")
    , @NamedQuery(name = "TipoVinculacionEps.findByTvepId", query = "SELECT t FROM TipoVinculacionEps t WHERE t.tvepId = :tvepId")
    , @NamedQuery(name = "TipoVinculacionEps.findByTvepDescripcion", query = "SELECT t FROM TipoVinculacionEps t WHERE t.tvepDescripcion = :tvepDescripcion")
    , @NamedQuery(name = "TipoVinculacionEps.findByTvepObservacion", query = "SELECT t FROM TipoVinculacionEps t WHERE t.tvepObservacion = :tvepObservacion")})
public class TipoVinculacionEps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tvep_id")
    private Integer tvepId;
    @Size(max = 50)
    @Column(name = "tvep_descripcion")
    private String tvepDescripcion;
    @Size(max = 100)
    @Column(name = "tvep_observacion")
    private String tvepObservacion;

    public TipoVinculacionEps() {
    }

    public TipoVinculacionEps(Integer tvepId) {
        this.tvepId = tvepId;
    }

    public Integer getTvepId() {
        return tvepId;
    }

    public void setTvepId(Integer tvepId) {
        this.tvepId = tvepId;
    }

    public String getTvepDescripcion() {
        return tvepDescripcion;
    }

    public void setTvepDescripcion(String tvepDescripcion) {
        this.tvepDescripcion = tvepDescripcion;
    }

    public String getTvepObservacion() {
        return tvepObservacion;
    }

    public void setTvepObservacion(String tvepObservacion) {
        this.tvepObservacion = tvepObservacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tvepId != null ? tvepId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVinculacionEps)) {
            return false;
        }
        TipoVinculacionEps other = (TipoVinculacionEps) object;
        if ((this.tvepId == null && other.tvepId != null) || (this.tvepId != null && !this.tvepId.equals(other.tvepId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.TipoVinculacionEps[ tvepId=" + tvepId + " ]";
    }
    
}
