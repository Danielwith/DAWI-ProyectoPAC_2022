package net.contratacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.entity.EntidadPublica;
import net.contratacion.repository.EntidadPublicaRepository;

@Service
public class EntidadPublicaService {
	@Autowired
	private EntidadPublicaRepository repo;
	
	public void guardar(EntidadPublica obj) {
		repo.save(obj);
	}  

	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
	
	public List<EntidadPublica> listarEntidadPublica(){
		return repo.findAll();
	}
	
	public List<EntidadPublica> encontrarPorUsuario(int codUsu){
		return repo.listEntByUsuario(codUsu);
	}
}
