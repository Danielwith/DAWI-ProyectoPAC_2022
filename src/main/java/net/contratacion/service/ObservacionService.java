package net.contratacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.entity.Observacion;
import net.contratacion.repository.ObservacionRepository;

@Service
public class ObservacionService {
	@Autowired
	private ObservacionRepository repo;
	
	public List<Observacion> listar(){
		return repo.findAll();
	}
}
