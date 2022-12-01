package net.contratacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.contratacion.config.CustomUserDetails;
import net.contratacion.entity.Usuario;
import net.contratacion.repository.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repo.findByLogin(username);
		
		if (user != null) {
			return new CustomUserDetails(user);
		}
		
		throw new UsernameNotFoundException("Usuario no disponible");
	}
	
	public Usuario obtenerDatosSesion(String email) {
		return repo.findByLogin(email);
	}
}
