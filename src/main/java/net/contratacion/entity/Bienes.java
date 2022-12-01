package net.contratacion.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="tb_bienes")
public class Bienes {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_bien")
	private Integer codigo;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="fech_registro")
	private LocalDate fecharegistro;
	@Column(name="unida_medida")
	private String unidadMedida;
	@Column(name="precio")
	private Double precio;
	
	@ManyToOne
	@JoinColumn(name="cod_tipo")
	private TipoBienes tipoBien;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(LocalDate fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public TipoBienes getTipoBien() {
		return tipoBien;
	}
	public void setTipoBien(TipoBienes tipoBien) {
		this.tipoBien = tipoBien;
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
		Bienes other = (Bienes) obj;
		return Objects.equals(codigo, other.codigo);
	}
}
