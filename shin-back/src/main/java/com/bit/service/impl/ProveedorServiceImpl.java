package com.bit.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.dao.ProveedorDAO;
import com.bit.dao.UsuarioDAO;
import com.bit.model.Producto;
import com.bit.model.Proveedor;
import com.bit.model.Usuario;
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.Item;
import com.bit.model.dto.ProductoItem;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.InformacionDashboardProveedorRSP;
import com.bit.service.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService {
	
	private static final Logger log = LoggerFactory.getLogger(ProveedorServiceImpl.class);

	@Autowired
	private ProveedorDAO proveedorDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	@Transactional
	public InformacionDashboardProveedorRSP obtieneTotalesDashboard(Proveedor item) {
		
		InformacionDashboardProveedorRSP rsp = new InformacionDashboardProveedorRSP();
		rsp.setCode( 200 );
		rsp.setMessage( "Exito" );
		
		log.info( "Obtiene totales del proveedor de la marca: {}", item.getMarca().getIdCatalogoMarca() );
		
		Double totalBonificaciones = proveedorDAO.obtieneTotalBonificacionesPorMarca( item.getMarca().getIdCatalogoMarca() );
		BigInteger totalProductosEscaneados = proveedorDAO.obtieneTotalProductosEscaneadosPorMarca( item.getMarca().getIdCatalogoMarca() );
		BigInteger total = usuarioDAO.obtieneTotalUsuarios();
		
		rsp.setTotalBonificaciones( null != totalBonificaciones ? totalBonificaciones : 0.0 );
		rsp.setTotalProductos( null != totalProductosEscaneados ? totalProductosEscaneados.longValue() : 0L );
		rsp.setTotalUsuarios(  null != total ? total.longValue() : 0L);
//		BigInteger totalProductos = productoDAO.obtieneTotalEscaneosProductos();
//		rsp.setTickets( usuarioDAO.calculaTicketsTotales(item).longValue() );
//		rsp.setRetiros( usuarioDAO.calculaBanoficacionesTotales(item).longValue() );
//		rsp.setMedios( usuarioDAO.calculaMediosBonificacionTotales(item).longValue() );
		
		return rsp;
	}

	@Override
	@Transactional
	public EstadisticasGeneralRSP obtieneEstadisticasEmpresaGeneral( Proveedor item, String tipo, String categoria ) {
		
		EstadisticasGeneralRSP rsp = new EstadisticasGeneralRSP();
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		if ( null == tipo && null == categoria ) {
			List<Item> usuariosDiarios = usuarioDAO.obtieneUsuariosPorDiaMesAnio(year, month);
			List<Item> escaneosPorDia = proveedorDAO.obtieneTotalEscaneosMarcaProductosPorDiaMesAnio(year, month, item.getId());
			List<Item> bonificacionesPorDia = proveedorDAO.obtieneTotalBonificacionesMarcaProductosPorDiaMesAnio(year, month, item.getId());
			
			rsp.setTotalUsuariosDias(usuariosDiarios);
			rsp.setTotalEscaneosDias(escaneosPorDia);
			rsp.setTotalBonificacionesDias(bonificacionesPorDia);
		}
		else {
			//tipo, null si se requiere obtener consulta inicial, 1 para bonificaciones, 2 para para productos, 3 para usuarios
			if ( tipo.equals( "1" )  ) {
				switch (categoria) {
				case "d":
					List<Item> bonificacionesPorDia = proveedorDAO.obtieneTotalBonificacionesMarcaProductosPorDiaMesAnio(year, month, item.getId());
					rsp.setTotalBonificacionesDias(bonificacionesPorDia);
					break;
				case "s":
					List<Item> bonificacionesPorSemana = proveedorDAO.obtieneTotalBonificacionesMarcaProductosPorSemanaMesAnio(year, month, item.getId());
					rsp.setTotalBonificacionesSemana(bonificacionesPorSemana);
					break;
				case "m":
					List<Item> bonificacionesPorMes = proveedorDAO.obtieneTotalBonificacionesMarcaProductosPorMesAnio(year, item.getId());
					List<Item> listaBonificacionesMensuales = new ArrayList<>();
					Utils.initEscaneosPorMes(listaBonificacionesMensuales, bonificacionesPorMes);
					rsp.setTotalBonificacionesMes(listaBonificacionesMensuales);
					break;
				default:
					bonificacionesPorMes = proveedorDAO.obtieneTotalBonificacionesMarcaProductosPorMesAnio(year, item.getId());
					listaBonificacionesMensuales = new ArrayList<>();
					Utils.initEscaneosPorMes(listaBonificacionesMensuales, bonificacionesPorMes);
					rsp.setTotalBonificacionesMes(listaBonificacionesMensuales);
					break;
				}
			}
			
			if ( tipo.equals( "2" ) ) {
				switch (categoria) {
				case "d":
					List<Item> escaneosPorDia = proveedorDAO.obtieneTotalEscaneosMarcaProductosPorDiaMesAnio(year, month, item.getId());
					rsp.setTotalEscaneosDias(escaneosPorDia);
					break;
				case "s":
					List<Item> escaneosPorSemana = proveedorDAO.obtieneTotalEscaneosMarcaProductosPorSemanaMesAnio(year, month, item.getId());
					rsp.setTotalEscaneosSemana(escaneosPorSemana);
					break;
				case "m":
					List<Item> escaneosPorMes = proveedorDAO.obtieneTotalEscaneosMarcaProductosPorMesAnio(year, item.getId());
					List<Item> listaEscaneosMensuales = new ArrayList<>();
					Utils.initEscaneosPorMes(listaEscaneosMensuales, escaneosPorMes);
					rsp.setTotalEscaneosMes(listaEscaneosMensuales);
					break;
				default:
					escaneosPorMes = proveedorDAO.obtieneTotalEscaneosMarcaProductosPorMesAnio(year, item.getId());
					listaEscaneosMensuales = new ArrayList<>();
					Utils.initEscaneosPorMes(listaEscaneosMensuales, escaneosPorMes);
					rsp.setTotalEscaneosMes(listaEscaneosMensuales);
					break;
				}
			}
			
			if ( tipo.equals( "3" ) ) {
				switch (categoria) {
				case "d":
					List<Item> usuariosDiarios = usuarioDAO.obtieneUsuariosPorDiaMesAnio(year, month);
					rsp.setTotalUsuariosDias(usuariosDiarios);
					break;
				case "s":
					List<Item> usuariosSemanales = usuarioDAO.obtieneUsuariosPorSemanaMesAnio(year, month);
					rsp.setTotalUsuariosSemana(usuariosSemanales);
					break;
				case "m":
					List<Item> usuariosMensuales = usuarioDAO.obtieneUsuariosPorMesYAnio(year);
					List<Item> listaUsuariosMensuales = new ArrayList<>();
					Utils.initListaMensual(listaUsuariosMensuales, usuariosMensuales);
					rsp.setTotalUsuariosMes(listaUsuariosMensuales);
					break;
				default:
					usuariosMensuales = usuarioDAO.obtieneUsuariosPorMesYAnio(year);
					listaUsuariosMensuales = new ArrayList<>();
					Utils.initListaMensual(listaUsuariosMensuales, usuariosMensuales);
					rsp.setTotalUsuariosMes(listaUsuariosMensuales);
					break;
				}
			}
		}
		
//		/*
//		 * Usuarios
//		 */
//		List<Item> usuariosDiarios = usuarioDAO.obtieneUsuariosPorDiaMesAnio(year, month);
//		List<Item> usuariosSemanales = usuarioDAO.obtieneUsuariosPorSemanaMesAnio(year, month);
//		List<Item> usuariosMensuales = usuarioDAO.obtieneUsuariosPorMesYAnio(year);
//		List<Item> listaUsuariosMensuales = new ArrayList<>();
//		Utils.initListaMensual(listaUsuariosMensuales, usuariosMensuales);
//
//		rsp.setTotalUsuariosDias(usuariosDiarios);
//		rsp.setTotalUsuariosSemana(usuariosSemanales);
//		rsp.setTotalUsuariosMes(listaUsuariosMensuales);
//		
//		/*
//		 * Productos escaneados
//		 */
//		List<Item> escaneosPorDia = proveedorDAO.obtieneTotalEscaneosMarcaProductosPorDiaMesAnio(year, month, item.getId());
//		List<Item> escaneosPorSemana = proveedorDAO.obtieneTotalEscaneosMarcaProductosPorSemanaMesAnio(year, month, item.getId());
//		List<Item> escaneosPorMes = proveedorDAO.obtieneTotalEscaneosMarcaProductosPorMesAnio(year, item.getId());
//		List<Item> listaEscaneosMensuales = new ArrayList<>();
//		Utils.initEscaneosPorMes(listaEscaneosMensuales, escaneosPorMes);
//		
//		rsp.setTotalEscaneosDias(escaneosPorDia);
//		rsp.setTotalEscaneosSemana(escaneosPorSemana);
//		rsp.setTotalEscaneosMes(listaEscaneosMensuales);
//		
//		/*
//		 * Bonificaciones
//		 */
//		List<Item> bonificacionesPorDia = proveedorDAO.obtieneTotalBonificacionesMarcaProductosPorDiaMesAnio(year, month, item.getId());
//		List<Item> bonificacionesPorSemana = proveedorDAO.obtieneTotalBonificacionesMarcaProductosPorSemanaMesAnio(year, month, item.getId());
//		List<Item> bonificacionesPorMes = proveedorDAO.obtieneTotalBonificacionesMarcaProductosPorMesAnio(year, item.getId());
//		List<Item> listaBonificacionesMensuales = new ArrayList<>();
//		Utils.initEscaneosPorMes(listaBonificacionesMensuales, bonificacionesPorMes);
//		
//		rsp.setTotalBonificacionesDias(bonificacionesPorDia);
//		rsp.setTotalBonificacionesSemana(bonificacionesPorSemana);
//		rsp.setTotalBonificacionesMes(listaBonificacionesMensuales);
		
		return rsp;
		
		/*
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
		*/
	}

	@Override
	@Transactional
	public List<List<Object>> obtieneInfoReporteEmpresaGeneral( Proveedor item ){
		List<List<Object>> rows = new ArrayList<>();
		
//		List<BonificacionItem> list = historicoMediosBonificacionDAO.obtieneHistoricoBonificaciones( null );
		List<ProductoItem> list = proveedorDAO.obtieneInfoReporteEmpresaGeneral( item.getMarca().getIdCatalogoMarca() );
		
		//Formatear fecha dd-MMM-yyyy
		//Formatear solicitudes y bonificaciones
		for (ProductoItem b : list) {
			b.setFechaFormateada( Utils.formatDateToString(b.getFecha(), "dd-MMM-yyyy") );
			b.setBonificacionFormateada( Utils.formatNumeros(b.getBonificacion(), "$###,###,###.00") );
		}
		
		for( ProductoItem i : list ) {
			rows.add( Arrays.asList( new Object[] {
					i.getId(),
					i.getNombre(), 
					i.getBonificacionFormateada()
					} ) );
		}

		
		return rows;
	}

	
	/*
	 * Dashboard empresa - Finanzas -
	 */
	@Override
	@Transactional
	public InformacionDashboardProveedorRSP obtieneTotalesDashboardFinanzasProductos(Proveedor item) {
		InformacionDashboardProveedorRSP rsp = new InformacionDashboardProveedorRSP();
		
		Double totalBonificaciones = proveedorDAO.obtieneTotalBonificacionesPorMarca( item.getMarca().getIdCatalogoMarca() );
		List<BonificacionItem> list = proveedorDAO.obtieneListaBonificacionesEmpresa( item.getMarca().getIdCatalogoMarca() );
		
		for (BonificacionItem b : list) {
			b.setFechaFormateada( Utils.formatDateToString(b.getFecha(), "dd-MMM-yyyy") );
			b.setImporteFormateado( Utils.formatNumeros(b.getImporte(), "$###,###,###.00") );
		}
		
		rsp.setTotalBonificaciones( null != totalBonificaciones ? totalBonificaciones : 0.0 );
		rsp.setBonificaciones(list);
		
		return rsp;
	}

	@Override
	@Transactional
	public List<List<Object>> obtieneInfoReporteEmpresaFinanzas(Proveedor item) {
		List<List<Object>> rows = new ArrayList<>();
		
		List<BonificacionItem> list = proveedorDAO.obtieneListaBonificacionesEmpresa( item.getMarca().getIdCatalogoMarca() );
		
		//Formatear fecha dd-MMM-yyyy
		//Formatear solicitudes y bonificaciones
		for (BonificacionItem b : list) {
			b.setFechaFormateada( Utils.formatDateToString(b.getFecha(), "dd-MMM-yyyy") );
			b.setImporteFormateado( Utils.formatNumeros(b.getImporte(), "$###,###,###.00") );
		}
		
		for( BonificacionItem i : list ) {
			rows.add( Arrays.asList( new Object[] {
					i.getId(),
					i.getFechaFormateada(),
					i.getImporteFormateado(), 
					} ) );
		}

		
		return rows;
	}
	
	
	/*
	 * Dashboard empresa - Productos -
	 */
	
	@Override
	@Transactional
	public InformacionDashboardProveedorRSP obtieneTotalesDashboardEmpresaProductos(Proveedor item) {
		
		InformacionDashboardProveedorRSP rsp = new InformacionDashboardProveedorRSP();
		
		BigInteger totalEscaneados = proveedorDAO.obtieneTotalProductosEscaneadosPorMarca( item.getMarca().getIdCatalogoMarca() );
		BigInteger totalProductos =  proveedorDAO.obtieneTotalProductosPorMarca( item.getMarca().getIdCatalogoMarca() );
		List<Producto> list = proveedorDAO.obtieneListaProductosEmpresa( item.getMarca().getIdCatalogoMarca() );
		
		rsp.setTotalProductosEscaneados( null != totalEscaneados ? totalEscaneados.longValue() : 0L );
		rsp.setTotalProductos( null != totalProductos ? totalProductos.longValue() : 0L );
		rsp.setProductos(list);
		
		return rsp;
	}

	@Override
	@Transactional
	public List<List<Object>> obtieneInfoReporteEmpresaProductos(Proveedor item) {
		List<List<Object>> rows = new ArrayList<>();
		
		List<Producto> list = proveedorDAO.obtieneListaProductosEmpresa( item.getMarca().getIdCatalogoMarca() );
		
		//Formatear fecha dd-MMM-yyyy
		//Formatear solicitudes y bonificaciones
		
		for( Producto i : list ) {
			rows.add( Arrays.asList( new Object[] {
					i.getIdProducto(),
					i.getNombreProducto(), 
					i.getTotalBonificacion(),
					i.getTotalEscaneos()
					} ) );
		}

		
		return rows;
	}
	
	/*
	 * Dashboard empresa - Usuarios -
	 */
	@Override
	@Transactional
	public InformacionDashboardProveedorRSP obtieneTotalesDashboardEmpresaUsuarios(Proveedor item) {

		InformacionDashboardProveedorRSP rsp = new InformacionDashboardProveedorRSP();

		List<Usuario> list = proveedorDAO.obtieneInformacionUsuariosEmpresa( item.getMarca().getIdCatalogoMarca() );
		List<Usuario> usuarios = proveedorDAO.obtieneListaUsuariosEmpresa( item.getMarca().getIdCatalogoMarca() );
		int total = 0; 
		long totalM = 0;
		long totalF = 0;
		double promedioEdad = 0.0;
		double porcentajeHombres = 0.0;
		double porcentajeMujeres = 0.0;
		
		if( !list.isEmpty() ) {
			totalM = list.stream().filter( i -> i.getIdCatalogoSexo() == 1 ).count();
			totalF = list.stream().filter( i -> i.getIdCatalogoSexo() == 3 ).count();
			
			promedioEdad = list
				    .stream()
				    .mapToInt(Usuario::getEdad)
				    .average()
				    .getAsDouble();
			
			total = list.size(); 
			porcentajeHombres = ( totalM * 100 ) /  total;
			porcentajeMujeres = ( totalF * 100 ) /  total;
		}
		
		rsp.setTotalUsuarios( Long.valueOf( total ) );
		rsp.setPorcentajeH( String.valueOf( porcentajeHombres ) + " %" );
		rsp.setPorcentajeM( String.valueOf( porcentajeMujeres ) + " %" );
		rsp.setPromedioEdad( String.valueOf( promedioEdad ) );
		
		rsp.setUsuarios(usuarios);
		
		return rsp;
	}

	@Override
	public List<List<Object>> obtieneInfoReporteEmpresaUsuarios(Proveedor item) {
		List<List<Object>> rows = new ArrayList<>();
		
//		List<BonificacionItem> list = historicoMediosBonificacionDAO.obtieneHistoricoBonificaciones( null );
		List<Usuario> list = proveedorDAO.obtieneListaUsuariosEmpresa( item.getMarca().getIdCatalogoMarca() );
		
		//Formatear fecha dd-MMM-yyyy
		//Formatear solicitudes y bonificaciones
		
		for( Usuario i : list ) {
			rows.add( Arrays.asList( new Object[] {
					i.getIdUsuario(),
					i.getSexo(), 
					i.getEdad(),
					i.getEscaneos()
					} ) );
		}

		
		return rows;
	}	
}
