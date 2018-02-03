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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Cacheable(false)
@Table(name = "pacientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pacientes.findAll", query = "SELECT p FROM Pacientes p"),
    @NamedQuery(name = "Pacientes.findByPaciId", query = "SELECT p FROM Pacientes p WHERE p.paciId = :paciId"),
    @NamedQuery(name = "Pacientes.findByPaciNombres", query = "SELECT p FROM Pacientes p WHERE p.paciNombres = :paciNombres"),
    @NamedQuery(name = "Pacientes.findByPaciApellidos", query = "SELECT p FROM Pacientes p WHERE p.paciApellidos = :paciApellidos"),
    @NamedQuery(name = "Pacientes.findByPaciDocumento", query = "SELECT p FROM Pacientes p WHERE p.paciDocumento = :paciDocumento"),
    @NamedQuery(name = "Pacientes.findByPaciDireccion", query = "SELECT p FROM Pacientes p WHERE p.paciDireccion = :paciDireccion"),
    @NamedQuery(name = "Pacientes.findByPaciTelefono", query = "SELECT p FROM Pacientes p WHERE p.paciTelefono = :paciTelefono"),
    @NamedQuery(name = "Pacientes.findByPaciFechanacimiento", query = "SELECT p FROM Pacientes p WHERE p.paciFechanacimiento = :paciFechanacimiento"),
    @NamedQuery(name = "Pacientes.findByPaciEps", query = "SELECT p FROM Pacientes p WHERE p.paciEps = :paciEps"),
    @NamedQuery(name = "Pacientes.findByPaciArl", query = "SELECT p FROM Pacientes p WHERE p.paciArl = :paciArl"),
    @NamedQuery(name = "Pacientes.findByPaciEcivil", query = "SELECT p FROM Pacientes p WHERE p.paciEcivil = :paciEcivil"),
    @NamedQuery(name = "Pacientes.findByPaciRh", query = "SELECT p FROM Pacientes p WHERE p.paciRh = :paciRh"),
    @NamedQuery(name = "Pacientes.findByPaciDominancia", query = "SELECT p FROM Pacientes p WHERE p.paciDominancia = :paciDominancia"),
    @NamedQuery(name = "Pacientes.findByPaciGenero", query = "SELECT p FROM Pacientes p WHERE p.paciGenero = :paciGenero"),
    @NamedQuery(name = "Pacientes.findByPaciEscolaridad", query = "SELECT p FROM Pacientes p WHERE p.paciEscolaridad = :paciEscolaridad"),
    @NamedQuery(name = "Pacientes.findByPaciRegistradopor", query = "SELECT p FROM Pacientes p WHERE p.paciRegistradopor = :paciRegistradopor"),
    @NamedQuery(name = "Pacientes.findByPaciFechacambio", query = "SELECT p FROM Pacientes p WHERE p.paciFechacambio = :paciFechacambio")})
public class Pacientes implements Serializable {
    @Lob
    @Column(name = "paci_huella")
    private byte[] paciHuella;
    @Lob
    @Column(name = "paci_firma")
    private byte[] paciFirma;
    @Column(name = "ciud_id")
    private Integer ciudId;
    @Lob
    @Column(name = "paci_foto")
    private String paciFoto;
    @Column(name = "paci_observaciones")
    private String paciObservaciones;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "paci_id")
    private Integer paciId;
    @Column(name = "paci_nombres")
    private String paciNombres;
    @Column(name = "paci_apellidos")
    private String paciApellidos;
    @Column(name = "paci_documento")
    private String paciDocumento;
    @Column(name = "paci_direccion")
    private String paciDireccion;
    @Column(name = "paci_telefono")
    private String paciTelefono;
    @Column(name = "paci_fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date paciFechanacimiento;
    @Column(name = "paci_eps")
    private String paciEps;
    @Column(name = "paci_arl")
    private String paciArl;
    @Column(name = "paci_ecivil")
    private String paciEcivil;
    @Column(name = "paci_rh")
    private String paciRh;
    @Column(name = "paci_dominancia")
    private String paciDominancia;
    @Column(name = "paci_genero")
    private String paciGenero;
    @Column(name = "paci_escolaridad")
    private String paciEscolaridad;
    @Column(name = "paci_registradopor")
    private String paciRegistradopor;
    @Column(name = "paci_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paciFechacambio;
    @OneToMany(mappedBy = "paciId")
    private List<Anotaciones> anotacionesList;

    public Pacientes() {
    }

    public Pacientes(Integer paciId) {
        this.paciId = paciId;
    }

    public Integer getPaciId() {
        return paciId;
    }

    public void setPaciId(Integer paciId) {
        this.paciId = paciId;
    }

    public String getPaciNombres() {
        return paciNombres;
    }

    public void setPaciNombres(String paciNombres) {
        this.paciNombres = paciNombres;
    }

    public String getPaciApellidos() {
        return paciApellidos;
    }

    public void setPaciApellidos(String paciApellidos) {
        this.paciApellidos = paciApellidos;
    }

    public String getPaciDocumento() {
        return paciDocumento;
    }

    public void setPaciDocumento(String paciDocumento) {
        this.paciDocumento = paciDocumento;
    }

    public String getPaciDireccion() {
        return paciDireccion;
    }

    public void setPaciDireccion(String paciDireccion) {
        this.paciDireccion = paciDireccion;
    }

    public String getPaciTelefono() {
        return paciTelefono;
    }

    public void setPaciTelefono(String paciTelefono) {
        this.paciTelefono = paciTelefono;
    }

    public Date getPaciFechanacimiento() {
        return paciFechanacimiento;
    }

    public void setPaciFechanacimiento(Date paciFechanacimiento) {
        this.paciFechanacimiento = paciFechanacimiento;
    }

    public String getPaciEps() {
        return paciEps;
    }

    public void setPaciEps(String paciEps) {
        this.paciEps = paciEps;
    }

    public String getPaciArl() {
        return paciArl;
    }

    public void setPaciArl(String paciArl) {
        this.paciArl = paciArl;
    }

    public String getPaciEcivil() {
        return paciEcivil;
    }

    public void setPaciEcivil(String paciEcivil) {
        this.paciEcivil = paciEcivil;
    }

    public String getPaciRh() {
        return paciRh;
    }

    public void setPaciRh(String paciRh) {
        this.paciRh = paciRh;
    }

    public String getPaciDominancia() {
        return paciDominancia;
    }

    public void setPaciDominancia(String paciDominancia) {
        this.paciDominancia = paciDominancia;
    }

    public String getPaciGenero() {
        return paciGenero;
    }

    public void setPaciGenero(String paciGenero) {
        this.paciGenero = paciGenero;
    }

    public String getPaciEscolaridad() {
        return paciEscolaridad;
    }

    public void setPaciEscolaridad(String paciEscolaridad) {
        this.paciEscolaridad = paciEscolaridad;
    }

    public String getPaciRegistradopor() {
        return paciRegistradopor;
    }

    public void setPaciRegistradopor(String paciRegistradopor) {
        this.paciRegistradopor = paciRegistradopor;
    }

    public Date getPaciFechacambio() {
        return paciFechacambio;
    }

    public void setPaciFechacambio(Date paciFechacambio) {
        this.paciFechacambio = paciFechacambio;
    }

    public byte[] getPaciHuella() {
        return paciHuella;
    }

    public void setPaciHuella(byte[] paciHuella) {
        this.paciHuella = paciHuella;
    }

    public byte[] getPaciFirma() {
        return paciFirma;
    }

    public void setPaciFirma(byte[] paciFirma) {
        this.paciFirma = paciFirma;
    }

    @XmlTransient
    public List<Anotaciones> getAnotacionesList() {
        return anotacionesList;
    }

    public void setAnotacionesList(List<Anotaciones> anotacionesList) {
        this.anotacionesList = anotacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paciId != null ? paciId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacientes)) {
            return false;
        }
        Pacientes other = (Pacientes) object;
        if ((this.paciId == null && other.paciId != null) || (this.paciId != null && !this.paciId.equals(other.paciId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formularios.entidades.Pacientes[ paciId=" + paciId + " ]";
    }


    public String getPaciObservaciones() {
        return paciObservaciones;
    }

    public void setPaciObservaciones(String paciObservaciones) {
        this.paciObservaciones = paciObservaciones;
    }

  

    public String getPaciFoto() {
        return paciFoto;
    }

    public void setPaciFoto(String paciFoto) {
        this.paciFoto = paciFoto;
    }


    public Integer getCiudId() {
        return ciudId;
    }

    public void setCiudId(Integer ciudId) {
        this.ciudId = ciudId;
    }




   

    
}
