package net.contratacion.entity;

import javax.persistence.*;


@Entity
@Table(name = "tb_evaluacion")
public class EvaluacionPAC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEvaluacion")
	private Integer IdEvaluacion;
	
	@ManyToOne
	@JoinColumn(name = "idInscripcionPAC")
	private InscripcionPAC IdInscripcion;
	
	@ManyToOne
	@JoinColumn(name = "idObservacion")
	private Observacion idObservacion;

	public Integer getIdEvaluacion() {
		return IdEvaluacion;
	}

	public void setIdEvaluacion(Integer idEvaluacion) {
		IdEvaluacion = idEvaluacion;
	}

	public InscripcionPAC getIdInscripcion() {
		return IdInscripcion;
	}

	public void setIdInscripcion(InscripcionPAC idInscripcion) {
		IdInscripcion = idInscripcion;
	}

	public Observacion getIdObservacion() {
		return idObservacion;
	}

	public void setIdObservacion(Observacion idObservacion) {
		this.idObservacion = idObservacion;
	}
	
	// Mediante Inscripcion obtengo idEstadoPAC
}
