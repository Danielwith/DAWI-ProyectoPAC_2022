package net.contratacion.entity;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="tb_registroproyecto")
public class RegistroProyecto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer codigo;
	@Column(name="num_detalle")
	private String numDet;
	@Column(name="fecha_reg")
	private String fechaReg;
	@Column(name="subtotal")
	private double subtotal;

	@ManyToOne
	@JoinColumn(name="id_proyecto")
	private DetalleProyecto idproyecto; /* de aqui se obtiene nomTitulo */
	
	@ManyToOne
	@JoinColumn(name="id_estado")
	private EstadoPAC idestado;
	
	@ManyToOne
	@JoinColumn(name="id_entidad")
	private EntidadPublica identidad;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNumDet() {
		return numDet;
	}

	public void setNumDet(String numDet) {
		this.numDet = numDet;
	}

	public String getFechaReg() {
		return fechaReg;
	}

	public void setFechaReg(String fechaReg) {
		this.fechaReg = fechaReg;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public DetalleProyecto getIdproyecto() {
		return idproyecto;
	}

	public void setIdproyecto(DetalleProyecto idproyecto) {
		this.idproyecto = idproyecto;
	}

	public EstadoPAC getIdestado() {
		return idestado;
	}

	public void setIdestado(EstadoPAC idestado) {
		this.idestado = idestado;
	}

	public EntidadPublica getIdentidad() {
		return identidad;
	}

	public void setIdentidad(EntidadPublica identidad) {
		this.identidad = identidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroProyecto other = (RegistroProyecto) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
}
