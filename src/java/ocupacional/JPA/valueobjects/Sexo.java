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
@Table(name = "sexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sexo.findAll", query = "SELECT s FROM Sexo s"),
    @NamedQuery(name = "Sexo.findBySexoId", query = "SELECT s FROM Sexo s WHERE s.sexoId = :sexoId"),
    @NamedQuery(name = "Sexo.findBySexoDescripcion", query = "SELECT s FROM Sexo s WHERE s.sexoDescripcion = :sexoDescripcion"),
    @NamedQuery(name = "Sexo.findBySexoFechacambio", query = "SELECT s FROM Sexo s WHERE s.sexoFechacambio = :sexoFechacambio"),
    @NamedQuery(name = "Sexo.findBySexoRegistradopor", query = "SELECT s FROM Sexo s WHERE s.sexoRegistradopor = :sexoRegistradopor")})
public class Sexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sexo_id")
    private Integer sexoId;
    @Basic(optional = false)
    @Column(name = "sexo_descripcion")
    private String sexoDescripcion;
    @Basic(optional = false)
    @Column(name = "sexo_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sexoFechacambio;
    @Basic(optional = false)
    @Column(name = "sexo_registradopor")
    private int sexoRegistradopor;

    public Sexo() {
    }

    public Sexo(Integer sexoId) {
        this.sexoId = sexoId;
    }

    public Sexo(Integer sexoId, String sexoDescripcion, Date sexoFechacambio, int sexoRegistradopor) {
        this.sexoId = sexoId;
        this.sexoDescripcion = sexoDescripcion;
        this.sexoFechacambio = sexoFechacambio;
        this.sexoRegistradopor = sexoRegistradopor;
    }

    public Integer getSexoId() {
        return sexoId;
    }

    public void setSexoId(Integer sexoId) {
        this.sexoId = sexoId;
    }

    public String getSexoDescripcion() {
        return sexoDescripcion;
    }

    public void setSexoDescripcion(String sexoDescripcion) {
        this.sexoDescripcion = sexoDescripcion;
    }

    public Date getSexoFechacambio() {
        return sexoFechacambio;
    }

    public void setSexoFechacambio(Date sexoFechacambio) {
        this.sexoFechacambio = sexoFechacambio;
    }

    public int getSexoRegistradopor() {
        return sexoRegistradopor;
    }

    public void setSexoRegistradopor(int sexoRegistradopor) {
        this.sexoRegistradopor = sexoRegistradopor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sexoId != null ? sexoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexo)) {
            return false;
        }
        Sexo other = (Sexo) object;
        if ((this.sexoId == null && other.sexoId != null) || (this.sexoId != null && !this.sexoId.equals(other.sexoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Sexo[ sexoId=" + sexoId + " ]";
    }
    
}
