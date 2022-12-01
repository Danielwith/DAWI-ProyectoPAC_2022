package net.contratacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.contratacion.entity.DetalleProyecto;
import net.contratacion.entity.EntidadPublica;
import net.contratacion.entity.EstadoPAC;
import net.contratacion.entity.InscripcionPAC;
import net.contratacion.entity.Observacion;
import net.contratacion.service.DetalleProyectoService;
import net.contratacion.service.EntidadPublicaService;
import net.contratacion.service.EstadoPACService;
import net.contratacion.service.EvaluacionService;
import net.contratacion.service.InscripcionPACService;
import net.contratacion.service.ObservacionService;

@Controller
@RequestMapping("/analista/evaluar")
public class EvaluacionController {
	@Autowired
	private EvaluacionService evaService;
	
	@Autowired
	private EstadoPACService estService;
	
	@Autowired
	private EntidadPublicaService entService;
	
	@Autowired
	private ObservacionService obsService;
	
	@Autowired
	private InscripcionPACService inscripcionService;
	
	@Autowired
	private DetalleProyectoService detProService;
	
	@RequestMapping("/")
	public String listar(Model model) {
		List<EntidadPublica> listaEntidad = entService.listarEntidadPublica();
		model.addAttribute("listarEntidadPublica",listaEntidad);
		
		List<EstadoPAC> listaEstado = estService.listar();
		model.addAttribute("listarEstados",listaEstado);
		
		List<Observacion> listaObservacion = obsService.listar();
		model.addAttribute("listaObservacion",listaObservacion);
		
		return "evaluarInscripcion";
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	public List<InscripcionPAC> buscarPorID(@RequestParam("entidad") int codEntidad) {
		List<InscripcionPAC> data = inscripcionService.buscarPorID(codEntidad);		
		return data;
	}
	
	@RequestMapping("/listarDetalle")
	@ResponseBody
	public List<DetalleProyecto> listarDetalleIDInscrip(@RequestParam("codInscripcion") int codInscrip){
		List<DetalleProyecto> data = detProService.listarDetallePorIDInscrip(codInscrip);
		return data;
	}
	
	@RequestMapping("/grabar")
	public String registrarEvaluacion(@RequestParam("inscripcion") int idInscrip,
									@RequestParam("observacion") int idObs,
									@RequestParam("estado") int idEstado, RedirectAttributes redirect) {
		try {
			evaService.grabar(idInscrip, idObs, idEstado);
			redirect.addFlashAttribute("MENSAJE","Evaluación del PAC registrada con éxito");
			redirect.addFlashAttribute("ICONO","success");
		}
		catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error al registrar evaluacion del PAC");
			redirect.addFlashAttribute("ICONO","error");
			e.printStackTrace();
		}
		return "redirect:/analista/evaluar/";
	}
}
