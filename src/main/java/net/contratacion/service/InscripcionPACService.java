package net.contratacion.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.entity.DetalleProyecto;
import net.contratacion.entity.InscripcionPAC;
import net.contratacion.repository.DetalleProyectoRepository;
import net.contratacion.repository.InscripcionPACRepository;

@Service
public class InscripcionPACService {
	@Autowired
	private InscripcionPACRepository repo;
	
	@Autowired
	private DetalleProyectoRepository repoDet;
	
	public List<InscripcionPAC> buscarPorID(int cod) {
		return repo.findByEntidad(cod);
	}
	
	public List<InscripcionPAC> buscarPorUsuario(int idUser){
		return repo.findByUser(idUser);
	}
	/*
	public int generarId() {
		return repo.generarIdDetalle();
	}*/
	
	public void registrar(InscripcionPAC insPac) {
		try {
			repo.save(insPac);
			for(DetalleProyecto dp: insPac.getListaProyectos()) {
				dp.setIdInscripcionPAC(insPac);
				repoDet.save(dp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eliminar(int idInscripcion) {
		try {
			repo.EliminarPAC(idInscripcion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public InscripcionPAC listarInscripcion(int IdInscripcion) {
		return repo.findInscripcionbyID(IdInscripcion);
	}
	
	
	public void Editar(int idDet, String titulo, String descripcion, double monto, int IdIns ) {
		try {
		 repo.EditarPAC(idDet, titulo, descripcion, monto, IdIns);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
