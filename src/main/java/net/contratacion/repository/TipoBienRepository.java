package net.contratacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.contratacion.entity.Bienes;
import net.contratacion.entity.TipoBienes;

public interface TipoBienRepository extends JpaRepository<TipoBienes,Integer>{
	@Query(value="select cod_bien,descripcion,precio from tb_bienes where cod_tipo=?1",nativeQuery=true)
	public List<Bienes> listarPorTipoBien(int codTipo);
}
