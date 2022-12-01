package net.contratacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.contratacion.entity.Tipo;
import net.contratacion.entity.Usuario;
import net.contratacion.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	
	@Override
	public Usuario createUser(Usuario user) {
		Tipo tipo = new Tipo();
		tipo.setCodigo(1);
		
		user.setClave(passwordEncode.encode(user.getClave()));
		user.setTipos(tipo);
		
		return repo.save(user);
	}

	@Override
	public boolean checkEmail(String email) {
		return repo.existsByLogin(email);
	}

	@Override
	public int maxIDUser() {
		return repo.maxLoginID();
	}

	@Override
	public Usuario updateUser(Usuario user) {
		
		user.setClave(passwordEncode.encode(user.getClave()));
		return repo.save(user);
	}

	@Override
	public Usuario getUser(String email) {
		return repo.findByLogin(email);
	}

}
