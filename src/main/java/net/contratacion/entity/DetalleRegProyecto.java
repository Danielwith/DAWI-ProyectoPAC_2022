package net.contratacion.entity;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "tb_detalleproyecto")
public class DetalleRegProyecto {
	@EmbeddedId
	private DetalleRegProyectoPK IDdetalleRegProyecto;
	
	@Column(name="cantidad")
	private int cantidad;
	@Column(name="subtotal")
	private double subtotal;
	
	@ManyToOne
	@JoinColumn(name="id_registro_proy")
	@MapsId(value="idRegistroProy")
	private RegistroProyecto codProyecto;

	@ManyToOne
	@JoinColumn(name="id_bien")
	@MapsId(value="idBien")
	private Bienes codBien;
	
	public DetalleRegProyectoPK getIDdetalleRegProyecto() {
		return IDdetalleRegProyecto;
	}

	public void setIDdetalleRegProyecto(DetalleRegProyectoPK iDdetalleRegProyecto) {
		IDdetalleRegProyecto = iDdetalleRegProyecto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public RegistroProyecto getCodProyecto() {
		return codProyecto;
	}

	public void setCodProyecto(RegistroProyecto codProyecto) {
		this.codProyecto = codProyecto;
	}

	public Bienes getCodBien() {
		return codBien;
	}

	public void setCodBien(Bienes codBien) {
		this.codBien = codBien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IDdetalleRegProyecto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleRegProyecto other = (DetalleRegProyecto) obj;
		return Objects.equals(IDdetalleRegProyecto, other.IDdetalleRegProyecto);
	}
	
	
}
