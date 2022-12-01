package net.contratacion.entity;

import javax.persistence.*;

@Entity
@Table(name = "detalle_proyecto_pac")
public class DetalleProyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle")
	private Integer codigo;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "monto")
	private double monto;
	
	@ManyToOne
	@JoinColumn(name = "id_inscripcion")
	private InscripcionPAC idInscripcionPAC;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public InscripcionPAC getIdInscripcionPAC() {
		return idInscripcionPAC;
	}

	public void setIdInscripcionPAC(InscripcionPAC idInscripcionPAC) {
		this.idInscripcionPAC = idInscripcionPAC;
	}
}
