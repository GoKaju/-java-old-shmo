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
import javax.persistence.Lob;
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
@Table(name = "huellafirma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Huellafirma.findAll", query = "SELECT h FROM Huellafirma h"),
    @NamedQuery(name = "Huellafirma.findByHufiId", query = "SELECT h FROM Huellafirma h WHERE h.hufiId = :hufiId"),
    @NamedQuery(name = "Huellafirma.findByAnotRegistradopor", query = "SELECT h FROM Huellafirma h WHERE h.anotRegistradopor = :anotRegistradopor"),
    @NamedQuery(name = "Huellafirma.findByAnotFechacambio", query = "SELECT h FROM Huellafirma h WHERE h.anotFechacambio = :anotFechacambio")})
public class Huellafirma implements Serializable {
    @Lob
    @Column(name = "anot_huella")
    private String anotHuella;
    @Lob
    @Column(name = "anot_firma")
    private String anotFirma;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hufi_id")
    private Integer hufiId;
    @Column(name = "anot_registradopor")
    private String anotRegistradopor;
    @Column(name = "anot_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date anotFechacambio;
    @JoinColumn(name = "anot_id", referencedColumnName = "anot_id")
    @ManyToOne(optional = false)
    private Anotaciones anotId;

    public Huellafirma() {
    }

    public Huellafirma(Integer hufiId) {
        this.hufiId = hufiId;
    }

    public Integer getHufiId() {
        return hufiId;
    }

    public void setHufiId(Integer hufiId) {
        this.hufiId = hufiId;
    }

  

    public String getAnotRegistradopor() {
        return anotRegistradopor;
    }

    public void setAnotRegistradopor(String anotRegistradopor) {
        this.anotRegistradopor = anotRegistradopor;
    }

    public Date getAnotFechacambio() {
        return anotFechacambio;
    }

    public void setAnotFechacambio(Date anotFechacambio) {
        this.anotFechacambio = anotFechacambio;
    }

    public Anotaciones getAnotId() {
        return anotId;
    }

    public void setAnotId(Anotaciones anotId) {
        this.anotId = anotId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hufiId != null ? hufiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Huellafirma)) {
            return false;
        }
        Huellafirma other = (Huellafirma) object;
        if ((this.hufiId == null && other.hufiId != null) || (this.hufiId != null && !this.hufiId.equals(other.hufiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Huellafirma[ hufiId=" + hufiId + " ]";
    }

    public String getAnotHuella() {
        return anotHuella;
    }

    public void setAnotHuella(String anotHuella) {
        this.anotHuella = anotHuella;
    }

    public String getAnotFirma() {
        return anotFirma;
    }

    public void setAnotFirma(String anotFirma) {
        this.anotFirma = anotFirma;
    }
    
}
