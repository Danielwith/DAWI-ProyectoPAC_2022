package net.contratacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.entity.DetalleProyecto;
import net.contratacion.repository.DetalleProyectoRepository;

@Service
public class DetalleProyectoService {
	@Autowired
	private DetalleProyectoRepository repo;
	
	public List<DetalleProyecto> listarDetallePorIDInscrip(int cod){
		return repo.listDETbyInscripcion(cod);
	}
	
	public List<DetalleProyecto> listarDetallePorUsuario(int idUser){
		return repo.listDETbyUser(idUser);
	}
	
	public String generarNumeroDET() {
		return repo.generateNumDET();
	}
	
	public List<DetalleProyecto> listaDetallexReporte(int idInscripcion){
		return repo.listDet(idInscripcion);
	}
	
}
