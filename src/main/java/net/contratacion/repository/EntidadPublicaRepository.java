package net.contratacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.contratacion.entity.EntidadPublica;

public interface EntidadPublicaRepository extends JpaRepository<EntidadPublica,Integer> {
	@Query(value="select * from entidadpublica where iduser=?1",nativeQuery=true)
	public List<EntidadPublica> listEntByUsuario(int codUsu);
}
