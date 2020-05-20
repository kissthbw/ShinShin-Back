package com.bit.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import com.bit.dao.CatalogoMediosBonificacionDAO.MedioBonificacionID;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Usuario;
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.Category;
import com.bit.model.dto.Item;
import com.bit.model.dto.ResumenItem;
import com.bit.model.dto.TicketItem;
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

	@Autowired
	private TicketDAO ticketDAO;
	
	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;

	private static final String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
	
	private static Integer[] mediosFull = new Integer[] {MedioBonificacionID.BANCARIA.value(), 
			MedioBonificacionID.PAYPAL.value(), 
			MedioBonificacionID.RECARGA.value()};
	
	private static Integer[] mediosWithoutRecarga = new Integer[] {MedioBonificacionID.BANCARIA.value(), 
			MedioBonificacionID.PAYPAL.value()};
	
	private static Integer[] mediosOnlyRecarga = new Integer[] {
			MedioBonificacionID.RECARGA.value()};
	
	public final static String dia = "d";
	public final static String semana = "s";
	public final static String mes = "m";
	public final static String all = "all";
	
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
		Utils.initListaMensual(listaUsuariosMensuales, usuariosMensuales);
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
			Utils.initEscaneosPorMes(listaEscaneoTiendas, listaTmpEscaneoTiendas);

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

	@Override
	@Transactional
	public EstadisticasGeneralRSP obtieneEstadisticasGeneral() {

		log.info("Obteniendo informacion de estadisticas general");

		EstadisticasGeneralRSP rsp = new EstadisticasGeneralRSP();

		// Se debe determinar el anio y mes actual
		// para mostrar los usuarios registrados del anio y mes en curso
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		// Total de usuarios
		BigInteger total = usuarioDAO.obtieneTotalUsuarios();
		rsp.setTotalUsuarios(  null != total ? total.intValue() : 0);
		
		// Usuarios por mes
		List<Item> usuariosMensuales = usuarioDAO.obtieneUsuariosPorMesYAnio(2020);
		List<Item> listaUsuariosMensuales = new ArrayList<>();
		Utils.initListaMensual(listaUsuariosMensuales, usuariosMensuales);
		rsp.setTotalUsuariosMes(listaUsuariosMensuales);

		// Usuarios por semana
		List<Item> usuariosSemanales = usuarioDAO.obtieneUsuariosPorSemanaMesAnio(year, month);
		rsp.setTotalUsuariosSemana(usuariosSemanales);

		List<Item> usuariosDiarios = usuarioDAO.obtieneUsuariosPorDiaMesAnio(year, month);
		rsp.setTotalUsuariosDias(usuariosDiarios);

		//Tickets escaneados
		rsp.setTotalTicketsEscaneados( null != ticketDAO.obtieneTotalTickets() ? ticketDAO.obtieneTotalTickets().intValue()
				: 0);
		rsp.setTotalEscaneosDias( ticketDAO.obtieneTicketsPorDiaMesAnio(year, month) );
		rsp.setTotalEscaneosSemana( ticketDAO.obtieneTicketsPorSemanaMesAnio(year, month) );
		
		List<Item> ticketsPorMes = ticketDAO.obtieneTicketsPorMesAnio(year);
		List<Item> listaTicketsMensuales = new ArrayList<>();
		Utils.initEscaneosPorMes(listaTicketsMensuales, ticketsPorMes);
		rsp.setTotalEscaneosMes(listaTicketsMensuales);
		
		//Productos escaneados
		BigInteger totalProductos = productoDAO.obtieneTotalEscaneosProductos();
		rsp.setTotalProductosEscaneados( null != totalProductos ? totalProductos.intValue() : 0 );
		
		List<Item> escaneosPorDia = productoDAO.obtieneTotalEscaneosProductosPorDiaMesAnio(year, month);
		rsp.setTotalProductosEscaneadosDias(escaneosPorDia);
		
		List<Item> escaneosPorSemana = productoDAO.obtieneTotalEscaneosProductosPorSemanaMesAnio(year, month);
		rsp.setTotalProductosEscaneadosSemana(escaneosPorSemana);
		
		List<Item> escaneosPorMes = productoDAO.obtieneTotalEscaneosProductosPorMesAnio(year, month);
		List<Item> listaEscaneosMensuales = new ArrayList<>();
		Utils.initEscaneosPorMes(listaEscaneosMensuales, escaneosPorMes);
		rsp.setTotalProductosEscaneadosMes(listaEscaneosMensuales);
		
		//Tiendas
		List<CatalogoTienda> tiendas =  catalogoTiendaDAO.getCatalogoTienda();
		List<Category> listaTiendas = new ArrayList<Category>();
		for( CatalogoTienda t : tiendas ) {
			List<Item> listaTmpEscaneoTiendas = catalogoTiendaDAO.obtieneTotalEscaneosPorTiendaMesAnio(year, t.getNombreTienda());
			List<Item> listaEscaneoTiendas = new ArrayList<>();
			Utils.initEscaneosPorMes(listaEscaneoTiendas, listaTmpEscaneoTiendas);
			
			Category c = new Category( t.getNombreTienda(), listaEscaneoTiendas );
			
			listaTiendas.add( c );
		}
		rsp.setTotalEscaneaosTiendaMes(listaTiendas);
		
		
		//Departamentos
		BigInteger totalDepartamentos = catalogoTipoProductoDAO.obtieneDepartamentosRegistrados();
		rsp.setTotalDepartamentos( null != totalDepartamentos ? totalDepartamentos.intValue() : 0 );
		
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
			Utils.initEscaneosPorMes(listaEscaneoDeptos, listaTmpEscaneoDeptos);
			
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
			Utils.initEscaneosPorMes(listaEscaneoTiendas, listaTmpEscaneoTiendas);
			
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
	public EstadisticasRSP obtieneEstadisticasTickets() {
		log.info("Obteniendo informacion de estadisticas de tickets");
		
		EstadisticasRSP rsp = new EstadisticasRSP();
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		// Total de tickets
		BigInteger total = ticketDAO.obtieneTotalTickets();
		rsp.setTotalTickets(null != total ? total.intValue() : 0);
		
		//Tickets por mes
		List<Item> ticketsMensuales = ticketDAO.obtieneTicketsPorMesAnio(year);
		List<Item> listaTicketsMensual = new ArrayList<>();
		Utils.initEscaneosPorMes(listaTicketsMensual, ticketsMensuales);
		rsp.setTotalTicketsMes(listaTicketsMensual);
		
		//Tickets por semana
		List<Item> ticketsSemanales = ticketDAO.obtieneTicketsPorSemanaMesAnio(year, month);
		rsp.setTotalTicketsSemana(ticketsSemanales);
		
		//Tickets por dia
		List<Item> ticketsDiarios = ticketDAO.obtieneTicketsPorDiaMesAnio(year, month);
		rsp.setTotalTicketsDia(ticketsDiarios);
		
		
//		rsp.setTotalTicketsEscaneados( null != ticketDAO.obtieneTotalTickets() ? ticketDAO.obtieneTotalTickets().intValue()
//				: 0);
//		rsp.setTotalEscaneosDias( ticketDAO.obtieneTicketsPorDiaMesAnio(year, month) );
//		rsp.setTotalEscaneosSemana( ticketDAO.obtieneTicketsPorSemanaMesAnio(year, month) );
//		
//		List<Item> ticketsPorMes = ticketDAO.obtieneTicketsPorMesAnio(year);
//		List<Item> listaTicketsMensuales = new ArrayList<>();
//		initEscaneosPorMes(listaTicketsMensuales, ticketsPorMes);
//		rsp.setTotalEscaneosMes(listaTicketsMensuales);
		
		//Tickets por tienda mes
		List<Item> ticketsTiendaMesnuales = ticketDAO.obtieneTicketsPorTiendaMes(2020);
		List<Item> listaTicketsTiendaMensual= new ArrayList<>();
		Utils.initEscaneosPorMes(listaTicketsTiendaMensual, ticketsTiendaMesnuales);
		rsp.setTotalTicketsTiendaMesHora(ticketsTiendaMesnuales);
		
		//Tickest por tienda semana
		List<Item> ticketsTiendaSemanales = ticketDAO.obtieneTicketsPorTiendaSemana(year, month);
		rsp.setTotalTicketsTiendaSemanaHora(ticketsTiendaSemanales);
		
		//Ticket pot tienda dia
		List<Item> ticketsTiendaDiarios = ticketDAO.obtieneTicketsPorTiendaDia(year, month);
		rsp.setTotalTicketsTiendaDiaHora(ticketsTiendaDiarios);
		
		List<TicketItem> tickets = ticketDAO.obtieneHistoricoTickets();
		for( TicketItem t : tickets ) {
			t.setFechaFormateada( Utils.formatDateToString(t.getFecha(), "dd-MMM-yyyy") );
			t.setImporteFormateado( Utils.formatNumeros(t.getImporte(), "$###,###,###.00") );
		}
		rsp.setHistoricoTickets(tickets);
		
		return rsp;
	}

	@Override
	@Transactional
	public List<TicketItem> obtieneTicketsPorFecha(String date) {
		
		//La fecha esta en formato 19-dic-2019, debe pasarse a yyyy-MM-dd
		Date d = Utils.formatStringToDate(date, "dd-MMM-yyyy");
		String strDate = Utils.formatDateToString(d, "yyyy-MM-dd");
		
		List<TicketItem> list = ticketDAO.obtieneTicketsPorFecha( strDate );
		
		for( TicketItem t : list ) {
			t.setFechaFormateada( Utils.formatDateToString(t.getFecha(), "dd-MMM-yyyy") );
			t.setHoraFormateada( Utils.formatDateToString(t.getFecha(), "hh:mm:ss") );
			t.setImporteFormateado( Utils.formatNumeros(t.getImporte(), "$###,###,###.00") );
		}
		
		return list;
	}


	@Override
	@Transactional
	public List<TicketItem> obtieneDetalleTicketPorId(Integer idTicket) {
		List<TicketItem> list = ticketDAO.obtieneDetalleTicketPorId( idTicket );
		
		for( TicketItem t : list ) {
			t.setImporteFormateado( Utils.formatNumeros(t.getImporte(), "$###,###,###.00") );
		}
		
		return list;
	}

	@Override
	@Transactional
	public List<BonificacionItem> obtieneHistoricoBonificaciones( BonificacionItem item ) {
		List<BonificacionItem> list = historicoMediosBonificacionDAO.obtieneHistoricoBonificaciones( item );
		
		//Formatear fecha dd-MMM-yyyy
		//Formatear solicitudes y bonificaciones
		for (BonificacionItem b : list) {
			b.setFechaFormateada( Utils.formatDateToString(b.getFecha(), "dd-MMM-yyyy") );
			b.setImporteFormateado( Utils.formatNumeros(b.getImporte(), "$###,###,###.00") );
			b.setId( "R-" + MedioBonificacionID.getById( b.getIdTipo() ) + "-" + Utils.formatDateToString(b.getFecha(), "ddMMyyyy") );
		}
		
		return list;
	}


	@Override
	@Transactional
	public List<BonificacionItem> obtieneDetalleHistoricoBonificacionesPorFechaYTipo(BonificacionItem item, Integer[] tipos) {
		
		List<BonificacionItem> list = historicoMediosBonificacionDAO.obtieneDetalleHistoricoBonificaciones(item, tipos);
		
		for (BonificacionItem i : list) {
			i.setFechaFormateada( Utils.formatDateToString(i.getFecha(), "dd/MM/yyyy") );
			i.setHoraFormateada( Utils.formatDateToString(i.getHora(), "hh:mm:ss") );
			i.setImporteFormateado( Utils.formatNumeros(i.getImporte(), "$###,###,###.00") );
		}
		
		return list;
	}

	private List<Item> dummyDepositos(){
		List<Item> items = new ArrayList<>();
		Item i1 = new Item();
		i1.setTotal( BigInteger.valueOf( 5l ) );
		i1.setImporte( 100.0 );
		i1.setIndice( 1 );
		
		Item i2 = new Item();
		i2.setTotal( BigInteger.valueOf( 6l ) );
		i2.setImporte( 100.0 );
		i2.setIndice( 2 );
		
		Item i3 = new Item();
		i3.setTotal( BigInteger.valueOf( 7l ) );
		i3.setImporte( 100.0 );
		i3.setIndice( 3 );
		
		Item i4 = new Item();
		i4.setTotal( BigInteger.valueOf( 8l ) );
		i4.setImporte( 100.0 );
		i4.setIndice( 4 );
		
		Item i5 = new Item();
		i5.setTotal( BigInteger.valueOf( 9l ) );
		i5.setImporte( 100.0 );
		i5.setIndice( 5 );
		
		Item i6 = new Item();
		i6.setTotal( BigInteger.valueOf( 9l ) );
		i6.setImporte( 100.0 );
		i6.setIndice( 5 );
		
		Item i7 = new Item();
		i7.setTotal( BigInteger.valueOf( 9l ) );
		i7.setImporte( 100.0 );
		i7.setIndice( 5 );
		
		Item i8 = new Item();
		i8.setTotal( BigInteger.valueOf( 9l ) );
		i8.setImporte( 100.0 );
		i8.setIndice( 5 );
		
		Item i9 = new Item();
		i9.setTotal( BigInteger.valueOf( 9l ) );
		i9.setImporte( 100.0 );
		i9.setIndice( 5 );
		
		Item i10 = new Item();
		i10.setTotal( BigInteger.valueOf( 9l ) );
		i10.setImporte( 100.0 );
		i10.setIndice( 5 );
		
		Item i11 = new Item();
		i11.setTotal( BigInteger.valueOf( 9l ) );
		i11.setImporte( 100.0 );
		i11.setIndice( 5 );
		
		Item i12 = new Item();
		i12.setTotal( BigInteger.valueOf( 9l ) );
		i12.setImporte( 100.0 );
		i12.setIndice( 5 );
		
		items.add(i1);
		items.add(i2);
		items.add(i3);
		items.add(i4);
		items.add(i5);
		items.add(i6);
		items.add(i7);
		items.add(i8);
		items.add(i9);
		items.add(i10);
		items.add(i11);
		items.add(i12);
		
		return items;
	}

	@Override
	@Transactional
	public EstadisticasBonificacionRSP obtieneBonificacionesGenerales(String tipo, String categoria) {
		
		EstadisticasBonificacionRSP rsp = new EstadisticasBonificacionRSP();
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		
//		year = 2020;
//		month = 1;
//		day = 28;
		
		if ( null == tipo && null == categoria ) {
			BigInteger totalDepositos = historicoMediosBonificacionDAO.obtieneTotalDepositos();
			rsp.setTotalDepositos( null != totalDepositos ? totalDepositos.intValue() : 0 );
			
			Double totalBonificaciones = historicoMediosBonificacionDAO.obtieneTotalBonificaciones();
			rsp.setTotalBonificaciones( null != totalBonificaciones ? totalBonificaciones : 0.0 );
			
			BigInteger totalRecargas = historicoMediosBonificacionDAO.obtieneTotalRecargas();
			rsp.setTotalRecargas( null != totalRecargas ? totalRecargas.intValue() : 0 );

			
			List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, day, 
							mediosWithoutRecarga);
			rsp.setDepositos( list1 );
			
			List<Item> listBonificaciones = historicoMediosBonificacionDAO.obtieneBonificacionesPorTipoDiaMesAnio(year, month, day, 
							mediosFull);
			rsp.setBonificaciones(listBonificaciones);
			
			List<Item> listRecargas =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, day, 
							mediosOnlyRecarga);
			
			rsp.setRecargas(listRecargas);
		}
		else {
			if( "1".equals( tipo ) ){
				if( dia.equalsIgnoreCase( categoria ) ) {
					List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, day, 
							mediosWithoutRecarga);
					rsp.setDepositos(list1);
				}
				
				if( semana.equalsIgnoreCase( categoria ) ) {
					List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year, month, 
							mediosWithoutRecarga);
					Utils.initListaSemanal(list1);
					rsp.setDepositos(list1);
				}
				
				if( mes.equalsIgnoreCase( categoria ) ) {
					List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year, 
							mediosWithoutRecarga);
					List<Item> listaMensual = new ArrayList<>();
					Utils.initListaMensual(listaMensual, list1);
					rsp.setDepositos(listaMensual);
				}
				if( all.equalsIgnoreCase( categoria ) ) {
					List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, day, 
							mediosWithoutRecarga);
					rsp.setDepositos(list1);
					
					List<Item> list2 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year, month, 
							mediosWithoutRecarga);
					Utils.initListaSemanal(list2);
					rsp.setDepositos(list2);
					
					
					List<Item> list3 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year, 
							mediosWithoutRecarga);
					List<Item> listaMensual = new ArrayList<>();
					Utils.initListaMensual(listaMensual, list3);
					rsp.setDepositos(listaMensual);
				}
			}
			
			if( "2".equals( tipo ) ){
				if( dia.equalsIgnoreCase( categoria ) ) {
					List<Item> list1 =  historicoMediosBonificacionDAO.obtieneBonificacionesPorTipoDiaMesAnio(year, month, day, mediosFull);
					rsp.setBonificaciones(list1);
				}
				
				if( semana.equalsIgnoreCase( categoria ) ) {
					List<Item> list1 =  historicoMediosBonificacionDAO.obtieneBonificacionesPorTipoSemanaMesAnio(year, month, mediosFull);
					Utils.initListaSemanal(list1);
					rsp.setBonificaciones(list1);
				}
				
				if( mes.equalsIgnoreCase( categoria ) ) {
					List<Item> list1 =  historicoMediosBonificacionDAO.obtieneBonificacionesPorTipoMesAnio(year, mediosFull);
					List<Item> listaMensual = new ArrayList<>();
					initListaImporteMensual(listaMensual, list1);
					rsp.setBonificaciones(listaMensual);
				}
			}
			
			if( "3".equals( tipo ) ){
				if( dia.equalsIgnoreCase( categoria ) ) {
					List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, day, mediosOnlyRecarga);
					rsp.setRecargas(list1);
				}
				
				if( semana.equalsIgnoreCase( categoria ) ) {
					List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year, month, mediosOnlyRecarga);
					Utils.initListaSemanal(list1);
					rsp.setRecargas(list1);
				}
				
				if( mes.equalsIgnoreCase( categoria ) ) {
					List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year, mediosOnlyRecarga);
					List<Item> listaMensual = new ArrayList<>();
					Utils.initListaMensual(listaMensual, list1);
					rsp.setRecargas(listaMensual);
				}
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
	
	@Override
	@Transactional
	public List<BonificacionItem> obtieneHistoricoBonificacionesPorTipo( Integer[] tipos ){
		List<BonificacionItem> list = historicoMediosBonificacionDAO.obtieneHistoricoBonificacionesPorTipo(tipos);
		
		//Formatear fecha dd-MMM-yyyy
		//Formatear solicitudes y bonificaciones
		for (BonificacionItem item : list) {
			item.setFechaFormateada( Utils.formatDateToString(item.getFecha(), "dd-MMM-yyyy") );
			item.setImporteFormateado( Utils.formatNumeros(item.getImporte(), "$###,###,###.00") );
			item.setId( "RT-" + MedioBonificacionID.getById( item.getIdTipo() ) + "-" + Utils.formatDateToString(item.getFecha(), "ddMMyyyy") );
		}
		
		return list;
	}


	@Override
	@Transactional
	public List<Category> obtieneRecargasPorCompania( String categoria ) {
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		
//		year = 2020;
//		month = 1;
//		day = 28;
		
		//Agregar catalogo en BD
		String[] companias = {"Telcel", "ATT&T"};
		List<Category> list = new ArrayList<Category>();
		
		
		if( dia.equalsIgnoreCase( categoria ) ) {
			
			for( String com : companias ) {
				List<Item> listaTmpRecargas = historicoMediosBonificacionDAO.obtieneRecargasPorCompaniaDiaMesAnio(year, month, day, com);
				
				Category c = new Category( com, listaTmpRecargas );
				
				list.add( c );
			}
		}
		
		if( semana.equalsIgnoreCase( categoria ) ) {
			for( String com : companias ) {
				List<Item> listaTmpRecargas = historicoMediosBonificacionDAO.obtieneRecargasPorCompaniaSemanaMesAnio(year, month, com);
				List<Item> listaEscaneoDeptos = new ArrayList<>();
				Utils.initListaSemanal(listaTmpRecargas);
				Category c = new Category( com, listaTmpRecargas );
				
				list.add( c );
			}
		}
		
		if( mes.equalsIgnoreCase( categoria ) ) {
			for( String com : companias ) {
				List<Item> listaTmpRecargas = historicoMediosBonificacionDAO.obtieneRecargasPorCompaniaMesAnio(year, com);
				List<Item> listaEscaneoDeptos = new ArrayList<>();
				Utils.initEscaneosPorMes(listaEscaneoDeptos, listaTmpRecargas);
				
				Category c = new Category( com, listaEscaneoDeptos );
				
				list.add( c );
			}

		}

		
		return list;
	}
}
