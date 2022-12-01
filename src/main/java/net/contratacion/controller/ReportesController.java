package net.contratacion.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.AbstractListenerReadPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.contratacion.entity.Bienes;
import net.contratacion.entity.DetalleProyecto;
import net.contratacion.entity.DetalleRegProyecto;
import net.contratacion.entity.InscripcionPAC;
import net.contratacion.entity.RegistroProyecto;
import net.contratacion.service.BienesService;
import net.contratacion.service.DetalleProyectoService;
import net.contratacion.service.DetalleRegProyectoService;
import net.contratacion.service.InscripcionPACService;
import net.contratacion.service.RegistroProyectoService;
import net.contratacion.utils.Libreria;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/Reportes")
public class ReportesController {
	
	@Autowired
	DetalleRegProyectoService servicio= new DetalleRegProyectoService();

	
	@Autowired
	DetalleProyectoService servicioDetalle = new DetalleProyectoService();
	
	@RequestMapping("/DetalleProyecto")
	public void DetalleProyecto(@RequestParam("codigo") int cod,
								HttpServletResponse response) {
		try {
			
			List<DetalleRegProyecto> regpro= servicio.listaDetalleProyectos(cod);
			
			File file= ResourceUtils.getFile("classpath:reporte_detalleproyecto.jrxml");
			
			JRBeanCollectionDataSource data= new JRBeanCollectionDataSource(regpro);
			
			JasperPrint print= Libreria.generarReporte(file, data);
			
			response.setContentType("application/pdf");
			
			ServletOutputStream salida= response.getOutputStream();
			
			JasperExportManager.exportReportToPdfStream(print, salida);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/inscripcion")
	public void inscripcionReporte(@RequestParam("idInsReporte") int idInscripcion,
								HttpServletResponse response) {
		try {
			
			List<DetalleProyecto> inscripcion= servicioDetalle.listaDetallexReporte(idInscripcion);
			
			File file= ResourceUtils.getFile("classpath:inscripcion_report_.jrxml");
			
			JRBeanCollectionDataSource data= new JRBeanCollectionDataSource(inscripcion);
			
			JasperPrint print= Libreria.generarReporte(file, data);
			
			response.setContentType("application/pdf");
			
			ServletOutputStream salida= response.getOutputStream();
			
			JasperExportManager.exportReportToPdfStream(print, salida);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
