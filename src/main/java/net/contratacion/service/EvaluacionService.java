package net.contratacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.repository.EvaluacionRepository;

@Service
public class EvaluacionService {
	@Autowired
	private EvaluacionRepository repo;
	
	public void grabar(int idInscrip,int idObs, int idEstado) {
		repo.registrarEvaluacion(idInscrip,idObs,idEstado);
	}
}
