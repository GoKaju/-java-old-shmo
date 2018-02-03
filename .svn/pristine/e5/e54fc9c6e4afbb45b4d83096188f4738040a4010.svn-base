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
import javax.persistence.Cacheable;
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
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author D4V3
 */
@Entity
@Cacheable(false)
@Table(name = "servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s"),
    @NamedQuery(name = "Servicios.findByServId", query = "SELECT s FROM Servicios s WHERE s.servId = :servId"),
    @NamedQuery(name = "Servicios.findByServNombre", query = "SELECT s FROM Servicios s WHERE s.servNombre = :servNombre"),
    @NamedQuery(name = "Servicios.findByServObservacion", query = "SELECT s FROM Servicios s WHERE s.servObservacion = :servObservacion"),
    @NamedQuery(name = "Servicios.findByServRegistradopor", query = "SELECT s FROM Servicios s WHERE s.servRegistradopor = :servRegistradopor"),
    @NamedQuery(name = "Servicios.findByServFechacambio", query = "SELECT s FROM Servicios s WHERE s.servFechacambio = :servFechacambio"),
    @NamedQuery(name = "Servicios.findByServEstado", query = "SELECT s FROM Servicios s WHERE s.servEstado = :servEstado")})
public class Servicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "serv_id")
    private Integer servId;
    @Basic(optional = false)
    @Column(name = "serv_nombre")
    private String servNombre;
    @Basic(optional = false)
    @Column(name = "serv_observacion")
    private String servObservacion;
    @Basic(optional = false)
    @Column(name = "serv_registradopor")
    private String servRegistradopor;
    @Basic(optional = false)
    @Column(name = "serv_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date servFechacambio;
    @Column(name = "serv_estado")
    private String servEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servId")
    private List<ClientesServicio> clientesServicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servId")
    private List<ServiciosExamenes> serviciosExamenesList;

    public Servicios() {
    }

    public Servicios(Integer servId) {
        this.servId = servId;
    }

    public Servicios(Integer servId, String servNombre, String servObservacion, String servRegistradopor, Date servFechacambio) {
        this.servId = servId;
        this.servNombre = servNombre;
        this.servObservacion = servObservacion;
        this.servRegistradopor = servRegistradopor;
        this.servFechacambio = servFechacambio;
    }

    public Integer getServId() {
        return servId;
    }

    public void setServId(Integer servId) {
        this.servId = servId;
    }

    public String getServNombre() {
        return servNombre;
    }

    public void setServNombre(String servNombre) {
        this.servNombre = servNombre;
    }

    public String getServObservacion() {
        return servObservacion;
    }

    public void setServObservacion(String servObservacion) {
        this.servObservacion = servObservacion;
    }

    public String getServRegistradopor() {
        return servRegistradopor;
    }

    public void setServRegistradopor(String servRegistradopor) {
        this.servRegistradopor = servRegistradopor;
    }

    public Date getServFechacambio() {
        return servFechacambio;
    }

    public void setServFechacambio(Date servFechacambio) {
        this.servFechacambio = servFechacambio;
    }

    public String getServEstado() {
        return servEstado;
    }

    public void setServEstado(String servEstado) {
        this.servEstado = servEstado;
    }

    @XmlTransient
    public List<ClientesServicio> getClientesServicioList() {
        return clientesServicioList;
    }

    public void setClientesServicioList(List<ClientesServicio> clientesServicioList) {
        this.clientesServicioList = clientesServicioList;
    }

    @XmlTransient
    public List<ServiciosExamenes> getServiciosExamenesList() {
        return serviciosExamenesList;
    }

    public void setServiciosExamenesList(List<ServiciosExamenes> serviciosExamenesList) {
        this.serviciosExamenesList = serviciosExamenesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servId != null ? servId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.servId == null && other.servId != null) || (this.servId != null && !this.servId.equals(other.servId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Servicios[ servId=" + servId + " ]";
    }
    
}
