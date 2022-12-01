package net.contratacion.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.contratacion.entity.Menu;
import net.contratacion.entity.Usuario;
import net.contratacion.service.MenuService;
import net.contratacion.service.UsuarioDetailsServiceImpl;

@Controller
@SessionAttributes({"ENLACES","USUARIO",""})
public class IntranetController {
	// CONTROLADOR LOGIN
	@Autowired
	private UsuarioDetailsServiceImpl UsuarioService;
	
	@Autowired
	private MenuService MenuService;
	
	@RequestMapping("/")
	public String index() {
		return "login";
	}
	
	@RequestMapping(value={"/entidad","/admin","/analista"})
	public String menu(Model model, Principal p) {
		String username = p.getName();
		Usuario user = UsuarioService.obtenerDatosSesion(username);
		
		List<Menu> data = MenuService.menuUsuario(user.getCodigo());
		model.addAttribute("ENLACES",data);
		model.addAttribute("USUARIO",user);
		return "home";
	}
	
}
