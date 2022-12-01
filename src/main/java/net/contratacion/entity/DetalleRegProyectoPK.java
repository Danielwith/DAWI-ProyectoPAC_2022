package net.contratacion.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetalleRegProyectoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idRegistroProy;
	
	private int idBien;

	public int getIdRegistroProy() {
		return idRegistroProy;
	}

	public void setIdRegistroProy(int idRegistroProy) {
		this.idRegistroProy = idRegistroProy;
	}

	public int getIdBien() {
		return idBien;
	}

	public void setIdBien(int idBien) {
		this.idBien = idBien;
	}
	
	
}
