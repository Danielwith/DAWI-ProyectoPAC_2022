package net.contratacion.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_tipo")
public class TipoBienes {
	@Id
	@Column(name="cod_tipo")
	private Integer codigo;
	@Column(name="nom_tipo")
	private String nomTipo;
	
	@JsonIgnore
	@OneToMany(mappedBy="tipoBien")
	private List<Bienes> listaBienes;
	
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
	public List<Bienes> getListaBienes() {
		return listaBienes;
	}
	public void setListaBienes(List<Bienes> listaBienes) {
		this.listaBienes = listaBienes;
	}
	
	
}
