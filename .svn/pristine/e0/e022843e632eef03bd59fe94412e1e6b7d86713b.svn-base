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
@Cacheable(false)
@Entity
@Table(name = "sincro_pacientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SincroPacientes.findAll", query = "SELECT s FROM SincroPacientes s"),
    @NamedQuery(name = "SincroPacientes.findBySipaId", query = "SELECT s FROM SincroPacientes s WHERE s.sipaId = :sipaId"),
    @NamedQuery(name = "SincroPacientes.findByPaciId", query = "SELECT s FROM SincroPacientes s WHERE s.paciId = :paciId"),
    @NamedQuery(name = "SincroPacientes.findBySipaFechacambio", query = "SELECT s FROM SincroPacientes s WHERE s.sipaFechacambio = :sipaFechacambio")})
public class SincroPacientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sipa_id")
    private Integer sipaId;
    @Basic(optional = false)
    @Column(name = "paci_id")
    private int paciId;
    @Basic(optional = false)
    @Column(name = "sipa_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sipaFechacambio;

    public SincroPacientes() {
    }

    public SincroPacientes(Integer sipaId) {
        this.sipaId = sipaId;
    }

    public SincroPacientes(Integer sipaId, int paciId, Date sipaFechacambio) {
        this.sipaId = sipaId;
        this.paciId = paciId;
        this.sipaFechacambio = sipaFechacambio;
    }

    public Integer getSipaId() {
        return sipaId;
    }

    public void setSipaId(Integer sipaId) {
        this.sipaId = sipaId;
    }

    public int getPaciId() {
        return paciId;
    }

    public void setPaciId(int paciId) {
        this.paciId = paciId;
    }

    public Date getSipaFechacambio() {
        return sipaFechacambio;
    }

    public void setSipaFechacambio(Date sipaFechacambio) {
        this.sipaFechacambio = sipaFechacambio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipaId != null ? sipaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SincroPacientes)) {
            return false;
        }
        SincroPacientes other = (SincroPacientes) object;
        if ((this.sipaId == null && other.sipaId != null) || (this.sipaId != null && !this.sipaId.equals(other.sipaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.SincroPacientes[ sipaId=" + sipaId + " ]";
    }
    
}
