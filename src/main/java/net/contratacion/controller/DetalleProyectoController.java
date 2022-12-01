package net.contratacion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.contratacion.entity.Bienes;
import net.contratacion.entity.DetalleProyecto;
import net.contratacion.entity.DetalleRegProyecto;
import net.contratacion.entity.DetalleRegProyectoPK;
import net.contratacion.entity.EntidadPublica;
import net.contratacion.entity.EstadoPAC;
import net.contratacion.entity.RegistroProyecto;
import net.contratacion.entity.TipoBienes;
import net.contratacion.entity.Usuario;
import net.contratacion.service.BienesService;
import net.contratacion.service.DetalleProyectoService;
import net.contratacion.service.DetalleRegProyectoService;
import net.contratacion.service.EntidadPublicaService;
import net.contratacion.service.RegistroProyectoService;
import net.contratacion.service.TipoBienService;

@Controller
@SessionAttributes({"DATA","USUARIO",""})
@RequestMapping("/entidad/regBienServ")
public class DetalleProyectoController {
	
	@Autowired
	private TipoBienService tipoBienService;
	
	@Autowired
	private EntidadPublicaService EntidadService;
	
	@Autowired
	private DetalleProyectoService dpService;
	
	@Autowired
	private BienesService bienService;
	
	@Autowired
	private RegistroProyectoService regProyService;
	
	@Autowired
	private DetalleRegProyectoService detRegProyService;
	
	@RequestMapping("/")
	public String listar(Model model) {
		Usuario user = (Usuario) model.getAttribute("USUARIO");
		List<DetalleProyecto> lista = dpService.listarDetallePorUsuario(user.getCodigo());
		List<TipoBienes> listaTipo = tipoBienService.listarTipoBienes();
		
		model.addAttribute("listarTiposBienes",listaTipo);
		model.addAttribute("listarDetalleProyecto",lista);
		return "detalleProyecto";
	}
	
	@RequestMapping("/mostrarEntidad")
	@ResponseBody
	public List<EntidadPublica> entidadPublica(Model model) {
		Usuario user = (Usuario) model.getAttribute("USUARIO");
		List<EntidadPublica> entidad = EntidadService.encontrarPorUsuario(user.getCodigo());
		return entidad;
	}
	
	@RequestMapping("/generarDET")
	@ResponseBody
	public String generarNumDET() {
		String numDET = dpService.generarNumeroDET();
		return numDET;
	}
	
	@RequestMapping("/listarPorTipo")
	@ResponseBody
	public List<Bienes> listarTipo(@RequestParam("codTipo") int cod) {
		List<Bienes> lista = bienService.listarPorTipoBien(cod);
		return lista;
	}
	
	@RequestMapping("/resetLista")
	@ResponseBody
	public Boolean reiniciarLista(Model model) {
		List<DetalleRegProyecto> lista=new ArrayList<DetalleRegProyecto>();
		model.addAttribute("DATA",lista);
		return true;
	}
	
	@RequestMapping("/adicionarBien")
	@ResponseBody
	public List<DetalleRegProyecto> adicionarBien(@RequestParam("codigo") int cod,
												  @RequestParam("descripcion") String des,
												  @RequestParam("cantidad") int cant,
												  @RequestParam("precio") double precio,Model model){
		double subtotal=0;
		List<DetalleRegProyecto> lista = null;
		if(model.getAttribute("DATA")==null) {
			lista = new ArrayList<DetalleRegProyecto>();
		}
		else {
			lista = (List<DetalleRegProyecto>) model.getAttribute("DATA");
		}
		subtotal=cant*precio;
		DetalleRegProyecto det = new DetalleRegProyecto();
		
		Bienes bien = new Bienes();
		bien.setCodigo(cod);
		bien.setDescripcion(des);
		det.setCodBien(bien);
		det.setCantidad(cant);
		det.setSubtotal(subtotal);
		lista.add(det);
		model.addAttribute("DATA",lista);
		return lista;
	}
	
	@RequestMapping("/eliminarBien")
	@ResponseBody
	public List<DetalleRegProyecto> eliminarBien(@RequestParam("codigo") int cod, Model model){
		List<DetalleRegProyecto> lista = (List<DetalleRegProyecto>) model.getAttribute("DATA");
		for(DetalleRegProyecto d : lista) {
			if(d.getCodBien().getCodigo()==cod) {
				lista.remove(d);
				break;
			}
		}
		model.addAttribute("DATA",lista);
		return lista;
	}
	
	@RequestMapping("/grabarDP")
	public String registrarProyecto(@RequestParam("numero") String numDet,
									@RequestParam("fecha") String fecha,
									@RequestParam("subt") double subtotal,
									@RequestParam("proyecto") int idProy,
									@RequestParam("entidad") int idEnt,Model model,RedirectAttributes redirect) {
		try {
		RegistroProyecto bean = new RegistroProyecto();
		bean.setNumDet(numDet);
		bean.setFechaReg(fecha);
		bean.setSubtotal(subtotal);
		
		DetalleProyecto idProyecto = new DetalleProyecto();
		idProyecto.setCodigo(idProy);
		bean.setIdproyecto(idProyecto);
		
		EntidadPublica idEntidad = new EntidadPublica();
		idEntidad.setIdEntidad(idEnt);
		bean.setIdentidad(idEntidad);
		
		EstadoPAC idEstado = new EstadoPAC();
		idEstado.setId(1);
		bean.setIdestado(idEstado);
		
		regProyService.guardar(bean);
		 
		List<DetalleRegProyecto> lista = (List<DetalleRegProyecto>) model.getAttribute("DATA");
		
		int codReq = regProyService.obtenerCodReq();
		
		for(var det : lista) {
			DetalleRegProyecto DRPbean = new DetalleRegProyecto();
			
			Optional<RegistroProyecto> rp = regProyService.listarPorID(codReq);
			
			Optional<Bienes> bien = bienService.buscarPorCodigoListDP(det.getCodBien().getCodigo());
			
			DRPbean.setCantidad(det.getCantidad());
			DRPbean.setSubtotal(det.getSubtotal());
			
			DetalleRegProyectoPK pkDet = new DetalleRegProyectoPK();
			pkDet.setIdBien(bien.get().getCodigo());
			pkDet.setIdRegistroProy(rp.get().getCodigo());
			
			DRPbean.setIDdetalleRegProyecto(pkDet);
			
			DRPbean.setCodProyecto(rp.get());
			DRPbean.setCodBien(bien.get());
			
			detRegProyService.registrarDetalleProyecto(DRPbean);
		}
		
		lista=new ArrayList<DetalleRegProyecto>();
		
		DetalleProyecto inactivo = dpService.buscarporID(idProy);
		inactivo.setActivo("0");
		dpService.guardar(inactivo);
		
		redirect.addFlashAttribute("MENSAJE","Proyecto registrado correctamente.");
		redirect.addFlashAttribute("ICONO","success");
		}
		catch(Exception e){
			List<DetalleRegProyecto> lista = (List<DetalleRegProyecto>) model.getAttribute("DATA");
			lista=new ArrayList<DetalleRegProyecto>();
			redirect.addFlashAttribute("MENSAJE","Ocurrio un error al registrar.");
			redirect.addFlashAttribute("ICONO","error");
		}
		
		return "redirect:/entidad/regBienServ/";
	}
	
}
