package net.contratacion.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "inscripcion_pac")
public class InscripcionPAC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_inscripcion")
	private Integer idInscripcion;
	@Column(name = "año_pac")
	private String ano_pac;
	@Column(name = "fecha")
	private Date fecha; 
	@Column(name = "presupuesto")
	private double presupuesto;
	
	@JsonIgnore
	@OneToMany(mappedBy ="idInscripcionPAC")
	private List<DetalleProyecto> listaProyectos;
	
	@ManyToOne
	@JoinColumn(name = "entidad_publica")
	private EntidadPublica nomEstado;
	
	@ManyToOne
	@JoinColumn(name = "estado_pac")
	private EstadoPAC idEstado;

	public Integer getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(Integer idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public String getAno_pac() {
		return ano_pac;
	}

	public void setAno_pac(String año_pac) {
		this.ano_pac = año_pac;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public EntidadPublica getNomEstado() {
		return nomEstado;
	}

	public void setNomEstado(EntidadPublica nomEstado) {
		this.nomEstado = nomEstado;
	}

	public EstadoPAC getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(EstadoPAC idEstado) {
		this.idEstado = idEstado;
	}

	public List<DetalleProyecto> getListaProyectos() {
		return listaProyectos;
	}

	public void setListaProyectos(List<DetalleProyecto> listaProyectos) {
		this.listaProyectos = listaProyectos;
	}
	
	
}
