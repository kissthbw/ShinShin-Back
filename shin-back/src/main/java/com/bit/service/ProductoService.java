package com.bit.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.ProductoFavorito;
import com.bit.model.ProductoValoracion;
import com.bit.model.SugerenciaProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.DetalleProducoRSP;
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
	
	ListItemsRSP getProductosPorTipoProducto( long idCatalogoTipoProducto );
	
	DetalleProducoRSP getDetalleProducto( long idProducto, long idUsuario );
	
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
	
	SimpleResponse eliminaProducto(Producto item);
	
	/*
	 * Productos favoritos
	 * 
	 */
	SimpleResponse agregarProductoFavoritoUsuario( ProductoFavorito item );
	SimpleResponse eliminarProductoFavoritoUsuario( ProductoFavorito item );
	ListItemsRSP getProductosFavoritosPorUsuario( long idUsuario );
	
	/*
	 * Productos valoracion
	 */
	SimpleResponse agregarProductoValoracionUsuario( ProductoValoracion item );
	SimpleResponse eliminarProductoValoracionUsuario( ProductoValoracion item );
}
