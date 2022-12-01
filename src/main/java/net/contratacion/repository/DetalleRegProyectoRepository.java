package net.contratacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.transaction.annotation.Transactional;

import net.contratacion.entity.DetalleProyecto;
import net.contratacion.entity.DetalleRegProyecto;
import net.contratacion.entity.DetalleRegProyectoPK;
import net.contratacion.entity.RegistroProyecto;

public interface DetalleRegProyectoRepository extends JpaRepository<DetalleRegProyecto,DetalleRegProyectoPK>{

	@Query("select dp from DetalleRegProyecto dp where dp.codProyecto.codigo=?1")
	List<DetalleRegProyecto> listaProyectos(int id);
}
