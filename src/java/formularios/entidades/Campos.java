/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)
@Table(name = "campos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campos.findAll", query = "SELECT c FROM Campos c"),
    @NamedQuery(name = "Campos.findByCampId", query = "SELECT c FROM Campos c WHERE c.campId = :campId"),
    @NamedQuery(name = "Campos.findByCampName", query = "SELECT c FROM Campos c WHERE c.campName = :campName"),
    @NamedQuery(name = "Campos.findByCampIdf", query = "SELECT c FROM Campos c WHERE c.campIdf = :campIdf"),
    @NamedQuery(name = "Campos.findByCampTipo", query = "SELECT c FROM Campos c WHERE c.campTipo = :campTipo"),
    @NamedQuery(name = "Campos.findByCampLabel", query = "SELECT c FROM Campos c WHERE c.campLabel = :campLabel"),
    @NamedQuery(name = "Campos.findByCampAtributos", query = "SELECT c FROM Campos c WHERE c.campAtributos = :campAtributos"),
    @NamedQuery(name = "Campos.findByCampValue", query = "SELECT c FROM Campos c WHERE c.campValue = :campValue")})
public class Campos implements Serializable {
    @Basic(optional = false)
    @Column(name = "camp_tipoAux")
    private String camptipoAux;
    @OneToMany(mappedBy = "campId")
    private List<Respuestas> respuestasList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "camp_id")
    private Integer campId;
    @Column(name = "camp_name")
    private String campName;
    @Column(name = "camp_idf")
    private String campIdf;
    @Column(name = "camp_tipo")
    private String campTipo;
    @Column(name = "camp_label")
    private String campLabel;
    @Column(name = "camp_atributos")
    private String campAtributos;
    @Column(name = "camp_value")
    private String campValue;
    @OneToMany(mappedBy = "campId")
    private List<Opciones> opcionesList;
    @OneToMany(mappedBy = "campId")
    private List<Ayudas> ayudasList;
    @JoinColumn(name = "cate_id", referencedColumnName = "cate_id")
    @ManyToOne
    private Categorias cateId;

    public Campos() {
    }

    public Campos(Integer campId) {
        this.campId = campId;
    }

    public Integer getCampId() {
        return campId;
    }

    public void setCampId(Integer campId) {
        this.campId = campId;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampIdf() {
        return campIdf;
    }

    public void setCampIdf(String campIdf) {
        this.campIdf = campIdf;
    }

    public String getCampTipo() {
        return campTipo;
    }

    public void setCampTipo(String campTipo) {
        this.campTipo = campTipo;
    }

    public String getCampLabel() {
        return campLabel;
    }

    public void setCampLabel(String campLabel) {
        this.campLabel = campLabel;
    }

    public String getCampAtributos() {
        return campAtributos;
    }

    public void setCampAtributos(String campAtributos) {
        this.campAtributos = campAtributos;
    }

    public String getCampValue() {
        return campValue;
    }

    public void setCampValue(String campValue) {
        this.campValue = campValue;
    }

    @XmlTransient
    public List<Opciones> getOpcionesList() {
        return opcionesList;
    }

    public void setOpcionesList(List<Opciones> opcionesList) {
        this.opcionesList = opcionesList;
    }

    @XmlTransient
    public List<Ayudas> getAyudasList() {
        return ayudasList;
    }

    public void setAyudasList(List<Ayudas> ayudasList) {
        this.ayudasList = ayudasList;
    }

    public Categorias getCateId() {
        return cateId;
    }

    public void setCateId(Categorias cateId) {
        this.cateId = cateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (campId != null ? campId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campos)) {
            return false;
        }
        Campos other = (Campos) object;
        if ((this.campId == null && other.campId != null) || (this.campId != null && !this.campId.equals(other.campId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Campos[ campId=" + campId + " ]";
    }

    @XmlTransient
    public List<Respuestas> getRespuestasList() {
        return respuestasList;
    }

    public void setRespuestasList(List<Respuestas> respuestasList) {
        this.respuestasList = respuestasList;
    }

    public String getCamptipoAux() {
        return camptipoAux;
    }

    public void setCamptipoAux(String camptipoAux) {
        this.camptipoAux = camptipoAux;
    }
    
}
