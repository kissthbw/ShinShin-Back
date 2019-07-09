package com.bit.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.communication.MediosComunicacionService;
import com.bit.dao.CatalogoMediosBonificacionDAO;
import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.dao.MediosBonificacionDAO;
import com.bit.dao.TicketDAO;
import com.bit.dao.UsuarioDAO;
import com.bit.exception.CommunicationException;
import com.bit.model.CatalogoMediosBonificacion;
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

		String codigo = Utils.generaCodigoVerficacion();
		Utils.generaCodigoVerficacion();
		item.setCodigoVerificacion(codigo);
		item.setEstatusActivacion(false);

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
	public SimpleResponse actualizarUsuarios(Usuario item) {

		log.info("Actualizando el/los valor(es) de usuario");

		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		String codigo = Utils.generaCodigoVerficacion();
		Utils.generaCodigoVerficacion();
		item.setCodigoVerificacion(codigo);
		item.setEstatusActivacion(false);

		SMSDTO sms = new SMSDTO();
		sms.setToMobileNumber(item.getTelMovil());
		sms.setBody("Tu codigo es: " + item.getCodigoVerificacion());

		try {
			mediosComunicacionService.sendSMS(sms);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			item = usuarioDAO.update(item);

			/*
			 * 1. Cuando un usuario registrado quiere modificar algun dato de su informacion
			 * personal, da clic en boton actualizar 2. El sistema obtiene la informacion
			 * del usuario y lo muestra en pantalla 3. El usuario realiza los cambios que
			 * desea y da clic en guardar cambios si no esta seguro da clic en cancelar y
			 * vuelve a la pagina de inicio 3.1 El sistema pregunta al usuario donde quiere
			 * que sea enviado su codigo de verificacion (correo/movil) para guardar los
			 * cambios 3.1.1 El sistema genera un codigo de verificacion de 4 digitos y lo
			 * envia al destino seleccionado previamente 3.1.2 El usuario ingresa el codigo
			 * de verificacion 3.1.3 El sistema compara el codigo que genero contra el que
			 * el usuario ingreso 3.1.4 Si el codigo es identico, entonces el sistema
			 * actualiza los datos 3.1.5 Si no es identico, el sistema envia mensaje de
			 * error y pregunta si desea cancelar el registro o generar un nuevo codigo
			 * random de verificacion 3.1.6 Si el usuario cancela, entonces el sistema
			 * vuelve a la pagina de inicio y no actualiza ningun campo 3.1.7 Si el usuario
			 * selecciona generar un nuevo codigo, entonces el sistema genera el nuevo
			 * codigo y lo envia al destino seleccionado previamente (determinar numero de
			 * intentos)
			 */
		}

		rsp.setId(item.getIdUsuario());
		return rsp;
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

		log.info("Buscando un usuario por nombre de usuario y por password para login");
		InformacionUsuarioRSP infoRSP = new InformacionUsuarioRSP();
		infoRSP.setCode(200);
		infoRSP.setMessage("Exito");
		
		String usuario = item.getUsuario();
		String contrasenia = item.getContrasenia();

		Usuario user = usuarioDAO.findUserByUserAndPassword(usuario, contrasenia);
		
		if( user == null ) {
			infoRSP.setCode(500);
			infoRSP.setMessage("Usuario no existe");
			
			return infoRSP;
		}
		else {
			Usuario tmp = new Usuario();
			tmp.setIdUsuario( user.getIdUsuario() );
			tmp.setUsuario( user.getUsuario() );
			infoRSP.setId( user.getIdUsuario() );
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
		
		Usuario tmp = usuarioDAO.findByPK( item.getIdUsuario() );
		for( Ticket t : item.getTickets() ) {
			tmp.addTicket( t );
		}
		
		tmp = usuarioDAO.update(tmp);
		
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
}
