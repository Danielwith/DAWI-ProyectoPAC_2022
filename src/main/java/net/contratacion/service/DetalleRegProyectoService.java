package net.contratacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.entity.DetalleRegProyecto;
import net.contratacion.repository.DetalleRegProyectoRepository;

@Service
public class DetalleRegProyectoService {
	@Autowired
	private DetalleRegProyectoRepository repo;
	
	public void registrarDetalleProyecto(DetalleRegProyecto bean) {
		repo.save(bean);
	}
	
	public List<DetalleRegProyecto> listaDetalleProyectos(int id){
		return repo.listaProyectos(id);
	}

}
