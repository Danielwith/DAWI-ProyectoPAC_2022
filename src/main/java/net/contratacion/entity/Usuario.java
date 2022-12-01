package net.contratacion.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class Usuario {
	@Id
	@Column(name="iduser")
	private Integer codigo;
	@Column(name="username")
	private String login;
	@Column(name="password")
	private String clave;
	
	@JsonIgnore
	@OneToMany(mappedBy="idUser")
	private List<EntidadPublica> listaEntidad;
	
	@ManyToOne
	@JoinColumn(name="tipousuario")
	private Tipo tipos;


	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public Tipo getTipos() {
		return tipos;
	}

	public void setTipos(Tipo tipos) {
		this.tipos = tipos;
	}

	public List<EntidadPublica> getListaEntidad() {
		return listaEntidad;
	}

	public void setListaEntidad(List<EntidadPublica> listaEntidad) {
		this.listaEntidad = listaEntidad;
	}
}
