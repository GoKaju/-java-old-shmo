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
@Table(name = "documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentos.findAll", query = "SELECT d FROM Documentos d"),
    @NamedQuery(name = "Documentos.findByDocuId", query = "SELECT d FROM Documentos d WHERE d.docuId = :docuId"),
    @NamedQuery(name = "Documentos.findByDocuExtencion", query = "SELECT d FROM Documentos d WHERE d.docuExtencion = :docuExtencion"),
    @NamedQuery(name = "Documentos.findByDocuNombre", query = "SELECT d FROM Documentos d WHERE d.docuNombre = :docuNombre"),
    @NamedQuery(name = "Documentos.findByDocuRuta", query = "SELECT d FROM Documentos d WHERE d.docuRuta = :docuRuta"),
    @NamedQuery(name = "Documentos.findByDocuPeso", query = "SELECT d FROM Documentos d WHERE d.docuPeso = :docuPeso"),
    @NamedQuery(name = "Documentos.findByDocuRegistradopor", query = "SELECT d FROM Documentos d WHERE d.docuRegistradopor = :docuRegistradopor"),
    @NamedQuery(name = "Documentos.findByDocFechacambio", query = "SELECT d FROM Documentos d WHERE d.docFechacambio = :docFechacambio")})
public class Documentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "docu_id")
    private Integer docuId;
    @Column(name = "docu_extencion")
    private String docuExtencion;
    @Column(name = "docu_nombre")
    private String docuNombre;
    @Column(name = "docu_ruta")
    private String docuRuta;
    @Column(name = "docu_peso")
    private String docuPeso;
    @Column(name = "docu_registradopor")
    private String docuRegistradopor;
    @Column(name = "doc_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docFechacambio;
    @JoinColumn(name = "anot_id", referencedColumnName = "anot_id")
    @ManyToOne
    private Anotaciones anotId;

    public Documentos() {
    }

    public Documentos(Integer docuId) {
        this.docuId = docuId;
    }

    public Integer getDocuId() {
        return docuId;
    }

    public void setDocuId(Integer docuId) {
        this.docuId = docuId;
    }

    public String getDocuExtencion() {
        return docuExtencion;
    }

    public void setDocuExtencion(String docuExtencion) {
        this.docuExtencion = docuExtencion;
    }

    public String getDocuNombre() {
        return docuNombre;
    }

    public void setDocuNombre(String docuNombre) {
        this.docuNombre = docuNombre;
    }

    public String getDocuRuta() {
        return docuRuta;
    }

    public void setDocuRuta(String docuRuta) {
        this.docuRuta = docuRuta;
    }

    public String getDocuPeso() {
        return docuPeso;
    }

    public void setDocuPeso(String docuPeso) {
        this.docuPeso = docuPeso;
    }

    public String getDocuRegistradopor() {
        return docuRegistradopor;
    }

    public void setDocuRegistradopor(String docuRegistradopor) {
        this.docuRegistradopor = docuRegistradopor;
    }

    public Date getDocFechacambio() {
        return docFechacambio;
    }

    public void setDocFechacambio(Date docFechacambio) {
        this.docFechacambio = docFechacambio;
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
        hash += (docuId != null ? docuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentos)) {
            return false;
        }
        Documentos other = (Documentos) object;
        if ((this.docuId == null && other.docuId != null) || (this.docuId != null && !this.docuId.equals(other.docuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Documentos[ docuId=" + docuId + " ]";
    }
    
}
