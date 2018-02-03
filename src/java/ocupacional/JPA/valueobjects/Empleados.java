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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Cache;

/**
 *
 * @author Sebas
 */
@Cacheable(false)
@Entity
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByEmplId", query = "SELECT e FROM Empleados e WHERE e.emplId = :emplId"),
    @NamedQuery(name = "Empleados.findByEmplNombres", query = "SELECT e FROM Empleados e WHERE e.emplNombres = :emplNombres"),
    @NamedQuery(name = "Empleados.findByEmplDocumento", query = "SELECT e FROM Empleados e WHERE e.emplDocumento = :emplDocumento"),
    @NamedQuery(name = "Empleados.busqueda", query = "SELECT e FROM Empleados e WHERE e.emplDocumento like :emplDocumento AND e.emplNombres LIKE :emplNombres"),
    @NamedQuery(name = "Empleados.findByEmplCargo", query = "SELECT e FROM Empleados e WHERE e.emplCargo = :emplCargo"),
    @NamedQuery(name = "Empleados.findByEmplfechaCambio", query = "SELECT e FROM Empleados e WHERE e.emplfechaCambio = :emplfechaCambio"),
    @NamedQuery(name = "Empleados.findByEmplRegistradopor", query = "SELECT e FROM Empleados e WHERE e.emplRegistradopor = :emplRegistradopor"),
    @NamedQuery(name = "Empleados.findByEmplEstado", query = "SELECT e FROM Empleados e WHERE e.emplEstado = :emplEstado"),
    @NamedQuery(name = "verificaExistencia", query = "SELECT e FROM Empleados e WHERE e.emplDocumento = :documento AND e.tidoId= :tido")})

public class Empleados implements Serializable {
    @Lob
    @Column(name = "empl_firma")
    private String emplFirma;
    @OneToMany(mappedBy = "emplId")
    private List<Empleadoexamen> empleadoexamenList;
    @JoinColumn(name = "sede_id", referencedColumnName = "sede_id")
    @ManyToOne(optional = false)
    private Sede sedeId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "empl_id")
    private Integer emplId;
    @Column(name = "empl_nombres")
    private String emplNombres;
    @Column(name = "empl_documento")
    private String emplDocumento;
    @Column(name = "empl_cargo")
    private String emplCargo;
    @Column(name = "empl_fechaCambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emplfechaCambio;
    @Column(name = "empl_registradopor")
    private String emplRegistradopor;
    @Basic(optional = false)
    @Column(name = "empl_estado")
    private String emplEstado;
    @JoinColumn(name = "tido_id", referencedColumnName = "tido_id")
    @ManyToOne(optional = false)
    private Tipodocumento tidoId;
//    too copas

    public Empleados() {
    }

    public Empleados(Integer emplId) {
        this.emplId = emplId;
    }

    public Empleados(Integer emplId, String emplEstado) {
        this.emplId = emplId;
        this.emplEstado = emplEstado;
    }

    public Integer getEmplId() {
        return emplId;
    }

    public void setEmplId(Integer emplId) {
        this.emplId = emplId;
    }

    public String getEmplNombres() {
        return emplNombres;
    }

    public void setEmplNombres(String emplNombres) {
        this.emplNombres = emplNombres;
    }

    public String getEmplDocumento() {
        return emplDocumento;
    }

    public void setEmplDocumento(String emplDocumento) {
        this.emplDocumento = emplDocumento;
    }

    public String getEmplCargo() {
        return emplCargo;
    }

    public void setEmplCargo(String emplCargo) {
        this.emplCargo = emplCargo;
    }

    public Date getEmplfechaCambio() {
        return emplfechaCambio;
    }

    public void setEmplfechaCambio(Date emplfechaCambio) {
        this.emplfechaCambio = emplfechaCambio;
    }

    public String getEmplRegistradopor() {
        return emplRegistradopor;
    }

    public void setEmplRegistradopor(String emplRegistradopor) {
        this.emplRegistradopor = emplRegistradopor;
    }

    public String getEmplEstado() {
        return emplEstado;
    }

    public void setEmplEstado(String emplEstado) {
        this.emplEstado = emplEstado;
    }

    public Tipodocumento getTidoId() {
        return tidoId;
    }

    public void setTidoId(Tipodocumento tidoId) {
        this.tidoId = tidoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emplId != null ? emplId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.emplId == null && other.emplId != null) || (this.emplId != null && !this.emplId.equals(other.emplId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Empleados[ emplId=" + emplId + " ]";
    }

    public Sede getSedeId() {
        return sedeId;
    }

    public void setSedeId(Sede sedeId) {
        this.sedeId = sedeId;
    }

    @XmlTransient
    public List<Empleadoexamen> getEmpleadoexamenList() {
        return empleadoexamenList;
    }

    public void setEmpleadoexamenList(List<Empleadoexamen> empleadoexamenList) {
        this.empleadoexamenList = empleadoexamenList;
    }

    public String getEmplFirma() {
        return emplFirma;
    }

    public void setEmplFirma(String emplFirma) {
        this.emplFirma = emplFirma;
    }
    
}
