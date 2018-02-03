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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)
@Table(name = "ayudas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ayudas.findAll", query = "SELECT a FROM Ayudas a"),
    @NamedQuery(name = "Ayudas.findByAyudId", query = "SELECT a FROM Ayudas a WHERE a.ayudId = :ayudId"),
    @NamedQuery(name = "Ayudas.findByAyudTipo", query = "SELECT a FROM Ayudas a WHERE a.ayudTipo = :ayudTipo"),
    @NamedQuery(name = "Ayudas.findByAyudRuta", query = "SELECT a FROM Ayudas a WHERE a.ayudRuta = :ayudRuta"),
    @NamedQuery(name = "Ayudas.findByAyudDescripcion", query = "SELECT a FROM Ayudas a WHERE a.ayudDescripcion = :ayudDescripcion")})
public class Ayudas implements Serializable {
    @Lob
    @Column(name = "ayud_img")
    private byte[] ayudImg;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ayud_id")
    private Integer ayudId;
    @Column(name = "ayud_tipo")
    private String ayudTipo;
    @Column(name = "ayud_ruta")
    private String ayudRuta;
    @Column(name = "ayud_descripcion")
    private String ayudDescripcion;
    @JoinColumn(name = "camp_id", referencedColumnName = "camp_id")
    @ManyToOne
    private Campos campId;

    public Ayudas() {
    }

    public Ayudas(Integer ayudId) {
        this.ayudId = ayudId;
    }

    public Integer getAyudId() {
        return ayudId;
    }

    public void setAyudId(Integer ayudId) {
        this.ayudId = ayudId;
    }

    public String getAyudTipo() {
        return ayudTipo;
    }

    public void setAyudTipo(String ayudTipo) {
        this.ayudTipo = ayudTipo;
    }

    public String getAyudRuta() {
        return ayudRuta;
    }

    public void setAyudRuta(String ayudRuta) {
        this.ayudRuta = ayudRuta;
    }

    public String getAyudDescripcion() {
        return ayudDescripcion;
    }

    public void setAyudDescripcion(String ayudDescripcion) {
        this.ayudDescripcion = ayudDescripcion;
    }

    public byte[] getAyudImg() {
        return ayudImg;
    }

    public void setAyudImg(byte[] ayudImg) {
        this.ayudImg = ayudImg;
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
        hash += (ayudId != null ? ayudId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ayudas)) {
            return false;
        }
        Ayudas other = (Ayudas) object;
        if ((this.ayudId == null && other.ayudId != null) || (this.ayudId != null && !this.ayudId.equals(other.ayudId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Ayudas[ ayudId=" + ayudId + " ]";
    }

    
}
