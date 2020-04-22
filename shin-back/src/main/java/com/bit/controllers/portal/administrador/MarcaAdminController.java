package com.bit.controllers.portal.administrador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit.model.CatalogoMarca;
import com.bit.model.Proveedor;
import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.CatalogoMarcaService;
import com.bit.service.UsuarioShingShingDetailService;

@Controller
@RequestMapping("portal-administrador")
public class MarcaAdminController {
	
	private static Logger log = LoggerFactory.getLogger( CatalogosController.class );
	
	@Autowired
	private CatalogoMarcaService catalogoMarcaService;
	
	/*
	 * MARCA
	 */
	@RequestMapping(value = "/marca-admin/save", method = RequestMethod.GET)
	public String getCatMarca(Model model) {

		log.info( "Entrando en marca-admin" );
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("item", new Proveedor());
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de marca-admin" );

		return "administrador/marca-admin";
	}
	
	@RequestMapping(value = "/marca-admin/save", method = RequestMethod.POST)
	public String postCatMarca( @ModelAttribute Proveedor item, BindingResult errors, Model model) {

		log.info( "Entrando en marca-admin para guardar usuario de marca" );
		catalogoMarcaService.registrarProveedorMarca(item);

		log.info( "Saliendo de marca-admin" );
		return "redirect:/portal-administrador/marca/list?id=1&type=1";
	}
	
	@RequestMapping(value = "/marca-admin/edit/{id}", method = RequestMethod.GET)
	public String getCatMarcaEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en GET editar proveedor" );
		Proveedor item = catalogoMarcaService.findProveedorById(Long.parseLong(id));
		model.addAttribute("item", item);
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de getCatMarcaEdit" );

		return "administrador/marca-admin";
	}
	//Si hay que eliminar la imagen de la marca agregar metodo sin file, agregar codigo y comentar el anterior
	@RequestMapping(value = "/marca-admin/edit/{id}", method = RequestMethod.POST)
	public String postCatMarcaEdit(@ModelAttribute Proveedor item, BindingResult errors, Model model, @PathVariable String id) {
		
		log.info( "Entrando en POST editar proveedor" );
		item.setId( Long.parseLong(id) );

		catalogoMarcaService.actualizaProveedorMarca(item);
		
		log.info( "Saliendo de postCatMarcaEdit" );
		
		return "redirect:/portal-administrador/marca/list?id="+id+"&type=1";
	}
	
	@RequestMapping (value="/marca-admin/delete/{id}", method=RequestMethod.GET)
	public String deleteMarca(Model model, @PathVariable String id) {
		log.info("Entrando a deleteProveedorrMarcas "+id);
		
		Proveedor item = new Proveedor();
		item.setId( Long.parseLong(id) );
		
		catalogoMarcaService.eliminaProveedorMarca(item);
		
		log.info("saliendo a deleteProveedorMarca "+id);
		return "redirect:/portal-administrador/marca/list?id="+id+"&type=0";
	}
	
	private UsuarioShingShingDetailService getAuthenticationUser() {
		UsuarioShingShingDetailService user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UsuarioShingShingDetailService) {
			user = (UsuarioShingShingDetailService) principal;
		}
		
		return user;
	}
}
