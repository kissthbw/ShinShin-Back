package com.bit.controllers.portal.administrador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.model.dto.Item;
import com.bit.service.ReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/portal-administrador")
public class PanelEstadisticasController {

	private static final Logger log = LoggerFactory.getLogger(PanelEstadisticasController.class);

	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/bonificaciones-general/reporte", method = RequestMethod.GET)
	@ResponseBody
	public void getBonificacionesGeneralReport(Model model, HttpServletResponse response) throws JRException, IOException {
		InputStream jasperStream = this.getClass().getResourceAsStream("/Bonificaciones-general.jasper");
	    Map<String,Object> params = new HashMap<>();
	    
	    List<Item> list = reportService.getBonificacionesGeneralInfo();
	    
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=Bonificaciones-general.pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
	}
	/*
	@RequestMapping(value = "/bonificaciones-recargas/report", method = RequestMethod.GET)
	@ResponseBody
	public void getBonificacionesRecargasReport(Model model, HttpServletResponse response) throws JRException, IOException {
		InputStream jasperStream = this.getClass().getResourceAsStream("/Bonificaciones-recargas.jasper");
	    Map<String,Object> params = new HashMap<>();
	    
//	    List<Item> list = reportService.getBonificacionesGeneralInfo();
	    Map<String, List<Item>> result = reportService.getBonificacionesRecargasInfo();
	    
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource( result.get("Recargas") );
//	    JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource( result.get("Companias") );
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//	    JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport, params, dataSource2);

	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=Bonificaciones-recargas.pdf");
//	    response.setHeader("Content-disposition", "inline; filename=Bonificaciones-companias.pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
//	    JasperExportManager.exportReportToPdfStream(jasperPrint2, outStream);
	}*/
}
