package com.bit.controllers.portal.administrador;
import java.io.IOException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.common.Utils;
import com.bit.model.Problema;
import com.bit.service.ProblemaService;

@Controller
@RequestMapping("/portal-administrador")
public class ProblemaController {
	@Autowired
	private ProblemaService problemaService;
	@RequestMapping(value = "/envia-problema", method = RequestMethod.POST)
	@ResponseBody
	public String guardaProblema(@RequestParam("problema") String problema){
		Problema prob=new Problema();
		prob.setProblema(problema);
		problemaService.guardaProblemas(prob);
		return problema;
	}
}
