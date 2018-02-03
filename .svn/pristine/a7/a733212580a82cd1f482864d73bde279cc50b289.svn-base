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
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)
@Table(name = "respuestas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuestas.findAll", query = "SELECT r FROM Respuestas r"),
    @NamedQuery(name = "Respuestas.findByRespId", query = "SELECT r FROM Respuestas r WHERE r.respId = :respId"),
    @NamedQuery(name = "Respuestas.findByRespDescripcion", query = "SELECT r FROM Respuestas r WHERE r.respDescripcion = :respDescripcion"),
    @NamedQuery(name = "Respuestas.findByRespObservacion", query = "SELECT r FROM Respuestas r WHERE r.respObservacion = :respObservacion"),
    @NamedQuery(name = "Respuestas.findByRespRegistradopor", query = "SELECT r FROM Respuestas r WHERE r.respRegistradopor = :respRegistradopor"),
    @NamedQuery(name = "Respuestas.findBycampAndAnot", query = "SELECT r FROM Respuestas r WHERE r.campId = :camp AND r.anotId = :anot"),
    @NamedQuery(name = "Respuestas.findByRespFechacambio", query = "SELECT r FROM Respuestas r WHERE r.respFechacambio = :respFechacambio")})
public class Respuestas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "resp_id")
    private Integer respId;
    @Column(name = "resp_descripcion")
    private String respDescripcion;
    @Column(name = "resp_observacion")
    private String respObservacion;
    @Column(name = "resp_registradopor")
    private String respRegistradopor;
    @Column(name = "resp_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date respFechacambio;
    @JoinColumn(name = "anot_id", referencedColumnName = "anot_id")
    @ManyToOne
    private Anotaciones anotId;
    @JoinColumn(name = "camp_id", referencedColumnName = "camp_id")
    @ManyToOne
    private Campos campId;

    public Respuestas() {
    }

    public Respuestas(Integer respId) {
        this.respId = respId;
    }

    public Integer getRespId() {
        return respId;
    }

    public void setRespId(Integer respId) {
        this.respId = respId;
    }

    public String getRespDescripcion() {
        return respDescripcion;
    }

    public void setRespDescripcion(String respDescripcion) {
        this.respDescripcion = respDescripcion;
    }

    public String getRespObservacion() {
        return respObservacion;
    }

    public void setRespObservacion(String respObservacion) {
        this.respObservacion = respObservacion;
    }

    public String getRespRegistradopor() {
        return respRegistradopor;
    }

    public void setRespRegistradopor(String respRegistradopor) {
        this.respRegistradopor = respRegistradopor;
    }

    public Date getRespFechacambio() {
        return respFechacambio;
    }

    public void setRespFechacambio(Date respFechacambio) {
        this.respFechacambio = respFechacambio;
    }

    public Anotaciones getAnotId() {
        return anotId;
    }

    public void setAnotId(Anotaciones anotId) {
        this.anotId = anotId;
    }

    public Campos getCampId() {
        return campId;
    }

    public void setCampId(Campos campId) {
        this.campId = campId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (respId != null ? respId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestas)) {
            return false;
        }
        Respuestas other = (Respuestas) object;
        if ((this.respId == null && other.respId != null) || (this.respId != null && !this.respId.equals(other.respId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Respuestas[ respId=" + respId + " ]";
    }
    
}
