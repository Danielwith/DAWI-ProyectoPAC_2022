package net.contratacion.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.contratacion.entity.Bienes;
import net.contratacion.entity.TipoBienes;
import net.contratacion.service.BienesService;
import net.contratacion.service.TipoBienService;

@Controller
@RequestMapping("/admin/mantBienes")
public class BienesController {
	@Autowired
	private BienesService bienService;
	
	@Autowired
	private TipoBienService tipoBienService;
	
	@RequestMapping("/")
	public String listar(Model model) {
		List<Bienes> data = bienService.listarBienes();
		model.addAttribute("listarBienes",data);
		
		List<TipoBienes> listaTipo = tipoBienService.listarTipoBienes();
		model.addAttribute("listarTipoBienes",listaTipo);

		return "bienes";
	}
	
	@RequestMapping("/maxCodigo")
	@ResponseBody
	public String obtenerMaxCod() {
		String code = bienService.obtenerMaxCodigo();
		return code;
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Bienes buscarPorId(@RequestParam("codigo") int cod) {
		Bienes bien=bienService.buscarPorId(cod);
		return bien;
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") int cod,
						 @RequestParam("codTipo") int codTipo,
						 @RequestParam("descripcion") String descr,
						 @RequestParam("fechRegistro") String fechReg,
						 @RequestParam("unidadMedida") String unidadMedida,
						 @RequestParam("precio") Double prec, RedirectAttributes redirect) {
		try {
			Bienes bien = new Bienes();
			
			bien.setCodigo(cod);
			bien.setDescripcion(descr);
			bien.setFecharegistro(LocalDate.parse(fechReg));
			bien.setUnidadMedida(unidadMedida);
			bien.setPrecio(prec);
			
			TipoBienes tipo = new TipoBienes();
			tipo.setCodigo(codTipo);
			bien.setTipoBien(tipo);
			
			String code = bienService.obtenerMaxCodigo();
			if(cod==Integer.parseInt(code)) {
				try {
					bienService.guardar(bien);
					redirect.addFlashAttribute("MENSAJE","Bien o Servicio registrado correctamente");
					redirect.addFlashAttribute("ICONO","success");
				}
				catch (Exception e) {
					redirect.addFlashAttribute("MENSAJE","Error en el registro de Bien o Servicio");
					redirect.addFlashAttribute("ICONO","error");
					e.printStackTrace();
				}
			}
			else {
				bien.setCodigo(cod);
				try {
					bienService.guardar(bien);
					redirect.addFlashAttribute("MENSAJE","Bien o Servicio actualizado correctamente");
					redirect.addFlashAttribute("ICONO","success");
				}
				catch(Exception e) {
					redirect.addFlashAttribute("MENSAJE","Error en la actualización");
					redirect.addFlashAttribute("ICONO","error");
					e.printStackTrace();
				}
			}
			
		}
		catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error Fatal al momento de registrar.");
			redirect.addFlashAttribute("ICONO","warning");
			e.printStackTrace();
		}
		return "redirect:/admin/mantBienes/";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigoEliminar") int cod, RedirectAttributes redirect) {
		try {
			bienService.eliminar(cod);
			redirect.addFlashAttribute("MENSAJE", "Bien o Servicio eliminado correctamente");
			redirect.addFlashAttribute("ICONO","success");
		}
		catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE", "Bien o Servicio esta siendo utilizado actualmente");
			redirect.addFlashAttribute("ICONO","error");
			e.printStackTrace();
		}
		return "redirect:/admin/mantBienes/";
	}
}
