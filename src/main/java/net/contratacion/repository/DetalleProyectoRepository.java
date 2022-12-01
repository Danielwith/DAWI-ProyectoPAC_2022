package net.contratacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.contratacion.entity.DetalleProyecto;

public interface DetalleProyectoRepository extends JpaRepository<DetalleProyecto, Integer> {
	@Query(value="select * from detalle_proyecto_pac where id_inscripcion=?1",nativeQuery = true)
	public List<DetalleProyecto> listDETbyInscripcion(int cod);
	
	@Query(value="select dp.*,e.descripcion from detalle_proyecto_pac dp join inscripcion_pac ip \r\n"
			+ "on dp.id_inscripcion= ip.id_inscripcion join estado_pac e\r\n"
			+ "on ip.estado_pac=e.id join entidadpublica ep\r\n"
			+ "on ip.entidad_publica=ep.id_entidad\r\n"
			+ "where ep.iduser=?1 and ip.estado_pac=1 and dp.activo=1",nativeQuery=true)
	public List<DetalleProyecto> listDETbyUser(int idUsu);
	
	@Query(value="select concat('DET000-',(count(num_detalle)+1))numero from tb_registroProyecto",nativeQuery=true)
	public String generateNumDET();
	
	@Query("select d from DetalleProyecto d where d.idInscripcionPAC.idInscripcion=?1")
	public List<DetalleProyecto> listDet(int idIns);
	
}
