package com.bit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bit.config.WebConfig;
import com.bit.model.Usuario;
import com.bit.model.dto.Category;
import com.bit.model.dto.Item;
import com.bit.model.dto.response.EstadisticasGeneralRSP;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EstadisticasService estadisticasService;
	
	@Test
	public void buscarrestaurarPasswordLink() {
		
		Usuario item = new Usuario();
		item.setPassword_restore_link("f4e38a7cd6be8ada6ac60376c32a8ed291b5ed2443d8563187ca11778be6e480");
		usuarioService.restaurarPasswordLink(item);
	}
	
	@Test
	public void estadisitcasUsuariosTest() {
		estadisticasService.obtieneEstadisticasUsuarios();
	}
	
	@Test
	public void estadisticasGeneral() {
		estadisticasService.obtieneEstadisticasGeneral();
	}
	
	@Test
	public void estadisticasMarcas() {
		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasMarcas();
		for( Category c : rsp.getTotalEscaneaosDepartamentoMes() ) {
			System.out.println( c.getTitulo() );
			
			for( Item i : c.getItems() ) {
				System.out.println( i.getTopico() + " - " + i.getIndice() + " - " + i.getTotal() );
			}
		}
		
		for( Category c : rsp.getTotalEscaneaosTiendaMes() ) {
			System.out.println( c.getTitulo() );
			
			for( Item i : c.getItems() ) {
				System.out.println( i.getTopico() + " - " + i.getIndice() + " - " + i.getTotal() );
			}
		}
	}
}
