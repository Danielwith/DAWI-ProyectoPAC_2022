package net.contratacion.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tipousuario")
public class Tipo {
	@Id
	@Column(name="idtipo")
	private Integer codigo;
	@Column(name="descripcion")
	private String nomTipo;
	
	@JsonIgnore
	@OneToMany(mappedBy="tipos")
	private List<Usuario> listaUsuario;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNomTipo() {
		return nomTipo;
	}

	public void setNomTipo(String nomTipo) {
		this.nomTipo = nomTipo;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
	
}
