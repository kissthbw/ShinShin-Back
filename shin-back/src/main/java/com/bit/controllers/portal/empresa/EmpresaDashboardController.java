package com.bit.controllers.portal.empresa;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.model.Proveedor;
import com.bit.model.Usuario;
import com.bit.model.dto.response.InformacionDashboardProveedorRSP;
import com.bit.service.CSVExporter;
import com.bit.service.ProveedorDetailService;
import com.bit.service.ProveedorService;
import com.bit.service.impl.CSVExporterImpl;
import com.bit.service.impl.ProveedorServiceImpl;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping(value="/portal-empresa")
public class EmpresaDashboardController {
	
	private static final Logger log = LoggerFactory.getLogger(EmpresaDashboardController.class);
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping(value="/dashboard")
	public String dasdboard(Model model, 
			@ModelAttribute("currentUser") Usuario currentUser) {
		
		ProveedorDetailService current = getAuthenticationUser();
		
		if (null != current) {
			Proveedor p = current.getUsuario();
			
			log.info( p.getMarca().getNombreMarca() );
			
			model.addAttribute("item", p);
			
			InformacionDashboardProveedorRSP rsp = proveedorService.obtieneTotalesDashboard( p );
			
			model.addAttribute("info", rsp);
		}
		
		//Se obtiene la siguiente informacion:
		//Dinero que ellos han dado para las bonificaciones de los productos, por ejemplo:
		//hoy escaneron 10 aguas y por agua es $1, entonces debería pintar miércoles una barra diciendo $10		
		
		return "empresa_dashboard";
	}
	
	@RequestMapping(value = "/dashboard/report", method = RequestMethod.GET)
	public void reportDasdboardEmpresa(Model model, HttpServletResponse response) throws JRException, IOException {
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                "general-proveedor.csv");
        response.setHeader(headerKey, headerValue);
		
        ProveedorDetailService current = getAuthenticationUser();
		
		if (null != current) {
			Proveedor p = current.getUsuario();
			
			List<List<Object>> rows = proveedorService.obtieneInfoReporteEmpresaGeneral(p);

			CSVExporter csv = new CSVExporterImpl();
			
			String [] headers = {"Id", "Producto", "Importe"};
			
			try {
				csv.writeCSV(response.getWriter(), headers, rows);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
//		List<List<Object>> rows = bonificacionesService.obtieneInfoReporteBonificacionesDepositosGeneral();
		
		
	}
	
	/*
	 * Dashboad de finanzas
	 */
	@GetMapping(value="/finanzas")
	public String finanzas(Model model, 
			@ModelAttribute("currentUser") Usuario currentUser) {
		
		ProveedorDetailService current = getAuthenticationUser();
		
		if (null != current) {
			Proveedor p = current.getUsuario();
			model.addAttribute("item", p);
			
			InformacionDashboardProveedorRSP rsp = proveedorService.obtieneTotalesDashboardFinanzasProductos(p);
			
			model.addAttribute("info", rsp);
		}
		
		return "empresa_finanzas";
		
	}
	
	@RequestMapping(value = "/finanzas/report", method = RequestMethod.GET)
	public void reportDasdboardEmpresaFinanzas(Model model, HttpServletResponse response) throws JRException, IOException {
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                "general-proveedor-finanzas.csv");
        response.setHeader(headerKey, headerValue);
		
        ProveedorDetailService current = getAuthenticationUser();
		
		if (null != current) {
			Proveedor p = current.getUsuario();
			
			List<List<Object>> rows = proveedorService.obtieneInfoReporteEmpresaProductos(p);

			CSVExporter csv = new CSVExporterImpl();
			
			String [] headers = {"Id", "Fecha", "Bonificacion"};
			
			try {
				csv.writeCSV(response.getWriter(), headers, rows);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Dashboard de productos
	 */
	@GetMapping(value="/productos")
	public String productos(Model model, 
			@ModelAttribute("currentUser") Usuario currentUser) {
		
		ProveedorDetailService current = getAuthenticationUser();
		
		if (null != current) {
			Proveedor p = current.getUsuario();
			model.addAttribute("item", p);
			
			InformacionDashboardProveedorRSP rsp = proveedorService.obtieneTotalesDashboardEmpresaProductos(p);
			
			model.addAttribute("info", rsp);
		}
		
		return "productos_empresa";
	}
	
	@RequestMapping(value = "/productos/report", method = RequestMethod.GET)
	public void reportDasdboardEmpresaProductos(Model model, HttpServletResponse response) throws JRException, IOException {
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                "general-proveedor-productos.csv");
        response.setHeader(headerKey, headerValue);
		
        ProveedorDetailService current = getAuthenticationUser();
		
		if (null != current) {
			Proveedor p = current.getUsuario();
			
			List<List<Object>> rows = proveedorService.obtieneInfoReporteEmpresaProductos(p);

			CSVExporter csv = new CSVExporterImpl();
			
			String [] headers = {"Id", "Nombre", "Bonificacion", "Escaneos"};
			
			try {
				csv.writeCSV(response.getWriter(), headers, rows);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Dashboard de usuarios
	 */
	
	@GetMapping(value="/usuarios")
	public String usuarios(Model model, 
			@ModelAttribute("currentUser") Usuario currentUser) {
		
		ProveedorDetailService current = getAuthenticationUser();
		
		if (null != current) {
			Proveedor p = current.getUsuario();
			model.addAttribute("item", p);
			
			InformacionDashboardProveedorRSP rsp = proveedorService.obtieneTotalesDashboardEmpresaUsuarios(p);
			
			model.addAttribute("info", rsp);
		}
		
		return "empresa_usuarios";
	}
	
	@RequestMapping(value = "/usuarios/report", method = RequestMethod.GET)
	public void reportDasdboardEmpresaUSuarios(Model model, HttpServletResponse response) throws JRException, IOException {
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                "general-proveedor-usuarios.csv");
        response.setHeader(headerKey, headerValue);
		
        ProveedorDetailService current = getAuthenticationUser();
		
		if (null != current) {
			Proveedor p = current.getUsuario();
			
			List<List<Object>> rows = proveedorService.obtieneInfoReporteEmpresaUsuarios(p);

			CSVExporter csv = new CSVExporterImpl();
			
			String [] headers = {"Id", "Usuario", "Edad", "Sexo", "Escaneos"};
			
			try {
				csv.writeCSV(response.getWriter(), headers, rows);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@GetMapping(value="/usuarios/informacion/{id}")
	public String usuariosEdit(Model model, 
			@ModelAttribute("currentUser") Usuario currentUser,@PathVariable String id) {
		
		ProveedorDetailService current = getAuthenticationUser();
		
		if (null != current) {
			Proveedor p = current.getUsuario();
			model.addAttribute("item", p);
			
		
		}
		
		return "empresa_usuarios_info";
	}

	private ProveedorDetailService getAuthenticationUser() {
		ProveedorDetailService user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof ProveedorDetailService) {
			user = (ProveedorDetailService) principal;
		}

		return user;
	}
}
