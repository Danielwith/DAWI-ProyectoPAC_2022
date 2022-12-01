package net.contratacion.entity;

import javax.persistence.*;

@Entity
@Table(name="tb_menu")
public class Menu {
	@Id
	@Column(name="idmenu")
	private Integer codigo;
	@Column(name="descripcion")
	private String nombre;
	@Column(name="url")
	private String url;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
