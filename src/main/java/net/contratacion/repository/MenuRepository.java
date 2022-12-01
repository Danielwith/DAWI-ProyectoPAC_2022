package net.contratacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.contratacion.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
	
	@Query(value="select m.idmenu,m.descripcion, m.url from users u join tb_acceso a on u.tipousuario=a.idtipo join tb_menu m on a.idmenu= m.idmenu where u.iduser=?1",nativeQuery = true)
	public List<Menu> findByCodigo(Integer codigo);
}
