package net.contratacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import net.contratacion.entity.EntidadPublica;
import net.contratacion.entity.RegistroProyecto;
import net.contratacion.service.EntidadPublicaService;
import net.contratacion.service.RegistroProyectoService;

@Controller
@RequestMapping("/entidad/listDetalle")
public class DetalleListaController {
	
	@Autowired
	private RegistroProyectoService regProyService;
	
	@Autowired
	private EntidadPublicaService entidadService;
	
	@RequestMapping("/")
	public String listar(Model model, @SessionAttribute("USUARIO")int idUser) {
		List<EntidadPublica> entidUser = entidadService.encontrarPorUsuario(idUser);
		List<RegistroProyecto> lista = regProyService.listarRegDetalle(entidUser.get(0).getIdEntidad());
		model.addAttribute("listaProyecto",lista);
		return "detalleBienyServ";
	}
	
	@RequestMapping("/filtrar")
	@ResponseBody
	public List<RegistroProyecto> filtrar(@RequestParam("dateini") String inicio,
										  @RequestParam("dateend") String fin,
										  @SessionAttribute("USUARIO")int idUser,Model model) {
		List<EntidadPublica> entidUser = entidadService.encontrarPorUsuario(idUser);
		List<RegistroProyecto> lista = regProyService.filtrarRegDetalle(inicio, fin, entidUser.get(0).getIdEntidad());
		return lista;
	}

}
