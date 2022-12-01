package net.contratacion.controller;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.contratacion.entity.DetalleInscripcion;
import net.contratacion.entity.DetalleProyecto;
import net.contratacion.entity.EntidadPublica;
import net.contratacion.entity.EstadoPAC;
import net.contratacion.entity.InscripcionPAC;
import net.contratacion.service.DetalleProyectoService;
import net.contratacion.service.EntidadPublicaService;
import net.contratacion.service.InscripcionPACService;

@Controller
@RequestMapping("/entidad/regInscrip")
public class InscripcionPACController {

	@Autowired
	private EntidadPublicaService entidadService;
	
	@Autowired 
	private InscripcionPACService inscripcionService;
	
	@Autowired
	private DetalleProyectoService detalleService;
	
	@RequestMapping("/")
	public String listar(Model model, @SessionAttribute("USUARIO")int idUser ) {
				
		List<InscripcionPAC> data= inscripcionService.buscarPorUsuario(idUser);
		model.addAttribute("listarInsPendientes",data);
		model.addAttribute("numInsPendientes",data.size());
		model.addAttribute("validateNotif",true);
		return "registrarInscripcion";
	}
	/*
	@RequestMapping("/generarIDDetalle")
	@ResponseBody
	public int generarIdDetalle() {
		int id= inscripcionService.generarId();
		return id;
	}*/
	
	@RequestMapping("/listarEntidad")
	@ResponseBody
	public List<EntidadPublica>  listarEntidad(@RequestParam("usuario") int iduser) {
		List<EntidadPublica> entidad= entidadService.encontrarPorUsuario(iduser);
		return entidad;
}
	
	@RequestMapping("/adicionarDetalle")
	@ResponseBody
	public List<DetalleInscripcion> Adicionar(@RequestParam("titulo") String titulo,
												@RequestParam("descripcion") String descripcion,
												@RequestParam("monto") double monto, 
												HttpSession session){
		//declarando arreglo
		List<DetalleInscripcion> data= null;
		//validar si existe atributo de tipo sesión
		if(session.getAttribute("DetallePAC")== null) //no existe
			//creamos atributo 
			data= new ArrayList<DetalleInscripcion>();
		else //existe
			data= (List<DetalleInscripcion>) session.getAttribute("DetallePAC");
		
		//crear objeto de la clase DetalleInscripcion
		DetalleInscripcion det= new DetalleInscripcion();
		det.setTitulo(titulo);
		det.setDescripcion(descripcion);
		det.setMonto(monto);
		data.add(det);
		
		//crear el atributo de tipo sesion
		session.setAttribute("DetallePAC", data);
		return data;
	}
	
	
	@RequestMapping("/eliminarDetalle")
	@ResponseBody
	public List<DetalleInscripcion> Eliminar(@RequestParam("titulo") String titulo, 
												HttpSession session){
		//obtener atributo DetallePAC
		List<DetalleInscripcion> data=  (List<DetalleInscripcion>) session.getAttribute("DetallePAC");
		//bucle
		for(DetalleInscripcion d:data) {
			if(d.getTitulo().equals(titulo)) {
				data.remove(d);
				break;
			}
		}
		
		session.setAttribute("DetallePAC", data);
		return data;
	}
	
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codEntidad") int codigoEntidad, 
						@RequestParam("fecha") String fecha,
						@RequestParam("ano_pac") String ano_pac,
						@RequestParam("presupuesto") double presupuesto,
						HttpSession session,
						RedirectAttributes redirect) {
		
		try {
			InscripcionPAC pac= new InscripcionPAC();
			pac.setFecha(Date.valueOf(fecha));
			EntidadPublica entidad= new EntidadPublica();
			entidad.setIdEntidad(codigoEntidad);
			pac.setNomEstado(entidad);
			pac.setAno_pac(ano_pac);
			pac.setPresupuesto(presupuesto);
			EstadoPAC estado= new EstadoPAC();
			estado.setId(4);
			pac.setIdEstado(estado);
			
			//obtener arreglo del detalle PAC
			List<DetalleInscripcion> data= (List<DetalleInscripcion>) session.getAttribute("DetallePAC");
			
			//arreglo detalle
			List<DetalleProyecto> listaDetalle= new ArrayList<DetalleProyecto>();
			
			//bucle para agregar detalle
				for(DetalleInscripcion d: data) {
					DetalleProyecto dp= new DetalleProyecto();
					dp.setTitulo(d.getTitulo());
					dp.setDescripcion(d.getDescripcion());
					dp.setMonto(d.getMonto());
					dp.setActivo("1");
					listaDetalle.add(dp);
				}
			pac.setListaProyectos(listaDetalle);
			//registrar
			inscripcionService.registrar(pac);
			
			//borrar arreglo
			data.clear();
			session.setAttribute("DetallePAC", data);
			redirect.addFlashAttribute("MENSAJE","Inscripción del PAC Registrado.");
			redirect.addFlashAttribute("ICONO","success");
		
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en el registro del PAC");
			redirect.addFlashAttribute("ICONO","error");
			e.printStackTrace();
		}
		
		return "redirect:/entidad/regInscrip/";
	}
	
	
	@RequestMapping("/eliminarPAC")
	public String Eliminar(@RequestParam("idInsElimina") int idIns,
							RedirectAttributes redirect) {
		try {
			inscripcionService.eliminar(idIns);
			redirect.addFlashAttribute("MENSAJE","Inscripción del PAC eliminado con éxito.");
			redirect.addFlashAttribute("ICONO","success");
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en la eliminación.");
			redirect.addFlashAttribute("ICONO","error");
			e.printStackTrace();
		}
		return "redirect:/entidad/regInscrip/";

	}
	
	@RequestMapping("/buscarPAC")
	@ResponseBody
	public InscripcionPAC buscarPAC(@RequestParam("idInsEdita") int idIns) {
		InscripcionPAC ins= inscripcionService.listarInscripcion(idIns);
		return ins;
	}
	
	@RequestMapping("/buscarDetalle")
	@ResponseBody
	public List<DetalleProyecto> BuscarDetalle(@RequestParam("idInsEdita") int idIns) {
		List<DetalleProyecto> data=detalleService.listarDetallePorIDInscrip(idIns);
		return data;
	}
	
	@RequestMapping("/editarPAC")
	public String Editar(@RequestParam("idDet") int idDetalle,
						@RequestParam("tituloEdit") String titulo,
						@RequestParam("desEdit") String des,
						@RequestParam("montoEdit") double monto,
						@RequestParam("idIns") int idInscripcion,
						RedirectAttributes redirect) {
		try {
			inscripcionService.Editar(idDetalle, titulo, des, monto, idInscripcion);
			redirect.addFlashAttribute("MENSAJE","Inscripción del PAC modificado con éxito.");
			redirect.addFlashAttribute("ICONO","success");
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en la modificación.");
			redirect.addFlashAttribute("ICONO","error");
			e.printStackTrace();
		}
		return "redirect:/entidad/regInscrip/";
	}
	
	
	
}
