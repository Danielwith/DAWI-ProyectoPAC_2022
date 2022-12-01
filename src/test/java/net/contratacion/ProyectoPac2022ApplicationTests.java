package net.contratacion;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.contratacion.entity.Bienes;
import net.contratacion.entity.DetalleProyecto;
import net.contratacion.entity.EntidadPublica;
import net.contratacion.entity.RegistroProyecto;
import net.contratacion.service.BienesService;
import net.contratacion.service.DetalleProyectoService;
import net.contratacion.service.EntidadPublicaService;
import net.contratacion.service.RegistroProyectoService;
import net.contratacion.service.UsuarioService;

@SpringBootTest
class ProyectoPac2022ApplicationTests {
	
	@Autowired
	private UsuarioService usuarioService;
	@Test
	void contextLoads() {
		boolean f = usuarioService.checkEmail("xd");
		
		System.out.println(f);
		
		
	}

}
