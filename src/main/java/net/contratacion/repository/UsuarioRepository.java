package net.contratacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.contratacion.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public boolean existsByLogin(String login);
	public Usuario findByLogin(String login);
	@Query(value="select max(iduser)+1 from users",nativeQuery=true)
	public int maxLoginID();
}
