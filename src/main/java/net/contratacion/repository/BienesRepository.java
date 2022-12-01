package net.contratacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.contratacion.entity.Bienes;

public interface BienesRepository extends JpaRepository<Bienes,Integer> {
	@Query(value="select b.cod_bien, b.descripcion,b.fech_registro,b.unida_medida,t.cod_tipo,t.nom_tipo, b.precio from tb_bienes b join tb_tipo t on b.cod_tipo=t.cod_tipo",nativeQuery=true)
	public List<Bienes> listarBienes();
	@Query(value="select * from tb_bienes where cod_tipo=?1",nativeQuery=true)
	public List<Bienes> listByTipo(int codTipo);
	@Query(value="call maxCodBien",nativeQuery=true)
	public String getMaxCode();
}
