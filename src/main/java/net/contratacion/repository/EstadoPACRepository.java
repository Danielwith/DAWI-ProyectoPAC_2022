package net.contratacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.contratacion.entity.EstadoPAC;

public interface EstadoPACRepository extends JpaRepository<EstadoPAC, Integer> {
	@Query(value="select * from estado_pac where id in (1,2)",nativeQuery = true)
	public List<EstadoPAC> listarEstado();
}
