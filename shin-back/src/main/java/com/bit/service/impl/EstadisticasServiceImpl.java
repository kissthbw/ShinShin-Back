package com.bit.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.dao.CatalogoMarcaDAO;
import com.bit.dao.CatalogoTiendaDAO;
import com.bit.dao.CatalogoTipoProductoDAO;
import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.dao.ProductoDAO;
import com.bit.dao.TicketDAO;
import com.bit.dao.UsuarioDAO;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Usuario;
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.Category;
import com.bit.model.dto.Item;
import com.bit.model.dto.ResumenItem;
import com.bit.model.dto.response.EstadisticasBonificacionRSP;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.EstadisticasRSP;
import com.bit.service.EstadisticasService;

@Service
public class EstadisticasServiceImpl implements EstadisticasService {

	private static final Logger log = LoggerFactory.getLogger(EstadisticasServiceImpl.class);

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired

	private ProductoDAO productoDAO;
	
	@Autowired
	private CatalogoTiendaDAO catalogoTiendaDAO;
	
	@Autowired
	private CatalogoTipoProductoDAO catalogoTipoProductoDAO; 
	
	@Autowired
	private CatalogoMarcaDAO catalogoMarcaDAO;

	private TicketDAO ticketDAO;
	
	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;

	private static final String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
	
	
	@Override
	@Transactional
	public EstadisticasRSP obtieneEstadisticasUsuarios() {

		log.info("Obteniendo informacion de estadisticas de usuarios");
		
		EstadisticasRSP rsp = new EstadisticasRSP();
		
		// Total de usuarios
		BigInteger total = usuarioDAO.obtieneTotalUsuarios();
		rsp.setTotalUsuarios(  null != total ? total.intValue() : 0);

		// Promedio de edad
		BigDecimal edad = usuarioDAO.obtienePromedioEdadUsuarios();
		rsp.setPromedioEdadUsuarios( null != edad ? edad.floatValue() : 0.0f );

		// Promedio de sexo
		List<Item> genero = usuarioDAO.obtieneGeneroUsuarios();
		rsp.setTotalUsuariosPorGenero(genero);
		
		//Promedio de edades
		List<Item> edades = usuarioDAO.obtieneRangoEdadUsuarios();
		rsp.setRangoEdadUsuarios(edades);
		
		
		// Usuarios por mes
		List<Item> usuariosMensuales = usuarioDAO.obtieneUsuariosPorMesYAnio(2020);
		List<Item> listaUsuariosMensuales = new ArrayList<>();
		initListaMensual(listaUsuariosMensuales, usuariosMensuales);
		rsp.setTotalUsuariosMes(listaUsuariosMensuales);
		
		// Usuarios por semana
		//Se debe determinar el anio y mes actual
		//para mostrar los usuarios registrados del anio y mes en curso
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		List<Item> usuariosSemanales = usuarioDAO.obtieneUsuariosPorSemanaMesAnio(year, month);
		rsp.setTotalUsuariosSemana(usuariosSemanales);
		
		List<Item> usuariosDiarios = usuarioDAO.obtieneUsuariosPorDiaMesAnio(year, month);
		rsp.setTotalUsuariosDias(usuariosDiarios);

		return rsp;
	}
	
	
	@Override
	@Transactional
	public EstadisticasGeneralRSP obtieneEstadisticasUsuarioDetalle( Usuario item ) {
		log.info("Obteniendo informacion de estadisticas de usuarios");

		EstadisticasGeneralRSP rsp = new EstadisticasGeneralRSP();

		LocalDate now = LocalDate.now();
		int year = now.getYear();
		
		// Tiendas
		List<CatalogoTienda> tiendas = catalogoTiendaDAO.getCatalogoTienda();
		List<Category> listaTiendas = new ArrayList<Category>();
		for (CatalogoTienda t : tiendas) {
			List<Item> listaTmpEscaneoTiendas = catalogoTiendaDAO.obtieneTotalEscaneosPorUsuarioTiendaMesAnio(item.getIdUsuario(), year,
					t.getNombreTienda());
			List<Item> listaEscaneoTiendas = new ArrayList<>();
			initEscaneosPorMes(listaEscaneoTiendas, listaTmpEscaneoTiendas);

			Category c = new Category(t.getNombreTienda(), listaEscaneoTiendas);

			listaTiendas.add(c);
		}
		rsp.setTotalEscaneaosTiendaMes(listaTiendas);
		
		//Top Marcas
		List<Item> topMarcas = catalogoMarcaDAO.obtieneTopMarcasEscaneadas(5, year, item.getIdUsuario());
		rsp.setListaTopMarcas(topMarcas);
		
		//Top Deptos 
		List<Item> topDeptos = catalogoTipoProductoDAO.obtieneTopDepartamentosEscaneados(5, year, item.getIdUsuario());
		rsp.setListaTopDeptos(topDeptos);

		return rsp;
	}
	
	private void initListaMensual(List<Item> list, List<Item> resultData) {
		
		//Se inicializa la lista con objetos Item, con los valores del mes
		for( String mes : meses ) {
			list.add( new Item(mes, BigInteger.valueOf(0)) );
		}
		
		//Se busca el elemento en la lista que coincida con el valor del campo indice
		//del resultData para asignar el total correspondiente
		for( Item data : resultData ) {
			Item i = list.get( data.getIndice() - 1 );
			
			if( null != i ) {
				i.setTotal( data.getTotal() );
			}
		}
	}

	@Override
	@Transactional
	public EstadisticasGeneralRSP obtieneEstadisticasGeneral() {

		log.info("Obteniendo informacion de estadisticas general");

		EstadisticasGeneralRSP rsp = new EstadisticasGeneralRSP();

		// Total de usuarios
		BigInteger total = usuarioDAO.obtieneTotalUsuarios();
		rsp.setTotalUsuarios(  null != total ? total.intValue() : 0);
		
		// Usuarios por mes
		List<Item> usuariosMensuales = usuarioDAO.obtieneUsuariosPorMesYAnio(2020);
		List<Item> listaUsuariosMensuales = new ArrayList<>();
		initListaMensual(listaUsuariosMensuales, usuariosMensuales);
		rsp.setTotalUsuariosMes(listaUsuariosMensuales);

		// Usuarios por semana
		// Se debe determinar el anio y mes actual
		// para mostrar los usuarios registrados del anio y mes en curso
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		List<Item> usuariosSemanales = usuarioDAO.obtieneUsuariosPorSemanaMesAnio(year, month);
		rsp.setTotalUsuariosSemana(usuariosSemanales);

		List<Item> usuariosDiarios = usuarioDAO.obtieneUsuariosPorDiaMesAnio(year, month);
		rsp.setTotalUsuariosDias(usuariosDiarios);

		//Tickets escaneados
		//TODO: Agregar DAO
		rsp.setTotalTicketsEscaneados( 0 );
		rsp.setTotalEscaneosDias( new ArrayList<Item>() );
		rsp.setTotalEscaneosSemana( new ArrayList<Item>() );
		rsp.setTotalEscaneosMes( new ArrayList<Item>() );
		
		//Productos escaneados
		BigInteger totalProductos = productoDAO.obtieneTotalEscaneosProductos();
		rsp.setTotalProductosEscaneados( null != totalProductos ? totalProductos.intValue() : 0 );
		
		List<Item> escaneosPorDia = productoDAO.obtieneTotalEscaneosProductosPorDiaMesAnio(year, month);
		rsp.setTotalProductosEscaneadosDias(escaneosPorDia);
		
		List<Item> escaneosPorSemana = productoDAO.obtieneTotalEscaneosProductosPorSemanaMesAnio(year, month);
		rsp.setTotalProductosEscaneadosSemana(escaneosPorSemana);
		
		List<Item> escaneosPorMes = productoDAO.obtieneTotalEscaneosProductosPorMesAnio(year, month);
		List<Item> listaEscaneosMensuales = new ArrayList<>();
		initEscaneosPorMes(listaEscaneosMensuales, escaneosPorMes);
		rsp.setTotalProductosEscaneadosMes(listaEscaneosMensuales);
		
		//Tiendas
		List<CatalogoTienda> tiendas =  catalogoTiendaDAO.getCatalogoTienda();
		List<Category> listaTiendas = new ArrayList<Category>();
		for( CatalogoTienda t : tiendas ) {
			List<Item> listaTmpEscaneoTiendas = catalogoTiendaDAO.obtieneTotalEscaneosPorTiendaMesAnio(year, t.getNombreTienda());
			List<Item> listaEscaneoTiendas = new ArrayList<>();
			initEscaneosPorMes(listaEscaneoTiendas, listaTmpEscaneoTiendas);
			
			Category c = new Category( t.getNombreTienda(), listaEscaneoTiendas );
			
			listaTiendas.add( c );
		}
		rsp.setTotalEscaneaosTiendaMes(listaTiendas);
		
		
		//Departamentos
		BigInteger totalDepartamentos = catalogoTipoProductoDAO.obtieneDepartamentosRegistrados();
		rsp.setTotalDepartamentos( null != totalDepartamentos ? totalDepartamentos.intValue() : 0 );
		
		return rsp;
	}
	
	private void initEscaneosPorMes(List<Item> list, List<Item> resultData) {
		
		//Se inicializa la lista con objetos Item, con los valores del mes
		for( String mes : meses ) {
			list.add( new Item(mes, BigInteger.valueOf(0)) );
		}
		
		//Se busca el elemento en la lista que coincida con el valor del campo indice
		//del resultData para asignar el total correspondiente
		for( Item data : resultData ) {
			Item i = list.get( data.getIndice() - 1 );
			
			if( null != i ) {
				i.setTotal( data.getTotal() );
			}
		}
	}

	
	@Override
	@Transactional
	public EstadisticasRSP obtieneEstadisticasTickets() {
		
		log.info("Obteniendo informacion de estadisticas de tickets");
		
		EstadisticasRSP rsp = new EstadisticasRSP();
		
		// Total de tickets
		BigInteger total = ticketDAO.obtieneTotalTickets();
		rsp.setTotalTickets(null != total ? total.intValue() : 0);
		
		return rsp;
	}

	@Override
	@Transactional
	public EstadisticasGeneralRSP obtieneEstadisticasMarcas() {
		
		log.info("Obteniendo informacion de estadisticas general");

		EstadisticasGeneralRSP rsp = new EstadisticasGeneralRSP();
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		//Total de marcas.
		BigInteger totalMarcas = catalogoMarcaDAO.obtieneTotalMarcas();
		rsp.setTotalMarcas( null != totalMarcas ? totalMarcas.intValue() : 0 );
		
		//Total de productos.
		BigInteger totalProductos = catalogoMarcaDAO.obtieneTotalProductos();
		rsp.setTotalProductos( null != totalProductos ? totalProductos.intValue() : 0 );
		
		//Escaneos totales.
		BigInteger totalEscaneos = catalogoMarcaDAO.obtieneTotalTicketsEscaneados();
		rsp.setTotalProductosEscaneados( null != totalEscaneos ? totalEscaneos.intValue() : 0 );
		
		//Info para la graficas
		//Departamentos.
		List<CatalogoTipoProducto> deptos =  catalogoTipoProductoDAO.getCatalogoTipoProductos();
		List<Category> listaDeptos = new ArrayList<Category>();
		for( CatalogoTipoProducto d : deptos ) {
			List<Item> listaTmpEscaneoDeptos = catalogoTipoProductoDAO.obtieneTotalEscaneosPorTiendaDepartamentoAnio(year, d.getNombreTipoProducto());
			List<Item> listaEscaneoDeptos = new ArrayList<>();
			initEscaneosPorMes(listaEscaneoDeptos, listaTmpEscaneoDeptos);
			
			Category c = new Category( d.getNombreTipoProducto(), listaEscaneoDeptos );
			
			listaDeptos.add( c );
		}
		rsp.setTotalEscaneaosDepartamentoMes(listaDeptos);
		
		//Tiendas.
		List<CatalogoTienda> tiendas =  catalogoTiendaDAO.getCatalogoTienda();
		List<Category> listaTiendas = new ArrayList<Category>();
		for( CatalogoTienda t : tiendas ) {
			List<Item> listaTmpEscaneoTiendas = catalogoTiendaDAO.obtieneTotalEscaneosPorTiendaMesAnio(year, t.getNombreTienda());
			List<Item> listaEscaneoTiendas = new ArrayList<>();
			initEscaneosPorMes(listaEscaneoTiendas, listaTmpEscaneoTiendas);
			
			Category c = new Category( t.getNombreTienda(), listaEscaneoTiendas );
			
			listaTiendas.add( c );
		}
		rsp.setTotalEscaneaosTiendaMes(listaTiendas);
		
		//Departamentos: total de productos, escaneos, bonificaciones
		List<ResumenItem> listaResumenDepartamentos = catalogoTipoProductoDAO.obtieneResumenDepartamento();
		rsp.setListaResumenDepartamentos(listaResumenDepartamentos);
		
		//Tiendas: total de productos, escaneos, bonificaciones
		List<ResumenItem> listaResumenTiendas = catalogoTiendaDAO.obtieneResumenTiendas();
		rsp.setListaResumenTiendas(listaResumenTiendas);
		
		return rsp;
	}


	@Override
	@Transactional
	public List<BonificacionItem> obtieneHistoricoBonificaciones() {
		List<BonificacionItem> list = historicoMediosBonificacionDAO.obtieneHistoricoBonificaciones();
		
		//Formatear fecha dd-MMM-yyyy
		//Formatear solicitudes y bonificaciones
		for (BonificacionItem item : list) {
			item.setFechaFormateada( Utils.formatFecha(item.getFecha(), "dd-MMM-yyyy") );
			item.setImporteFormateado( Utils.formatNumeros(item.getImporte(), "$###,###,###.00") );
		}
		
		return list;
	}


	@Override
	@Transactional
	public List<BonificacionItem> obtieneDetalleHistoricoBonificacionesPorFechaYTipo(BonificacionItem item) {
		
		List<BonificacionItem> list = historicoMediosBonificacionDAO.obtieneDetalleHistoricoBonificaciones(item);
		
		for (BonificacionItem i : list) {
			i.setFechaFormateada( Utils.formatFecha(i.getFecha(), "dd-MMM-yyyy") );
			i.setHoraFormateada( Utils.formatFecha(i.getHora(), "hh:mm:ss") );
			i.setImporteFormateado( Utils.formatNumeros(i.getImporte(), "$###,###,###.00") );
		}
		
		return list;
	}


	@Override
	@Transactional
	public EstadisticasBonificacionRSP obtieneBonificacionesGenerales(String tipo, String categoria) {
		
		EstadisticasBonificacionRSP rsp = new EstadisticasBonificacionRSP();
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		if ( null == tipo && null == categoria ) {
			BigInteger totalDepositos = historicoMediosBonificacionDAO.obtieneTotalDepositos();
			rsp.setTotalDepositos( null != totalDepositos ? totalDepositos.intValue() : 0 );
			
			Double totalBonificaciones = historicoMediosBonificacionDAO.obtieneTotalBonificaciones();
			rsp.setTotalBonificaciones( null != totalBonificaciones ? totalBonificaciones : 0.0 );
			
			BigInteger totalRecargas = historicoMediosBonificacionDAO.obtieneTotalRecargas();
			rsp.setTotalRecargas( null != totalRecargas ? totalRecargas.intValue() : 0 );

			
			List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, new Integer[] {1, 2});
			rsp.setDepositos(list1);
			
			List<Item> listBonificaciones = historicoMediosBonificacionDAO.obtieneBonificacionesPorTipoDiaMesAnio(year, month, new Integer[] {1, 2, 3});
			rsp.setBonificaciones(listBonificaciones);
			
			List<Item> listRecargas =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, new Integer[] {3});
			rsp.setRecargas(listRecargas);
		}
		
		if( "1".equals( tipo ) ){
			if( "d".equalsIgnoreCase( categoria ) ) {
				List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, new Integer[] {1, 2});
				rsp.setDepositos(list1);
			}
			
			if( "s".equalsIgnoreCase( categoria ) ) {
				List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year, month, new Integer[] {1, 2});
				rsp.setDepositos(list1);
			}
			
			if( "m".equalsIgnoreCase( categoria ) ) {
				List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year, new Integer[] {1, 2});
				List<Item> listaMensual = new ArrayList<>();
				initListaMensual(listaMensual, list1);
				rsp.setDepositos(listaMensual);
			}
		}
		
		if( "2".equals( tipo ) ){
			if( "d".equalsIgnoreCase( categoria ) ) {
				List<Item> list1 =  historicoMediosBonificacionDAO.obtieneBonificacionesPorTipoDiaMesAnio(year, month, new Integer[] {1, 2, 3});
				rsp.setBonificaciones(list1);
			}
			
			if( "s".equalsIgnoreCase( categoria ) ) {
				List<Item> list1 =  historicoMediosBonificacionDAO.obtieneBonificacionesPorTipoSemanaMesAnio(year, month, new Integer[] {1, 2, 3});
				rsp.setBonificaciones(list1);
			}
			
			if( "m".equalsIgnoreCase( categoria ) ) {
				List<Item> list1 =  historicoMediosBonificacionDAO.obtieneBonificacionesPorTipoMesAnio(year, new Integer[] {1, 2, 3});
				List<Item> listaMensual = new ArrayList<>();
				initListaImporteMensual(listaMensual, list1);
				rsp.setBonificaciones(listaMensual);
			}
		}
		
		if( "3".equals( tipo ) ){
			if( "d".equalsIgnoreCase( categoria ) ) {
				List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, new Integer[] {3});
				rsp.setRecargas(list1);
			}
			
			if( "s".equalsIgnoreCase( categoria ) ) {
				List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year, month, new Integer[] {3});
				rsp.setRecargas(list1);
			}
			
			if( "m".equalsIgnoreCase( categoria ) ) {
				List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year, new Integer[] {3});
				List<Item> listaMensual = new ArrayList<>();
				initListaMensual(listaMensual, list1);
				rsp.setRecargas(listaMensual);
			}
		}
		
		return rsp;
	}
	
	private void initListaImporteMensual(List<Item> list, List<Item> resultData) {
		
		//Se inicializa la lista con objetos Item, con los valores del mes
		for( String mes : meses ) {
			list.add( new Item(mes, BigInteger.valueOf(0)) );
		}
		
		//Se busca el elemento en la lista que coincida con el valor del campo indice
		//del resultData para asignar el total correspondiente
		for( Item data : resultData ) {
			Item i = list.get( data.getIndice() - 1 );
			
			if( null != i ) {
				i.setImporte( data.getImporte() );
			}
		}
	}
}
