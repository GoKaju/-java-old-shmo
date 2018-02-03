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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t order by t.tickId desc"),
    @NamedQuery(name = "Ticket.reporteExcel", query = "SELECT T FROM Ticket AS T WHERE T.cecoId.clieId=:cliente AND t.tickEstado='PROCESADO' AND T.tickFechaprocesado BETWEEN :fechaInicio AND :fechaFin"),
    @NamedQuery(name = "Ticket.reporteExcelEstadoCreacion", query = "SELECT T FROM Ticket AS T WHERE T.cecoId.clieId=:cliente AND t.tickEstado like :estado AND T.tickFecharecepcion BETWEEN :fechaInicio AND :fechaFin"),
    @NamedQuery(name = "Ticket.reporteExcelEstadoCierre", query = "SELECT T FROM Ticket AS T WHERE T.cecoId.clieId=:cliente AND t.tickEstado like :estado AND T.tickFechaprocesado BETWEEN :fechaInicio AND :fechaFin"),
    @NamedQuery(name = "Ticket.findByTickId", query = "SELECT t FROM Ticket t WHERE t.tickId = :tickId"),
    @NamedQuery(name = "Ticket.findByTickEstado", query = "SELECT t FROM Ticket t WHERE t.tickEstado = :tickEstado"),
    @NamedQuery(name = "Ticket.findByTickRegistradopor", query = "SELECT t FROM Ticket t WHERE t.tickRegistradopor = :tickRegistradopor"),
    @NamedQuery(name = "Ticket.findBySedeyEstado", query = "SELECT t FROM Ticket t WHERE t.sedeId = :sede AND t.tickEstado = :estado"),
    @NamedQuery(name = "Ticket.findBySedeyEstadoFechas", query = "SELECT t FROM Ticket t WHERE t.sedeId = :sede AND t.tickEstado = :estado AND  t.tickFechaprocesado BETWEEN :inicio AND :fin"),
    @NamedQuery(name = "Ticket.findByTickFechacambio", query = "SELECT t FROM Ticket t WHERE t.tickFechacambio = :tickFechacambio"),
    @NamedQuery(name = "Ticket.findByTickPaciente", query = "SELECT t FROM Ticket t WHERE t.tickPaciente = :tickPaciente"),
    @NamedQuery(name = "Ticket.findByTickFecharegistro", query = "SELECT t FROM Ticket t WHERE t.tickFecharegistro = :tickFecharegistro"),
    @NamedQuery(name = "Ticket.findByTickFecharecepcion", query = "SELECT t FROM Ticket t WHERE t.tickFecharecepcion = :tickFecharecepcion"),
    @NamedQuery(name = "Ticket.findByTickFechaprocesado", query = "SELECT t FROM Ticket t WHERE t.tickFechaprocesado = :tickFechaprocesado"),
    @NamedQuery(name = "Ticket.findByFechas", query = "SELECT t FROM Ticket t,Clientes clie, Centrocostos cc WHERE cc.clieId = clie AND cc = t.cecoId  and  clie.clieId= :clieId and t.tickEstado = 'PROCESADO' and \n"
            + "t.tickFechaprocesado BETWEEN :inicio AND :fin"),
    @NamedQuery(name = "Ticket.findByFechasSedeJavap", query = "SELECT t FROM Ticket t,Clientes clie, Centrocostos cc WHERE cc.clieId = clie AND cc = t.cecoId  and  clie.clieId= :clieId and t.sedeId=:sede and t.tickEstado = 'PROCESADO' and \n"
            + "t.tickFechaprocesado BETWEEN :inicio AND :fin"),
    @NamedQuery(name = "Ticket.findByFechasEstadoCreacion", query = "SELECT t FROM Ticket t,Clientes clie, Centrocostos cc WHERE cc.clieId = clie AND cc = t.cecoId  and  clie.clieId= :clieId and t.tickEstado = :estado and \n"
            + "t.tickFecharegistro BETWEEN :inicio AND :fin"),
    @NamedQuery(name = "Ticket.findByFechasEstadoCierre", query = "SELECT t FROM Ticket t,Clientes clie, Centrocostos cc WHERE cc.clieId = clie AND cc = t.cecoId  and  clie.clieId= :clieId and t.tickEstado = :estado and \n"
            + "t.tickFechaprocesado BETWEEN :inicio AND :fin"),
    @NamedQuery(name = "Ticket.findByFechasYsede", query = "SELECT t FROM Ticket t,Clientes clie, Centrocostos cc WHERE cc.clieId = clie AND cc = t.cecoId  and  cc = :sede and t.tickEstado = 'PROCESADO' and \n"
            + "t.tickFechaprocesado BETWEEN :inicio AND :fin"),
    @NamedQuery(name = "Ticket.listaMedicosxsede", query = "SELECT t FROM Ticket t WHERE t.sedeId.sedeId = :sede AND t.tickEstado='PROCESANDO' ORDER BY  t.tickFecharecepcion")
})
public class Ticket implements Serializable {

    @Column(name = "empl_idauxiliar")
    private Integer emplIdauxiliar;
    @Column(name = "empl_idmedico")
    private Integer emplIdmedico;
    @Column(name = "tick_brigada")   private String tickBrigada;
    @Column(name = "tick_otroexamen")
    private String tickOtroexamen;

    @Column(name = "tick_clsede")
    private String tickClsede;
    @JoinColumn(name = "mofo_id", referencedColumnName = "mofa_id")
    @ManyToOne
    private Movimientosfacturacion mofoId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tick_id")
    private Integer tickId;
    @Basic(optional = false)
    @Column(name = "tick_estado")
    private String tickEstado;
    @Basic(optional = false)
    @Column(name = "tick_registradopor")
    private int tickRegistradopor;
    @Basic(optional = false)
    @Column(name = "tick_fechacambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tickFechacambio;
    @Basic(optional = false)
    @Column(name = "tick_paciente")
    private int tickPaciente;
    @Column(name = "tick_fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tickFecharegistro;
    @Column(name = "tick_fecharecepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tickFecharecepcion;
    @Column(name = "tick_fechaprocesado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tickFechaprocesado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tickId")
    private List<TicketClienteservicio> ticketClienteservicioList;
    @JoinColumn(name = "sede_id", referencedColumnName = "sede_id")
    @ManyToOne(optional = false)
    private Sede sedeId;
    @JoinColumn(name = "ceco_id", referencedColumnName = "ceco_id")
    @ManyToOne(optional = false)
    private Centrocostos cecoId;
    @JoinColumn(name = "teme_id", referencedColumnName = "teme_id")
    @ManyToOne(optional = false)
    private TipoexamenMedico temeId;

    public Ticket() {
    }

    public Ticket(Integer tickId) {
        this.tickId = tickId;
    }

    public Ticket(Integer tickId, String tickEstado, int tickRegistradopor, Date tickFechacambio, int tickPaciente) {
        this.tickId = tickId;
        this.tickEstado = tickEstado;
        this.tickRegistradopor = tickRegistradopor;
        this.tickFechacambio = tickFechacambio;
        this.tickPaciente = tickPaciente;
    }

    public Integer getTickId() {
        return tickId;
    }

    public void setTickId(Integer tickId) {
        this.tickId = tickId;
    }

    public String getTickEstado() {
        return tickEstado;
    }

    public void setTickEstado(String tickEstado) {
        this.tickEstado = tickEstado;
    }

    public int getTickRegistradopor() {
        return tickRegistradopor;
    }

    public void setTickRegistradopor(int tickRegistradopor) {
        this.tickRegistradopor = tickRegistradopor;
    }

    public Date getTickFechacambio() {
        return tickFechacambio;
    }

    public void setTickFechacambio(Date tickFechacambio) {
        this.tickFechacambio = tickFechacambio;
    }

    public int getTickPaciente() {
        return tickPaciente;
    }

    public void setTickPaciente(int tickPaciente) {
        this.tickPaciente = tickPaciente;
    }

    public Date getTickFecharegistro() {
        return tickFecharegistro;
    }

    public void setTickFecharegistro(Date tickFecharegistro) {
        this.tickFecharegistro = tickFecharegistro;
    }

    public Date getTickFecharecepcion() {
        return tickFecharecepcion;
    }

    public void setTickFecharecepcion(Date tickFecharecepcion) {
        this.tickFecharecepcion = tickFecharecepcion;
    }

    public Date getTickFechaprocesado() {
        return tickFechaprocesado;
    }

    public void setTickFechaprocesado(Date tickFechaprocesado) {
        this.tickFechaprocesado = tickFechaprocesado;
    }

    @XmlTransient
    public List<TicketClienteservicio> getTicketClienteservicioList() {
        return ticketClienteservicioList;
    }

    public void setTicketClienteservicioList(List<TicketClienteservicio> ticketClienteservicioList) {
        this.ticketClienteservicioList = ticketClienteservicioList;
    }

    public Sede getSedeId() {
        return sedeId;
    }

    public void setSedeId(Sede sedeId) {
        this.sedeId = sedeId;
    }

    public Centrocostos getCecoId() {
        return cecoId;
    }

    public void setCecoId(Centrocostos cecoId) {
        this.cecoId = cecoId;
    }

    public TipoexamenMedico getTemeId() {
        return temeId;
    }

    public void setTemeId(TipoexamenMedico temeId) {
        this.temeId = temeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tickId != null ? tickId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.tickId == null && other.tickId != null) || (this.tickId != null && !this.tickId.equals(other.tickId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ocupacional.JPA.valueobjects.Ticket[ tickId=" + tickId + " ]";
    }

    public Movimientosfacturacion getMofoId() {
        return mofoId;
    }

    public void setMofoId(Movimientosfacturacion mofoId) {
        this.mofoId = mofoId;
    }

    public String getTickClsede() {
        return tickClsede;
    }

    public void setTickClsede(String tickClsede) {
        this.tickClsede = tickClsede;
    }

    public String getTickBrigada() {
        return tickBrigada;
    }

    public void setTickBrigada(String tickBrigada) {
        this.tickBrigada = tickBrigada;
    }

    public String getTickOtroexamen() {
        return tickOtroexamen;
    }

    public void setTickOtroexamen(String tickOtroexamen) {
        this.tickOtroexamen = tickOtroexamen;
    }

    public Integer getEmplIdauxiliar() {
        return emplIdauxiliar;
    }

    public void setEmplIdauxiliar(Integer emplIdauxiliar) {
        this.emplIdauxiliar = emplIdauxiliar;
    }

    public Integer getEmplIdmedico() {
        return emplIdmedico;
    }

    public void setEmplIdmedico(Integer emplIdmedico) {
        this.emplIdmedico = emplIdmedico;
    }

}
