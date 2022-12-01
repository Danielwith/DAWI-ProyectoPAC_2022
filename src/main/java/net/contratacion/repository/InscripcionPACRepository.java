package net.contratacion.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.contratacion.entity.InscripcionPAC;

public interface InscripcionPACRepository extends JpaRepository<InscripcionPAC, Integer> {
	@Query(value ="select * from inscripcion_pac i join estado_pac e on i.estado_pac = e.id where i.entidad_publica=?1",nativeQuery=true)
	public List<InscripcionPAC> findByEntidad(int cod);
	/*
	@Query("select i from InscripcionPAC i where i.idEstado.id=2 and i.nomEstado.idUser.iduser=?1")
	public List<InscripcionPAC> findByUser(int idUser);*/
	
	
	@Query(value= "select * from inscripcion_pac i join entidadpublica e on i.entidad_publica=e.id_Entidad where i.estado_pac=4 and e.iduser=?1", nativeQuery = true)
	public List<InscripcionPAC> findByUser(int iduser);
	/*
	@Query(value="select max(id_detalle)+1 from detalle_proyecto_pac", nativeQuery = true)
	public int generarIdDetalle();*/
	
	@Transactional
	@Modifying
	@Query(value="CALL procEliminarPAC(?1)", nativeQuery = true)
	public void EliminarPAC(int IdInscripcion);
	
	
	@Query(value="select * from inscripcion_pac where id_inscripcion=?1", nativeQuery = true)
	public InscripcionPAC  findInscripcionbyID(int IdInscripcion);
	
	@Transactional
	@Modifying
	@Query(value="CALL procEditarPAC(?1,?2,?3,?4,?5)", nativeQuery = true)
	public void EditarPAC(int idDet, String titulo, String descripcion, double monto, int IdIns );
	
	
}
