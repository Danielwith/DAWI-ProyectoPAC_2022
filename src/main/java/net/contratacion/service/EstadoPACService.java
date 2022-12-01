package net.contratacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.entity.EstadoPAC;
import net.contratacion.repository.EstadoPACRepository;

@Service
public class EstadoPACService {
	@Autowired
	private EstadoPACRepository repo;
	
	public List<EstadoPAC> listar(){
		return repo.listarEstado();
	}
}
