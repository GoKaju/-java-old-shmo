/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)
@Table(name = "anotaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anotaciones.findAll", query = "SELECT a FROM Anotaciones a"),
    @NamedQuery(name = "Anotaciones.findByAnotId", query = "SELECT a FROM Anotaciones a WHERE a.anotId = :anotId"),
    @NamedQuery(name = "Anotaciones.findByAnotEstado", query = "SELECT a FROM Anotaciones a WHERE a.anotEstado = :anotEstado"),
    @NamedQuery(name = "Anotaciones.findByTCLS", query = "SELECT a FROM Anotaciones a WHERE (a.anotEstado = 'EN EDICION' OR a.anotEstado = 'PRE EDICION') AND a.ticsId = :ticsId AND a.formId = :formId"),
    @NamedQuery(name = "Anotaciones.tics", query = "SELECT a FROM Anotaciones a WHERE a.anotEstado = :estado AND a.ticsId = :id "),
    @NamedQuery(name = "Anotaciones.t", query = "SELECT a FROM Anotaciones a WHERE  a.ticsId = :id "),
    @NamedQuery(name = "Anotaciones.findByAnotFechacambio", query = "SELECT a FROM Anotaciones a WHERE a.anotFechacambio = :anotFechacambio"),
    @NamedQuery(name = "Anotaciones.findByAnotRegistradopor", query = "SELECT a FROM Anotaciones a WHERE a.anotRegistradopor = :anotRegistradopor")})
public class Anotaciones implements Serializable {
    @Lob
    @Size(max = 2147483647)
    @Column(name = "anot_foto")
    private String anotFoto;
    @Size(max = 100)
    @Column(name = "anot_edad")
    private String anotEdad;
    @Basic(optional = false)
    @Column(name = "anot_visiblePorClie")
    private int anotvisiblePorClie;
    @Column(name = "tics_id")
    private Integer ticsId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "anot_id")
    private Integer anotId;
    @Column(name = "anot_estado")
    private String anotEstado;
    @Column(name = "anot_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date anotFechacambio;
    @Column(name = "anot_registradopor")
    private String anotRegistradopor;
    @JoinColumn(name = "paci_id", referencedColumnName = "paci_id")
    @ManyToOne
    private Pacientes paciId;
    @JoinColumn(name = "form_id", referencedColumnName = "form_id")
    @ManyToOne
    private Formularios formId;
    @OneToMany(mappedBy = "anotId")
    private List<Documentos> documentosList;
    @OneToMany(mappedBy = "anotId")
    private List<Respuestas> respuestasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anotId")
    private List<Huellafirma> huellafirmaList;

    public Anotaciones() {
    }

    public Anotaciones(Integer anotId) {
        this.anotId = anotId;
    }

    public Integer getAnotId() {
        return anotId;
    }

    public void setAnotId(Integer anotId) {
        this.anotId = anotId;
    }

    public String getAnotEstado() {
        return anotEstado;
    }

    public void setAnotEstado(String anotEstado) {
        this.anotEstado = anotEstado;
    }

    public Date getAnotFechacambio() {
        return anotFechacambio;
    }

    public void setAnotFechacambio(Date anotFechacambio) {
        this.anotFechacambio = anotFechacambio;
    }

    public String getAnotRegistradopor() {
        return anotRegistradopor;
    }

    public void setAnotRegistradopor(String anotRegistradopor) {
        this.anotRegistradopor = anotRegistradopor;
    }

    public Pacientes getPaciId() {
        return paciId;
    }

    public void setPaciId(Pacientes paciId) {
        this.paciId = paciId;
    }

    public Formularios getFormId() {
        return formId;
    }

    public void setFormId(Formularios formId) {
        this.formId = formId;
    }

    @XmlTransient
    public List<Documentos> getDocumentosList() {
        return documentosList;
    }

    public void setDocumentosList(List<Documentos> documentosList) {
        this.documentosList = documentosList;
    }

    @XmlTransient
    public List<Respuestas> getRespuestasList() {
        return respuestasList;
    }

    public void setRespuestasList(List<Respuestas> respuestasList) {
        this.respuestasList = respuestasList;
    }

    @XmlTransient
    public List<Huellafirma> getHuellafirmaList() {
        return huellafirmaList;
    }

    public void setHuellafirmaList(List<Huellafirma> huellafirmaList) {
        this.huellafirmaList = huellafirmaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anotId != null ? anotId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anotaciones)) {
            return false;
        }
        Anotaciones other = (Anotaciones) object;
        if ((this.anotId == null && other.anotId != null) || (this.anotId != null && !this.anotId.equals(other.anotId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Anotaciones[ anotId=" + anotId + " ]";
    }

    public Integer getTicsId() {
        return ticsId;
    }

    public void setTicsId(Integer ticsId) {
        this.ticsId = ticsId;
    }

    public int getAnotvisiblePorClie() {
        return anotvisiblePorClie;
    }

    public void setAnotvisiblePorClie(int anotvisiblePorClie) {
        this.anotvisiblePorClie = anotvisiblePorClie;
    }

    public String getAnotFoto() {
        return anotFoto;
    }

    public void setAnotFoto(String anotFoto) {
        this.anotFoto = anotFoto;
    }

    public String getAnotEdad() {
        return anotEdad;
    }

    public void setAnotEdad(String anotEdad) {
        this.anotEdad = anotEdad;
    }
    
}
