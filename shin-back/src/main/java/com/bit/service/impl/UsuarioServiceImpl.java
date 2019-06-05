package com.bit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bit.common.Utils;
import com.bit.communication.MediosComunicacionService;
import com.bit.dao.MediosBonificacionDAO;
import com.bit.dao.UsuarioDAO;
import com.bit.exception.CommunicationException;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.MediosBonificacion;
import com.bit.model.Producto;
import com.bit.model.Ticket;
import com.bit.model.Usuario;
import com.bit.model.dto.SMSDTO;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.InformacionUsuarioRSP;
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
	private MediosComunicacionService mediosComunicacionService;

	@Override
	@Transactional
	public List<Usuario> getUsuarios() {

		log.info("Obteniendo lista de usuario de la base de datos");

		List<Usuario> list = usuarioDAO.getUsuarios();

		return list;
	}

	@Override
	@Transactional
	public SimpleResponse registrarUsuarios(Usuario item) {

		log.info("Registrando valores para crar nuevo usuario");

		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		String usuario = item.getUsuario();
		// TODO Agregar validacion de usuario unico
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
		sms.setBody("Tu código es: " + item.getCodigoVerificacion());

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
			System.out.println("Código correcto");
		} else {
			rsp.setMessage("Error");
			rsp.setCode(500);
			System.out.println("El código no coincide");
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
		sms.setBody("Tu código es: " + item.getCodigoVerificacion());

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
	public Usuario findUserByUserAndPassword(Usuario item) {

		log.info("Buscando un usuario por nombre de usuario y por contraseña para login");

		String usuario = item.getUsuario();
		String contrasenia = item.getContrasenia();

		Usuario user = usuarioDAO.findUserByUserAndPassword(usuario, contrasenia);

		return user;
	}

	@Override
	@Transactional
	public InformacionUsuarioRSP obtenerTotalBonificacion(Usuario item) {

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

	@Override
	@Transactional
	public InformacionUsuarioRSP obtenerMediosBonificacion(Usuario item) {

		log.info("Obteniendo medios de bonificacion de usuario");

		InformacionUsuarioRSP user = new InformacionUsuarioRSP();

		List<MediosBonificacion> list = mediosBonificacionDAO.findMediosBonificacionByIdUser(item.getIdUsuario());
		user.setMediosBonificacion(transform(list));

		return user;
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
			
			cmb.setIdCatalogoMediosBonificacion(item.getCatalogoMediosBonificacion().getIdCatalogoMediosBonificacion());
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
}
