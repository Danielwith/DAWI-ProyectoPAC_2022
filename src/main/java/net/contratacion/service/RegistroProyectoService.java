package net.contratacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.entity.RegistroProyecto;
import net.contratacion.repository.RegistroProyectoRepository;

@Service
public class RegistroProyectoService {
	
	@Autowired
	private RegistroProyectoRepository repo;
	
	public void guardar(RegistroProyecto obj) {
		repo.save(obj);
	}
	
	public int obtenerCodReq() {
		return repo.codReq();
	}
	
	public Optional<RegistroProyecto> listarPorID(int cod){
		return repo.findById(cod);
	}
	
	public List<RegistroProyecto> listarRegDetalle(){
		return repo.listRegDetalle();
	}
	
	public List<RegistroProyecto> filtrarRegDetalle(String inicio, String fin){
		return repo.filtrarPorFecha(inicio, fin);
	}
}
