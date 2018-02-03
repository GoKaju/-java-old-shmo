/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.valueobjects;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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

/**
 *
 * @author D4V3
 */
@Entity
@Table(name = "actividadeconomica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividadeconomica.findAll", query = "SELECT a FROM Actividadeconomica a"),
    @NamedQuery(name = "Actividadeconomica.findByAcecId", query = "SELECT a FROM Actividadeconomica a WHERE a.acecId = :acecId"),
    @NamedQuery(name = "Actividadeconomica.findByAcecDescripcion", query = "SELECT a FROM Actividadeconomica a WHERE a.acecDescripcion = :acecDescripcion"),
    @NamedQuery(name = "Actividadeconomica.findByAcecCodigo", query = "SELECT a FROM Actividadeconomica a WHERE a.acecCodigo = :acecCodigo"),
    @NamedQuery(name = "Actividadeconomica.findByAcecFechacambio", query = "SELECT a FROM Actividadeconomica a WHERE a.acecFechacambio = :acecFechacambio"),
    @NamedQuery(name = "Actividadeconomica.findByAcecRegistradopor", query = "SELECT a FROM Actividadeconomica a WHERE a.acecRegistradopor = :acecRegistradopor")})
public class Actividadeconomica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acec_id")
    private Integer acecId;
    @Basic(optional = false)
    @Column(name = "acec_descripcion")
    private String acecDescripcion;
    @Basic(optional = false)
    @Column(name = "acec_codigo")
    private String acecCodigo;
    @Basic(optional = false)
    @Column(name = "acec_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acecFechacambio;
    @Basic(optional = false)
    @Column(name = "acec_registradopor")
    private int acecRegistradopor;

    public Actividadeconomica() {
    }

    public Actividadeconomica(Integer acecId) {
        this.acecId = acecId;
    }

    public Actividadeconomica(Integer acecId, String acecDescripcion, String acecCodigo, Date acecFechacambio, int acecRegistradopor) {
        this.acecId = acecId;
        this.acecDescripcion = acecDescripcion;
        this.acecCodigo = acecCodigo;
        this.acecFechacambio = acecFechacambio;
        this.acecRegistradopor = acecRegistradopor;
    }

    public Integer getAcecId() {
        return acecId;
    }

    public void setAcecId(Integer acecId) {
        this.acecId = acecId;
    }

    public String getAcecDescripcion() {
        return acecDescripcion;
    }

    public void setAcecDescripcion(String acecDescripcion) {
        this.acecDescripcion = acecDescripcion;
    }

    public String getAcecCodigo() {
        return acecCodigo;
    }

    public void setAcecCodigo(String acecCodigo) {
        this.acecCodigo = acecCodigo;
    }

    public Date getAcecFechacambio() {
        return acecFechacambio;
    }

    public void setAcecFechacambio(Date acecFechacambio) {
        this.acecFechacambio = acecFechacambio;
    }

    public int getAcecRegistradopor() {
        return acecRegistradopor;
    }

    public void setAcecRegistradopor(int acecRegistradopor) {
        this.acecRegistradopor = acecRegistradopor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acecId != null ? acecId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividadeconomica)) {
            return false;
        }
        Actividadeconomica other = (Actividadeconomica) object;
        if ((this.acecId == null && other.acecId != null) || (this.acecId != null && !this.acecId.equals(other.acecId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Actividadeconomica[ acecId=" + acecId + " ]";
    }
    
}
