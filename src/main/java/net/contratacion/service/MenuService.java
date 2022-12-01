package net.contratacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.contratacion.entity.Menu;
import net.contratacion.repository.MenuRepository;

@Service
public class MenuService {
	@Autowired
	private MenuRepository repo;
	
	public List<Menu> menuUsuario(Integer codigo){
		return repo.findByCodigo(codigo);
	};
}
