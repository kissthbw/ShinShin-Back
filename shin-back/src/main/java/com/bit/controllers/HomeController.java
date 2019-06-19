package com.bit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.model.CatalogoMediosBonificacion;
import com.bit.service.CatalogoMediosBonificacionService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private CatalogoMediosBonificacionService catalogoMediosBonificacionService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "redirect:/medio/save";
	}
	
	@RequestMapping(value="/medio/save", method = RequestMethod.GET)
	public String redireccionaMedio(Model model) {
		model.addAttribute("item", new CatalogoMediosBonificacion());
		return "index";
//		return "redirect:/upload/textDetect";
	}
	
	@RequestMapping(value = "/medio/save", method = RequestMethod.POST)
    public String saveMedio(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors, Model model) {
		
		System.out.println( item.getNombreMedioBonificacion() );
		catalogoMediosBonificacionService.registrarCatalogoMediosBonificacion(item);
		
		return "redirect:/medio/save";
    }
	
	@RequestMapping(value="test", method = RequestMethod.POST)
	public String test(@RequestParam("txtID") String txtID, Model model) {
		System.out.println( txtID );
		model.addAttribute("item", new CatalogoMediosBonificacion());
		return "index";
	}
	
//	@RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
//    public String saveStudent(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors, Model model) {
//		
//		System.out.println( item.getNombreMedioBonificacion() );
//		
//		return "index";
//    }
	
	
}