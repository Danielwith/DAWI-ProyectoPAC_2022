package net.contratacion.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.contratacion.entity.EntidadPublica;
import net.contratacion.entity.Usuario;
import net.contratacion.service.EntidadPublicaService;
import net.contratacion.service.UsuarioService;

@Controller
@RequestMapping("/admin/mantEntidad")
public class EntidadPublicaController {
	@Autowired
	private EntidadPublicaService entidadService;
	
	@Autowired
	private UsuarioService regUserService;
	
	
	@RequestMapping("/")
	public String listar(Model model) {
		List<EntidadPublica> data = entidadService.listarEntidadPublica();
		model.addAttribute("listarEntidades",data);
		return "entidad";
	}

	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") int cod,
						 @RequestParam("nombre") String nom,
						 @RequestParam("ruc") String ruc,
						 @RequestParam("direccion") String dir,
						 @RequestParam("email") String eml,
						 @RequestParam("telefono") String tel,
						 @RequestParam("usercontraseña") String usercon,
						 @RequestParam("usuario") String usu,
						 @RequestParam("contraseña") String con, RedirectAttributes redirect, Principal p) {
		try {
			EntidadPublica ent = new EntidadPublica();
			ent.setNombre(nom);
			ent.setRUC(ruc);
			ent.setDireccion_postal(dir);
			ent.setEmail(eml);
			ent.setTelefono(tel);

			Usuario user = new Usuario();
			user.setCodigo(regUserService.maxIDUser());
			ent.setIdUser(user);
			
			if(cod==0) {
				try {
					boolean verif = regUserService.checkEmail(nom);
					
					if(verif) {
						redirect.addFlashAttribute("MENSAJE", "Dicha entidad ya existe.");
						redirect.addFlashAttribute("ICONO","error");
						return "redirect:/admin/mantEntidad/";
					}
					else {
						Usuario userReg = new Usuario();
						userReg.setCodigo(regUserService.maxIDUser());
						userReg.setLogin(nom);
						userReg.setClave(usercon);
				
						regUserService.createUser(userReg); 
						entidadService.guardar(ent);
						redirect.addFlashAttribute("MENSAJE","Entidad Registrada con éxito");
						redirect.addFlashAttribute("ICONO","success");
					}
				}
				catch(Exception e) {
					redirect.addFlashAttribute("MENSAJE", "Error al Registrar");
					redirect.addFlashAttribute("ICONO","error");
					e.printStackTrace();
				}

			}
			else {
				try {
					Usuario usuarioGet = regUserService.getUser(nom);
					usuarioGet.setLogin(nom);
					if(usercon.trim() != "") {
						usuarioGet.setClave(usercon);
					}
					
					regUserService.updateUser(usuarioGet);
					
					ent.setIdEntidad(cod);
					entidadService.guardar(ent);
					redirect.addFlashAttribute("MENSAJE","Entidad modificada con éxito");
					redirect.addFlashAttribute("ICONO","success");
				}
				catch(Exception e) {
					redirect.addFlashAttribute("MENSAJE", "Error al Modificar");
					redirect.addFlashAttribute("ICONO","error");
					e.printStackTrace();
				}
			}
		}
		catch(Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error Fatal al momento de registrar.");
			redirect.addFlashAttribute("ICONO","error");
			e.printStackTrace();
		}
		return "redirect:/admin/mantEntidad/";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codEliminar") int cod,RedirectAttributes redirect) {
		try {
			entidadService.eliminar(cod);
			redirect.addFlashAttribute("MENSAJE", "Entidad eliminada con éxito");
			redirect.addFlashAttribute("ICONO","success");
			
		}
		catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE", "No se puede eliminar porque existe un Registro PAC con esta entidad");
			redirect.addFlashAttribute("ICONO","error");
			e.printStackTrace();
		}
		return "redirect:/admin/mantEntidad/";
	}
}
