package net.contratacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.entity.TipoBienes;
import net.contratacion.repository.TipoBienRepository;

@Service
public class TipoBienService {
	@Autowired
	private TipoBienRepository repo;
	
	public List<TipoBienes> listarTipoBienes(){
		return repo.findAll();
	}
}
