package net.contratacion.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.contratacion.entity.EvaluacionPAC;

public interface EvaluacionRepository extends JpaRepository<EvaluacionPAC,Integer> {
	@Transactional
	@Modifying
	@Query(value="CALL procEvaluarPac(?1,?2,?3)",nativeQuery = true)
	public void registrarEvaluacion(int idInscrip,int idObs, int idEstado);
}
