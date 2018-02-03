/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "cie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cie.findAll", query = "SELECT c FROM Cie c"),
    @NamedQuery(name = "Cie.findByCieId", query = "SELECT c FROM Cie c WHERE c.cieId = :cieId"),
    @NamedQuery(name = "Cie.findByCieDesc", query = "SELECT c FROM Cie c WHERE c.cieDesc LIKE :cieDesc"),
    @NamedQuery(name = "Cie.findByCieGrupo", query = "SELECT c FROM Cie c WHERE c.cieGrupo = :cieGrupo")})
public class Cie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cie_id")
    private String cieId;
    @Column(name = "cie_desc")
    private String cieDesc;
    @Column(name = "cie_grupo")
    private String cieGrupo;

    public Cie() {
    }

    public Cie(String cieId) {
        this.cieId = cieId;
    }

    public String getCieId() {
        return cieId;
    }

    public void setCieId(String cieId) {
        this.cieId = cieId;
    }

    public String getCieDesc() {
        return cieDesc;
    }

    public void setCieDesc(String cieDesc) {
        this.cieDesc = cieDesc;
    }

    public String getCieGrupo() {
        return cieGrupo;
    }

    public void setCieGrupo(String cieGrupo) {
        this.cieGrupo = cieGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cieId != null ? cieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cie)) {
            return false;
        }
        Cie other = (Cie) object;
        if ((this.cieId == null && other.cieId != null) || (this.cieId != null && !this.cieId.equals(other.cieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Cie[ cieId=" + cieId + " ]";
    }
    
}
