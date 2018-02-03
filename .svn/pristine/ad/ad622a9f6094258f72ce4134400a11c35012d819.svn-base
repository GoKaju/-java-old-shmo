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
@Table(name = "categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"),
    @NamedQuery(name = "Categorias.findByCateId", query = "SELECT c FROM Categorias c WHERE c.cateId = :cateId"),
    @NamedQuery(name = "Categorias.findByCateScript", query = "SELECT c FROM Categorias c WHERE c.cateScript = :cateScript"),
    @NamedQuery(name = "Categorias.findByCateDescripcion", query = "SELECT c FROM Categorias c WHERE c.cateDescripcion = :cateDescripcion")})
public class Categorias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cate_id")
    private Integer cateId;
    @Column(name = "cate_script")
    private String cateScript;
    @Column(name = "cate_descripcion")
    private String cateDescripcion;
    @JoinColumn(name = "form_id", referencedColumnName = "form_id")
    @ManyToOne
    private Formularios formId;
    @OneToMany(mappedBy = "cateId")
    private List<Campos> camposList;

    public Categorias() {
    }

    public Categorias(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateScript() {
        return cateScript;
    }

    public void setCateScript(String cateScript) {
        this.cateScript = cateScript;
    }

    public String getCateDescripcion() {
        return cateDescripcion;
    }

    public void setCateDescripcion(String cateDescripcion) {
        this.cateDescripcion = cateDescripcion;
    }

    public Formularios getFormId() {
        return formId;
    }

    public void setFormId(Formularios formId) {
        this.formId = formId;
    }

    @XmlTransient
    public List<Campos> getCamposList() {
        return camposList;
    }

    public void setCamposList(List<Campos> camposList) {
        this.camposList = camposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cateId != null ? cateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.cateId == null && other.cateId != null) || (this.cateId != null && !this.cateId.equals(other.cateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Categorias[ cateId=" + cateId + " ]";
    }
    
}
