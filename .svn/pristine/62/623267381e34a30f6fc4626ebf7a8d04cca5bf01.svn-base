/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.JPA.valueobjects;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

@Table(name = "naturales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Naturales.findAll", query = "SELECT n FROM Naturales n"),
    @NamedQuery(name = "Naturales.findByNatuPrimernombre", query = "SELECT n FROM Naturales n WHERE n.natuPrimernombre = :natuPrimernombre"),
    @NamedQuery(name = "Naturales.findByNatuSegundonombre", query = "SELECT n FROM Naturales n WHERE n.natuSegundonombre = :natuSegundonombre"),
    @NamedQuery(name = "Naturales.findByNatuPrimerapellido", query = "SELECT n FROM Naturales n WHERE n.natuPrimerapellido = :natuPrimerapellido"),
    @NamedQuery(name = "Naturales.findByNatuSegundoapellido", query = "SELECT n FROM Naturales n WHERE n.natuSegundoapellido = :natuSegundoapellido"),
    @NamedQuery(name = "Naturales.findByPegeId", query = "SELECT n FROM Naturales n WHERE n.pegeId = :pegeId"),
    @NamedQuery(name = "Naturales.findByNatuFechanacimiento", query = "SELECT n FROM Naturales n WHERE n.natuFechanacimiento = :natuFechanacimiento"),
    @NamedQuery(name = "Naturales.findByNatuGruposanguineo", query = "SELECT n FROM Naturales n WHERE n.natuGruposanguineo = :natuGruposanguineo"),
    @NamedQuery(name = "Naturales.findByNatuEstrato", query = "SELECT n FROM Naturales n WHERE n.natuEstrato = :natuEstrato"),
    @NamedQuery(name = "Naturales.findByEsciId", query = "SELECT n FROM Naturales n WHERE n.esciId = :esciId"),
    @NamedQuery(name = "Naturales.findByNatuFechacambio", query = "SELECT n FROM Naturales n WHERE n.natuFechacambio = :natuFechacambio"),
    @NamedQuery(name = "Naturales.findByNatuRegistradopor", query = "SELECT n FROM Naturales n WHERE n.natuRegistradopor = :natuRegistradopor"),
    @NamedQuery(name = "Naturales.findByEntiIdeps", query = "SELECT n FROM Naturales n WHERE n.entiIdeps = :entiIdeps"),
    @NamedQuery(name = "Naturales.findByEntiIdarl", query = "SELECT n FROM Naturales n WHERE n.entiIdarl = :entiIdarl"),
    @NamedQuery(name = "Naturales.findBySexoId", query = "SELECT n FROM Naturales n WHERE n.sexoId = :sexoId")})
public class Naturales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "natu_primernombre")
    private String natuPrimernombre;
    @Column(name = "natu_segundonombre")
    private String natuSegundonombre;
    @Column(name = "natu_primerapellido")
    private String natuPrimerapellido;
    @Column(name = "natu_segundoapellido")
    private String natuSegundoapellido;
    @Id
    @Basic(optional = false)
    @Column(name = "pege_id")
    private Integer pegeId;
    @Column(name = "natu_fechanacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date natuFechanacimiento;
    @Column(name = "natu_gruposanguineo")
    private String natuGruposanguineo;
    @Column(name = "natu_estrato")
    private String natuEstrato;
    @Column(name = "esci_id")
    private Integer esciId;
    @Column(name = "natu_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date natuFechacambio;
    @Column(name = "natu_registradopor")
    private Integer natuRegistradopor;
    @Column(name = "enti_ideps")
    private Integer entiIdeps;
    @Column(name = "enti_idarl")
    private Integer entiIdarl;
    @Column(name = "sexo_id")
    private Integer sexoId;

    public Naturales() {
    }

    public Naturales(Integer pegeId) {
        this.pegeId = pegeId;
    }

    public String getNatuPrimernombre() {
        return natuPrimernombre;
    }

    public void setNatuPrimernombre(String natuPrimernombre) {
        this.natuPrimernombre = natuPrimernombre;
    }

    public String getNatuSegundonombre() {
        return natuSegundonombre;
    }

    public void setNatuSegundonombre(String natuSegundonombre) {
        this.natuSegundonombre = natuSegundonombre;
    }

    public String getNatuPrimerapellido() {
        return natuPrimerapellido;
    }

    public void setNatuPrimerapellido(String natuPrimerapellido) {
        this.natuPrimerapellido = natuPrimerapellido;
    }

    public String getNatuSegundoapellido() {
        return natuSegundoapellido;
    }

    public void setNatuSegundoapellido(String natuSegundoapellido) {
        this.natuSegundoapellido = natuSegundoapellido;
    }

    public Integer getPegeId() {
        return pegeId;
    }

    public void setPegeId(Integer pegeId) {
        this.pegeId = pegeId;
    }

    public Date getNatuFechanacimiento() {
        return natuFechanacimiento;
    }

    public void setNatuFechanacimiento(Date natuFechanacimiento) {
        this.natuFechanacimiento = natuFechanacimiento;
    }

    public String getNatuGruposanguineo() {
        return natuGruposanguineo;
    }

    public void setNatuGruposanguineo(String natuGruposanguineo) {
        this.natuGruposanguineo = natuGruposanguineo;
    }

    public String getNatuEstrato() {
        return natuEstrato;
    }

    public void setNatuEstrato(String natuEstrato) {
        this.natuEstrato = natuEstrato;
    }

    public Integer getEsciId() {
        return esciId;
    }

    public void setEsciId(Integer esciId) {
        this.esciId = esciId;
    }

    public Date getNatuFechacambio() {
        return natuFechacambio;
    }

    public void setNatuFechacambio(Date natuFechacambio) {
        this.natuFechacambio = natuFechacambio;
    }

    public Integer getNatuRegistradopor() {
        return natuRegistradopor;
    }

    public void setNatuRegistradopor(Integer natuRegistradopor) {
        this.natuRegistradopor = natuRegistradopor;
    }

    public Integer getEntiIdeps() {
        return entiIdeps;
    }

    public void setEntiIdeps(Integer entiIdeps) {
        this.entiIdeps = entiIdeps;
    }

    public Integer getEntiIdarl() {
        return entiIdarl;
    }

    public void setEntiIdarl(Integer entiIdarl) {
        this.entiIdarl = entiIdarl;
    }

    public Integer getSexoId() {
        return sexoId;
    }

    public void setSexoId(Integer sexoId) {
        this.sexoId = sexoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pegeId != null ? pegeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Naturales)) {
            return false;
        }
        Naturales other = (Naturales) object;
        if ((this.pegeId == null && other.pegeId != null) || (this.pegeId != null && !this.pegeId.equals(other.pegeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Naturales[ pegeId=" + pegeId + " ]";
    }
    
}
