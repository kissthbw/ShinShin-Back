package com.bit.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.SugerenciaProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.model.report.ProductoReport;

public interface ProductoService {

	/**
	 * Obtiene la lista de productos registrados en BD Pensar en mejorar este
	 * servicio agregando: Paginado. Total de registros a devolver. Filtardo.
	 * Ordenado.
	 * 
	 * @return
	 */
	ListItemsRSP getProductos();
	
	ListItemsRSP getProductosPorPaginas(int page, int max);
	
	ListItemsRSP getBanners();

	SimpleResponse registrarProductos(MultipartFile file, Producto item);

	SimpleResponse actualizarProductos(MultipartFile file, Producto item);

	ListItemsRSP getProductosPorMarca(CatalogoMarca item, Producto i);

	ListItemsRSP getProductosPorTipo(CatalogoTipoProducto item, Producto i);

	ListItemsRSP getProductosPorNombre(Producto i);
	
	List<Producto> getProductosPorIDYEmpresa(List<String> items, int idEmpresa);
	
	Producto findById(Long id);
	
	SimpleResponse registraSugerencia(SugerenciaProducto item);

	List<ProductoReport> getAllProductoReport();
}
