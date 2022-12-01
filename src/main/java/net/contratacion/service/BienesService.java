package net.contratacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.entity.Bienes;
import net.contratacion.repository.BienesRepository;

@Service
public class BienesService {
	@Autowired
	private BienesRepository repo;
	
	public void guardar(Bienes bean) {
		repo.save(bean);
	}
	//Guardar ya realiza el actualizar.
	
	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
	
	public List<Bienes> listarBienes(){
		return repo.listarBienes();
	}
	
	public Bienes buscarPorId(int cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public List<Bienes> listarPorTipoBien(int codTipo){
		return repo.listByTipo(codTipo);
	}
	
	public String obtenerMaxCodigo() {
		return repo.getMaxCode();
	}
	
	public Optional<Bienes> buscarPorCodigoListDP(int cod) {
		return repo.findById(cod);
	}
}
