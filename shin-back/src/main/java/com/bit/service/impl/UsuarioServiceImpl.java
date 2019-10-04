package com.bit.service.impl;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.communication.MediosComunicacionService;
import com.bit.dao.CatalogoMediosBonificacionDAO;
import com.bit.dao.ContactoDAO;
import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.dao.MediosBonificacionDAO;
import com.bit.dao.TicketDAO;
import com.bit.dao.UsuarioDAO;
import com.bit.exception.CommunicationException;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.Contacto;
import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.MediosBonificacion;
import com.bit.model.Producto;
import com.bit.model.Ticket;
import com.bit.model.Usuario;
import com.bit.model.dto.SMSDTO;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.model.dto.response.MedioBonificacionUsuario;
import com.bit.service.UsuarioService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	private static final SMSDTO sms = null;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private MediosBonificacionDAO mediosBonificacionDAO;
	
	@Autowired
	private CatalogoMediosBonificacionDAO catalogoMediosBonificacionDAO;

	@Autowired
	private MediosComunicacionService mediosComunicacionService;
	
	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;
	
	@Autowired
	private TicketDAO ticketDAO;
	
	@Autowired
	private ContactoDAO contactoDAO;

	@Override
	@Transactional
	public ListItemsRSP getUsuarios() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obteniendo lista de usuario de la base de datos");

		List<Usuario> list = usuarioDAO.getUsuarios();
		
		rsp.setUsuarios(list);
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse registrarUsuarios(Usuario item) {

		log.info("Registrando valores para crar nuevo usuario");

		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		String usuario = item.getUsuario();
		Usuario user = usuarioDAO.findUserByUser(usuario);
		if (user == null) {
			// Si es nulo significa que el usuario no exite y por lo tanto el usuario puede
			// ser registrado
		} else {
			// Si no es nulo significa que el usuario ya existe y no puede ser registrado
			// nuevamente
			rsp.setMessage("Usuario ya existe");
			rsp.setCode(500);

			return rsp;
		}

		try {
			String hash = Utils.generaHash(item.getContrasenia());
			item.setHash(hash);
		} catch (NoSuchAlgorithmException e) {
			log.error( "Error al generar hash.", e );
		}
		
		String codigo = Utils.generaCodigoVerficacion();
		item.setCodigoVerificacion(codigo);
		item.setEstatusActivacion(false);
		item.setEstatus(1);

		SMSDTO sms = new SMSDTO();
		sms.setToMobileNumber(item.getTelMovil());
		sms.setBody("Tu codigo es: " + item.getCodigoVerificacion());

		try {
			mediosComunicacionService.sendSMS(sms);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		item = usuarioDAO.save(item);
		/*
		 * Funcionalidad de activacion de usuarios: 1. Clic en registrar nuevo usuario
		 * 2. Llenar los campos de registro necesarios 3. Clic sobre boton de registrar
		 * y se guarda en BD 4. El sistema pregunta al usuario donde quiere que sea
		 * enviado su codigo de verificacion (correo/sms) y da en enviar 5. El sistema
		 * genera un codigo de verificacion de 4 digitos y lo envia al destino
		 * seleccionado previamente 6. El usuario ingresa el codigo de verificacion 7.
		 * El sistema compara el codigo que genero contra el codigo que el usuario
		 * ingreso 7.1 Si el codigo es identico, entonces el sistema activa el registro
		 * del usuario 7.2 Si no es identico, el sistema envia mensaje de error y
		 * pregunta si desea cancelar el registro o generar un nuevo codigo random de
		 * verificacion 7.2.1 Si el usuario cancela, entonces el sistema vuelve a la
		 * pagina de inicio 7.2.2 Si el usuario selecciona generar un nuevo codigo,
		 * entonces el sistema genera el nuevo codigo y lo envia al destino seleccionado
		 * previamente (determinar numero de intentos)
		 */

		rsp.setId(item.getIdUsuario());
		return rsp;
	}
	
	@Override
	@Transactional
	public InformacionUsuarioRSP registrarUsuarioSocialMedia(Usuario item) {
		log.info("Verificando existe de usuario social media");

		InformacionUsuarioRSP infoRSP = new InformacionUsuarioRSP();
		infoRSP.setMessage("Exitoso");
		infoRSP.setCode(200);

		Usuario entity = usuarioDAO.findBySocialMediaUser(item);
		if (entity == null) {
			log.info( "Registrando usuario social media: {}", item.getUsuario() );
			item.setCodigoVerificacion("0000");
			item.setEstatusActivacion(true);
			item.setEstatus(1);
			entity = usuarioDAO.save(item);
		} 
		
		
		log.info( "Obteniendo informacion usuario social media: {}", item.getUsuario() );
		
		Usuario tmp = datosUsuario(entity);
		infoRSP.setId( tmp.getIdUsuario() );
		infoRSP.setUsuario(tmp);
		BigDecimal credito = calculaCreditoTotal(tmp);			
		infoRSP.setBonificacion( credito.doubleValue() );

		infoRSP.setId(tmp.getIdUsuario());
		return infoRSP;
	}

	private Usuario datosUsuario(Usuario user) {
		Usuario tmp = new Usuario();
		tmp.setIdUsuario( user.getIdUsuario() );
		tmp.setUsuario( user.getUsuario() );
		tmp.setNombre( user.getNombre() );
		tmp.setCorreoElectronico( user.getCorreoElectronico() );
		tmp.setTelMovil( user.getTelMovil() );
		tmp.setFechaNac( user.getFechaNac() );
		tmp.setIdCatalogoSexo( user.getIdCatalogoSexo() );
		tmp.setCodigoPostal( user.getCodigoPostal() );
		tmp.setImgUrl( user.getImgUrl() );
		tmp.setHash( user.getHash() );
		
		return tmp;
	}
	
	@Override
	@Transactional
	public SimpleResponse reenviarCodigoUsuario(Usuario item) {
		log.info("Registrando valores para crar nuevo usuario");

		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		Usuario temp = usuarioDAO.findByPK(item.getIdUsuario());
		String codigo = Utils.generaCodigoVerficacion();
		temp.setCodigoVerificacion(codigo);
		
		SMSDTO sms = new SMSDTO();
		sms.setToMobileNumber(temp.getTelMovil());
		sms.setBody("Tu codigo es: " + temp.getCodigoVerificacion());

		try {
			mediosComunicacionService.sendSMS(sms);
		} catch (CommunicationException e) {
			e.printStackTrace();
		}

		rsp.setId(temp.getIdUsuario());
		item = usuarioDAO.update(temp);
		
		return rsp;
	}
	
	@Override
	@Transactional
	public SimpleResponse activarUsuarios(Usuario item) {

		log.info("Activando la cuenta de un usuario nuevo o por modificacion");

		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		Usuario temp = usuarioDAO.findByPK(item.getIdUsuario());

		if (temp.getCodigoVerificacion().equals(item.getCodigoVerificacion())) {
			temp.setEstatusActivacion(true);
			System.out.println("Codigo correcto");
		} else {
			rsp.setMessage("Error");
			rsp.setCode(500);
			System.out.println("El codigo no coincide");
		}

		rsp.setId(item.getIdUsuario());
		return rsp;
	}
	
	@Override
	@Transactional
	public SimpleResponse eliminarUsuario(Usuario item) {

		log.info("Eliminando usuario con id: {}", item.getIdUsuario());

		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		Usuario temp = usuarioDAO.findByPK(item.getIdUsuario());
		
		if( null != temp ) {
			temp.setEstatus(2);//1: Activo, 2: Eliminado, 3: Suspendido 
		}
		else {
			rsp.setMessage("Usuario no existe");
			rsp.setCode(500);
		}
		
		usuarioDAO.update(temp);

		rsp.setId(item.getIdUsuario());
		return rsp;
	}

	@Override
	@Transactional
	public InformacionUsuarioRSP actualizarUsuarios(Usuario item) {

		log.info("Actualizando el/los valor(es) de usuario");

		InformacionUsuarioRSP infoRSP = new InformacionUsuarioRSP();
		infoRSP.setCode(200);
		infoRSP.setMessage("Exito");

		//Buscar en BD el usuario a actualizar
		Usuario entity = usuarioDAO.findByPK( item.getIdUsuario() );
		if ( null == entity ) {
			infoRSP.setMessage("Usuario no existe");
			infoRSP.setCode(500);
			
			return infoRSP;
		}
		
		//Validar password, en caso de haber sido actualizado
		if ( null != item.getContrasenia() ) {
			if ( !entity.getContrasenia().equals( item.getContraseniaActual() ) ) {
				infoRSP.setMessage("Contraseña actual incorrecta");
				infoRSP.setCode(202);
				
				return infoRSP;
			}
			else {
				entity.setContrasenia( item.getContrasenia() );
			}
		}
		
		//Guardar foto en cloudinary
		if ( null != item.getImageData() ) {
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
					  "cloud_name", "shingshing",
					  "api_key", "657472936977876",
					  "api_secret", "cZ8wZWzSvTqXdqBO7P1e62xnzVY")); 
			
			Map params = ObjectUtils.asMap(
					   "public_id", "shingshing/usuarios/" + item.getIdUsuario(), 
					   "overwrite", true
					);
			
			
			
			try {
				log.info( "Subiendo imagen de usuario" );
//				byte[] bytes = Base64.getDecoder().decode(item.getImageData());
				byte[] bytes = Base64.getMimeDecoder().decode( item.getImageData() );
				
				Map uploadResult = cloudinary.uploader().upload(bytes, params);
				String url = cloudinary.url().generate("shingshing/usuarios/" + + item.getIdUsuario() + ".jpeg");
				log.info("URL 1: {}", url);
				log.info("URL 2 : {}", uploadResult.get("url"));
				String url2 = (String) uploadResult.get("url");
				
				entity.setImgUrl(url2);
			} catch (Exception e) {
				log.error( "Ocurrio un error al subir imagen: {}", e );
			}
		}
		
		//Actualizar campos
		updateUsuario(item, entity);
		item = datosUsuario(entity);
		infoRSP.setId( item.getIdUsuario() );
		infoRSP.setUsuario(item);
		
		//Devolver datos actualizados
		usuarioDAO.update(entity);

		infoRSP.setId(item.getIdUsuario());
		log.info( "Actualizacion exitosa" );
		return infoRSP;
	}
	
	private void updateUsuario( Usuario item, Usuario entity ) {
		
		if( item.getNombre() != null ) {
			entity.setNombre( item.getNombre() );
		}
		
		if( item.getCorreoElectronico() != null ) {
			entity.setCorreoElectronico( item.getCorreoElectronico() );
		}
		
		if( item.getTelMovil() != null ) {
			entity.setTelMovil( item.getTelMovil() );
		}
		
		if( item.getFechaNac() != null ) {
			entity.setFechaNac( item.getFechaNac() );
		}
		
		if( item.getCodigoPostal() != null ) {
			entity.setCodigoPostal( item.getCodigoPostal() );
		}
		
		if( item.getIdCatalogoSexo() != null ) {
			entity.setIdCatalogoSexo( item.getIdCatalogoSexo() );
		}
	}

	@Override
	@Transactional
	public Usuario findUserByUser(Usuario item) {

		log.info("Buscando un usuario por nombre de usuario para login");

		String usuario = item.getUsuario();

		Usuario user = usuarioDAO.findUserByUser(usuario);

		return user;
	}

	@Override
	@Transactional
	public InformacionUsuarioRSP findUserByUserAndPassword(Usuario item) {

		log.info("Verificando credenciales de acceso para: {}", item.getUsuario());
		InformacionUsuarioRSP infoRSP = new InformacionUsuarioRSP();
		infoRSP.setCode(200);
		infoRSP.setMessage("Exito");

		Usuario user = null;
		if( null != item.getContrasenia() ) {
			log.info("Acceso por password");
			user = usuarioDAO.findUserByUserAndPassword(item.getUsuario(), item.getContrasenia());
		}
		else if( null != item.getHash() ) {
			log.info("Acceso por hash");
			user = usuarioDAO.findUserByUserAndHash(item.getUsuario(), item.getHash());
		}
		
		
		if( user == null ) {
			infoRSP.setCode(500);
			infoRSP.setMessage("Usuario no existe");
			
			return infoRSP;
		}
		else {
			try {
				String hash = Utils.generaHash( user.getContrasenia() );
				user.setHash(hash);
			} catch (NoSuchAlgorithmException e) {
				log.error( "Error al generar hash.", e );
			}
			Usuario tmp = datosUsuario(user);
			infoRSP.setId( tmp.getIdUsuario() );
			infoRSP.setUsuario(tmp);
			
			BigDecimal credito = calculaCreditoTotal(user);
			
			infoRSP.setBonificacion( credito.doubleValue() );

			return infoRSP;
		}
	}

	@Override
	@Transactional
	public InformacionUsuarioRSP obtenerTotalBonificacion(Usuario item) {

		//La bonificacion debe calcularse en base a la bonififacion de productos por ticket registrados
		//menos las bonificaciones solicitadas
		log.info("Obteniendo bonificacion total de usuario");

		Usuario user = usuarioDAO.findUserByUser(item.getUsuario());
		InformacionUsuarioRSP iUser = new InformacionUsuarioRSP();
		iUser.setNombreUsuario(user.getUsuario());

		List<Ticket> list = user.getTickets();

		double bonificacion = 0;
		for (Ticket t : list) {
			for (Producto p : t.getProductos()) {
				bonificacion += p.getCantidadBonificacion();
			}
		}

		iUser.setBonificacion(bonificacion);
		return iUser;
	}

	/*
	 * 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public BigDecimal calculaCreditoTotal(Usuario user) {
		BigDecimal credito = new BigDecimal(0.0);
		BigDecimal abonos = new BigDecimal(0.0);
		BigDecimal cargos = new BigDecimal(0.0);
		
		log.info("Obteniendo tickets del usuario: {}", user.getIdUsuario());
		//Se obtienen los tickets del usuario (abonos)
		List<Long> ids = usuarioDAO.getTicketsPorUsuario( user );
		
		if( !ids.isEmpty() ) {
			List<Ticket> tickets = ticketDAO.getTicketsPorUsuario(ids);
			
			
			for (Ticket t : tickets) {
				for (Producto p : t.getProductos()) {
					abonos = abonos.add( BigDecimal.valueOf( p.getCantidadBonificacion() ) );
//					bonificacion += p.getCantidadBonificacion();
				}
			}
		}
		
		//Obtener las solicitudes bonificacion (cargos)
		log.info("Obteniendo bonificaciones del usuario: {}", user.getIdUsuario());
		List<HistoricoMediosBonificacion> bonificaciones = historicoMediosBonificacionDAO.getHistoricosMediosBonificacionPorUsuario(user);
		
		if( !bonificaciones.isEmpty() ) {
			for( HistoricoMediosBonificacion b : bonificaciones ) {
				cargos = cargos.add( BigDecimal.valueOf( b.getCantidadBonificacion() ) );
			}
		}
		
		credito = abonos.subtract(cargos);
		log.info("Credito del usuario: {}", credito);
		
		return credito;
		
	}
	
	@Override
	@Transactional
	public InformacionUsuarioRSP obtenerMediosBonificacion(Usuario item) {

		log.info("Obteniendo medios de bonificacion de usuario");

		InformacionUsuarioRSP user = new InformacionUsuarioRSP();
		
		List<CatalogoMediosBonificacion> catMediosList = catalogoMediosBonificacionDAO.getCatalogoMediosBonificacion();
		List<MediosBonificacion> list = mediosBonificacionDAO.findMediosBonificacionByIdUser(item.getIdUsuario());
		
		MedioBonificacionUsuario bancariaList = new MedioBonificacionUsuario();
		MedioBonificacionUsuario paypalList = new MedioBonificacionUsuario();
		MedioBonificacionUsuario recargaList = new MedioBonificacionUsuario();
		
		for( CatalogoMediosBonificacion cat : catMediosList ) {
			if ( cat.getIdCatalogoMedioBonificacion() == 1 ) {
				bancariaList.setNombreMedioBonificacion(cat.getNombreMedioBonificacion());
			}
			else if ( cat.getIdCatalogoMedioBonificacion() == 2 ) {
				paypalList.setNombreMedioBonificacion(cat.getNombreMedioBonificacion());
			}
			else if ( cat.getIdCatalogoMedioBonificacion() == 3 ) {
				recargaList.setNombreMedioBonificacion(cat.getNombreMedioBonificacion());
			}
		}
		
		List<MediosBonificacion> listTmp = transform(list);
		
		for( MediosBonificacion medio : listTmp ) {
			if( medio.getCatalogoMediosBonificacion().getIdCatalogoMedioBonificacion() == 1 ) {
				bancariaList.addToList(medio);
			}
			else if( medio.getCatalogoMediosBonificacion().getIdCatalogoMedioBonificacion() == 2 ) {
				paypalList.addToList(medio);
			}
			else if( medio.getCatalogoMediosBonificacion().getIdCatalogoMedioBonificacion() == 3 ) {
				recargaList.addToList(medio);
			}
		}
		
		
		//Agrupar los medios de bonificacion segun el catalogo de medios bonificacion
		
//		user.setMediosBonificacion(transform(list));
		user.addToList(bancariaList);
		user.addToList(paypalList);
		user.addToList(recargaList);

		return user;
	}
	
	@Override
	@Transactional
	public InformacionUsuarioRSP registrarTicketUsuario(Usuario item) {
		log.info("Entrando en registrarTicketUsuario");
		InformacionUsuarioRSP rsp = new InformacionUsuarioRSP();
		rsp.setCode(200);
		rsp.setMessage("Exito");
		
		//Verificar que el ticket no haya sido registrado previamente
		for( Ticket t : item.getTickets() ) {
			log.info("Verificando existencia del ticket con transaccion y tienda: {}, {}", t.getTicket_transaccion(), t.getTicket_tienda());
			
			boolean existe = ticketDAO.existeTicketByTransaccionTienda(t);
			
			log.info( "Existe?: {}", existe );
			
			
			if(!existe) {
				Usuario tmp = usuarioDAO.findByPK( item.getIdUsuario() );
				tmp.addTicket( t );
				tmp = usuarioDAO.update(tmp);
			}
			else {
				rsp.setCode(203);
				rsp.setMessage("Este ticket ya ha sido registrado");
			}
			
			
		}

		
		log.info( "Ticket guardado de forma exitosa" );
		return rsp;
	}

	@Override
	@Transactional
	public ListItemsRSP obtienetHistoricosMediosBonificacionPorUsuario(Usuario item) {
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obtiene una lista de bonificaciones del usuario {}", item.getIdUsuario());
		
		List<HistoricoMediosBonificacion> list = historicoMediosBonificacionDAO.getHistoricosMediosBonificacionPorUsuario(item);
		
		rsp.setHistoricoMediosBonificaciones( transformHistoricoMediosBonificacion(list) );
		return rsp;
	}

	@Override
	@Transactional
	public ListItemsRSP obtieneTicketsPorUsuario(Usuario item) {
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obtiene una lista de tickets del usuario {}", item.getIdUsuario());
		
		List<Long> ids = usuarioDAO.getTicketsPorUsuario(item);
		
		if( !ids.isEmpty() ) {
			List<Ticket> tickets = ticketDAO.getTicketsPorUsuario(ids);
			rsp.setTickets( transformTicketList(tickets) );
		}
		else {
			List<Ticket> list = new ArrayList<>();
			rsp.setTickets(list);
		}
		
		return rsp;
	}
	
	/*
	 * SECCION DE TOTALES PARA EL DASHBOARD DEL USUARIO
	 */
	@Override
	@Transactional
	public InformacionUsuarioRSP obtieneInformacionGeneralUsuario(Usuario item) {
		InformacionUsuarioRSP rsp = new InformacionUsuarioRSP();
		try {
			Usuario entity = usuarioDAO.findByPK(2L);
			Usuario tmp = new Usuario();
			
			tmp.setNombre( entity.getNombre() );
			rsp.setUsuario(tmp);
			rsp.setTickets( usuarioDAO.calculaTicketsTotales(item).longValue() );
			rsp.setRetiros( usuarioDAO.calculaBanoficacionesTotales(item).longValue() );
			rsp.setMedios( usuarioDAO.calculaMediosBonificacionTotales(item).longValue() );
			rsp.setBonificacion( calculaCreditoTotal(item).doubleValue() );
		}
		catch(Exception e) {
			log.error("Error en obtieneInformacionGeneralUsuario ", e);
		}
		return rsp;
	}
	
	private List<MediosBonificacion> transform(List<MediosBonificacion> list) {
		
		List<MediosBonificacion> tmp = new ArrayList<>();
		
		for(MediosBonificacion item : list) {
			MediosBonificacion mTemp = new MediosBonificacion();
			CatalogoMediosBonificacion cmb = new CatalogoMediosBonificacion();
			Usuario u = new Usuario();
			mTemp.setIdMediosBonificacion(item.getIdMediosBonificacion());
			mTemp.setCuentaMedioBonificacion(item.getCuentaMedioBonificacion());
			mTemp.setCompaniaMedioBonificacion(item.getCompaniaMedioBonificacion());
			mTemp.setIdCuentaMedioBonificacion( item.getIdCuentaMedioBonificacion() );
			mTemp.setAliasMedioBonificacion( item.getAliasMedioBonificacion() );
			mTemp.setVigenciaMedioBonificacion( item.getVigenciaMedioBonificacion() );
			mTemp.setIdTipo( item.getIdTipo() );
			mTemp.setBanco( item.getBanco() );
			
			cmb.setIdCatalogoMedioBonificacion(item.getCatalogoMediosBonificacion().getIdCatalogoMedioBonificacion());
			cmb.setNombreMedioBonificacion(item.getCatalogoMediosBonificacion().getNombreMedioBonificacion());
			mTemp.setCatalogoMediosBonificacion(cmb);
			
			u.setIdUsuario(item.getUsuario().getIdUsuario());
			u.setNombre(item.getUsuario().getNombre());
			u.setApPaterno(item.getUsuario().getApPaterno());
			u.setApMaterno(item.getUsuario().getApMaterno());
			u.setFechaNac(item.getUsuario().getFechaNac());
			u.setFotoUsuario(item.getUsuario().getFotoUsuario());
			u.setTelMovil(item.getUsuario().getTelMovil());
			u.setCorreoElectronico(item.getUsuario().getCorreoElectronico());
			u.setUsuario(item.getUsuario().getUsuario());
			u.setContrasenia(item.getUsuario().getContrasenia());
			u.setCalle(item.getUsuario().getCalle());
			u.setNumeroExt(item.getUsuario().getNumeroExt());
			u.setNumeroInt(item.getUsuario().getNumeroInt());
			u.setColonia(item.getUsuario().getColonia());
			u.setCodigoPostal(item.getUsuario().getCodigoPostal());
			u.setDelMun(item.getUsuario().getDelMun());
			u.setEstado(item.getUsuario().getEstado());
			mTemp.setUsuario(u);
			
			tmp.add(mTemp);
		}
		
		return tmp;
	}
	
	private List<Ticket> transformTicketList(List<Ticket> list){
		
		List<Ticket> tickets = new ArrayList<>();
		
		for( Ticket t : list ) {
			Ticket tmp = new Ticket();
			tmp.setIdTicket( t.getIdTicket() );
			tmp.setFecha( t.getFecha() );
			tmp.setHora( t.getHora() );
			tmp.setNombreTienda( t.getNombreTienda() );
			tmp.setSucursal( t.getSucursal() );
			tmp.setTotal( t.getTotal() );
			tmp.setTicket_tienda( t.getTicket_tienda() );
			tmp.setTicket_subTienda( t.getTicket_subTienda() );
			tmp.setTicket_fecha( t.getTicket_fecha() );
			tmp.setTicket_hora( t.getTicket_hora() );
			tmp.setTicket_transaccion( t.getTicket_transaccion() );
			
			tickets.add(tmp);
		}
		
		return tickets;
	}
	
	private List<HistoricoMediosBonificacion> transformHistoricoMediosBonificacion( List<HistoricoMediosBonificacion> list ){
		List<HistoricoMediosBonificacion> historicoBonificaciones = new ArrayList<>();
		
		for( HistoricoMediosBonificacion h : list ) {
			HistoricoMediosBonificacion tmp = new HistoricoMediosBonificacion();
			MediosBonificacion medio = new MediosBonificacion();
			
			tmp.setIdHistoricoMediosBonificacion( h.getIdHistoricoMediosBonificacion() );
			tmp.setCantidadBonificacion( h.getCantidadBonificacion() );
			tmp.setFechaBonificacion( h.getFechaBonificacion() );
			tmp.setHoraBonificacion( h.getHoraBonificacion() );
			medio.setIdCuentaMedioBonificacion( h.getMediosBonificacion().getIdCuentaMedioBonificacion() );
			medio.setAliasMedioBonificacion( h.getMediosBonificacion().getAliasMedioBonificacion() );
			medio.setCuentaMedioBonificacion( h.getMediosBonificacion().getCuentaMedioBonificacion() ); 
			
			tmp.setMediosBonificacion(medio);
			historicoBonificaciones.add(tmp);
		}
		
		return historicoBonificaciones;
	}

	@Override
	@Transactional
	public SimpleResponse registraContacto(Contacto item) {
		log.info( "Registrando datos de contacto: {} de usuario: {}", item.getTopico(), item.getIdUsuario() );
		SimpleResponse rsp = new SimpleResponse();
		rsp.setCode(200);
		rsp.setMessage("Exito");
		
		contactoDAO.save(item);
		
		return rsp;
	}
}
