package net.contratacion.service;

import org.springframework.stereotype.Service;

import net.contratacion.entity.Usuario;

@Service
public interface UsuarioService {
	public Usuario createUser(Usuario user);
	public boolean checkEmail(String email);
	public int maxIDUser();
	public Usuario updateUser(Usuario user);
	public Usuario getUser(String email);
	public Usuario getUserByCode(Usuario usuario);
	public void deleteUser(int codigo);
}
