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

@Table(name = "juridicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Juridicas.findAll", query = "SELECT j FROM Juridicas j"),
    @NamedQuery(name = "Juridicas.findByPegeId", query = "SELECT j FROM Juridicas j WHERE j.pegeId = :pegeId"),
    @NamedQuery(name = "Juridicas.findByJuriRazonsocial", query = "SELECT j FROM Juridicas j WHERE j.juriRazonsocial = :juriRazonsocial"),
    @NamedQuery(name = "Juridicas.findByJuriRepresentante", query = "SELECT j FROM Juridicas j WHERE j.juriRepresentante = :juriRepresentante"),
    @NamedQuery(name = "Juridicas.findByJuriFechacambio", query = "SELECT j FROM Juridicas j WHERE j.juriFechacambio = :juriFechacambio"),
    @NamedQuery(name = "Juridicas.findByJuriRegistradopor", query = "SELECT j FROM Juridicas j WHERE j.juriRegistradopor = :juriRegistradopor"),
    @NamedQuery(name = "Juridicas.findByAcecId", query = "SELECT j FROM Juridicas j WHERE j.acecId = :acecId"),
    @NamedQuery(name = "Juridicas.findByJuriSede", query = "SELECT j FROM Juridicas j WHERE j.juriSede = :juriSede")})
public class Juridicas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pege_id")
    private Integer pegeId;
    @Basic(optional = false)
    @Column(name = "juri_razonsocial")
    private String juriRazonsocial;
    @Column(name = "juri_representante")
    private String juriRepresentante;
    @Basic(optional = false)
    @Column(name = "juri_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date juriFechacambio;
    @Basic(optional = false)
    @Column(name = "juri_registradopor")
    private int juriRegistradopor;
    @Column(name = "acec_id")
    private Integer acecId;
    @Column(name = "juri_sede")
    private String juriSede;

    public Juridicas() {
    }

    public Juridicas(Integer pegeId) {
        this.pegeId = pegeId;
    }

    public Juridicas(Integer pegeId, String juriRazonsocial, Date juriFechacambio, int juriRegistradopor) {
        this.pegeId = pegeId;
        this.juriRazonsocial = juriRazonsocial;
        this.juriFechacambio = juriFechacambio;
        this.juriRegistradopor = juriRegistradopor;
    }

    public Integer getPegeId() {
        return pegeId;
    }

    public void setPegeId(Integer pegeId) {
        this.pegeId = pegeId;
    }

    public String getJuriRazonsocial() {
        return juriRazonsocial;
    }

    public void setJuriRazonsocial(String juriRazonsocial) {
        this.juriRazonsocial = juriRazonsocial;
    }

    public String getJuriRepresentante() {
        return juriRepresentante;
    }

    public void setJuriRepresentante(String juriRepresentante) {
        this.juriRepresentante = juriRepresentante;
    }

    public Date getJuriFechacambio() {
        return juriFechacambio;
    }

    public void setJuriFechacambio(Date juriFechacambio) {
        this.juriFechacambio = juriFechacambio;
    }

    public int getJuriRegistradopor() {
        return juriRegistradopor;
    }

    public void setJuriRegistradopor(int juriRegistradopor) {
        this.juriRegistradopor = juriRegistradopor;
    }

    public Integer getAcecId() {
        return acecId;
    }

    public void setAcecId(Integer acecId) {
        this.acecId = acecId;
    }

    public String getJuriSede() {
        return juriSede;
    }

    public void setJuriSede(String juriSede) {
        this.juriSede = juriSede;
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
        if (!(object instanceof Juridicas)) {
            return false;
        }
        Juridicas other = (Juridicas) object;
        if ((this.pegeId == null && other.pegeId != null) || (this.pegeId != null && !this.pegeId.equals(other.pegeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Juridicas[ pegeId=" + pegeId + " ]";
    }
    
}
