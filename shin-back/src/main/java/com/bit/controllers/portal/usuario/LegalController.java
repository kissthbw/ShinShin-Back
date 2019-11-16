package com.bit.controllers.portal.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/")
public class LegalController {
	
	

	@RequestMapping(value = "/legal", method = RequestMethod.GET)
	public String redireccionaLogin() {
		
		return "aviso";
	}
	
	
}