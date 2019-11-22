package mx.com.backend.admin.reclutamiento.services.usuario.impl;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.core.util.UtilCore;
import mx.com.backend.admin.reclutamiento.dao.usuario.IUsuarioDao;
import mx.com.backend.admin.reclutamiento.models.EnvioEmailBeans;
import mx.com.backend.admin.reclutamiento.models.Usuario;
import mx.com.backend.admin.reclutamiento.services.mail.IEnvioEmail;
import mx.com.backend.admin.reclutamiento.services.usuario.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	private final static Logger log = LoggerFactory.getLogger(UsuarioService.class);

	private static final int VEINTE = 20;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IEnvioEmail envioEmail;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UtilCore utilCore;

	@Value("${mail.plantilla}")
	private String plantillaCorreo;

	@Value("${mail.recuperar.password}")
	private String plantillaCorreoRecuperar;

	@Override
	public Usuario crearUsuario(Usuario usuario) throws BussinesException {

		try {

			Usuario user = usuarioDao.buscarUsuarioPorCorreo(usuario.getEmail());

			if (user != null) {
				throw new BussinesException("El correo ya existe");
			}

			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

			user = usuarioDao.crearUsuario(usuario);

			this.enviarEmail(user);

			return user;

		} catch (DaoDataAccesException e) {

			log.error("..:: ERROR AL CREAR USUARIO::.. [METODO: crearUsuario] " + e.getMessage(), e);
			throw new BussinesException(e);

		} catch (BussinesException e) {

			log.error("..:: ERROR DE NEGOCIO ::.. [METODO: crearUsuario] " + e.getMessage(), e);
			throw new BussinesException(e);

		}
	}

	@Override
	public Usuario reestablecerContrasenia(Usuario usuario) throws BussinesException {

		String contraseniaTmp = null;
		try {

			Usuario user = usuarioDao.buscarUsuarioPorCorreo(usuario.getEmail());

			if (user == null) {
				throw new BussinesException("Ingrese un correo valido");
			}
			
			contraseniaTmp = utilCore.generaContraseniaTmp(VEINTE);
			
			user.setPasswordTmp(passwordEncoder.encode(contraseniaTmp));

			usuarioDao.actualizarRecoverUsuario(user);
			
			user.setPasswordTmp(contraseniaTmp);
			
			this.enviarEmailRecuperarPassword(user);

			return user;

		} catch (DaoDataAccesException e) {

			log.error("..:: ERROR AL REESTABLECCER CONTRASENIA::.. [METODO: reestablecerContrasenia] " + e.getMessage(),
					e);
			throw new BussinesException(e);

		} catch (BussinesException e) {

			throw new BussinesException(e);

		}
	}

	@Override
	public Usuario actualizarPassword(Usuario uuario) throws BussinesException {
		try {

			Usuario user = usuarioDao.buscarUsuarioPorCorreo(uuario.getEmail());

			if (user == null) {
				throw new BussinesException("Ingrese un correo valido");
			}

			user.setPassword(passwordEncoder.encode(uuario.getPassword()));

			usuarioDao.actualizarPassword(user);
			user.setPassword(uuario.getPassword());
			this.enviarEmailActualizarPassword(user);

			return user;

		} catch (DaoDataAccesException e) {

			log.error("..:: ERROR AL REESTABLECCER CONTRASENIA::.. [METODO: reestablecerContrasenia] " + e.getMessage(),
					e);
			throw new BussinesException(e);

		} catch (BussinesException e) {

			throw new BussinesException(e);

		}
	}

	private void enviarEmailRecuperarPassword(Usuario user) throws BussinesException {

		try {
			EnvioEmailBeans objMail = new EnvioEmailBeans();
			objMail.setBodyEmail(
					utilCore.replaceCadena(plantillaCorreoRecuperar, user.getNombre(), user.getPasswordTmp()));
			objMail.setEmail(user.getEmail());
			objMail.setSubjetc("Recuperar contraseña");
			envioEmail.envioEmail(objMail);

		} catch (MessagingException | IOException e) {

			log.error("..:: ERROR AL ENVIAR CORREO::.. [METODO: enviarEmailRecuperarPassword] " + e.getMessage(), e);
			throw new BussinesException(e);
		}

	}

	private void enviarEmail(Usuario user) throws BussinesException {

		try {
			EnvioEmailBeans objMail = new EnvioEmailBeans();
			objMail.setBodyEmail(utilCore.replaceCadena(plantillaCorreo, user.getNombre()));
			objMail.setEmail(user.getEmail());
			objMail.setSubjetc("Bienvenido");
			envioEmail.envioEmail(objMail);
		} catch (MessagingException | IOException e) {
			log.error("..:: ERROR AL ENVIAR CORREO::.. [METODO: enviarEmail] " + e.getMessage(), e);
			throw new BussinesException(e);
		}

	}

	private void enviarEmailActualizarPassword(Usuario user) throws BussinesException {
		try {
			EnvioEmailBeans objMail = new EnvioEmailBeans();
			objMail.setBodyEmail(
					utilCore.replaceCadena(plantillaCorreoRecuperar, user.getNombre(), user.getPassword()));
			objMail.setEmail(user.getEmail());
			objMail.setSubjetc("Recuperar contraseña");
			envioEmail.envioEmail(objMail);

		} catch (MessagingException | IOException e) {

			log.error("..:: ERROR AL ENVIAR CORREO::.. [METODO: enviarEmailRecuperarPassword] " + e.getMessage(), e);
			throw new BussinesException(e);
		}

	}

}
