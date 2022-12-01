package net.contratacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import net.contratacion.entity.RegistroProyecto;

public interface RegistroProyectoRepository extends JpaRepository<RegistroProyecto, Integer>{
	@Query(value="SELECT MAX(id) FROM proyecto_pac_2022_22.tb_registroproyecto",nativeQuery = true)
	public int codReq();
	
	@Query("SELECT rp from RegistroProyecto rp")
	public List<RegistroProyecto> listRegDetalle();
	
	@Query("SELECT rp from RegistroProyecto rp WHERE rp.fechaReg BETWEEN ?1 AND ?2")
	public List<RegistroProyecto> filtrarPorFecha(String ini, String fin);

}
