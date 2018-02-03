/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.valueobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author D4V3
 */
@Entity
@Table(name = "tipodocumento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipodocumento.findAll", query = "SELECT t FROM Tipodocumento t"),
    @NamedQuery(name = "Tipodocumento.findByTidoId", query = "SELECT t FROM Tipodocumento t WHERE t.tidoId = :tidoId"),
    @NamedQuery(name = "Tipodocumento.findByTidoDescripcion", query = "SELECT t FROM Tipodocumento t WHERE t.tidoDescripcion = :tidoDescripcion"),
    @NamedQuery(name = "Tipodocumento.findByTidoFechacambio", query = "SELECT t FROM Tipodocumento t WHERE t.tidoFechacambio = :tidoFechacambio"),
    @NamedQuery(name = "Tipodocumento.findByTidoRegistradopor", query = "SELECT t FROM Tipodocumento t WHERE t.tidoRegistradopor = :tidoRegistradopor")})
public class Tipodocumento implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tidoId")
    private List<Empleados> empleadosList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tido_id")
    private Integer tidoId;
    @Basic(optional = false)
    @Column(name = "tido_descripcion")
    private String tidoDescripcion;
    @Basic(optional = false)
    @Column(name = "tido_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tidoFechacambio;
    @Basic(optional = false)
    @Column(name = "tido_registradopor")
    private int tidoRegistradopor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tidoId")
    private List<Clientes> clientesList;

    public Tipodocumento() {
    }

    public Tipodocumento(Integer tidoId) {
        this.tidoId = tidoId;
    }

    public Tipodocumento(Integer tidoId, String tidoDescripcion, Date tidoFechacambio, int tidoRegistradopor) {
        this.tidoId = tidoId;
        this.tidoDescripcion = tidoDescripcion;
        this.tidoFechacambio = tidoFechacambio;
        this.tidoRegistradopor = tidoRegistradopor;
    }

    public Integer getTidoId() {
        return tidoId;
    }

    public void setTidoId(Integer tidoId) {
        this.tidoId = tidoId;
    }

    public String getTidoDescripcion() {
        return tidoDescripcion;
    }

    public void setTidoDescripcion(String tidoDescripcion) {
        this.tidoDescripcion = tidoDescripcion;
    }

    public Date getTidoFechacambio() {
        return tidoFechacambio;
    }

    public void setTidoFechacambio(Date tidoFechacambio) {
        this.tidoFechacambio = tidoFechacambio;
    }

    public int getTidoRegistradopor() {
        return tidoRegistradopor;
    }

    public void setTidoRegistradopor(int tidoRegistradopor) {
        this.tidoRegistradopor = tidoRegistradopor;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tidoId != null ? tidoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodocumento)) {
            return false;
        }
        Tipodocumento other = (Tipodocumento) object;
        if ((this.tidoId == null && other.tidoId != null) || (this.tidoId != null && !this.tidoId.equals(other.tidoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Tipodocumento[ tidoId=" + tidoId + " ]";
    }

    @XmlTransient
    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }
    
}
