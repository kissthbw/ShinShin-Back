package com.bit.service.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bit.communication.CloundinaryService;
import com.bit.dao.CatalogoMarcaDAO;
import com.bit.dao.ProveedorDAO;
import com.bit.model.Authority;
import com.bit.model.AuthorityType;
import com.bit.model.CatalogoMarca;
import com.bit.model.Proveedor;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.CatalogoMarcaService;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CatalogoMarcaServiceImpl implements CatalogoMarcaService {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoMarcaServiceImpl.class);
	
	@Autowired
	private CatalogoMarcaDAO catalogoMarcaDAO;
	
	@Autowired
	private ProveedorDAO proveedorDAO;
	
	@Autowired
	private CloundinaryService cloundinaryService;

	@Override
	@Transactional
	public ListItemsRSP getCatalogoMarca() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obteniendo lista de marcas");
		
		List<CatalogoMarca> list = catalogoMarcaDAO.getCatalogoMarca();
		
		for(CatalogoMarca lista : list) {
			BigInteger productos=catalogoMarcaDAO.getProducts(lista.getIdCatalogoMarca());
			if(productos!=null) {
				lista.setProducts(productos);
			}else {
				lista.setProducts(BigInteger.valueOf(0));
			}
		}
		
		rsp.setMarcas(list);
		return rsp;
	}
	
	@Override
	@Transactional
	public ListItemsRSP getProveedoresMarca() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obteniendo lista de proveedores");
		
		List<Proveedor> list = proveedorDAO.getProveedores();
		List<Proveedor> dtoList = new ArrayList<>();
		
		for( Proveedor p :list ) {
			Proveedor dto = toDto( p );
			dtoList.add(dto);
		}
		
		rsp.setProveedores(dtoList);
		
		return rsp;
	}
	
	@Override
	@Transactional
	public SimpleResponse registrarProveedorMarca(Proveedor item) {
		log.info("Registrando un proveedor de marca");
		
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		Authority aut = new Authority();
		aut.setId(2);
		aut.setName(AuthorityType.ROLE_ADMIN);
		Set<Authority> auts = new HashSet<>();
		auts.add(aut);
		item.setActive(true);
		item.setAuthorities(auts);
		
		proveedorDAO.save(item);
		return rsp;
	}
	
	@Override
	@Transactional
	public SimpleResponse actualizaProveedorMarca(Proveedor item) {
		log.info("Actualizando un provedor de marca");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		proveedorDAO.findByPK( item.getId() );
		

		item.setActive( true );
		item = proveedorDAO.update(item);
		rsp.setId(item.getId());
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse eliminaProveedorMarca(Proveedor item) {

		log.info("Elimnando un provedor de marca");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		proveedorDAO.deleteById( item.getId() );
		

//		item.setActive( false );
//		item = proveedorDAO.update(item);
		rsp.setId(item.getId());
		return rsp;
	}
	
	private Proveedor toDto( Proveedor entity ) {
		Proveedor dto = new Proveedor();
		CatalogoMarca m = new CatalogoMarca();
		
		dto.setId( entity.getId() );
		dto.setNombre( entity.getNombre() );
		dto.setEmail( entity.getEmail() );
		dto.setPassword( entity.getPassword() );
		
		CatalogoMarca marca = entity.getMarca();
		
		if ( null != marca ) {
			m.setactive( marca.getactive() );
			m.setIdCatalogoMarca( marca.getIdCatalogoMarca() );
			m.setNombreMarca( marca.getNombreMarca() );
			m.setImgUrl( marca.getImgUrl() );
			dto.setMarca(marca);
		}
		
		
		return dto;
	}

	@Override
	@Transactional
	public SimpleResponse registrarMarcas(MultipartFile file, CatalogoMarca item) {
		
		log.info("Registrando una marca");
		
		//Subir imagen a cloudinary
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/marcas/" + item.getNombreMarca(), 
				   "overwrite", true
				);
		
		if ( !file.isEmpty() ) {
			try {
				log.info("Subiendo imagen de: {}", item.getNombreMarca());
				String url = cloundinaryService.uploadImage(file.getBytes(), params);
				item.setImgUrl( url );
			} catch (IOException e) {
				log.error("Ocurrio un error al subir imagen", e);
			}
		}
		
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		item.setactive(1);
		item = catalogoMarcaDAO.save(item);
		rsp.setId(item.getIdCatalogoMarca());
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarMarcas(MultipartFile file, CatalogoMarca item) {
		
		log.info("Actializando una marca");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		catalogoMarcaDAO.findByPK(item.getIdCatalogoMarca());
		
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/marcas/" + item.getNombreMarca(), 
				   "overwrite", true
				);
		
		if ( !file.isEmpty() ) {
			try {
				log.info("Subiendo imagen de: {}", item.getNombreMarca());
				String url = cloundinaryService.uploadImage(file.getBytes(), params);
				item.setImgUrl( url );
			} catch (IOException e) {
				log.error("Ocurrio un error al subir imagen", e);
			}
		}
		item.setactive(1);
		item = catalogoMarcaDAO.update(item);
		rsp.setId(item.getIdCatalogoMarca());
		return rsp;
	}

	@Override
	@Transactional
	public CatalogoMarca findById(Long id) {
		log.info("Buscando marca por id: {}", id);		
		CatalogoMarca item = catalogoMarcaDAO.findByPK(id);
		
		
		return transform(item);
	}
	
	@Override
	@Transactional
	public Proveedor findProveedorById(Long id) {
		log.info("Buscando proveedor por id: {}", id);		
		Proveedor item = proveedorDAO.findByPK(id);
		
		return toDto(item);
	}
	
	private CatalogoMarca transform( CatalogoMarca entity ) {
		CatalogoMarca item = new CatalogoMarca();
		
		item.setIdCatalogoMarca( entity.getIdCatalogoMarca() );
		item.setNombreMarca( entity.getNombreMarca() );
		item.setImgUrl( entity.getImgUrl() );
		
		return item;
	}
	
	@Override
	@Transactional
	public SimpleResponse eliminaMarcas(CatalogoMarca item) {
		log.info("Eliminado logico de Marca con id: {}", item.getIdCatalogoMarca());
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item =  catalogoMarcaDAO.findByPK(item.getIdCatalogoMarca() );
		item.setactive(0);
		catalogoMarcaDAO.update(item);
		
		item = catalogoMarcaDAO.update(item);
		int rows = catalogoMarcaDAO.eliminaMarcaProductos(item);
		log.info("Filas afectadas: "+rows);
		rsp.setId(item.getIdCatalogoMarca());
		return rsp;
	}

}
