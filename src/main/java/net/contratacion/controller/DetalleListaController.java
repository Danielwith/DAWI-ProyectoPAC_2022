package net.contratacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.contratacion.entity.RegistroProyecto;
import net.contratacion.service.RegistroProyectoService;

@Controller
@RequestMapping("/entidad/listDetalle")
public class DetalleListaController {
	
	@Autowired
	private RegistroProyectoService regProyService;
	
	@RequestMapping("/")
	public String listar(Model model) {
		List<RegistroProyecto> lista = regProyService.listarRegDetalle();
		model.addAttribute("listaProyecto",lista);
		return "detalleBienyServ";
	}
	
	@RequestMapping("/filtrar")
	@ResponseBody
	public List<RegistroProyecto> filtrar(@RequestParam("dateini") String inicio,
										  @RequestParam("dateend") String fin,Model model) {
		List<RegistroProyecto> lista = regProyService.filtrarRegDetalle(inicio, fin);
		return lista;
	}

}
